package com.oath.snakewars.common;

import com.oath.snakewars.utils.GameRequestType;

public class SettingsRequest implements EngineRequest
{
  public String getType(){
    return GameRequestType.SETTINGS_UPDATE;
  }
}

