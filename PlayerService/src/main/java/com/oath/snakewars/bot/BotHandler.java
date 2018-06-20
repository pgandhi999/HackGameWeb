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
    PlayerBot genericBot = new PlayerBot();
    MoveType mt;
    try {
      mt = genericBot.makeMove(botState);
    } catch(Exception e) {
      mt = MoveType.PASS;
    }
    return mt;
  }
}
