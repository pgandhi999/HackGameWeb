package com.oath.snakewars.test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oath.common.snakewars.settings.GameBoard;

public class GameUpdateTest
{
  private final int roundNumber;
  private final GameBoardTest gameBoard;

  public int getTest()
  {
    return test;
  }

  public void setTest(int test)
  {
    this.test = test;
  }

  private int test ;
  public int getRoundNumber()
  {
    return roundNumber;
  }

  public GameBoardTest getGameBoard()
  {
    return gameBoard;
  }

  @JsonCreator
  public GameUpdateTest(
      @JsonProperty("roundNumber") int roundNumber,
      @JsonProperty("gameBoard") GameBoardTest gameBoard)
  {
    this.roundNumber = roundNumber;
    this.gameBoard = gameBoard;
    test = 4;
  }
}


