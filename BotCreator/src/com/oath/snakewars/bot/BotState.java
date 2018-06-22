package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.GameBoardState;


public class BotState
{
  private final int MOVE_TIME = 1200;
  private final int singleMoveTime;

  private GameBoardState board;

  public BotState (GameBoardState gameBoard) {
    this.singleMoveTime = MOVE_TIME;
    this.board = gameBoard;
  }

  /*
   * Returns the time allocated for returning a move for each round
   */
  public int getSingleMoveTime()
  {
    return singleMoveTime;
  }

  /*
   * Returns the current Game board state
   */
  public GameBoardState getBoard()
  {
    return board;
  }

}
