package com.oath.snakewars.utils;

import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.GameBoard;
import com.oath.common.snakewars.settings.GameSettings;

public class SettingsProvider
{
  private static GameSettings gameSettings;
  private static int currentRound;
  private static GameBoard gameBoard;
  private static MoveType previousMove;
  public static GameSettings fetchSettings()
  {
    if (gameSettings == null) {
      //TODO log error
      return null;
    } else {
      return gameSettings;
    }
  }

  public static int getCurrentRound()
  {
    return currentRound;
  }

  public static GameBoard getGameBoard()
  {
    return gameBoard;
  }

  public static MoveType getPreviousMove()
  {
    return previousMove;
  }
  public static void build(GameSettings settings)
  {
    gameSettings = settings;
    currentRound = 0;
    gameBoard = null;
    previousMove = null;
  }

  // Rounds can be incremented only one at a time
  public static void updateCurrentRound()
  {
    currentRound += 1;
  }
  public static void updateGameBoard(GameBoard gBoard)
  {
    gameBoard = gBoard;
  }
  public static void updateLastMove(MoveType move)
  {
    previousMove = move;
  }
}
