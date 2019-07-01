package com.oath.common.snakewars.settings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.oath.common.snakewars.board.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

  public GameBoardState()
  {
    this.boardHeight = 16;
    this.boardWidth = 16;
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
  public int getBoardWidth() {
    return boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  public int getCellContent(Cell cell) {
    return board[cell.getX()][cell.getY()];
  }

  public void swapPlayerCells () {

    /*System.out.println("Current Value at my cell is: "+myCurrentCell.getX()+ " "+myCurrentCell.getY());
    System.out.println("Current Value at enemy cell is: "+enemyCurrentCell.getX()+ " "+enemyCurrentCell.getY());*/
    board[enemyCurrentCell.getX()][enemyCurrentCell.getY()] = CellType.MYCELL.getValue();
    board[myCurrentCell.getX()][myCurrentCell.getY()] = CellType.ENEMYCELL.getValue();
    Cell tempCell = new Cell(myCurrentCell.getX(),myCurrentCell.getY());
    myCurrentCell.setX(enemyCurrentCell.getX());
    myCurrentCell.setY(enemyCurrentCell.getY());
    enemyCurrentCell.setX(tempCell.getX());
    enemyCurrentCell.setY(tempCell.getY());
    /*System.out.println("Swapped Value at my cell is: "+myCurrentCell.getX()+ " "+myCurrentCell.getY());
    System.out.println("Swapped Value at enemy cell is: "+enemyCurrentCell.getX()+ " "+enemyCurrentCell.getY());*/

  }
  public List<Cell> fetchTrapCells() {
    ArrayList<Cell> trapList = new ArrayList<>();
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardWidth; j++) {
        if ( board[i][j] == CellType.TRAP.getValue()) {
          trapList.add(new Cell(i,j));
        };
      }
    }
    return trapList;
  }

  public Cell getMyCurrentCell()
  {
    return myCurrentCell;
  }
  public Cell getEnemyCurrentCell()
  {
    return enemyCurrentCell;
  }
  public void setMyCurrentCell(Cell inputCell)
  {
    myCurrentCell = new Cell(inputCell.getX(),inputCell.getY());
  }
  public void setEnemyCurrentCell(Cell inputCell)
  {
    enemyCurrentCell = new Cell(inputCell.getX(),inputCell.getY());
  }
  public void setCellContent(Cell position, int cellType)
  {
    board[position.getX()][position.getY()] = cellType;
  }
  public boolean isTrap( Cell cell) {
    if (board[cell.getX()][cell.getY()] == CellType.TRAP.getValue()) {
      return true;
    }
    return false;
  }
}

