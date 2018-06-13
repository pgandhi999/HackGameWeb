package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.GameBoard;
import com.oath.common.snakewars.settings.GameBoardState;
import org.apache.log4j.Logger;


public class GenericBot implements Bot
{
  private final static Logger logger = Logger.getLogger(GenericBot.class);
  public MoveType makeMove(BotState botState)
  {
    logger.info("Returning generic move");
    GameBoardState gb = botState.getBoard();
    //if (botState.getRoundNumber()%5==0)
    return MoveType.RIGHT;
    //else
    //  return MoveType.RIGHT;
  }
}
