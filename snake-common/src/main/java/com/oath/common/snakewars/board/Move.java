package com.oath.common.snakewars.board;

/*
  Can be used by Gameboard to applyMove(Move). Don't think it should be visible to the playerservice though.
 */
public class Move
{
  private final MoveType moveType;
  private final int xPath;
  private final int yPath;

  public Move(MoveType moveType)
  {
    this.moveType = moveType;
    switch (moveType) {
      case UP:
        this.xPath = 0;
        this.yPath = 1;
        break;
      case DOWN:
        this.xPath = 0;
        this.yPath = -1;
        break;
      case LEFT:
        this.xPath = -1;
        this.yPath = 0;
        break;
      case RIGHT:
        this.xPath = 1;
        this.yPath = 0;
        break;
      default:
        this.xPath = 0;
        this.yPath = 0;
        break;
    }
  }
}
