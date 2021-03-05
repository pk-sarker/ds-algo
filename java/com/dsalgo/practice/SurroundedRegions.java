package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Input: board = [
 *      ["X","X","X","X"],
 *      ["X","O","O","X"],
 *      ["X","X","O","X"],
 *      ["X","O","X","X"]]
 * Output: [
 *      ["X","X","X","X"],
 *      ["X","X","X","X"],
 *      ["X","X","X","X"],
 *      ["X","O","X","X"]]
 */
public class SurroundedRegions {

    public char[][] solve(char[][] board) {
        int nr = board.length, nc = board[0].length;

        List<int[]> borderPos = new ArrayList<>();
        for(int i=0; i<nr; i++) {
            borderPos.add(new int[]{i, 0});
            borderPos.add(new int[]{i, nc-1});
        }
        for(int i=0; i<nc; i++) {
            borderPos.add(new int[]{0, i});
            borderPos.add(new int[]{nr-1, i});
        }

        for(int i=0; i<borderPos.size(); i++) {
            int[] pos = borderPos.get(i);
            // this.dfs(board, pos[0], pos[1]);
            this.bfs(board, pos[0], pos[1]);
        }

        for(int i=0;i <nr; i++) {
            for(int j=0; j<nc; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'E') {
                    board[i][j] = 'O';
                }
            }
        }
        return board;
    }

    public void dfs(char[][] board, int row, int col) {
        if (board[row][col] != 'O') {
            return;
        }
        board[row][col] = 'E';

        if (row < board.length-1) {
            this.dfs(board, row+1, col);
        }
        if (row > 0) {
            this.dfs(board, row-1, col);
        }
        if (col < board[0].length) {
            this.dfs(board, row, col+1);
        }
        if (col > 0) {
            this.dfs(board, row, col-1);
        }
    }

    public void bfs(char[][] board, int row, int col)  {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});

        while(!queue.isEmpty()) {
            int[] pos = queue.poll();
            if (board[pos[0]][pos[1]] !=  'O') {
                continue;
            }
            board[pos[0]][pos[1]] = 'E';

            if (pos[0] < board.length - 1) {
                queue.offer(new int[]{pos[0]+1, pos[1]});
            }
            if (pos[0] > 0) {
                queue.offer(new int[]{pos[0]-1, pos[1]});
            }
            if (pos[1] < board[0].length - 1) {
                queue.offer(new int[]{pos[0], pos[1]+1});
            }
            if (pos[1] > 0) {
                queue.offer(new int[]{pos[0], pos[1]-1});
            }
        }
    }
    public static void main(String args[]) {
        SurroundedRegions ob = new SurroundedRegions();
        char[][] board = new char[][] {{'X','X','X','X'}, {'X','O','O','X'}, {'X','X','O','X'}, {'X','O','X','X'}};
        char[][]  b = ob.solve(board);
        System.out.println(Arrays.deepToString(board));
        System.out.println(Arrays.deepToString(b));
    }
}
