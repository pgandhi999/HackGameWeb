package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.GameBoardState;

public class BotHandler
{
  private final GameBoardState gameBoardState;
  public BotHandler(GameBoardState gameBoardState) {
    this.gameBoardState = gameBoardState;
  }
  public MoveType fetchNextMove() {
    BotState botState = new BotState(gameBoardState);
    GenericBot genericBot = new GenericBot();
    return genericBot.makeMove(botState);
  }
}
