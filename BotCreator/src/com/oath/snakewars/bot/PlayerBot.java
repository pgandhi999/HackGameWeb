package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;

/*
 * This class represents your bot that will compete in Snakewars!
 */
public class PlayerBot implements Bot
{
  /*
   * This is where all the magic happens. Implement the method to make your bot smarter.
   */
  public MoveType makeMove(BotState botState) throws Exception
  {
    return MoveType.LEFT;
  }
}
