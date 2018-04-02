package com.oath.hackgame.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GameLandingPageController
{

	public PlayerInfo player1 = null;
	public PlayerInfo player2 = null;
	public GameState gs = null;

	@SuppressWarnings( "unchecked" )
	@RequestMapping( value = "/getInitGameState", method = RequestMethod.POST )
	public void getInitGameState( HttpServletRequest request, HttpServletResponse response, ModelMap model ) throws ServletException, IOException
	{
		try
		{
			gs = new GameState();
			gs.initGameState();
			int initPlayer1PosX = ThreadLocalRandom.current().nextInt( 0, 16 );
			int initPlayer1PosY = ThreadLocalRandom.current().nextInt( 0, 16 );
			gs.setGameState( initPlayer1PosX, initPlayer1PosY, Globals.currPositionPlayer1 );
			int initPlayer2PosX = ThreadLocalRandom.current().nextInt( 0, 16 );
			int initPlayer2PosY = ThreadLocalRandom.current().nextInt( 0, 16 );
			if( initPlayer1PosX == initPlayer2PosX && initPlayer1PosY == initPlayer2PosY )
			{
				initPlayer2PosX = ThreadLocalRandom.current().nextInt( 0, 16 );
				initPlayer2PosY = ThreadLocalRandom.current().nextInt( 0, 16 );
			}
			gs.setGameState( initPlayer2PosX, initPlayer2PosY, Globals.currPositionPlayer2 );
			initializePlayerClient();
			JSONArray parentJsonArray = new JSONArray();
			int arrayLength = gs.getGameStateArray().length;
			for( int i = 0; i < arrayLength; i++ )
			{
				JSONArray childJsonArray = new JSONArray();
				for( int j = 0; j < arrayLength; j++ )
				{
					childJsonArray.add( gs.getGameState( i, j ) );
				}
				parentJsonArray.add( childJsonArray );
			}
			JSONObject jo = new JSONObject();
			jo.put( "player1name", player1.getPlayerName() );
			jo.put( "player2name", player2.getPlayerName() );
			parentJsonArray.add( jo );
			StringWriter out = new StringWriter();
			JSONValue.writeJSONString( parentJsonArray, out );
			String jsonText = out.toString();
			response.setContentType( "text/plain" );
			response.setHeader( "Content-Type", "application/x-www-form-urlencoded" );
			response.setHeader( "Cache-Control", "no-cache" );
			response.setHeader( "Pragma", "no-cache" );
			response.getWriter().write( jsonText );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

	}

	private void initializePlayerClient()
	{
		// TODO Auto-generated method stub

	}

	@RequestMapping( value = "/startGame", method = RequestMethod.POST )
	public void startGame( HttpServletRequest request, HttpServletResponse response, ModelMap model ) throws ServletException, IOException
	{
		while( !gs.isGameOver() )
		{
			sendGameStateToPlayers( gs.getCurrMove() );
			try
			{
				Thread.sleep( 10000 );
			}
			catch( InterruptedException e )
			{
				e.printStackTrace();
			}
			validatePlayerMovesAndUpdateGameState();
		}
		getWinner();
	}

	private void getWinner()
	{
		// TODO Auto-generated method stub

	}

	private void validatePlayerMovesAndUpdateGameState()
	{
		int currentMove = gs.getCurrMove();
		String movePlayer1 = gs.getMoveListPlayer1().get( currentMove );
		String movePlayer2 = gs.getMoveListPlayer2().get( currentMove );
		if( movePlayer1 == null )
		{
			movePlayer1 = currentMove != 0 ? gs.getMoveListPlayer1().get( currentMove - 1 ) : Globals.moves.Up.toString();
			gs.getMoveListPlayer1().put( currentMove, movePlayer1 );
		}
		if( movePlayer2 == null )
		{
			movePlayer2 = currentMove != 0 ? gs.getMoveListPlayer2().get( currentMove - 1 ) : Globals.moves.Up.toString();
			gs.getMoveListPlayer2().put( currentMove, movePlayer2 );
		}
		boolean player1loses = false;
		boolean player2loses = false;
		int arrayLength = gs.getGameStateArray().length;
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		for( int i = 0; i < arrayLength; i++ )
		{
			for( int j = 0; j < arrayLength; j++ )
			{
				if( gs.getGameState( i, j ) == Globals.currPositionPlayer1 )
				{
					x1 = i;
					y1 = j;
				}
				else if( gs.getGameState( i, j ) == Globals.currPositionPlayer2 )
				{
					x2 = i;
					y2 = j;
				}
			}
		}
		player1loses = validateMove( movePlayer1, x1, y1 );
		player2loses = validateMove( movePlayer2, x2, y2 );
		if( player1loses && player2loses )
		{
			gs.setWinner( 0 );
			gs.setGameOver( true );
		}
		else if( player1loses )
		{
			gs.setWinner( 2 );
			gs.setGameOver( true );
		}
		else if( player2loses )
		{
			gs.setWinner( 1 );
			gs.setGameOver( true );
		}
		else
		{
			int x10 = x1;
			int y10 = y1;
			int x20 = x2;
			int y20 = y2;
			if( movePlayer1.equalsIgnoreCase( Globals.moves.Up.toString() ) )
			{
				y1++;
			}
			else if( movePlayer1.equalsIgnoreCase( Globals.moves.Down.toString() ) )
			{
				y1--;
			}
			else if( movePlayer1.equalsIgnoreCase( Globals.moves.Left.toString() ) )
			{
				x1--;
			}
			else if( movePlayer1.equalsIgnoreCase( Globals.moves.Right.toString() ) )
			{
				x1++;
			}

			if( movePlayer2.equalsIgnoreCase( Globals.moves.Up.toString() ) )
			{
				y2++;
			}
			else if( movePlayer2.equalsIgnoreCase( Globals.moves.Down.toString() ) )
			{
				y2--;
			}
			else if( movePlayer2.equalsIgnoreCase( Globals.moves.Left.toString() ) )
			{
				x2--;
			}
			else if( movePlayer2.equalsIgnoreCase( Globals.moves.Right.toString() ) )
			{
				x2++;
			}

			if( x1 == x2 && y1 == y2 )
			{
				player1loses = true;
				player2loses = true;
				gs.setWinner( 0 );
				gs.setGameOver( true );
			}
			else
			{
				gs.setGameState( x10, y10, Globals.wallCell );
				gs.setGameState( x1, y1, Globals.currPositionPlayer1 );
				gs.setGameState( x20, y20, Globals.wallCell );
				gs.setGameState( x2, y2, Globals.currPositionPlayer2 );
			}
		}
	}

	private boolean validateMove( String movePlayer, int x, int y )
	{
		if( ( movePlayer.equalsIgnoreCase( Globals.moves.Up.toString() ) && y + 1 > 15 ) || ( movePlayer.equalsIgnoreCase( Globals.moves.Down.toString() ) && y - 1 < 0 )
				|| ( movePlayer.equalsIgnoreCase( Globals.moves.Left.toString() ) && x - 1 < 0 ) || ( movePlayer.equalsIgnoreCase( Globals.moves.Right.toString() ) && x + 1 > 15 ) )
		{
			return true;
		}
		if( movePlayer.equalsIgnoreCase( Globals.moves.Up.toString() ) && gs.getGameState( x, y + 1 ) == Globals.wallCell )
		{
			return true;
		}
		if( movePlayer.equalsIgnoreCase( Globals.moves.Down.toString() ) && gs.getGameState( x, y - 1 ) == Globals.wallCell )
		{
			return true;
		}
		if( movePlayer.equalsIgnoreCase( Globals.moves.Left.toString() ) && gs.getGameState( x - 1, y ) == Globals.wallCell )
		{
			return true;
		}
		if( movePlayer.equalsIgnoreCase( Globals.moves.Right.toString() ) && gs.getGameState( x + 1, y ) == Globals.wallCell )
		{
			return true;
		}
		return false;
	}

	private void sendGameStateToPlayers( int currMove )
	{
		// TODO Auto-generated method stub

	}
}
