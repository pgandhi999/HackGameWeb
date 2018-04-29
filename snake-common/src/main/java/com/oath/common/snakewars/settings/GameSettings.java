package com.oath.common.snakewars.settings;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class GameSettings
{
  private int timeBank;
  private final int timePerMove;
  private final int maxTimeBank;
  private final List<String> playerNames;
  private final String selfBotName;
  private final int botId;
  private final GameBoard gameBoard;
  public int getTimeBank()
  {
    return timeBank;
  }

  public int getTimePerMove()
  {
    return timePerMove;
  }

  public int getMaxTimeBank()
  {
    return maxTimeBank;
  }

  public List<String> getPlayerNames()
  {
    return playerNames;
  }

  public String getSelfBotName()
  {
    return selfBotName;
  }

  public int getBotId()
  {
    return botId;
  }

  public GameBoard getGameBoard()
  {
    return gameBoard;
  }
  // To be performed only by daemon that handles update requests in PlayerService
  public void setTimeBank(int timeBank)
  {
    this.timeBank = timeBank;
  }

  public GameSettings(
      int timeBank,
      int timePerMove,
      int maxTimeBank,
      List<String> playerNames,
      String selfBotName,
      int botId,
      int boardHeight,
      int boardWidth
  )
  {
    this.timeBank = timeBank;
    this.timePerMove = timePerMove;
    this.maxTimeBank = maxTimeBank;
    this.playerNames = new ArrayList<String>(playerNames);
    this.selfBotName = selfBotName;
    this.botId = botId;
    this.gameBoard = new GameBoard(boardHeight, boardWidth);
  }
}
