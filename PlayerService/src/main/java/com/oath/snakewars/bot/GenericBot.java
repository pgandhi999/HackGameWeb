package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;
import org.apache.log4j.Logger;


public class GenericBot implements Bot
{
  private final static Logger logger = Logger.getLogger(GenericBot.class);
  public MoveType makeMove(BotState botState)
  {
    logger.info("Returning generic move");
    return MoveType.LEFT;
  }
}
