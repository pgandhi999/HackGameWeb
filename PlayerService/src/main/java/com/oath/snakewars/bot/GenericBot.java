package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;

public class GenericBot implements Bot
{
  public MoveType makeMove(BotState botState)
  {
    return MoveType.LEFT;
  }
}
