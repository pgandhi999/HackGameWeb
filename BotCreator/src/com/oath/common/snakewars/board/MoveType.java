package com.oath.common.snakewars.board;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * The type of moves that can be made.
 * UP : Move one cell up from the current position
 * DOWN : Move one cell down from the current position
 * LEFT : Move one cell left from the current position
 * RIGHT : Move one cell right from the current position
 * PASS : Move in the same direction as previous move
 */
public enum MoveType {
  UP,
  DOWN,
  LEFT,
  RIGHT,
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