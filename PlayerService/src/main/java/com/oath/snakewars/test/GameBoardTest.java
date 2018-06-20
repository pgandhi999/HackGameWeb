package com.oath.snakewars.test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oath.common.snakewars.board.Cell;
import com.oath.common.snakewars.settings.CellType;

public class GameBoardTest
{
  private final int boardWidth;
  private final int boardHeight;
  @JsonProperty
  private int board[][];
  @JsonProperty
  private Cell currentCellPlayer1;
  @JsonProperty
  private Cell currentCellPlayer2;
  @JsonProperty
  private int testboard;

  @JsonCreator
  public GameBoardTest (
      @JsonProperty("boardHeight") int boardHeight,
      @JsonProperty("boardWidth") int boardWidth) {
    this.boardHeight = boardHeight;
    this.boardWidth = boardWidth;
    this.board = new int[boardHeight][boardWidth];
    testboard = 6;
    for (int i=0; i<boardHeight; i++) {
      for (int j=0; j<boardWidth; j++) {
        board[i][j] = CellType.EMPTY;
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
    board[player1.getX()][player1.getY()] = CellType.PLAYER1;
    board[player2.getX()][player2.getY()] = CellType.PLAYER2;
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
  public void getTestBoard() {
    System.out.print("-->"+testboard + "!~~~");
  }

  public void setTestboard(int x) {
    testboard = x;
  }
}
