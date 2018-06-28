package com.oath.common.snakewars.settings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oath.common.snakewars.board.Cell;

public class GameBoard
{
  public final int boardWidth;
  public final int boardHeight;
  @JsonProperty
  public int board[][];
  @JsonProperty
  public Cell currentCellPlayer1;
  @JsonProperty
  public Cell currentCellPlayer2;


  @JsonCreator
  public GameBoard (
      @JsonProperty("boardHeight") int boardHeight,
      @JsonProperty("boardWidth") int boardWidth) {
    this.boardHeight = boardHeight;
    this.boardWidth = boardWidth;
    this.board = new int[boardHeight][boardWidth];
    for (int i=0; i<boardHeight; i++) {
      for (int j=0; j<boardWidth; j++) {
        board[i][j] = CellType.EMPTY.getValue();
      }
    }
    currentCellPlayer1 = new Cell(0,0);
    currentCellPlayer2 = new Cell(0,0);

  }
  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  public void updateGameBoard(Cell player1, Cell player2) {
    board[player1.getX()][player1.getY()] = CellType.MYCELL.getValue();
    board[player2.getX()][player2.getY()] = CellType.ENEMYCELL.getValue();
    setCurrentCellPlayer1(player1);
    setCurrentCellPlayer2(player2);
  }
  public int getCellContent(int x, int y) {
    return board[x][y];
  }
  public void setCellContent(int x, int y, int z) {
    board[x][y] = z;
  }
  public Cell getCurrentCellPlayer1()
{
  return currentCellPlayer1;
}
  public void setCurrentCellPlayer1(Cell currentCell) {
    this.currentCellPlayer1 = currentCell;
  }

  public Cell getCurrentCellPlayer2()
  {
    return currentCellPlayer2;
  }
  public void setCurrentCellPlayer2(Cell currentCell) {
    this.currentCellPlayer2 = currentCell;
  }

  public void displayGameBoard () {
    System.out.println("Game board is:");
    for (int i = 0; i < boardHeight; i++) {
      System.out.print("\n");
      for (int j = 0; j < boardWidth; j++) {
        System.out.print(board[i][j] + " ");
      }
    }
  }
}
