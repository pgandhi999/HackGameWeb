package com.oath.common.snakewars.settings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.oath.common.snakewars.board.Cell;

public class GameBoardState
{
  private final int boardWidth;
  private final int boardHeight;
  private int board[][];
  private Cell myCurrentCell;
  private Cell enemyCurrentCell;

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
    myCurrentCell = gameBoard.getCurrentCellPlayer1();
    enemyCurrentCell = gameBoard.getCurrentCellPlayer2();

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

  public void swapPlayerCells () {

    /*System.out.println("Current Value at my cell is: "+myCurrentCell.getX()+ " "+myCurrentCell.getY());
    System.out.println("Current Value at enemy cell is: "+enemyCurrentCell.getX()+ " "+enemyCurrentCell.getY());*/
    board[enemyCurrentCell.getX()][enemyCurrentCell.getY()] = CellType.PLAYER1;
    board[myCurrentCell.getX()][myCurrentCell.getY()] = CellType.PLAYER2;
    Cell tempCell = new Cell(myCurrentCell.getX(),myCurrentCell.getY());
    myCurrentCell.setX(enemyCurrentCell.getX());
    myCurrentCell.setY(enemyCurrentCell.getY());
    enemyCurrentCell.setX(tempCell.getX());
    enemyCurrentCell.setY(tempCell.getY());
    /*System.out.println("Swapped Value at my cell is: "+myCurrentCell.getX()+ " "+myCurrentCell.getY());
    System.out.println("Swapped Value at enemy cell is: "+enemyCurrentCell.getX()+ " "+enemyCurrentCell.getY());*/

  }
  public Cell getMyCurrentCell()
  {
    return myCurrentCell;
  }
  public Cell getEnemyCurrentCell()
  {
    return enemyCurrentCell;
  }
}

