package com.oath.common.snakewars.settings;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.oath.common.snakewars.board.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
  @JsonProperty
  public int trapCellCount;

  @JsonCreator
  public GameBoard(
      @JsonProperty("boardHeight") int boardHeight,
      @JsonProperty("boardWidth") int boardWidth,
      @JsonProperty("trapCellCount") int trapCellCount
  )
  {
    this.boardHeight = boardHeight;
    this.boardWidth = boardWidth;
    this.board = new int[boardHeight][boardWidth];
    for (int i = 0; i < boardHeight; i++) {
      for (int j = 0; j < boardWidth; j++) {
        board[i][j] = CellType.EMPTY.getValue();
      }
    }
    this.trapCellCount = trapCellCount;
    addTraps(board, trapCellCount);
    currentCellPlayer1 = new Cell(0, 0);
    currentCellPlayer2 = new Cell(0, 0);

  }

  public int getBoardWidth()
  {
    return boardWidth;
  }

  public int getBoardHeight()
  {
    return boardHeight;
  }

  public int getTrapCellCount()
  {
    return trapCellCount;
  }

  public void updateGameBoard(Cell player1, Cell player2)
  {
    board[player1.getX()][player1.getY()] = CellType.MYCELL.getValue();
    board[player2.getX()][player2.getY()] = CellType.ENEMYCELL.getValue();
    setCurrentCellPlayer1(player1);
    setCurrentCellPlayer2(player2);
  }

  public int getCellContent(int x, int y)
  {
    return board[x][y];
  }

  public void setCellContent(int x, int y, int z)
  {
    board[x][y] = z;
  }

  public Cell getCurrentCellPlayer1()
  {
    return currentCellPlayer1;
  }

  public void setCurrentCellPlayer1(Cell currentCell)
  {
    this.currentCellPlayer1 = currentCell;
  }

  public Cell getCurrentCellPlayer2()
  {
    return currentCellPlayer2;
  }

  public void setCurrentCellPlayer2(Cell currentCell)
  {
    this.currentCellPlayer2 = currentCell;
  }

  public void displayGameBoard()
  {
    System.out.println("Game board is:");
    for (int i = 0; i < boardHeight; i++) {
      System.out.print("\n");
      for (int j = 0; j < boardWidth; j++) {
        System.out.print(board[i][j] + " ");
      }
    }
  }

  private void addTraps(int[][] board, int trapCount)
  {
    int remainingTrapCount = trapCount - boardHeight;
    int trapLimit = boardHeight;
    if (remainingTrapCount < 0) {
      trapLimit = trapCount;
    }
    List<Integer> randomXList = IntStream.rangeClosed(0, boardHeight - 1).boxed().collect(Collectors.toList());
    List<Integer> randomYList = IntStream.rangeClosed(0, boardWidth - 1).boxed().collect(Collectors.toList());
    Collections.shuffle(randomXList);
    Collections.shuffle(randomYList);
    for (int i =0; i < trapLimit; i++) {
      int trapXCell = randomXList.get(i);
      int trapYCell = randomYList.get(i);
      if (board[trapXCell][trapYCell] != CellType.TRAP.getValue()) {
        board[trapXCell][trapYCell] = CellType.TRAP.getValue();
      }
    }
    if (remainingTrapCount > 0) {
      trapLimit = trapCount;
      Collections.shuffle(randomXList);
      Collections.shuffle(randomYList);
      for (int i =0; i < remainingTrapCount; i++) {
        int trapXCell = randomXList.get(i);
        int trapYCell = randomYList.get(i);
        if (board[trapXCell][trapYCell] != CellType.TRAP.getValue()) {
          board[trapXCell][trapYCell] = CellType.TRAP.getValue();
        }
      }
    }
  }

  public boolean validateStartPoint (int startX, int startY) {
    int trapCount = 0;
    if (( startX+1 < boardHeight ) && board[startX+1][startY] == CellType.TRAP.getValue()) {
      trapCount++;
    }
    if (( startX-1 >= 0 ) && board[startX-1][startY] == CellType.TRAP.getValue()) {
      trapCount++;
    }
    if (( startY+1 < boardWidth ) && board[startX][startY+1] == CellType.TRAP.getValue()) {
      trapCount++;
    }
    if (( startY-1 >= 0 ) && board[startX][startY-1] == CellType.TRAP.getValue()) {
      trapCount++;
    }
    // As long as the cell itself isnt a trap and there is not more than one trap nearby, we're good!
    if (board[startX][startY] == CellType.TRAP.getValue() || trapCount >= 2) {
      return false;
    }
    return true;
  }

  public List<List<Integer>> fetchTraps() {
    List<List<Integer>> trapList = new ArrayList<>();
    for (int x = 0; x < boardHeight; x++) {
      for (int y = 0; y < boardWidth; y++) {
        if (board[x][y] == CellType.TRAP.getValue()) {
          trapList.add(Arrays.asList(x, y));
        }
      }
    }
    return trapList;
  }
}
