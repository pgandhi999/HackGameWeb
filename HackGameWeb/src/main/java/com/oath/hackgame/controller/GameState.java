package com.oath.hackgame.controller;

import com.oath.common.snakewars.board.Cell;
import com.oath.common.snakewars.settings.GameBoard;

import java.util.HashMap;

public class GameState
{
  private int[][] gameState = new int[16][16];
  private boolean isGameOver = false;
  //Maps round number to player1 move
  private HashMap<Integer, String> moveListPlayer1 = new HashMap<Integer, String>();
  //Maps round number to player2 move
  private HashMap<Integer, String> moveListPlayer2 = new HashMap<Integer, String>();
  private int currMove = 1;
  private int winner = -1;
  private String currentMovePlayer1;
  private String currentMovePlayer2;
  private boolean isMoveOver = false;
  private GameBoard gameBoard;
  private int roundNumber = 0;

  public boolean isMoveOver()
  {
    return isMoveOver;
  }

  public void setMoveOver(boolean isMoveOver)
  {
    this.isMoveOver = isMoveOver;
  }

  public String getCurrentMovePlayer1()
  {
    return currentMovePlayer1;
  }

  public void setCurrentMovePlayer1(String currentMovePlayer1)
  {
    this.currentMovePlayer1 = currentMovePlayer1;
  }

  public String getCurrentMovePlayer2()
  {
    return currentMovePlayer2;
  }

  public void setCurrentMovePlayer2(String currentMovePlayer2)
  {
    this.currentMovePlayer2 = currentMovePlayer2;
  }

  public int getWinner()
  {
    return winner;
  }

  public void setWinner(int winner)
  {
    this.winner = winner;
  }

  public int getCurrMove()
  {
    return currMove;
  }

  public void setCurrMove(int currMove)
  {
    this.currMove = currMove;
  }

  public void initGameState()
  {
    for (int i = 0; i < this.gameState.length; i++) {
      for (int j = 0; j < this.gameState.length; j++) {
        this.gameState[i][j] = Globals.EMPTY_CELL;
      }
    }
  }

  public int getGameBoard(int x, int y)
  {
    return gameBoard.getCellContent(x,y);
    //return gameState[x][y];
  }

  public void setGameState(int x, int y, int z)
  {
    this.gameState[x][y] = z;
  }

  public void setWholeGameState(Cell player1, Cell player2)
  {
    gameBoard.updateGameBoard(player1, player2);
  }

  public int[][] getGameStateArray()
  {
    return gameState;
  }

  public HashMap<Integer, String> getMoveListPlayer1()
  {
    return moveListPlayer1;
  }

  public void setMoveListPlayer1(String move)
  {
    //TODO Replace roundNumber with currmove
    //this.moveListPlayer1.put(this.getRoundNumber(), move);
    this.moveListPlayer1.put(this.currMove - 1, move);

  }

  public HashMap<Integer, String> getMoveListPlayer2()
  {
    return moveListPlayer2;
  }


  public void setMoveListPlayer2(String move)
  {
    //TODO Replace roundNumber with currmove
    //this.moveListPlayer2.put(this.getRoundNumber(), move);
    this.moveListPlayer2.put(this.currMove - 1, move);

  }

  public boolean isGameOver()
  {
    return isGameOver;
  }

  public void setGameOver(boolean isGameOver)
  {
    this.isGameOver = isGameOver;
  }

  public void setGameBoard(GameBoard gameBoard)
  {
    this.gameBoard = gameBoard;
  }

  public GameBoard getGameBoard()
  {
    return gameBoard;
  }

  public void incrementRound()
  {
    this.roundNumber += 1;
  }
  public int getRoundNumber() {
    return this.roundNumber;
  }
}
