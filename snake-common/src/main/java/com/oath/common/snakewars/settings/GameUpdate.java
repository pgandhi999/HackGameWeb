package com.oath.common.snakewars.settings;

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

  public GameUpdate(int roundNumber, GameBoard gameBoard)
  {
    this.roundNumber = roundNumber;
    this.gameBoard = gameBoard;
  }
}
