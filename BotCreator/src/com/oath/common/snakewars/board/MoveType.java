package com.oath.common.snakewars.board;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum MoveType {
  UP,
  DOWN,
  LEFT,
  RIGHT,
  PASS;
  private static final List<MoveType> movesList = Collections.unmodifiableList(Arrays.asList(values()));
  private static final Random rand = new Random();

  public static MoveType getRandomMoveType() {
    return movesList.get(rand.nextInt(movesList.size()));
  }
}