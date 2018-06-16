package com.oath.hackgame.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.oath.common.snakewars.settings.GameBoard;
import com.oath.common.snakewars.settings.GameSettings;
import com.oath.common.snakewars.settings.GameUpdate;
import com.oath.common.snakewars.board.Cell;
import com.oath.hackgame.common.PlayerMove;
import com.oath.hackgame.common.StartValues;
import com.oath.hackgame.exec.MoveManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GameLandingPageController
{

  public String player1 = null;
  public String player2 = null;
  public GameState gs = null;
  public ObjectMapper objectMapper = new ObjectMapper();
  public MoveManager moveManager = new MoveManager();
  public static final Object lock = new Object();

  @RequestMapping(value = "/getInitGameState", method = RequestMethod.POST)
  public void getInitGameState(HttpServletRequest request, HttpServletResponse response, ModelMap model)
      throws ServletException, IOException
  {
    try {
      gs = new GameState();
      moveManager = new MoveManager();
      gs.initGameState();
      int initPlayer1PosX = ThreadLocalRandom.current().nextInt(0, 16);
      int initPlayer1PosY = ThreadLocalRandom.current().nextInt(0, 16);
      Cell initPlayer1Pos = new Cell(initPlayer1PosX, initPlayer1PosY);
      //gs.setGameState(initPlayer1PosX, initPlayer1PosY, CellType.PLAYER1);
      int initPlayer2PosX = ThreadLocalRandom.current().nextInt(0, 16);
      int initPlayer2PosY = ThreadLocalRandom.current().nextInt(0, 16);
      Cell initPlayer2Pos = new Cell(initPlayer2PosX,initPlayer2PosY);
      if (initPlayer1Pos.getX() == initPlayer2Pos.getX() && initPlayer1Pos.getY() == initPlayer2Pos.getY()) {
        initPlayer2PosX = ThreadLocalRandom.current().nextInt(0, 16);
        initPlayer2PosY = ThreadLocalRandom.current().nextInt(0, 16);
        initPlayer2Pos.setX(initPlayer2PosX);
        initPlayer2Pos.setY(initPlayer2PosY);
      }
      List<String> playerNames = initializePlayerClient(initPlayer1Pos,initPlayer2Pos,gs);
      //gs.setGameState(initPlayer2PosX, initPlayer2PosY, CellType.PLAYER2);
      System.out.println("Board value at "+initPlayer1PosX+" "+initPlayer1PosY+" before is"+gs.getGameBoard().getCellContent(initPlayer1PosX,initPlayer1PosY));
      gs.setWholeGameState(initPlayer1Pos,initPlayer2Pos);
      //System.out.println("Board value at x1 y1 after is"+gs.getGameBoard().getCellContent(initPlayer1PosX,initPlayer1PosY));
      // TODO Try if this works
      HashMap<String, Object> playerProps = new HashMap<String, Object>();
      player1 = playerNames.get(0);
      player2 = playerNames.get(1);
      playerProps.put("player1name", player1);
      playerProps.put("player2name", player2);
      playerProps.put("player1x", initPlayer1Pos.getX());
      playerProps.put("player1y", initPlayer1Pos.getY());
      playerProps.put("player2x", initPlayer2Pos.getX());
      playerProps.put("player2y", initPlayer2Pos.getY());
      response.setContentType("text/plain");
      response.setHeader("Content-Type", "application/x-www-form-urlencoded");
      response.setHeader("Cache-Control", "no-cache");
      response.setHeader("Pragma", "no-cache");
      response.getWriter().write(objectMapper.writeValueAsString(playerProps));

    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  private List<String> initializePlayerClient(Cell player1, Cell player2, GameState gs)
  {
    GameSettings initialSettings = new GameSettings(StartValues.TIME_BANK, StartValues.TIME_PER_MOVE,
                                                    240, "Test",
                                                    1, StartValues.BOARD_HEIGHT, StartValues.BOARD_WIDTH
    );
    GameBoard initialBoard = initialSettings.getGameBoard();
    gs.setGameBoard(initialBoard);
    //Following update call will also set the current player cells
    initialBoard.updateGameBoard(player1,player2);
    String completePlayer1Url = new StringBuffer(StartValues.SERVICE_URL1).append(StartValues.PLAYER_SERVICE_CONTEXT).toString();
    String completePlayer2Url = new StringBuffer(StartValues.SERVICE_URL2).append(StartValues.PLAYER_SERVICE_CONTEXT).toString();

    //Following map will be of the form "PlayerName -> PlayerServiceURL"
    Map<String,String> playerNames = moveManager.sendInitialSettings(ImmutableList.<String>of(
        completePlayer1Url,
        completePlayer2Url
    ), initialSettings);
    List<PlayerInfo> playerInfoList = new ArrayList<PlayerInfo>();
    List<String> playerNameList = new ArrayList<String>();
    for(Map.Entry<String,String> entry:playerNames.entrySet()){
      PlayerInfo player = new PlayerInfo(entry.getValue(), entry.getKey());
    playerInfoList.add(player);
    playerNameList.add(entry.getValue());
  }
    moveManager.addPlayers(playerInfoList);
    return playerNameList;
  }

  @RequestMapping(value = "/startGame", method = RequestMethod.POST)
  public void startGame(HttpServletRequest request, HttpServletResponse response, ModelMap model)
      throws ServletException, IOException
  {
    try {
      while (!gs.isGameOver()) {

        //TODO Replace currMove with round
        sendGameStateToPlayers( gs.getCurrMove() );
        //sendGameStateToPlayers( gs.getRoundNumber() );

        //simulateGame();
        try {
          BlockingQueue<PlayerMove> currentQueue = moveManager.getBlockingMovesQueue();
          //Block until we get both player moves
          PlayerMove playerMove1 = currentQueue.take();
          PlayerMove playerMove2 = currentQueue.take();
          PlayerMove pl1 = null;
          PlayerMove pl2 = null;
          if (playerMove1.getPlayerInfo().getPlayerName().equalsIgnoreCase(player2)) {
            pl1 = playerMove2;
            pl2 = playerMove1;
          } else {
            pl1 = playerMove1;
            pl2 = playerMove2;
          }
          System.out.println("SLEEPING GN");
          Thread.sleep(2000);
          System.out.println("Got player1 moves as"+pl1.getMoveType().toString());
          System.out.println("Got player2 moves as"+pl2.getMoveType().toString());

          if(pl1.getMoveType().toString().equalsIgnoreCase(Globals.moves.Up.toString()) ||
                  pl1.getMoveType().toString().equalsIgnoreCase(Globals.moves.Down.toString()) ||
                  pl1.getMoveType().toString().equalsIgnoreCase(Globals.moves.Left.toString()) ||
                  pl1.getMoveType().toString().equalsIgnoreCase(Globals.moves.Right.toString())) {
            gs.setMoveListPlayer1(pl1.getMoveType().toString());
          } else {
            gs.setMoveListPlayer1(null);
          }
          if(pl2.getMoveType().toString().equalsIgnoreCase(Globals.moves.Up.toString()) ||
                  pl2.getMoveType().toString().equalsIgnoreCase(Globals.moves.Down.toString()) ||
                  pl2.getMoveType().toString().equalsIgnoreCase(Globals.moves.Left.toString()) ||
                  pl2.getMoveType().toString().equalsIgnoreCase(Globals.moves.Right.toString())) {
            gs.setMoveListPlayer2(pl2.getMoveType().toString());
          } else {
            gs.setMoveListPlayer2(null);
          }

          synchronized (lock){
            lock.wait();
          }
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
        validatePlayerMovesAndUpdateGameState();
      }
      System.out.println("GAME OVER!!!");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void simulateGame()
  {
    try {
      int sim1 = ThreadLocalRandom.current().nextInt(0, 4);
      int sim2 = ThreadLocalRandom.current().nextInt(0, 4);
      int arrayLength = gs.getGameStateArray().length;
      int x1 = 0;
      int y1 = 0;
      int x2 = 0;
      int y2 = 0;
      System.out.println("SEED is : " + sim1 + " AND " + sim2);
      for (int i = 0; i < arrayLength; i++) {
        for (int j = 0; j < arrayLength; j++) {
          if (gs.getGameBoard(i, j) == Globals.CURR_POSITION_PLAYER_1) {
            x1 = i;
            y1 = j;
          } else if (gs.getGameBoard(i, j) == Globals.CURR_POSITION_PLAYER_2) {
            x2 = i;
            y2 = j;
          }
        }
      }

      if (sim1 == 0) {
        if (x1 - 1 < 0) {
          gs.setMoveListPlayer1(Globals.moves.Left.toString());
        } else {
          gs.setMoveListPlayer1(Globals.moves.Up.toString());
        }
      } else if (sim1 == 1) {
        if (x1 + 1 > 15) {
          gs.setMoveListPlayer1(Globals.moves.Right.toString());
        } else {
          gs.setMoveListPlayer1(Globals.moves.Down.toString());
        }
      } else if (sim1 == 2) {
        if (y1 - 1 < 0) {
          gs.setMoveListPlayer1(Globals.moves.Up.toString());
        } else {
          gs.setMoveListPlayer1(Globals.moves.Left.toString());
        }
      } else if (sim1 == 3) {
        if (y1 + 1 > 15) {
          gs.setMoveListPlayer1(Globals.moves.Down.toString());
        } else {
          gs.setMoveListPlayer1(Globals.moves.Right.toString());
        }
      }

      if (sim2 == 0) {
        if (x2 - 1 < 0) {
          gs.setMoveListPlayer2(Globals.moves.Left.toString());
        } else {
          gs.setMoveListPlayer2(Globals.moves.Up.toString());
        }
      } else if (sim2 == 1) {
        if (x2 + 1 > 15) {
          gs.setMoveListPlayer2(Globals.moves.Right.toString());
        } else {
          gs.setMoveListPlayer2(Globals.moves.Down.toString());
        }
      } else if (sim2 == 2) {
        if (y2 - 1 < 0) {
          gs.setMoveListPlayer2(Globals.moves.Up.toString());
        } else {
          gs.setMoveListPlayer2(Globals.moves.Left.toString());
        }
      } else if (sim2 == 3) {
        if (y2 + 1 > 15) {
          gs.setMoveListPlayer2(Globals.moves.Down.toString());
        } else {
          gs.setMoveListPlayer2(Globals.moves.Right.toString());
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  private void validatePlayerMovesAndUpdateGameState()
  {
    try {

      //TODO Replace currMove with roundNumber
      int currentRound = gs.getCurrMove();
      currentRound --;
      //int currentRound = gs.getRoundNumber();

      String movePlayer1 = gs.getMoveListPlayer1().get(currentRound);
      String movePlayer2 = gs.getMoveListPlayer2().get(currentRound);
      if (movePlayer1 == null) {
        movePlayer1 = currentRound != 0 ? gs.getMoveListPlayer1().get(currentRound - 1) : Globals.moves.Up.toString();
        gs.getMoveListPlayer1().put(currentRound, movePlayer1);
      }
      if (movePlayer2 == null) {
        movePlayer2 = currentRound != 0 ? gs.getMoveListPlayer2().get(currentRound - 1) : Globals.moves.Up.toString();
        gs.getMoveListPlayer2().put(currentRound, movePlayer2);
      }
      System.out.println("hereeeeeeee 1 " + movePlayer1 + " " + movePlayer2);
      if (currentRound > 0) {
        if (movePlayer1.equalsIgnoreCase(Globals.moves.Up.toString())
                && gs.getMoveListPlayer1().get(currentRound - 1).equalsIgnoreCase(Globals.moves.Down.toString())) {
          movePlayer1 = Globals.moves.Down.toString();
          gs.getMoveListPlayer1().put(currentRound, movePlayer1);
        } else if (movePlayer1.equalsIgnoreCase(Globals.moves.Down.toString())
                   && gs.getMoveListPlayer1().get(currentRound - 1).equalsIgnoreCase(Globals.moves.Up.toString())) {
          movePlayer1 = Globals.moves.Up.toString();
          gs.getMoveListPlayer1().put(currentRound, movePlayer1);
        } else if (movePlayer1.equalsIgnoreCase(Globals.moves.Left.toString())
                   && gs.getMoveListPlayer1().get(currentRound - 1).equalsIgnoreCase(Globals.moves.Right.toString())) {
          movePlayer1 = Globals.moves.Right.toString();
          gs.getMoveListPlayer1().put(currentRound, movePlayer1);
        } else if (movePlayer1.equalsIgnoreCase(Globals.moves.Right.toString())
                   && gs.getMoveListPlayer1().get(currentRound - 1).equalsIgnoreCase(Globals.moves.Left.toString())) {
          movePlayer1 = Globals.moves.Left.toString();
          gs.getMoveListPlayer1().put(currentRound, movePlayer1);
        }

        if (movePlayer2.equalsIgnoreCase(Globals.moves.Up.toString()) && gs.getMoveListPlayer2()
                                                                           .get(currentRound - 1)
                                                                           .equalsIgnoreCase(Globals.moves.Down.toString())) {
          movePlayer2 = Globals.moves.Down.toString();
          gs.getMoveListPlayer2().put(currentRound, movePlayer2);
        } else if (movePlayer2.equalsIgnoreCase(Globals.moves.Down.toString())
                   && gs.getMoveListPlayer2().get(currentRound - 1).equalsIgnoreCase(Globals.moves.Up.toString())) {
          movePlayer2 = Globals.moves.Up.toString();
          gs.getMoveListPlayer2().put(currentRound, movePlayer2);
        } else if (movePlayer2.equalsIgnoreCase(Globals.moves.Left.toString())
                   && gs.getMoveListPlayer2().get(currentRound - 1).equalsIgnoreCase(Globals.moves.Right.toString())) {
          movePlayer2 = Globals.moves.Right.toString();
          gs.getMoveListPlayer2().put(currentRound, movePlayer2);
        } else if (movePlayer2.equalsIgnoreCase(Globals.moves.Right.toString())
                   && gs.getMoveListPlayer2().get(currentRound - 1).equalsIgnoreCase(Globals.moves.Left.toString())) {
          movePlayer2 = Globals.moves.Left.toString();
          gs.getMoveListPlayer2().put(currentRound, movePlayer2);
        }
      }
      boolean player1loses = false;
      boolean player2loses = false;
      int arrayLength = gs.getGameStateArray().length;
      int x1 = 0;
      int y1 = 0;
      int x2 = 0;
      int y2 = 0;
      for (int i = 0; i < arrayLength; i++) {
        for (int j = 0; j < arrayLength; j++) {
          if (gs.getGameBoard(i, j) == Globals.CURR_POSITION_PLAYER_1) {
            x1 = i;
            y1 = j;
          } else if (gs.getGameBoard(i, j) == Globals.CURR_POSITION_PLAYER_2) {
            x2 = i;
            y2 = j;
          }
        }
      }
      player1loses = validateMove(movePlayer1, x1, y1);
      player2loses = validateMove(movePlayer2, x2, y2);
      System.out.println("Hereeeeeeeee 2 " + player1loses + " : " + player2loses);
      if (player1loses && player2loses) {
        gs.setWinner(0);
        gs.setGameOver(true);
      } else if (player1loses) {
        gs.setWinner(2);
        gs.setGameOver(true);
      } else if (player2loses) {
        gs.setWinner(1);
        gs.setGameOver(true);
      } else {
        int x10 = x1;
        int y10 = y1;
        int x20 = x2;
        int y20 = y2;
        if (movePlayer1.equalsIgnoreCase(Globals.moves.Up.toString())) {
          x1--;
        } else if (movePlayer1.equalsIgnoreCase(Globals.moves.Down.toString())) {
          x1++;
        } else if (movePlayer1.equalsIgnoreCase(Globals.moves.Left.toString())) {
          y1--;
        } else if (movePlayer1.equalsIgnoreCase(Globals.moves.Right.toString())) {
          y1++;
        }

        if (movePlayer2.equalsIgnoreCase(Globals.moves.Up.toString())) {
          x2--;
        } else if (movePlayer2.equalsIgnoreCase(Globals.moves.Down.toString())) {
          x2++;
        } else if (movePlayer2.equalsIgnoreCase(Globals.moves.Left.toString())) {
          y2--;
        } else if (movePlayer2.equalsIgnoreCase(Globals.moves.Right.toString())) {
          y2++;
        }

        if (x1 == x2 && y1 == y2) {
          player1loses = true;
          player2loses = true;
          gs.setWinner(0);
          gs.setGameOver(true);
        } else {
          gs.getGameBoard().setCellContent(x10, y10, Globals.WALL_CELL);
          gs.getGameBoard().setCellContent(x1, y1, Globals.CURR_POSITION_PLAYER_1);
          gs.getGameBoard().setCellContent(x20, y20, Globals.WALL_CELL);
          gs.getGameBoard().setCellContent(x2, y2, Globals.CURR_POSITION_PLAYER_2);
          gs.getGameBoard().setCurrentCellPlayer1(new Cell(x1,y1));
          gs.getGameBoard().setCurrentCellPlayer2(new Cell(x2,y2));

          /*gs.setGameState(x10, y10, Globals.WALL_CELL);
          gs.setGameState(x1, y1, Globals.CURR_POSITION_PLAYER_1);
          gs.setGameState(x20, y20, Globals.WALL_CELL);
          gs.setGameState(x2, y2, Globals.CURR_POSITION_PLAYER_2);*/
        }
      }
      //TODO Replacing move with round
      gs.setCurrMove(currentRound + 2);
      //gs.incrementRound();

      gs.setCurrentMovePlayer1(movePlayer1.toUpperCase());
      gs.setCurrentMovePlayer2(movePlayer2.toUpperCase());
      gs.setMoveOver(true);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private boolean validateMove(String movePlayer, int x, int y)
  {
    try {
      if ((movePlayer.equalsIgnoreCase(Globals.moves.Up.toString()) && x - 1 < 0) || (movePlayer.equalsIgnoreCase(
          Globals.moves.Down.toString()) && x + 1 > 15)
          || (movePlayer.equalsIgnoreCase(Globals.moves.Left.toString()) && y - 1 < 0)
          || (movePlayer.equalsIgnoreCase(Globals.moves.Right.toString()) && y + 1 > 15)) {
        System.out.println("MOVE FAIL 1 "+x+" : "+y);
        return true;
      }
      if (movePlayer.equalsIgnoreCase(Globals.moves.Up.toString()) && (gs.getGameBoard(x - 1, y) == Globals.WALL_CELL
                                                                       || gs.getGameBoard(x - 1, y)
                                                                          == Globals.CURR_POSITION_PLAYER_1
                                                                       || gs.getGameBoard(x - 1, y)
                                                                          == Globals.CURR_POSITION_PLAYER_2)) {
        System.out.println("MOVE FAIL 2 "+x+" : "+y);
        return true;
      }
      if (movePlayer.equalsIgnoreCase(Globals.moves.Down.toString()) && (gs.getGameBoard(x + 1, y) == Globals.WALL_CELL
                                                                         || gs.getGameBoard(x + 1, y)
                                                                            == Globals.CURR_POSITION_PLAYER_1
                                                                         || gs.getGameBoard(x + 1, y)
                                                                            == Globals.CURR_POSITION_PLAYER_2)) {
        System.out.println("MOVE FAIL 3 "+x+" : "+y);
        return true;
      }
      if (movePlayer.equalsIgnoreCase(Globals.moves.Left.toString()) && (gs.getGameBoard(x, y - 1) == Globals.WALL_CELL
                                                                         || gs.getGameBoard(x, y - 1)
                                                                            == Globals.CURR_POSITION_PLAYER_1
                                                                         || gs.getGameBoard(x, y - 1)
                                                                            == Globals.CURR_POSITION_PLAYER_2)) {
        System.out.println("MOVE FAIL 4 "+x+" : "+y);
        return true;
      }
      if (movePlayer.equalsIgnoreCase(Globals.moves.Right.toString()) && (gs.getGameBoard(x, y + 1) == Globals.WALL_CELL
                                                                          || gs.getGameBoard(x, y + 1)
                                                                             == Globals.CURR_POSITION_PLAYER_1
                                                                          || gs.getGameBoard(x, y + 1)
                                                                             == Globals.CURR_POSITION_PLAYER_2)) {
        System.out.println("MOVE FAIL 5 "+x+" : "+y);
        return true;
      }
      System.out.println("MOVE PASS");
      return false;
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  private void sendGameStateToPlayers(int currMove)
  {
    gs.incrementRound();
    GameUpdate gameUpdate = new GameUpdate(gs.getRoundNumber(),gs.getGameBoard());
    System.out.println("Incrementing round"+gameUpdate.getRoundNumber());
    moveManager.sendConfigAndMoveRequest(gameUpdate);

  }

  @RequestMapping(value = "/getCurrentGameState", method = RequestMethod.POST)
  public void getCurrentGameState(HttpServletRequest request, HttpServletResponse response, ModelMap model)
      throws ServletException, IOException
  {
    try {
      synchronized (lock) {
        lock.notifyAll();
      }
      // TODO: Check if this works for you
      /* JSONObject jo = new JSONObject(); */
      System.out.println("UI asking for next move...");
      HashMap<String, Object> playerProps = new HashMap<String, Object>();
      /*
       * jo.put( "player1currentmove",
       * gs.getCurrentMovePlayer1() ); jo.put(
       * "player2currentmove", gs.getCurrentMovePlayer2() );
       * jo.put( "isMoveOver", gs.isMoveOver() ); jo.put(
       * "isGameOver", gs.isGameOver() );
       */
      playerProps.put("player1currentmove", gs.getCurrentMovePlayer1());
      playerProps.put("player2currentmove", gs.getCurrentMovePlayer2());
      playerProps.put("isMoveOver", gs.isMoveOver());
      playerProps.put("isGameOver", gs.isGameOver());

      //TODO Replace currMove with roundNumber
      playerProps.put("roundNumber", gs.getCurrMove() - 1);
      //playerProps.put("roundNumber", gs.getRoundNumber());

      if (gs.isGameOver()) {
        playerProps.put("winner", gs.getWinner());
      }
      if (gs.isMoveOver()) {
        gs.setMoveOver(false);
      }
      /*
       * StringWriter out = new StringWriter();
       * JSONValue.writeJSONString( jo, out ); String jsonText
       * = out.toString();
       */
      response.setContentType("text/plain");
      response.setHeader("Content-Type", "application/x-www-form-urlencoded");
      response.setHeader("Cache-Control", "no-cache");
      response.setHeader("Pragma", "no-cache");
      /* response.getWriter().write( jsonText ); */
      response.getWriter().write(objectMapper.writeValueAsString(playerProps));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/clearGameState", method = RequestMethod.POST)
  public void clearGameState(HttpServletRequest request, HttpServletResponse response, ModelMap model)
      throws ServletException, IOException
  {
    player1 = null;
    player2 = null;
    gs = null;
    moveManager = null;
    response.setContentType("text/plain");
    response.setHeader("Content-Type", "application/x-www-form-urlencoded");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
  }

  @RequestMapping(value = "/landPage", method = RequestMethod.GET)
  public String getTest(HttpServletRequest request, HttpServletResponse response, ModelMap model)
      throws ServletException, IOException
  {
    return "land";
  }
}