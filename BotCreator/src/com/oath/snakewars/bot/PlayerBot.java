package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;
import java.util.ArrayList;

public class PlayerBot implements Bot
{
  /*
   * This is where all the magic happens. Implement the method to make your bot smarter.
   */
  @Override
  public MoveType makeMove(BotState botState)
  {
    ArrayList<GameTreeNode> bfs1 = new ArrayList<GameTreeNode>();
    ArrayList<GameTreeNode> bfs2 = new ArrayList<GameTreeNode>();
    ArrayList<GameTreeNode> bfs3 = new ArrayList<GameTreeNode>();
    GameBoardState board = botState.getBoard();
    int [][] playerBoard = new int[16][16];
    for(int i=0; i<16; i++) {
      for(int j=0; j<16; j++) {
        playerBoard[i][j] = board.getCellContent(new Cell(i, j));
      }
    }
    GameTreeNode g0 = new GameTreeNode();
    for(int i=0; i<16; i++) {
      for(int j=0; j<16; j++) {
        g0.setBoard(i, j, playerBoard[i][j]);
      }
    }
    int i1=0;
    int j1=0;
    for(int i=0; i<16; i++) {
      for(int j=0; j<16; j++) {
        if(g0.getBoard(i, j) == 2) {
          i1=i;
          j1=j;
        }
      }
    }
    GameTreeNode g1 = g0.clone();
    boolean isValid = validateMove(g1, i1 - 1, j1);
    if (isValid) {
      g1.setBoard(i1, j1, 1);
      g1.setBoard(i1 - 1, j1, 2);
      g1.setMove(0);
      bfs1.add(g1);
    }
    GameTreeNode g2 = g0.clone();
    isValid = validateMove(g2, i1 + 1, j1);
    if (isValid) {
      g2.setBoard(i1, j1, 1);
      g2.setBoard(i1 + 1, j1, 2);
      g2.setMove(1);
      bfs1.add(g2);
    }
    GameTreeNode g3 = g0.clone();
    isValid = validateMove(g3, i1, j1 - 1);
    if (isValid) {
      g3.setBoard(i1, j1, 1);
      g3.setBoard(i1, j1 - 1, 2);
      g3.setMove(2);
      bfs1.add(g3);
    }
    GameTreeNode g4 = g0.clone();
    isValid = validateMove(g4, i1, j1 + 1);
    if (isValid) {
      g4.setBoard(i1, j1, 1);
      g4.setBoard(i1, j1 + 1, 2);
      g4.setMove(3);
      bfs1.add(g4);
    }

    for(int i=0; i<bfs1.size();i++){
      GameTreeNode gt = bfs1.get(i);
      for(int i=0; i<16; i++) {
        for(int j=0; j<16; j++) {
          if(gt.getBoard(i, j) == 3) {
            i1=i;
            j1=j;
          }
        }
      }
      GameTreeNode gt1 = gt.clone();
      isValid = validateMove(gt1, i1 - 1, j1);
      if (isValid) {
        gt1.setBoard(i1, j1, 1);
        gt1.setBoard(i1 - 1, j1, 3);
        gt1.setMove(0);
      } else {
        gt1.setScore(100);
      }
      bfs2.add(gt1);
      GameTreeNode gt2 = gt.clone();
      isValid = validateMove(gt2, i1 + 1, j1);
      if (isValid) {
        gt2.setBoard(i1, j1, 1);
        gt2.setBoard(i1 + 1, j1, 3);
        gt2.setMove(1);
      } else {
        gt2.setScore(100);
      }
      bfs2.add(gt2);
      GameTreeNode gt3 = gt.clone();
      isValid = validateMove(gt3, i1, j1 - 1);
      if (isValid) {
        gt3.setBoard(i1, j1, 1);
        gt3.setBoard(i1, j1 - 1, 3);
        gt3.setMove(2);
      } else {
        gt3.setScore(100);
      }
      bfs2.add(gt3);
      GameTreeNode gt4 = gt.clone();
      isValid = validateMove(gt4, i1, j1 + 1);
      if (isValid) {
        gt4.setBoard(i1, j1, 1);
        gt4.setBoard(i1, j1 + 1, 3);
        gt4.setMove(3);
      } else {
        gt4.setScore(100);
      }
      bfs2.add(gt4);
    }

    for(int i=0; i<bfs2.size();i++){
      GameTreeNode gt = bfs2.get(i);
      for(int i=0; i<16; i++) {
        for(int j=0; j<16; j++) {
          if(gt.getBoard(i, j) == 2) {
            i1=i;
            j1=j;
          }
        }
      }
      GameTreeNode gt1 = gt.clone();
      isValid = validateMove(gt1, i1 - 1, j1);
      if (isValid) {
        gt1.setBoard(i1, j1, 1);
        gt1.setBoard(i1 - 1, j1, 2);
        gt1.setMove(0);
        bfs3.add(gt1);
      }
      GameTreeNode gt2 = gt.clone();
      isValid = validateMove(gt2, i1 + 1, j1);
      if (isValid) {
        gt2.setBoard(i1, j1, 1);
        gt2.setBoard(i1 + 1, j1, 2);
        gt2.setMove(1);
        bfs3.add(gt2);
      }
      GameTreeNode gt3 = gt.clone();
      isValid = validateMove(gt3, i1, j1 - 1);
      if (isValid) {
        gt3.setBoard(i1, j1, 1);
        gt3.setBoard(i1, j1 - 1, 2);
        gt3.setMove(2);
        bfs3.add(gt3);
      }
      GameTreeNode gt4 = gt.clone();
      isValid = validateMove(gt1, i1, j1 + 1);
      if (isValid) {
        gt4.setBoard(i1, j1, 1);
        gt4.setBoard(i1, j1 + 1, 2);
        gt4.setMove(3);
        bfs3.add(gt4);
      }
    }

    for(int i = 0;i < bfs3.size(); i++) {
      GameTreeNode gt = bfs3.get(i);
      for(int i=0; i<16; i++) {
        for(int j=0; j<16; j++) {
          if(gt.getBoard(i, j) == 2) {
            i1=i;
            j1=j;
          }
        }
      }
      int score = 25;
      if (i1+1 < 16 && (gt.getBoard(i1+1, j1) == 1 || gt.getBoard(i1+1, j1) == 3)) {
        score = score - 5;
      }
      if (i1-1 > -1 && (gt.getBoard(i1-1, j1) == 1 || gt.getBoard(i1-1, j1) == 3)) {
        score = score - 5;
      }
      if (j1+1 < 16 && (gt.getBoard(i1, j1+1) == 1 || gt.getBoard(i1, j1+1) == 3)) {
        score = score - 5;
      }
      if (j1-1 > -1 && (gt.getBoard(i1, j1-1) == 1 || gt.getBoard(i1, j1-1) == 3)) {
        score = score - 5;
      }
      if(i1 < 0 || i1 > 15 || j1 < 0 || j1 > 15) {
        score = score - 5;
      }
      gt.setScore(score);
    }
    return MoveType.LEFT;
  }

  public boolean validateMove(GameTreeNode g, int x, int y) {
    if (x < 0 || x > 15 || y < 0 || y > 15) {
      return false;
    }
    int pos = g.getBoard(x, y);
    if (pos == 1 || pos == 2 || pos == 3) {
      return false;
    }
  }
}
