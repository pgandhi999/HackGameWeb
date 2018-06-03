package com.oath.common.snakewars.settings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameBoard
{
  private final int boardWidth;
  private final int boardHeight;
  private final int board[][];

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
  }
  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  public void updateGameBoard(int player1X, int player1Y, int player2X, int player2Y) {
    board[player1X][player1Y] = CellType.PLAYER1;
    board[player2X][player2Y] = CellType.PLAYER2;
  }
}
