package com.oath.common.snakewars.settings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oath.common.snakewars.board.Cell;

public class GameBoardState
{
  private final int boardWidth;
  private final int boardHeight;
  private final int board[][];
  private Cell currentCellPlayer1;
  private Cell currentCellPlayer2;

  @JsonCreator
  public GameBoardState (GameBoard gameBoard) {
    this.boardHeight = gameBoard.getBoardHeight();
    this.boardWidth = gameBoard.getBoardWidth();
    this.board = new int[boardHeight][boardWidth];
    for (int i=0; i<boardHeight; i++) {
      for (int j=0; j<boardWidth; j++) {
        board[i][j] = gameBoard.getCellContent(i,j);
      }
    }
    currentCellPlayer1 = gameBoard.getCurrentCellPlayer1();
    currentCellPlayer2 = gameBoard.getCurrentCellPlayer2();

  }
  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  public int getCellContent(int x, int y) {
    return board[x][y];
  }
  public Cell getCurrentCellPlayer1()
  {
    return currentCellPlayer1;
  }
  public Cell getCurrentCellPlayer2()
  {
    return currentCellPlayer2;
  }
}

