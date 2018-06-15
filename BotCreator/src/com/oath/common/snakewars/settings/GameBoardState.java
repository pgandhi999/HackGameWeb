package com.oath.common.snakewars.settings;

import com.oath.common.snakewars.board.Cell;

import java.util.concurrent.ThreadLocalRandom;

public class GameBoardState
{
  private final int GAME_BOARD_HEIGHT = 16;
  private final int GAME_BOARD_WIDTH = 16;
  private final int boardWidth;
  private final int boardHeight;
  private final int board[][];
  private Cell currentCellPlayer1;
  private Cell currentCellPlayer2;

  /*
   * Generates an empty board with random cells assigned to both players
   */
  public GameBoardState()
  {
    this.boardHeight = GAME_BOARD_HEIGHT;
    this.boardWidth = GAME_BOARD_WIDTH;
    this.board = new int[boardHeight][boardWidth];
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardWidth; j++) {
        board[i][j] = CellType.EMPTY;
      }
    }
    currentCellPlayer1 = new Cell(ThreadLocalRandom.current().nextInt(0, 16),
                                  ThreadLocalRandom.current().nextInt(0, 16));
    currentCellPlayer2 = new Cell(ThreadLocalRandom.current().nextInt(0, 16),
                                  ThreadLocalRandom.current().nextInt(0, 16));
    while (currentCellPlayer1.equals(currentCellPlayer2)) {
      currentCellPlayer2 = new Cell(ThreadLocalRandom.current().nextInt(0, 16),
                                    ThreadLocalRandom.current().nextInt(0, 16));
    }
  }

  public int getBoardWidth()
  {
    return boardWidth;
  }

  public int getBoardHeight()
  {
    return boardHeight;
  }

  public int getCellContent(int x, int y)
  {
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

