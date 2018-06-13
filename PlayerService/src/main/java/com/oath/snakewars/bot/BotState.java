package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.GameBoard;
import com.oath.common.snakewars.settings.GameBoardState;
import com.oath.common.snakewars.settings.GameSettings;
import com.oath.snakewars.utils.SettingsProvider;

public class BotState
{
  private final int maxTimeBank;
  private final int singleMoveTime;
  private final int roundNumber;
  private final int timebank;

  private GameBoardState board;

  private MoveType lastMove;
  public BotState (GameBoardState gameBoard) {
    GameSettings gameSettings = SettingsProvider.fetchSettings();
    this.maxTimeBank = gameSettings.getMaxTimeBank();
    this.singleMoveTime = gameSettings.getTimePerMove();
    this.timebank = gameSettings.getTimeBank();
    this.roundNumber = SettingsProvider.getCurrentRound();
    this.board = gameBoard;
    this.lastMove = SettingsProvider.getPreviousMove();
  }

  public int getMaxTimeBank()
  {
    return maxTimeBank;
  }

  public int getSingleMoveTime()
  {
    return singleMoveTime;
  }

  public int getRoundNumber()
  {
    return roundNumber;
  }

  public int getTimebank()
  {
    return timebank;
  }

  public GameBoardState getBoard()
  {
    return board;
  }

  public MoveType getLastDirection()
  {
    return lastMove;
  }
}
