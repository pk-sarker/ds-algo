package com.ds.practice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Let's play the minesweeper game (Wikipedia, online game)!
 *
 * You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine,
 * 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has
 * no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents
 * how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.
 *
 * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'),
 * return the board after revealing this position according to the following rules:
 *
 * - If a mine ('M') is revealed, then the game is over - change it to 'X'.
 * - If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and
 *   all of its adjacent unrevealed squares should be revealed recursively.
 * - If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8')
 *   representing the number of adjacent mines.
 * - Return the board when no more squares will be revealed.
 *
 * Input:
 *
 * [['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'M', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E']]
 * Click : [3,0]
 *
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 *
 *  Input:
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 * Click : [1,2]
 *
 * Output:
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'X', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 */
public class Minesweeper {
    int[][] directions = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public char[][] updateBoard(char[][] board, int[] click) {

        int nr = board.length;
        int nc = board[0].length;

        int cr = click[0];
        int cl = click[1];

        if (board[cr][cl] == 'M') {
            board[cr][cl] = 'X';
            return board;
        }

        if (board[cr][cl] == 'E') {
            int mines = countMines(board, cr, cl);
            if (mines > 0) {
                board[cr][cl] = (char) ('0' + mines);
                return board;
            }

            board[cr][cl] = 'B';

            Queue<Integer> q = new LinkedList<>();
            q.offer(cr * nc + cl);
            while (!q.isEmpty()) {
                int tmp = q.poll();
                for (int[] dir : directions) {
                    int x = tmp / nc + dir[0];
                    int y = tmp % nc + dir[1];
                    if (x >= 0 && x < nr && y >= 0 && y < nc && (board[x][y] == 'E')) {
                        mines = countMines(board, x, y);
                        if (mines > 0) {
                            board[x][y] = (char) ('0' + mines);
                        } else {
                            board[x][y] = 'B';
                            q.offer(x * nc + y);
                        }
                    }
                }
            }
        }
        return board;
    }

    public int countMines(char[][] board, int row, int col) {
        int count = 0;
        for(int[] dir : directions) {
            int x = row+dir[0], y = col+dir[1];
            if ( x >=0 && x < board.length && y >= 0 && y < board[0].length && (board[x][y] == 'M' || board[x][y] == 'X')) {
                count++;
            }
        }
        return count;
    }
    public static void main(String args[]) {
        Minesweeper ob = new Minesweeper();
        char[][] res = ob.updateBoard(new char[][]{{'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}}, new int[]{3,0});

        System.out.println(Arrays.deepToString(res));
        // [[B, 1, E, 1, B],
        //  [B, 1, M, 1, B],
        //  [B, 1, 1, 1, B],
        //  [B, B, B, B, B]]

        //[["B","1","E","1","B"],
        // ["B","1","M","1","B"],
        // ["B","1","1","1","B"],
        // ["B","B","B","B","B"]]

        // [["E","E","E","E","E"],
        // ["E","E","M","E","E"],
        // ["E","1","1","1","B"],
        // ["B","B","B","B","B"]]
    }
}
