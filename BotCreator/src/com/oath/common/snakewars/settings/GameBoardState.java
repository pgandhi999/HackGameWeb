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
  private Cell myCurrentCell;
  private Cell enemyCurrentCell;

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
        board[i][j] = CellType.EMPTY.getValue();
      }
    }
    myCurrentCell = new Cell(ThreadLocalRandom.current().nextInt(0, 16),
                             ThreadLocalRandom.current().nextInt(0, 16));
    enemyCurrentCell = new Cell(ThreadLocalRandom.current().nextInt(0, 16),
                                ThreadLocalRandom.current().nextInt(0, 16));
    while (myCurrentCell.equals(enemyCurrentCell)) {
      enemyCurrentCell = new Cell(ThreadLocalRandom.current().nextInt(0, 16),
                                  ThreadLocalRandom.current().nextInt(0, 16));
    }
    setCellContent(myCurrentCell, CellType.MYCELL.getValue());
    setCellContent(enemyCurrentCell, CellType.ENEMYCELL.getValue());
  }

  public GameBoardState (int boardHeight, int boardWidth ) {
    this.boardHeight = boardHeight;
    this.boardWidth = boardWidth;
    this.board = new int[boardHeight][boardWidth];
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardWidth; j++) {
        board[i][j] = CellType.EMPTY.getValue();
      }
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

  public int getCellContent(Cell position)
  {
    return board[position.getX()][position.getY()];
  }

  public Cell getMyCurrentCell()
  {
    return myCurrentCell;
  }

  public Cell getEnemyCurrentCell()
  {
    return enemyCurrentCell;
  }

  /*
   *  Used only for BotRunner helper
   */
  public void setCellContent (Cell position, int cellType) {
    board[position.getX()][position.getY()] = cellType;
  }
}

