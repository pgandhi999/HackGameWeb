package com.oath.hackgame.common;

import java.util.ArrayList;
import java.util.Random;

public class TournamentBracket
{
  public static void main (String args[]) {
    Random rand = new Random();
    ArrayList<String> playerList = new ArrayList<String>(16);
    playerList.add("Unteam");
    playerList.add("Janith");
    playerList.add("Xuefeng");
    playerList.add("Team B551");
    playerList.add("Aaron Canary");
    playerList.add("Edward");
    playerList.add("Aaron Klish");
    playerList.add("abc");
    playerList.add("mEatU");
    playerList.add("Pradeep");
    playerList.add("Aditya");
    playerList.add("Oleksiy");
    playerList.add("Kedar");
    playerList.add("Naila");
    playerList.add("Govind");
    playerList.add("Tarrant");
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
