package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;

public interface Bot
{
  MoveType makeMove (BotState botState) throws Exception;
}
