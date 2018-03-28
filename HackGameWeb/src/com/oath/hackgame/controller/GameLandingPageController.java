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

	@RequestMapping( value = "/getInitGameState", method = RequestMethod.POST )
	public void getInitGameState( HttpServletRequest request, HttpServletResponse response, ModelMap model ) throws ServletException, IOException
	{
		try
		{
			for( int i = 0; i < Globals.gameState.length; i++ )
			{
				for( int j = 0; j < Globals.gameState.length; j++ )
				{
					Globals.gameState[i][j] = Globals.emptyCell;
				}
			}
			int initPlayer1PosX = ThreadLocalRandom.current().nextInt( 0, 16 );
			int initPlayer1PosY = ThreadLocalRandom.current().nextInt( 0, 16 );
			Globals.gameState[initPlayer1PosX][initPlayer1PosY] = Globals.currPositionPlayer1;
			int initPlayer2PosX = ThreadLocalRandom.current().nextInt( 0, 16 );
			int initPlayer2PosY = ThreadLocalRandom.current().nextInt( 0, 16 );
			if( initPlayer1PosX == initPlayer2PosX && initPlayer1PosY == initPlayer2PosY )
			{
				initPlayer2PosX = ThreadLocalRandom.current().nextInt( 0, 16 );
				initPlayer2PosY = ThreadLocalRandom.current().nextInt( 0, 16 );
			}
			Globals.gameState[initPlayer2PosX][initPlayer2PosY] = Globals.currPositionPlayer2;
			initializePlayerClient();
			JSONArray parentJsonArray = new JSONArray();
			for( int i = 0; i < Globals.gameState.length; i++ )
			{
				JSONArray childJsonArray = new JSONArray();
				for( int j = 0; j < Globals.gameState.length; j++ )
				{
					childJsonArray.add( Globals.gameState[i][j] );
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
		while( !Globals.isGameOver )
		{
			sendGameStateToPlayers();
			try
			{
				Thread.sleep( 10000 );
			}
			catch( InterruptedException e )
			{
				e.printStackTrace();
			}
			receivePlayerMoves();
			validatePlayerMoves();
			updateAndValidateGameState();
		}
		getWinner();
	}

	private void getWinner()
	{
		// TODO Auto-generated method stub

	}

	private void updateAndValidateGameState()
	{
		// TODO Auto-generated method stub

	}

	private void validatePlayerMoves()
	{
		// TODO Auto-generated method stub

	}

	private void receivePlayerMoves()
	{
		// TODO Auto-generated method stub

	}

	private void sendGameStateToPlayers()
	{
		// TODO Auto-generated method stub

	}
}
