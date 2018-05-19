package com.oath.hackgame.exec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.ListenableFuture;
import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.GameUpdate;
import com.oath.hackgame.common.PlayerMove;
import com.oath.hackgame.controller.GameState;
import com.oath.hackgame.controller.PlayerInfo;

import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

public class MoveManager
{
  private final List<PlayerInfo> playerInfoList;
  private final ExecutorService managerExec;
  private final BlockingQueue<PlayerMove> blockingMovesQueue;
  private CloseableHttpClient httpClient;
  private final ObjectMapper objectMapper;
  public MoveManager() {
    playerInfoList = new ArrayList<PlayerInfo>();
    managerExec = Executors.newFixedThreadPool(playerInfoList.size());
    blockingMovesQueue = new LinkedBlockingQueue<PlayerMove>();
    httpClient = HttpClients.createDefault();
    objectMapper = new ObjectMapper();
  }
  public List<Future> sendConfigAndMoveRequest (final GameUpdate gameUpdate)
  {
    List<Future> futureList = new ArrayList<Future>();
    for (final PlayerInfo player:playerInfoList) {
      futureList.add(
          managerExec.submit(
          new Runnable()
          {
            public void run()
            {
              //Make calls to config API and move API calls
              if (postConfigToPlayer(player,gameUpdate)) {
                MoveType nextMove = getMoveFromPlayer(player);
                // Resort to fail fast for now
                blockingMovesQueue.add(new PlayerMove(nextMove,gameUpdate.getRoundNumber(),player));
              }
              else {
                //TODO this should return previous move
              }
            }
          }
      )
      );
    }
    return futureList;
  }

  private boolean postConfigToPlayer (PlayerInfo playerInfo, GameUpdate gameUpdate) {
    String configEndPoint = new StringBuffer(playerInfo.getServiceUrl()).append("/update").toString();
    HttpPost httpPost = new HttpPost(configEndPoint);
    httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    try {
      StringEntity requestEntity = new StringEntity(
          objectMapper.writeValueAsString(gameUpdate),
          ContentType.APPLICATION_JSON.toString()
      );
      httpPost.setEntity(requestEntity);
      CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
      Map<String,Boolean> isSuccess = objectMapper.readValue(httpResponse.getEntity().getContent(),Map.class);
      return isSuccess.get("receivedUpdate");
    }
    catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  private MoveType getMoveFromPlayer (PlayerInfo playerInfo) {
    String configEndPoint = new StringBuffer(playerInfo.getServiceUrl()).append("/move").toString();
    HttpGet httpGet = new HttpGet(configEndPoint);
    try {
      CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
      MoveType playerMove = objectMapper.readValue(httpResponse.getEntity().getContent(),MoveType.class);
      return playerMove;
    }
    catch (IOException ioe) {
      throw new RuntimeException(ioe);
    }
  }
}
