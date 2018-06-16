package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.GameBoardState;


public class BotState
{
  private final int MOVE_TIME = 2000;
  private final int singleMoveTime;

  private GameBoardState board;

  private MoveType lastMove;
  public BotState (GameBoardState gameBoard) {
    this.singleMoveTime = MOVE_TIME;
    this.board = gameBoard;
  }


  public int getSingleMoveTime()
  {
    return singleMoveTime;
  }

  public GameBoardState getBoard()
  {
    return board;
  }

  public MoveType getLastDirection()
  {
    return lastMove;
  }
}
