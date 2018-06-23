package com.oath.common.snakewars.settings;

import com.oath.common.snakewars.board.Cell;

import java.util.concurrent.ThreadLocalRandom;

/*
 * This class has all the information specific to the current Game board state
 */
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
   * Generates an empty board of size 16x16 with random cells assigned to both players
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
    myCurrentCell = new Cell(
        ThreadLocalRandom.current().nextInt(0, 16),
        ThreadLocalRandom.current().nextInt(0, 16)
    );
    enemyCurrentCell = new Cell(
        ThreadLocalRandom.current().nextInt(0, 16),
        ThreadLocalRandom.current().nextInt(0, 16)
    );
    while (myCurrentCell.equals(enemyCurrentCell)) {
      enemyCurrentCell = new Cell(
          ThreadLocalRandom.current().nextInt(0, 16),
          ThreadLocalRandom.current().nextInt(0, 16)
      );
    }
    setCellContent(myCurrentCell, CellType.MYCELL.getValue());
    setCellContent(enemyCurrentCell, CellType.ENEMYCELL.getValue());
  }

  /*
   * Generates an empty board with the specified height and width
   */
  public GameBoardState(int boardHeight, int boardWidth)
  {
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

  /*
   * Returns the CellType content of the cell
   */
  public int getCellContent(Cell position)
  {
    return board[position.getX()][position.getY()];
  }

  /*
   * Returns the coordinates of your current position on the board
   */
  public Cell getMyCurrentCell()
  {
    return myCurrentCell;
  }

  /*
   * Returns the coordinates of the enemy current position on the board
   */
  public Cell getEnemyCurrentCell()
  {
    return enemyCurrentCell;
  }

  /*
   *  Used only by BotRunner helper class to set specific cell types to position
   *  Do not use this method in PlayerBot#makeMove as altering the
   *  GameBoardState is not a supported operation on the game server
   */
  public void setCellContent(Cell position, int cellType)
  {
    board[position.getX()][position.getY()] = cellType;
  }
}

