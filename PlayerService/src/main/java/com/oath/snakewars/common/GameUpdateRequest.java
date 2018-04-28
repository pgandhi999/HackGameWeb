package com.oath.snakewars.common;

import com.oath.snakewars.utils.GameRequestType;

public class GameUpdateRequest implements EngineRequest
{
  public String getType(){
    return GameRequestType.GAME_UPDATE;
  }
}

