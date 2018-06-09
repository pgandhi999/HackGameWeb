package com.oath.hackgame.common;

import com.oath.common.snakewars.board.MoveType;
import com.oath.hackgame.controller.PlayerInfo;

public class PlayerMove
{
  private final MoveType moveType;
  private final int round;
  private final PlayerInfo playerInfo;

  public PlayerMove(MoveType currentMove, int roundNumber, PlayerInfo playerInfo) {
    this.moveType = currentMove;
    this.round = roundNumber;
    this.playerInfo = playerInfo;
  }

  public MoveType getMoveType()
  {
    return moveType;
  }

  public int getRound()
  {
    return round;
  }

  public PlayerInfo getPlayerInfo()
  {
    return playerInfo;
  }

}
