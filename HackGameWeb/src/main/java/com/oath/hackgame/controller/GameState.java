package com.oath.hackgame.controller;

import java.util.HashMap;

public class GameState
{
	private int[][] gameState = new int[16][16];
	private boolean isGameOver = false;
	private HashMap< Integer, String > moveListPlayer1 = new HashMap< Integer, String >();
	private HashMap< Integer, String > moveListPlayer2 = new HashMap< Integer, String >();
	private int currMove = 0;
	private int winner = -1;
	private String currentMovePlayer1;
	private String currentMovePlayer2;
	private boolean isMoveOver = false;

	public boolean isMoveOver()
	{
		return isMoveOver;
	}

	public void setMoveOver( boolean isMoveOver )
	{
		this.isMoveOver = isMoveOver;
	}

	public String getCurrentMovePlayer1()
	{
		return currentMovePlayer1;
	}

	public void setCurrentMovePlayer1( String currentMovePlayer1 )
	{
		this.currentMovePlayer1 = currentMovePlayer1;
	}

	public String getCurrentMovePlayer2()
	{
		return currentMovePlayer2;
	}

	public void setCurrentMovePlayer2( String currentMovePlayer2 )
	{
		this.currentMovePlayer2 = currentMovePlayer2;
	}

	public int getWinner()
	{
		return winner;
	}

	public void setWinner( int winner )
	{
		this.winner = winner;
	}

	public int getCurrMove()
	{
		return currMove;
	}

	public void setCurrMove( int currMove )
	{
		this.currMove = currMove;
	}

	public void initGameState()
	{
		for( int i = 0; i < this.gameState.length; i++ )
		{
			for( int j = 0; j < this.gameState.length; j++ )
			{
				this.gameState[i][j] = Globals.emptyCell;
			}
		}
	}

	public int getGameState( int x, int y )
	{
		return gameState[x][y];
	}

	public void setGameState( int x, int y, int z )
	{
		this.gameState[x][y] = z;
	}

	public int[][] getGameStateArray()
	{
		return gameState;
	}

	public HashMap< Integer, String > getMoveListPlayer1()
	{
		return moveListPlayer1;
	}

	public void setMoveListPlayer1( String move )
	{
		this.moveListPlayer1.put( this.currMove, move );
	}

	public HashMap< Integer, String > getMoveListPlayer2()
	{
		return moveListPlayer2;
	}

	public void setMoveListPlayer2( String move )
	{
		this.moveListPlayer2.put( this.currMove, move );
	}

	public boolean isGameOver()
	{
		return isGameOver;
	}

	public void setGameOver( boolean isGameOver )
	{
		this.isGameOver = isGameOver;
	}
}