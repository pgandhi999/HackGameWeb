package com.oath.common.snakewars.board;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MoveType {
  @JsonProperty("LEFT")
  LEFT,
  @JsonProperty("RIGHT")
  RIGHT,
  @JsonProperty("UP")
  UP,
  @JsonProperty("DOWN")
  DOWN,
  @JsonProperty("PASS")
  PASS
}