package com.oath.hackgame.common;

import java.util.ArrayList;
import java.util.Random;

public class TournamentBracket
{
  public static void main (String args[]) {
    Random rand = new Random();
    ArrayList<String> playerList = new ArrayList<String>(16);
    playerList.add("Rishi");
    playerList.add("Team B551");
    playerList.add("Aaron Canary");
    playerList.add("Cheezesnake");
    playerList.add("Onix");
    playerList.add("5G");
    playerList.add("mEatU");
    playerList.add("Jonathan");
    playerList.add("TheWall");
    playerList.add("Oleksiy");
    playerList.add("Kedar");
    playerList.add("SnakeBite");
    while (!playerList.isEmpty()) {
      int player1 = rand.nextInt(playerList.size());
      String player1Name = playerList.get(player1);
      playerList.remove(player1);
      int player2 = rand.nextInt(playerList.size());
      String player2Name = playerList.get(player2);
      playerList.remove(player2);
      System.out.println(player1Name + " vs " + player2Name);
    }
  }
}
