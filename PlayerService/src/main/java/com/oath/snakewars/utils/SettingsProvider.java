package com.oath.snakewars.utils;

import com.oath.common.snakewars.settings.GameBoard;
import com.oath.common.snakewars.settings.GameSettings;

public class SettingsProvider
{
  private static GameSettings gameSettings;
  private static int currentRound;
  private static GameBoard gameBoard;
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
  public static void build(GameSettings settings)
  {
    gameSettings = settings;
    currentRound = 0;
    gameBoard = null;
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
}
