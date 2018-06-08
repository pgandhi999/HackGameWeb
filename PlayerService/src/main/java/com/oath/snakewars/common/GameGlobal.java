package com.oath.snakewars.common;

public class GameGlobal
{
  public static void setPlayerName(String playerName)
  {
    GameGlobal.playerName = playerName;
  }
  public static String getPlayerName()
  {
    return playerName;
  }

  public static String playerName="BOT";

}
