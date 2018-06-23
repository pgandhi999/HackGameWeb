package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.Cell;
import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.CellType;
import com.oath.common.snakewars.settings.GameBoardState;

import java.util.Scanner;

/*
 * Helper class which can be used to test your bot implementation. Generates two types of Game boards:
 * 1. Default game board: An empty 16x16 game board with the player randomly assigned to a given cell
 * 2. Custom game board: A custom game board based on the dimensions that the user provides. The cell
 *                       contents of this board needs to be explicitly provided as command line input
 *    For example: 3 3 1 2 3 1 1 0 0 0 1 will generate the following 3x3 Game board:
 *    1   2   3
 *    1   1   0
 *    0   0   1
 *
 * Once the Game board type is chosen, the game board is passed to PlayerBot#makeMove requesting a valid move
 * The MoveType returned by the PlayerBot is displayed
 */
public class BotRunner
{
  public static void main(String args[])
  {
    Scanner scan = new Scanner(System.in);
    int boardHeight, boardWidth;
    PlayerBot playerBot = new PlayerBot();
    System.out.println("Select Game board type:\n 1. Default\n 2. Custom\n Choose number and press ENTER");
    int gameTypeInput = scan.nextInt();
    if (gameTypeInput == 1) {
      System.out.println("Generating 16x16 game board...");
      GameBoardState gameBoard = new GameBoardState();
      BotRunner.displayGameBoard(gameBoard);
      BotState botState = new BotState(gameBoard);
      BotRunner.requestMove(playerBot, botState);
    } else if (gameTypeInput == 2) {
      System.out.println("Command line format\n <boardHeight> <boardWidth> {Space separated list of values}\n"
                         + "Example of a 3 by 3 Game board can be created as 3 3 1 2 3 1 1 0 0 0 1:");
      scan.nextLine();
      while (scan.hasNextLine()) {
        int playerPositionCount = 0;
        int opponentPositionCount = 0;
        String line = scan.nextLine();
        String[] commandParts = line.split(" ");
        if (commandParts.length < 3) {
          System.out.println(
              "Insufficient number of parameters. \n Format: <boardHeight> <boardWidth> {Space separated list of values}");
        }
        boardHeight = Integer.parseInt(commandParts[0]);
        boardWidth = Integer.parseInt(commandParts[1]);
        GameBoardState gameBoard = new GameBoardState(boardHeight, boardWidth);
        int x = 0;
        int y = 0;
        for (int c = 2; c < commandParts.length; c++) {
          int cellType = Integer.parseInt(commandParts[c]);
          if (!(CellType.validateCellType(cellType))) {
            System.out.println(
                "Cell type should be one of the follows: \n 0 - Empty cell "
                + "\n 1 - Occupied cell \n 2 - My current cell \n 3 - Opponent cell");
            return;
          }
          gameBoard.setCellContent(new Cell(x, y), cellType);
          if (cellType == CellType.MYCELL.getValue()) {
            playerPositionCount++;
          } else if (cellType == CellType.ENEMYCELL.getValue()) {
            opponentPositionCount++;
          }

          if (++y == boardWidth) {
            y = 0;
            x++;
          }
        }
        BotState botState = new BotState(gameBoard);
        if (playerPositionCount != 1 || opponentPositionCount != 1) {
          System.out.println("Current position of player should in a single cell "
                             + playerPositionCount
                             + " "
                             + opponentPositionCount);
          return;
        }
        BotRunner.displayGameBoard(gameBoard);
        BotRunner.requestMove(playerBot, botState);
      }
    } else {
      System.out.println("Invalid option");
    }
  }

  private static void displayGameBoard(GameBoardState gameBoard)
  {
    System.out.println("Game board is:");
    for (int i = 0; i < gameBoard.getBoardHeight(); i++) {
      System.out.print("\n");
      for (int j = 0; j < gameBoard.getBoardWidth(); j++) {
        System.out.print(gameBoard.getCellContent(new Cell(i, j)) + " ");
      }
    }
  }

  private static void requestMove(Bot playerBot, BotState botState)
  {
    System.out.println("\n Requesting your move ..");
    long startTime = System.currentTimeMillis();
    MoveType yourMove = MoveType.PASS;
    try {
      yourMove = playerBot.makeMove(botState);
    }
    catch (Exception e) {
      yourMove = MoveType.PASS;
    }
    long endTime = System.currentTimeMillis();
    long timeTaken = endTime - startTime;
    System.out.println("Your bot moved " + yourMove.name() + " in " + timeTaken + " ms.");
  }
}
