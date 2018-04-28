package com.oath.snakewars.common;

import com.oath.snakewars.utils.GameRequestType;

public class ActionRequest implements EngineRequest
{
  public String getType(){
    return GameRequestType.ACTION_REQUEST;
  }
}
