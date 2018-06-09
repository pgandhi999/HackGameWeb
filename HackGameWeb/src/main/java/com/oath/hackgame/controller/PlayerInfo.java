package com.oath.hackgame.controller;

public class PlayerInfo
{
  private final String playerName;
  private final String serviceUrl;
  PlayerInfo(String playerName, String serviceUrl)
  {
    this.playerName = playerName;
    this.serviceUrl = serviceUrl;
  }

  public String getPlayerName()
  {
    return playerName;
  }
  public String getServiceUrl() { return serviceUrl; }
}
