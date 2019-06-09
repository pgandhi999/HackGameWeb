package com.oath.common.snakewars.settings;

/*
 * The contents of a cell in the Game Board would be one of the following types:
 * EMPTY : This cell is empty and moving to this cell will be valid. Denoted by the number 0 on the Game Board
 * WALL : This cell is used by either of the players in a previous round and moving to this cell will be invalid.
 *       Denoted by the number 1 on the Game Board
 * MYCELL : This cell is your current position on the Game Board. Denoted by the number 2 on the Game Board
 * ENEMYCELL : This cell is your opponent's current position and moving to this cell will be invalid.
 *             Denoted by the number 3 on the Game Board
 */
public enum CellType
{
  EMPTY(0),
  WALL(1),
  MYCELL(2),
  ENEMYCELL(3),
  TRAP(4);

  private final int value;
  CellType(int value) {
    this.value = value;
  }
  /*
   * Returns the numerical value of the Cell Type
   */
  public int getValue() {
    return value;
  }

  /*
   * Validates if the numerical value corresponds to a valid Cell type
   */
  public static boolean validateCellType (int cellType) {
    for (CellType c : CellType.values()) {
      if (cellType == c.getValue()) {
        return true;
      }
    }
    return false;
  }
}
