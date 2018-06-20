package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;
import java.util.ArrayList;

public class GameTreeNode implements Cloneable {
    private int [][] board = new int[16][16];
    private int score = -1;
    private int move = -1;
    private ArrayList<GameTreeNode> children = new ArrayList<GameTreeNode>();

    public ArrayList<GameTreeNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<GameTreeNode> children) {
        this.children = children;
    }

    public int getBoard(int x, int y) {
        return board[x][y];
    }

    public void setBoard(int x, int y, int z) {
        this.board[x][y] = z;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMove() {
        return move;
    }

    public void setMove(int move) {
        this.move = move;
    }

    public Object clone() {
        Object c = null;
        try {
            c = super.clone();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}