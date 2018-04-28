package com.oath.common.snakewars.settings;

public class GameBoard
{
  private final int boardWidth;
  private final int boardHeight;
  private final CellType board[][];

  public GameBoard (int boardHeight, int boardWidth) {
    this.boardHeight = boardHeight;
    this.boardWidth = boardWidth;
    this.board = new CellType[boardHeight][boardWidth];
  }
  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }
}
