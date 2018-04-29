package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;

public class BotHandler
{
  public MoveType fetchNextMove() {
    BotState botState = new BotState();
    GenericBot genericBot = new GenericBot();
    return genericBot.makeMove(botState);
  }
}
