package com.ds.practice;

import java.util.*;

/**
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
 *
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved.
 * If it is impossible for the state of the board to be solved, return -1.
 *
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 *
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 *
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 *
 * Input: board = [[3,2,4],[1,5,0]]
 * Output: 14
 */

public class SlidingPuzzle {
    class Node {
        int[][] board;
        String boardStr;
        int pos_0_row;
        int pos_0_col;
        int depth;
        Node(int[][] b, int row, int col, int depth) {
            this.board = b;
            this.boardStr = Arrays.deepToString(board);
            this.pos_0_row = row;
            this.pos_0_col = col;
            this.depth = depth;
        }
    }

    public int slidingPuzzle(int[][] board) {
        int nRow = board.length, nCol = board[0].length;
        int ri = 0, ci = 0;
        search:
        for (ri = 0; ri < nRow; ri++) {
            for (ci = 0; ci < nCol; ci++) {
                if (board[ri][ci] == 0) {
                    break search;
                }
            }
        }

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<Node> queue = new ArrayDeque();
        Node start = new Node(board, ri, ci, 0);
        queue.add(start);

        Set<String> seen = new HashSet();
        seen.add(start.boardStr);

        String target = Arrays.deepToString(new int[][]{{1,2,3}, {4,5,0}});
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.boardStr.equals(target)) {
                return node.depth;
            }
            for (int[] di: directions) {
                int nei_r = di[0] + node.pos_0_row;
                int nei_c = di[1] + node.pos_0_col;
                // if move goes out of the board
                if ((Math.abs(nei_r - node.pos_0_row) + Math.abs(nei_c - node.pos_0_col) != 1) ||
                        nei_r < 0 || nei_r >= nRow || nei_c < 0 || nei_c >= nCol)
                    continue;

                int[][] newboard = new int[nRow][nCol];
                int t = 0;
                for (int[] row: node.board) {
                    newboard[t++] = row.clone();
                }

                newboard[node.pos_0_row][node.pos_0_col] = newboard[nei_r][nei_c];
                newboard[nei_r][nei_c] = 0;

                Node nei = new Node(newboard, nei_r, nei_c, node.depth+1);

                if (seen.contains(nei.boardStr))
                    continue;
                queue.add(nei);
                seen.add(nei.boardStr);
            }
        }

        return -1;
    }
    public static void main(String args[]) {
        SlidingPuzzle ob  = new SlidingPuzzle();
        System.out.println("\nInput: [[4,1,2],[5,0,3]]\nOutput: " + ob.slidingPuzzle(new int[][]{{4,1,2},{5,0,3}}));
        System.out.println("\nInput: [[1,2,3],[4,0,5]]\nOutput: " + ob.slidingPuzzle(new int[][]{{1,2,3},{4,0,5}}));
        System.out.println("\nInput: [[1,2,3],[5,4,0]]\nOutput: " + ob.slidingPuzzle(new int[][]{{1,2,3},{5,4,0}}));

    }
}
