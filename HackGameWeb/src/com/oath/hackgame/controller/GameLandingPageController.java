package com.oath.hackgame.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	public ObjectMapper objectMapper = new ObjectMapper();

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
			// TODO Try if this works
			HashMap< String, Object > playerProps = new HashMap< String, Object >();

			/*
			 * JSONArray ja = new JSONArray(); JSONObject jo = new
			 * JSONObject();
			 */
			/*
			 * jo.put( "player1name", player1.getPlayerName() );
			 * jo.put( "player2name", player2.getPlayerName() );
			 */

			/*
			 * jo.put( "player1name", "Parth" ); jo.put(
			 * "player2name", "Sunny" ); jo.put( "player1x",
			 * initPlayer1PosX ); jo.put( "player1y",
			 * initPlayer1PosY ); jo.put( "player2x",
			 * initPlayer2PosX ); jo.put( "player2y",
			 * initPlayer2PosY ); ja.add( jo );
			 */
			playerProps.put( "player1name", "Parth" );
			playerProps.put( "player2name", "Sunny" );
			playerProps.put( "player1x", initPlayer1PosX );
			playerProps.put( "player1y", initPlayer1PosY );
			playerProps.put( "player2x", initPlayer2PosX );
			playerProps.put( "player2y", initPlayer2PosY );
			/* JSONValue.writeJSONString( ja, out ); */
			response.setContentType( "text/plain" );
			response.setHeader( "Content-Type", "application/x-www-form-urlencoded" );
			response.setHeader( "Cache-Control", "no-cache" );
			response.setHeader( "Pragma", "no-cache" );
			/* response.getWriter().write( jsonText ); */
			response.getWriter().write( objectMapper.writeValueAsString( playerProps ) );

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
		try
		{
			while( !gs.isGameOver() )
			{
				// sendGameStateToPlayers( gs.getCurrMove() );
				simulateGame();
				try
				{
					Thread.sleep( 4000 );
				}
				catch( InterruptedException e )
				{
					e.printStackTrace();
				}
				validatePlayerMovesAndUpdateGameState();
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	private void simulateGame()
	{
		try
		{
			int sim1 = ThreadLocalRandom.current().nextInt( 0, 4 );
			int sim2 = ThreadLocalRandom.current().nextInt( 0, 4 );
			int arrayLength = gs.getGameStateArray().length;
			int x1 = 0;
			int y1 = 0;
			int x2 = 0;
			int y2 = 0;
			System.out.println( "SEED is : " + sim1 + " AND " + sim2 );
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

			if( sim1 == 0 )
			{
				if( x1 - 1 < 0 )
				{
					gs.setMoveListPlayer1( Globals.moves.Left.toString() );
				}
				else
				{
					gs.setMoveListPlayer1( Globals.moves.Up.toString() );
				}
			}
			else if( sim1 == 1 )
			{
				if( x1 + 1 > 15 )
				{
					gs.setMoveListPlayer1( Globals.moves.Right.toString() );
				}
				else
				{
					gs.setMoveListPlayer1( Globals.moves.Down.toString() );
				}
			}
			else if( sim1 == 2 )
			{
				if( y1 - 1 < 0 )
				{
					gs.setMoveListPlayer1( Globals.moves.Up.toString() );
				}
				else
				{
					gs.setMoveListPlayer1( Globals.moves.Left.toString() );
				}
			}
			else if( sim1 == 3 )
			{
				if( y1 + 1 > 15 )
				{
					gs.setMoveListPlayer1( Globals.moves.Down.toString() );
				}
				else
				{
					gs.setMoveListPlayer1( Globals.moves.Right.toString() );
				}
			}

			if( sim2 == 0 )
			{
				if( x2 - 1 < 0 )
				{
					gs.setMoveListPlayer2( Globals.moves.Left.toString() );
				}
				else
				{
					gs.setMoveListPlayer2( Globals.moves.Up.toString() );
				}
			}
			else if( sim2 == 1 )
			{
				if( x2 + 1 > 15 )
				{
					gs.setMoveListPlayer2( Globals.moves.Right.toString() );
				}
				else
				{
					gs.setMoveListPlayer2( Globals.moves.Down.toString() );
				}
			}
			else if( sim2 == 2 )
			{
				if( y2 - 1 < 0 )
				{
					gs.setMoveListPlayer2( Globals.moves.Up.toString() );
				}
				else
				{
					gs.setMoveListPlayer2( Globals.moves.Left.toString() );
				}
			}
			else if( sim2 == 3 )
			{
				if( y2 + 1 > 15 )
				{
					gs.setMoveListPlayer2( Globals.moves.Down.toString() );
				}
				else
				{
					gs.setMoveListPlayer2( Globals.moves.Right.toString() );
				}
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}

	}

	private void validatePlayerMovesAndUpdateGameState()
	{
		try
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
			System.out.println( "hereeeeeeee 1 " + movePlayer1 + " " + movePlayer2 );
			if( currentMove > 0 )
			{
				if( movePlayer1.equalsIgnoreCase( Globals.moves.Up.toString() ) && gs.getMoveListPlayer1().get( currentMove - 1 ).equalsIgnoreCase( Globals.moves.Down.toString() ) )
				{
					movePlayer1 = Globals.moves.Down.toString();
					gs.getMoveListPlayer1().put( currentMove, movePlayer1 );
				}
				else if( movePlayer1.equalsIgnoreCase( Globals.moves.Down.toString() )
						&& gs.getMoveListPlayer1().get( currentMove - 1 ).equalsIgnoreCase( Globals.moves.Up.toString() ) )
				{
					movePlayer1 = Globals.moves.Up.toString();
					gs.getMoveListPlayer1().put( currentMove, movePlayer1 );
				}
				else if( movePlayer1.equalsIgnoreCase( Globals.moves.Left.toString() )
						&& gs.getMoveListPlayer1().get( currentMove - 1 ).equalsIgnoreCase( Globals.moves.Right.toString() ) )
				{
					movePlayer1 = Globals.moves.Right.toString();
					gs.getMoveListPlayer1().put( currentMove, movePlayer1 );
				}
				else if( movePlayer1.equalsIgnoreCase( Globals.moves.Right.toString() )
						&& gs.getMoveListPlayer1().get( currentMove - 1 ).equalsIgnoreCase( Globals.moves.Left.toString() ) )
				{
					movePlayer1 = Globals.moves.Left.toString();
					gs.getMoveListPlayer1().put( currentMove, movePlayer1 );
				}

				if( movePlayer2.equalsIgnoreCase( Globals.moves.Up.toString() ) && gs.getMoveListPlayer2().get( currentMove - 1 ).equalsIgnoreCase( Globals.moves.Down.toString() ) )
				{
					movePlayer2 = Globals.moves.Down.toString();
					gs.getMoveListPlayer2().put( currentMove, movePlayer2 );
				}
				else if( movePlayer2.equalsIgnoreCase( Globals.moves.Down.toString() )
						&& gs.getMoveListPlayer2().get( currentMove - 1 ).equalsIgnoreCase( Globals.moves.Up.toString() ) )
				{
					movePlayer2 = Globals.moves.Up.toString();
					gs.getMoveListPlayer2().put( currentMove, movePlayer2 );
				}
				else if( movePlayer2.equalsIgnoreCase( Globals.moves.Left.toString() )
						&& gs.getMoveListPlayer2().get( currentMove - 1 ).equalsIgnoreCase( Globals.moves.Right.toString() ) )
				{
					movePlayer2 = Globals.moves.Right.toString();
					gs.getMoveListPlayer2().put( currentMove, movePlayer2 );
				}
				else if( movePlayer2.equalsIgnoreCase( Globals.moves.Right.toString() )
						&& gs.getMoveListPlayer2().get( currentMove - 1 ).equalsIgnoreCase( Globals.moves.Left.toString() ) )
				{
					movePlayer2 = Globals.moves.Left.toString();
					gs.getMoveListPlayer2().put( currentMove, movePlayer2 );
				}
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
			System.out.println( "Hereeeeeeeee 2 " + player1loses + " : " + player2loses );
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
					x1--;
				}
				else if( movePlayer1.equalsIgnoreCase( Globals.moves.Down.toString() ) )
				{
					x1++;
				}
				else if( movePlayer1.equalsIgnoreCase( Globals.moves.Left.toString() ) )
				{
					y1--;
				}
				else if( movePlayer1.equalsIgnoreCase( Globals.moves.Right.toString() ) )
				{
					y1++;
				}

				if( movePlayer2.equalsIgnoreCase( Globals.moves.Up.toString() ) )
				{
					x2--;
				}
				else if( movePlayer2.equalsIgnoreCase( Globals.moves.Down.toString() ) )
				{
					x2++;
				}
				else if( movePlayer2.equalsIgnoreCase( Globals.moves.Left.toString() ) )
				{
					y2--;
				}
				else if( movePlayer2.equalsIgnoreCase( Globals.moves.Right.toString() ) )
				{
					y2++;
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
			gs.setCurrMove( currentMove + 1 );
			gs.setCurrentMovePlayer1( movePlayer1.toUpperCase() );
			gs.setCurrentMovePlayer2( movePlayer2.toUpperCase() );
			gs.setMoveOver( true );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	private boolean validateMove( String movePlayer, int x, int y )
	{
		try
		{
			if( ( movePlayer.equalsIgnoreCase( Globals.moves.Up.toString() ) && x - 1 < 0 ) || ( movePlayer.equalsIgnoreCase( Globals.moves.Down.toString() ) && x + 1 > 15 )
					|| ( movePlayer.equalsIgnoreCase( Globals.moves.Left.toString() ) && y - 1 < 0 )
					|| ( movePlayer.equalsIgnoreCase( Globals.moves.Right.toString() ) && y + 1 > 15 ) )
			{
				return true;
			}
			if( movePlayer.equalsIgnoreCase( Globals.moves.Up.toString() ) && ( gs.getGameState( x - 1, y ) == Globals.wallCell
					|| gs.getGameState( x - 1, y ) == Globals.currPositionPlayer1 || gs.getGameState( x - 1, y ) == Globals.currPositionPlayer2 ) )
			{
				return true;
			}
			if( movePlayer.equalsIgnoreCase( Globals.moves.Down.toString() ) && ( gs.getGameState( x + 1, y ) == Globals.wallCell
					|| gs.getGameState( x + 1, y ) == Globals.currPositionPlayer1 || gs.getGameState( x + 1, y ) == Globals.currPositionPlayer2 ) )
			{
				return true;
			}
			if( movePlayer.equalsIgnoreCase( Globals.moves.Left.toString() ) && ( gs.getGameState( x, y - 1 ) == Globals.wallCell
					|| gs.getGameState( x, y - 1 ) == Globals.currPositionPlayer1 || gs.getGameState( x, y - 1 ) == Globals.currPositionPlayer2 ) )
			{
				return true;
			}
			if( movePlayer.equalsIgnoreCase( Globals.moves.Right.toString() ) && ( gs.getGameState( x, y + 1 ) == Globals.wallCell
					|| gs.getGameState( x, y + 1 ) == Globals.currPositionPlayer1 || gs.getGameState( x, y + 1 ) == Globals.currPositionPlayer2 ) )
			{
				return true;
			}
			return false;
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return false;
		}
	}

	private void sendGameStateToPlayers( int currMove )
	{
		// TODO Auto-generated method stub

	}

	@RequestMapping( value = "/getCurrentGameState", method = RequestMethod.POST )
	public void getCurrentGameState( HttpServletRequest request, HttpServletResponse response, ModelMap model ) throws ServletException, IOException
	{
		try
		{
			// TODO: Check if this works for you
			/* JSONObject jo = new JSONObject(); */
			HashMap< String, Object > playerProps = new HashMap< String, Object >();
			/*
			 * jo.put( "player1currentmove",
			 * gs.getCurrentMovePlayer1() ); jo.put(
			 * "player2currentmove", gs.getCurrentMovePlayer2() );
			 * jo.put( "isMoveOver", gs.isMoveOver() ); jo.put(
			 * "isGameOver", gs.isGameOver() );
			 */
			playerProps.put( "player1currentmove", gs.getCurrentMovePlayer1() );
			playerProps.put( "player2currentmove", gs.getCurrentMovePlayer2() );
			playerProps.put( "isMoveOver", gs.isMoveOver() );
			playerProps.put( "isGameOver", gs.isGameOver() );
			if( gs.isGameOver() )
			{
				playerProps.put( "winner", gs.getWinner() );
			}
			if( gs.isMoveOver() )
			{
				gs.setMoveOver( false );
			}
			/*
			 * StringWriter out = new StringWriter();
			 * JSONValue.writeJSONString( jo, out ); String jsonText
			 * = out.toString();
			 */
			response.setContentType( "text/plain" );
			response.setHeader( "Content-Type", "application/x-www-form-urlencoded" );
			response.setHeader( "Cache-Control", "no-cache" );
			response.setHeader( "Pragma", "no-cache" );
			/* response.getWriter().write( jsonText ); */
			response.getWriter().write( objectMapper.writeValueAsString( playerProps ) );
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	@RequestMapping( value = "/clearGameState", method = RequestMethod.POST )
	public void clearGameState( HttpServletRequest request, HttpServletResponse response, ModelMap model ) throws ServletException, IOException
	{
		player1 = null;
		player2 = null;
		gs = null;
		response.setContentType( "text/plain" );
		response.setHeader( "Content-Type", "application/x-www-form-urlencoded" );
		response.setHeader( "Cache-Control", "no-cache" );
		response.setHeader( "Pragma", "no-cache" );
	}
}