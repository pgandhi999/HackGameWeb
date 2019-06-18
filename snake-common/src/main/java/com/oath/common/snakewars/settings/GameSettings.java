package com.oath.common.snakewars.settings;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class GameSettings
{
  private int timeBank;
  private final int timePerMove;
  private final int maxTimeBank;
  private List<String> playerNames;
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

  public void setPlayerNames (List<String> playerNames) {
    this.playerNames = playerNames;
  }
  @JsonCreator
  public GameSettings(
      @JsonProperty("timeBank") int timeBank,
      @JsonProperty("timePerMove") int timePerMove,
      @JsonProperty("maxTimeBank") int maxTimeBank,
      @JsonProperty("selfBotName") String selfBotName,
      @JsonProperty("botId") int botId,
      @JsonProperty("gameBoard") GameBoard gameBoard
  )
  {
    this.timeBank = timeBank;
    this.timePerMove = timePerMove;
    this.maxTimeBank = maxTimeBank;
    this.playerNames = new ArrayList<String>();
    this.selfBotName = selfBotName;
    this.botId = botId;
    this.gameBoard = gameBoard;
  }
}
