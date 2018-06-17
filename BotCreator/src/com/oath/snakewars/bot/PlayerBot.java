package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;

public class PlayerBot implements Bot
{
  /*
   * This is where all the magic happens. Implement the method to make your bot smarter.
   */
  @Override
  public MoveType makeMove(BotState botState)
  {
    return MoveType.LEFT;
  }
}
