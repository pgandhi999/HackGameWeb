package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.GameBoard;
import com.oath.common.snakewars.settings.GameBoardState;
import com.oath.snakewars.common.GameGlobal;
import org.apache.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class GenericBot implements Bot
{
  private final static Logger logger = Logger.getLogger(GenericBot.class);

  public MoveType makeMove(BotState botState) throws Exception
  {
    logger.info("Returning generic move");
    GameBoardState gb = botState.getBoard();
    //if (botState.getRoundNumber()%5==0)
    System.out.println(GameGlobal.getPlayerName()
                       + ": My position: "
                       + gb.getMyCurrentCell().getX()
                       + " "
                       + gb.getMyCurrentCell().getY());
    System.out.println(GameGlobal.getPlayerName()
                       + ": Enemy position: "
                       + gb.getEnemyCurrentCell().getX()
                       + " "
                       + gb.getEnemyCurrentCell().getY());
    int sim1 = ThreadLocalRandom.current().nextInt(0, 4);
    System.out.println("SEED is : " + sim1);
    if (sim1 == 0) {
      return MoveType.UP;
    } else if (sim1 == 1) {
      return MoveType.DOWN;
    } else if (sim1 == 2) {
      return MoveType.LEFT;
    } else {
      return MoveType.RIGHT;
    }
    //else
    //  return MoveType.RIGHT;
  }
}
