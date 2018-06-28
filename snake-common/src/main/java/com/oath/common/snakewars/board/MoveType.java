package com.oath.common.snakewars.board;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
  PASS;

  private static final List<MoveType> movesList = Collections.unmodifiableList(Arrays.asList(values()));
  private static final Random rand = new Random();

  /*
   * Generates a random move. This method does not validate the move type and so the returned move may be an invalid one.
   */
  public static MoveType getRandomMoveType() {
    return movesList.get(rand.nextInt(movesList.size()));
  }
}