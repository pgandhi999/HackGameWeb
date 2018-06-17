package com.oath.snakewars.common;

public class GameGlobal
{
  public static String playerName = "BOT";
  public static int playerNumber = 2;

  public static void setPlayerName(String playerName)
  {
    GameGlobal.playerName = playerName;
  }

  public static String getPlayerName()
  {
    return playerName;
  }

  public static int getPlayerNumber()
  {
    return playerNumber;
  }

  public static void setPlayerNumber(int playerNumber)
  {
    GameGlobal.playerNumber = playerNumber;
  }

}
