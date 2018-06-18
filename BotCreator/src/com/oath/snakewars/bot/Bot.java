package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;

public interface Bot
{
  public MoveType makeMove (BotState botState) throws Exception;
}
