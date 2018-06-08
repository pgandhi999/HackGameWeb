package com.oath.common.snakewars.settings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oath.common.snakewars.board.Cell;

public class GameBoard
{
  private final int boardWidth;
  private final int boardHeight;
  private final int board[][];
  private Cell currentCell;

  @JsonCreator
  public GameBoard (
      @JsonProperty("boardHeight") int boardHeight,
      @JsonProperty("boardWidth") int boardWidth) {
    this.boardHeight = boardHeight;
    this.boardWidth = boardWidth;
    this.board = new int[boardHeight][boardWidth];
    for (int i=0; i<boardHeight; i++) {
      for (int j=0; j<boardWidth; j++) {
        board[i][j] = CellType.EMPTY;
      }
    }
    currentCell = new Cell(0,0);
  }
  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  public void updateGameBoard(Cell player1, Cell player2) {
    board[player1.getX()][player1.getY()] = CellType.PLAYER1;
    board[player2.getX()][player2.getY()] = CellType.PLAYER2;
  }
  public int getCellContent(int x, int y) {
    return board[x][y];
  }
  public void setCellContent(int x, int y, int z) {
    board[x][y] = z;
  }
  public Cell getCurrentCell()
  {
    return currentCell;
  }
  public void setCurrentCell(Cell currentCell) {
    this.currentCell = currentCell;
  }
}
