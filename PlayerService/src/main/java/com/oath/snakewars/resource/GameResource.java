package com.oath.snakewars.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.oath.common.snakewars.board.Cell;
import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.GameBoard;
import com.oath.common.snakewars.settings.GameBoardState;
import com.oath.common.snakewars.settings.GameSettings;
import com.oath.common.snakewars.settings.GameUpdate;
import com.oath.snakewars.bot.BotHandler;
import com.oath.snakewars.common.GameGlobal;
import com.oath.snakewars.utils.SettingsProvider;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Path("oath/snakewars/playerservice")
@RequestScoped
public class GameResource
{
  private final static Logger logger = Logger.getLogger(GameResource.class);
  @Inject
  public GameResource(){

  }
  @POST
  @Path("/settings")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response postSettings(
      final InputStream in,
      @Context final HttpServletRequest req
  )
  {
    //TODO Make mapper injectable
    ObjectMapper objectMapper = new ObjectMapper();
    logger.info(GameGlobal.getPlayerName()+": Received settings from game server");
    try {
      Cell initCell = new Cell(0,0);
      GameSettings initialSettings = objectMapper.readValue(in, GameSettings.class);
      SettingsProvider.build(initialSettings);
    logger.info("Sending response back");
    }
    catch (IOException e) {
      logger.error("Error while reading response", e);
    }
    return Response.ok(ImmutableMap.of("receivedSettings", GameGlobal.getPlayerName())).build();
  }
  @POST
  @Path("/update")
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces(MediaType.APPLICATION_JSON)
  public Response postUpdate(
      final InputStream in,
      @QueryParam("Test") final String cluster,
      @Context final HttpServletRequest req
  ) throws IOException
  {
    //TODO Make mapper injectable
    ObjectMapper objectMapper = new ObjectMapper();
    logger.info(GameGlobal.getPlayerName()+":Received update endpoint");
    GameUpdate gameUpdate = objectMapper.readValue(in, GameUpdate.class);
    SettingsProvider.updateCurrentRound();
    SettingsProvider.updateGameBoard(gameUpdate.getGameBoard());
    System.out.println(GameGlobal.getPlayerName()+":Round number Settings"+SettingsProvider.getCurrentRound());
    System.out.println(GameGlobal.getPlayerName()+" Round number gameUpdate"+gameUpdate.getRoundNumber());
    //Uncomment if the board needs to be displayed
    //gameUpdate.getGameBoard().displayGameBoard();
    if (SettingsProvider.getCurrentRound() == gameUpdate.getRoundNumber()) {
      return Response.ok(ImmutableMap.of("receivedUpdate", true)).build();
    }
    else {
      return Response.ok(ImmutableMap.of("receivedUpdate", false)).build();
    }
  }
  @GET
  @Path("/move")
  @Produces(MediaType.APPLICATION_JSON)
  public MoveType sendNextMove() {
    if (SettingsProvider.fetchSettings()==null) {
      logger.error("Invoke the settings endpoint first");
      return null;
    }
    GameBoardState gameBoardState = new GameBoardState(SettingsProvider.getGameBoard());
    System.out.println("hereeeeeee WAY BEFORE SWAPPPPPPPPPPP%%%%%%");
    if (GameGlobal.getPlayerNumber() == 3) {
      System.out.println("hereeeeeee JUST BEFORE SWAPPPPPPPPPPP%%%%%%");
      gameBoardState.swapPlayerCells();
    }
    BotHandler botHandler = new BotHandler(gameBoardState);
    logger.info("Requested next move endpoint");
    return botHandler.fetchNextMove();
  }

  @GET
  @Path("/testme")
  @Produces({MediaType.APPLICATION_JSON})
  public Response testMe() {
    return Response.ok(ImmutableMap.of("receivedUpdate", "false")).build();
  }
}
