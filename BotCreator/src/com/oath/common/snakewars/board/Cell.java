package com.oath.common.snakewars.board;

/*
  Represents the coordinates of a cell within the GameBoard
 */

public class Cell
{
  private int x;
  private int y;

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }

  public void setX(int x)
  {
    this.x = x;
  }

  public void setY(int y)
  {
    this.y = y;
  }

  public Cell(int x, int y)
  {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object cellObject)
  {

    if (cellObject == this) {
      return true;
    }

    if (!(cellObject instanceof Cell)) {
      return false;
    }
    Cell c = (Cell) cellObject;

    return c.getX() == getX()
           && c.getY() == getY();
  }

  @Override
  public int hashCode()
  {
    int result = x;
    result = 31 * result + y;
    return result;
  }

}
