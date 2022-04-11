package com.practise.problemsolving.array;

import org.junit.Assert;
import org.junit.Test;


/*

Name - Game of Life
Link - https://leetcode.com/problems/game-of-life/

 */
public class GameOfLife {

    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();
        int arr[][] = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife.bruteForceApproach(arr);
    }

    private int[][] bruteForceApproach(int board[][]) {

        int row = board.length;
        int col = board[0].length;
        int[][] copy_board = new int[row][col];
        int[][] direction_arr = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1},
                {1, 0}, {1, 1}};

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int count = 0;
                for (int[] d : direction_arr) {
                    int p = i + d[0];
                    int q = j + d[1];
                    if (p >= 0 && p < row && q >= 0 && q < col && board[p][q] == 1) {
                        count++;
                    }
                }
                if (board[i][j] == 1) {
                    if (count == 2 || count == 3) {
                        copy_board[i][j] = 1;
                    }
                } else {
                    if (count == 3) {
                        copy_board[i][j] = 1;
                    }
                }
            }
        }

        // copy value to original array
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = copy_board[i][j];
            }
        }

        return copy_board;
    }

    private int[][] bestApproach(int board[][]) {

        int row = board.length;
        int col = board[0].length;
        // int[][] copy_board = new int[row][col];
        int[][] direction_arr = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1},
                {1, 0}, {1, 1}};

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int count = 0;
                for (int[] d : direction_arr) {
                    int p = i + d[0];
                    int q = j + d[1];
                    if (p >= 0 && p < row && q >= 0 && q < col && ((board[p][q] == 1) || (board[p][q] == 2))) {
                        count++;
                    }
                }
                if (board[i][j] == 1) {
                    if (count < 2 || count > 3) {
                        board[i][j] = 2;
                    }
                } else {
                    if (count == 3) {
                        board[i][j] = 3;
                    }
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] %= 2;
            }
        }
        return board;
    }

    @Test
    public void testGameOfLife() {
        GameOfLife gameOfLife = new GameOfLife();
        int arr[][] = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        int result[][] = {{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 1, 0}};
        Assert.assertArrayEquals(result, gameOfLife.bruteForceApproach(arr));
    }
}
