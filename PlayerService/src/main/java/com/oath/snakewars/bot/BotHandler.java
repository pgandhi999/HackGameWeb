package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.GameBoardState;

import org.apache.log4j.Logger;

public class BotHandler
{
  private final GameBoardState gameBoardState;
  private final static Logger logger = Logger.getLogger(BotHandler.class);
  public BotHandler(GameBoardState gameBoardState) {
    this.gameBoardState = gameBoardState;
  }
  public MoveType fetchNextMove() {
    BotState botState = new BotState(gameBoardState);
    //GenericBot genericBot = new GenericBot();
    PlayerBot genericBot = new PlayerBot();
    MoveType mt = MoveType.PASS;
    try {
      mt = genericBot.makeMove(botState);
    } catch(Exception e) {
      logger.error("PlayerBot Code threw error");
      mt = MoveType.PASS;
    }
    logger.info("Move Generated By PlayerBot is " + mt);
    return mt;
  }
}
