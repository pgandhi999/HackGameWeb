package com.oath.common.snakewars.settings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameUpdate
{
  private final int roundNumber;
  private final GameBoard gameBoard;

  public int getRoundNumber()
  {
    return roundNumber;
  }

  public GameBoard getGameBoard()
  {
    return gameBoard;
  }

  @JsonCreator
  public GameUpdate(
      @JsonProperty("roundNumber") int roundNumber,
      @JsonProperty("gameBoard") GameBoard gameBoard)
  {
    this.roundNumber = roundNumber;
    this.gameBoard = gameBoard;
  }
}
