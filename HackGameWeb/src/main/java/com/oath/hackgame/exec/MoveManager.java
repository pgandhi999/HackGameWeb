package com.oath.hackgame.exec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.oath.common.snakewars.board.Cell;
import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.GameSettings;
import com.oath.common.snakewars.settings.GameUpdate;
import com.oath.hackgame.common.PlayerMove;
import com.oath.hackgame.controller.GameState;
import com.oath.hackgame.controller.Globals;
import com.oath.hackgame.controller.PlayerInfo;


import org.apache.commons.httpclient.util.TimeoutController;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeoutException;

public class MoveManager
{
  private final List<PlayerInfo> playerInfoList;
  private ExecutorService managerExec;
  private final BlockingQueue<PlayerMove> blockingMovesQueue;
  private CloseableHttpClient httpClient;
  private final ObjectMapper objectMapper;
  public MoveManager() {
    playerInfoList = new ArrayList<PlayerInfo>();
    blockingMovesQueue = new LinkedBlockingQueue<PlayerMove>();
    httpClient = HttpClients.createDefault();
    objectMapper = new ObjectMapper();
  }
  public void addPlayers (List<PlayerInfo> players) {
    playerInfoList.addAll(players);
    managerExec = Executors.newFixedThreadPool(playerInfoList.size());
  }
  public Map<String,String> sendInitialSettings (List<String> playerServiceUrls, final GameSettings gameSettings) {
    Map<String,String> playerNames = new HashMap<String, String>();
    List<Future> responseFutures = new ArrayList<Future>();
    managerExec = Executors.newFixedThreadPool(2);
    for (final String playerServiceUrl : playerServiceUrls) {
        System.out.println("Sending initial settings to " + playerServiceUrl);
        responseFutures.add(managerExec.submit(
            new Callable()
            {
              public String call()
              {
                //Make calls to config API and move API calls
                String playerName = postInitSettingsToUser(playerServiceUrl, gameSettings);
                return playerName;
              }
            }
        ));
        try {
          for (Future fut : responseFutures) {
            String playName = String.valueOf(fut.get());
            playerNames.put(playerServiceUrl, playName);
          }
        }
        catch (Exception ie) {
          System.out.println("Thread interrupted!");
        }
      }
    return playerNames;
  }
  private String postInitSettingsToUser (String playerServiceUrl, GameSettings gameSettings) {
    String configEndPoint = new StringBuffer(playerServiceUrl).append("/settings").toString();
    HttpPost httpPost = new HttpPost(configEndPoint);
    httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    try {
      StringEntity requestEntity = new StringEntity(
          objectMapper.writeValueAsString(gameSettings));
      httpPost.setEntity(requestEntity);
      CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
      //String responseString = new BasicResponseHandler().handleResponse(httpResponse);
      Map<String,String> isSuccess = objectMapper.readValue(httpResponse.getEntity().getContent(),Map.class);
      return isSuccess.get("receivedSettings");
    }
    catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }
  public List<Future> sendConfigAndMoveRequest (final GameUpdate gameUpdate)
  {
    List<Future> futureList = new ArrayList<Future>();
    System.out.println("Player list is"+playerInfoList.size());
    for (final PlayerInfo player:playerInfoList) {
      futureList.add(
          managerExec.submit(
          new Runnable()
          {
            public void run()
            {
              //Make calls to config API and move API calls
              System.out.println("Sending update request to"+player.getPlayerName());
              if (postConfigToPlayer(player,gameUpdate)) {
                MoveType nextMove = getMoveFromPlayer(player);
                System.out.println("Adding move from "+player.getPlayerName() + "for round" +gameUpdate.getRoundNumber()+ " to queue.");
                // Resort to fail fast for now
                blockingMovesQueue.add(new PlayerMove(nextMove,gameUpdate.getRoundNumber(),player));
              }
              else {
                //TODO this should return previous move
                System.out.println("Received false update from player "+ player.getPlayerName());
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
    System.out.println("Before update call to "+configEndPoint);
    HttpPost httpPost = new HttpPost(configEndPoint);
    httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    try {
      StringEntity requestEntity = new StringEntity(
          objectMapper.writeValueAsString(gameUpdate)
      );
      httpPost.setEntity(requestEntity);
      CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
      Map<String,Boolean> isSuccess = objectMapper.readValue(httpResponse.getEntity().getContent(),Map.class);
      System.out.println("Received update is "+ isSuccess.get("receivedUpdate"));
      return isSuccess.get("receivedUpdate");
    }
    catch (IOException exception) {
      System.out.println("Exception while posting config to player");
      throw new RuntimeException(exception);
    }
  }

  private MoveType getMoveFromPlayer (PlayerInfo playerInfo) {
    String configEndPoint = new StringBuffer(playerInfo.getServiceUrl()).append("/move").toString();
    System.out.println("Asking for move"+configEndPoint);
    HttpGet httpGet = new HttpGet(configEndPoint);
    try {
      // Timeout specific http client builder
      RequestConfig.Builder requestBuilder = RequestConfig.custom();
      requestBuilder = requestBuilder.setConnectTimeout(Globals.MOVE_TIME+200);
      requestBuilder = requestBuilder.setSocketTimeout(Globals.MOVE_TIME+200);
      HttpClientBuilder builder = HttpClientBuilder.create();
      builder.setDefaultRequestConfig(requestBuilder.build());
      HttpClient timedClient = builder.build();

      HttpResponse httpResponse = timedClient.execute(httpGet);
      MoveType playerMove = objectMapper.readValue(httpResponse.getEntity().getContent(),MoveType.class);
      System.out.println("Got move "+playerMove.toString());
      return playerMove;
    }
    catch (SocketTimeoutException exception) {
      System.out.println("Connection timeout. Defaulting move");
      return MoveType.PASS;
    }
    catch (IOException ioe) {
      System.out.println("Error while getting move");
      ioe.printStackTrace();
      throw new RuntimeException(ioe);
    }
  }

  public BlockingQueue<PlayerMove> getBlockingMovesQueue()
  {
    return blockingMovesQueue;
  }
}
