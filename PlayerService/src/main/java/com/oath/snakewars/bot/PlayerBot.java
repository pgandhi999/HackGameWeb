package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.Cell;
import com.oath.common.snakewars.board.MoveType;
import com.oath.common.snakewars.settings.GameBoardState;

import java.util.ArrayList;

public class PlayerBot implements Bot
{
  /*
   * This is where all the magic happens. Implement the method to make your bot smarter.
   */
  GameBoardState board = null;
  public MoveType makeMove(BotState botState) throws Exception
  {
    ArrayList<GameTreeNode> bfs1 = new ArrayList<GameTreeNode>();
    ArrayList<GameTreeNode> bfs2 = new ArrayList<GameTreeNode>();
    ArrayList<GameTreeNode> bfs3 = new ArrayList<GameTreeNode>();
    board = botState.getBoard();
    int [][] playerBoard = new int[board.getBoardWidth()][board.getBoardHeight()];
    for(int i=0; i<board.getBoardWidth(); i++) {
      for(int j=0; j<board.getBoardHeight(); j++) {
        playerBoard[i][j] = board.getCellContent(i, j);
      }
    }
    GameTreeNode g0 = new GameTreeNode();
    for(int i=0; i<board.getBoardWidth(); i++) {
      for(int j=0; j<board.getBoardHeight(); j++) {
        g0.setBoard(i, j, playerBoard[i][j]);
        if(playerBoard[i][j]==3){
          System.out.println("hereeeeee ENEMYYYYYYYYY" +i+" "+j);
        }
      }
    }
    int i1=0;
    int j1=0;
    for(int i=0; i<board.getBoardWidth(); i++) {
      for(int j=0; j<board.getBoardHeight(); j++) {
        if(g0.getBoard(i, j) == 2) {
          i1=i;
          j1=j;
        }
      }
    }
    System.out.println("Hereeeeeeeeee SELFFFF$$$$$$$$$$$$$$$$$$ "+i1 + " : " +j1);
    ArrayList<GameTreeNode> children0 = new ArrayList<GameTreeNode>();
    GameTreeNode g1 = (GameTreeNode) g0.clone();
    boolean isValid = validateMove(g1, i1 - 1, j1);
    System.out.println("hereeeee 3 "+isValid);
    if (isValid) {
      g1.setBoard(i1, j1, 1);
      g1.setBoard(i1 - 1, j1, 2);
      g1.setMove(0);
      bfs1.add(g1);
      children0.add(g1);
    }
    GameTreeNode g2 = (GameTreeNode) g0.clone();
    isValid = validateMove(g2, i1 + 1, j1);
    System.out.println("hereeeee 2 "+isValid);
    if (isValid) {
      g2.setBoard(i1, j1, 1);
      g2.setBoard(i1 + 1, j1, 2);
      g2.setMove(1);
      bfs1.add(g2);
      children0.add(g2);
    }
    GameTreeNode g3 = (GameTreeNode)g0.clone();
    isValid = validateMove(g3, i1, j1 - 1);
    System.out.println("hereeeee 4 "+isValid);
    if (isValid) {
      g3.setBoard(i1, j1, 1);
      g3.setBoard(i1, j1 - 1, 2);
      g3.setMove(2);
      bfs1.add(g3);
      children0.add(g3);
    }
    GameTreeNode g4 = (GameTreeNode)g0.clone();
    isValid = validateMove(g4, i1, j1 + 1);
    System.out.println("hereeeee 5 "+isValid);
    if (isValid) {
      g4.setBoard(i1, j1, 1);
      g4.setBoard(i1, j1 + 1, 2);
      g4.setMove(3);
      bfs1.add(g4);
      children0.add(g4);
    }
    g0.setChildren(children0);

    for(int i=0; i<bfs1.size();i++){
      GameTreeNode gt = bfs1.get(i);
      for(int i2=0; i2<board.getBoardWidth(); i2++) {
        for(int j=0; j<board.getBoardHeight(); j++) {
          if(gt.getBoard(i2, j) == 3) {
            i1=i2;
            j1=j;
          }
        }
      }
      ArrayList<GameTreeNode> children = new ArrayList<GameTreeNode>();
      GameTreeNode gt1 = (GameTreeNode)gt.clone();
      isValid = validateMove(gt1, i1 - 1, j1);
      if (isValid) {
        gt1.setBoard(i1, j1, 1);
        gt1.setBoard(i1 - 1, j1, 3);
        gt1.setMove(0);
      } else {
        gt1.setScore(100);
      }
      bfs2.add(gt1);
      children.add(gt1);
      GameTreeNode gt2 = (GameTreeNode)gt.clone();
      isValid = validateMove(gt2, i1 + 1, j1);
      if (isValid) {
        gt2.setBoard(i1, j1, 1);
        gt2.setBoard(i1 + 1, j1, 3);
        gt2.setMove(1);
      } else {
        gt2.setScore(100);
      }
      bfs2.add(gt2);
      children.add(gt2);
      GameTreeNode gt3 = (GameTreeNode)gt.clone();
      isValid = validateMove(gt3, i1, j1 - 1);
      if (isValid) {
        gt3.setBoard(i1, j1, 1);
        gt3.setBoard(i1, j1 - 1, 3);
        gt3.setMove(2);
      } else {
        gt3.setScore(100);
      }
      bfs2.add(gt3);
      children.add(gt3);
      GameTreeNode gt4 = (GameTreeNode)gt.clone();
      isValid = validateMove(gt4, i1, j1 + 1);
      if (isValid) {
        gt4.setBoard(i1, j1, 1);
        gt4.setBoard(i1, j1 + 1, 3);
        gt4.setMove(3);
      } else {
        gt4.setScore(100);
      }
      bfs2.add(gt4);
      children.add(gt4);
      gt.setChildren(children);
    }

    for(int i=0; i<bfs2.size();i++){
      GameTreeNode gt = bfs2.get(i);
      for(int i2=0; i2<board.getBoardWidth(); i2++) {
        for(int j=0; j<board.getBoardHeight(); j++) {
          if(gt.getBoard(i2, j) == 2) {
            i1=i2;
            j1=j;
          }
        }
      }
      ArrayList<GameTreeNode> children1 = new ArrayList<GameTreeNode>();
      GameTreeNode gt1 = (GameTreeNode)gt.clone();
      isValid = validateMove(gt1, i1 - 1, j1);
      if (isValid) {
        gt1.setBoard(i1, j1, 1);
        gt1.setBoard(i1 - 1, j1, 2);
        gt1.setMove(0);
        bfs3.add(gt1);
        children1.add(gt1);
      }
      GameTreeNode gt2 = (GameTreeNode)gt.clone();
      isValid = validateMove(gt2, i1 + 1, j1);
      if (isValid) {
        gt2.setBoard(i1, j1, 1);
        gt2.setBoard(i1 + 1, j1, 2);
        gt2.setMove(1);
        bfs3.add(gt2);
        children1.add(gt2);
      }
      GameTreeNode gt3 = (GameTreeNode)gt.clone();
      isValid = validateMove(gt3, i1, j1 - 1);
      if (isValid) {
        gt3.setBoard(i1, j1, 1);
        gt3.setBoard(i1, j1 - 1, 2);
        gt3.setMove(2);
        bfs3.add(gt3);
        children1.add(gt3);
      }
      GameTreeNode gt4 = (GameTreeNode)gt.clone();
      isValid = validateMove(gt1, i1, j1 + 1);
      if (isValid) {
        gt4.setBoard(i1, j1, 1);
        gt4.setBoard(i1, j1 + 1, 2);
        gt4.setMove(3);
        bfs3.add(gt4);
        children1.add(gt4);
      }
      gt.setChildren(children1);
    }

    for(int i = 0;i < bfs3.size(); i++) {
      GameTreeNode gt = bfs3.get(i);
      for(int i2=0; i2<board.getBoardWidth(); i2++) {
        for(int j=0; j<board.getBoardHeight(); j++) {
          if(gt.getBoard(i2, j) == 2) {
            i1=i2;
            j1=j;
          }
        }
      }
      int score = 25;
      if (i1+1 < board.getBoardWidth() && (gt.getBoard(i1+1, j1) == 1 || gt.getBoard(i1+1, j1) == 3)) {
        score = score - 5;
      }
      if (i1-1 > -1 && (gt.getBoard(i1-1, j1) == 1 || gt.getBoard(i1-1, j1) == 3)) {
        score = score - 5;
      }
      if (j1+1 < board.getBoardWidth() && (gt.getBoard(i1, j1+1) == 1 || gt.getBoard(i1, j1+1) == 3)) {
        score = score - 5;
      }
      if (j1-1 > -1 && (gt.getBoard(i1, j1-1) == 1 || gt.getBoard(i1, j1-1) == 3)) {
        score = score - 5;
      }
      if(i1 < 0 || i1 > board.getBoardWidth() - 1 || j1 < 0 || j1 > board.getBoardWidth() - 1) {
        score = score - 5;
      }
      gt.setScore(score);
    }

    for(int i = 0;i < bfs2.size(); i++) {
      GameTreeNode gt = bfs2.get(i);
      int maxScore = 0;
      for(int j=0;j<gt.getChildren().size();j++) {
        if (gt.getChildren().get(j).getScore() > maxScore) {
          maxScore = gt.getChildren().get(j).getScore();
        }
      }
      gt.setScore(maxScore);
    }

    for(int i = 0;i < bfs1.size(); i++) {
      GameTreeNode gt = bfs1.get(i);
      int minScore = 200;
      for(int j=0;j<gt.getChildren().size();j++) {
        if (gt.getChildren().get(j).getScore() < minScore) {
          minScore = gt.getChildren().get(j).getScore();
        }
      }
      gt.setScore(minScore);
    }

    int maxScore = -1;
    int move = 0;
    for(int j=0;j<g0.getChildren().size();j++) {
      System.out.println("hereeeee 66 "+g0.getChildren().get(j).getScore());
      if (g0.getChildren().get(j).getScore() > maxScore) {
        System.out.println("hereeeee "+move);
        maxScore = g0.getChildren().get(j).getScore();
        move = g0.getChildren().get(j).getMove();
      }
    }
    g0.setScore(maxScore);
    MoveType mt = MoveType.PASS;
    if (move == 0) {
      mt = MoveType.UP;
    } else if (move == 1) {
      mt = MoveType.DOWN;
    } else if (move == 2) {
      mt = MoveType.LEFT;
    } else if (move == 3) {
      mt = MoveType.RIGHT;
    }
    return mt;
  }

  public boolean validateMove(GameTreeNode g, int x, int y) {
    //System.out.println("Hereeeeeee VALIDATE "+x+" "+y);
    if (x < 0 || x > board.getBoardWidth() - 1 || y < 0 || y > board.getBoardWidth() - 1) {
      return false;
    }
    int pos = g.getBoard(x, y);
    //System.out.println("Hereeeeeee VALIDATE 2 "+pos);
    if (pos == 1 || pos == 2 || pos == 3) {
      return false;
    }
    return true;
  }
}
