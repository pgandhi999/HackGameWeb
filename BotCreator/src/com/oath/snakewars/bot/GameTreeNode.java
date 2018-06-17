package com.oath.snakewars.bot;

import com.oath.common.snakewars.board.MoveType;

public class GameTreeNode {
    private int [][] board = new int[16][16];
    private int score = -1;
    private int move = -1;

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
}