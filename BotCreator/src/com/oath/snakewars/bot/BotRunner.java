package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.Cell;
import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.CellType;
import com.oath.common.snakewars.settings.GameBoardState;

import java.util.Scanner;

public class BotRunner
{
  public static void main(String args[])
  {
    Scanner scan = new Scanner(System.in);
    int boardHeight, boardWidth;
    PlayerBot playerBot = new PlayerBot();
    System.out.println("Command line format\n <boardHeight> <boardWidth> {Space separated list of values}\n"
                       + "Example of a 3 by 3 Game board can be created as 3 3 1 2 3 1 1 0 0 0 1:");
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
      System.out.println("Game board is:");
      for (int i = 0; i < boardHeight; i++) {
        System.out.print("\n");
        for (int j = 0; j < boardWidth; j++) {
          System.out.print(gameBoard.getCellContent(new Cell(i, j)) + " ");
        }
      }
      System.out.println("\n Requesting your move ..");
      long startTime = System.currentTimeMillis();
      MoveType yourMove = playerBot.makeMove(botState);
      long endTime = System.currentTimeMillis();
      long timeTaken = endTime - startTime;
      System.out.println("Your bot moved " + yourMove.name() + " in " + timeTaken + " ms.");
    }
  }
}
