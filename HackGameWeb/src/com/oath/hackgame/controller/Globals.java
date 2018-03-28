package com.oath.hackgame.controller;

public class Globals
{
	public static int[][] gameState = new int[16][16];
	public static final int emptyCell = 0;
	public static final int wallCell = 1;
	public static final int currPositionPlayer1 = 2;
	public static final int currPositionPlayer2 = 3;
	public static boolean isGameOver = false;
}
