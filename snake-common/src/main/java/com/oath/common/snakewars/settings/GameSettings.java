package com.oath.common.snakewars.settings;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class GameSettings
{
  private DateTime timeBank;
  private final DateTime timePerMove;
  private final List<String> playerNames;
  private final String selfBotName;
  private final int botId;
  private final GameBoard gameBoard;
  public DateTime getTimeBank()
  {
    return timeBank;
  }

  public DateTime getTimePerMove()
  {
    return timePerMove;
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
  public void setTimeBank(DateTime timeBank)
  {
    this.timeBank = timeBank;
  }

  public GameSettings(
      DateTime timeBank,
      DateTime timePerMove,
      List<String> playerNames,
      String selfBotName,
      int botId,
      int boardHeight,
      int boardWidth
  )
  {
    this.timeBank = timeBank;
    this.timePerMove = timePerMove;
    this.playerNames = new ArrayList<String>(playerNames);
    this.selfBotName = selfBotName;
    this.botId = botId;
    this.gameBoard = new GameBoard(boardHeight, boardWidth);
  }
}
