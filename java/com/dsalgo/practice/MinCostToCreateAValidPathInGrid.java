package com.dsalgo.practice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell.
 * The sign of grid[i][j] can be:
 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 * Notice that there could be some invalid signs on the cells of the grid which points
 * outside the grid.
 *
 * You will initially start at the upper left cell (0,0). A valid path in the grid is
 * a path which starts from the upper left cell (0,0) and ends at the bottom-right cell
 * (m - 1, n - 1) following the signs on the grid. The valid path doesn't have to be
 * the shortest.
 *
 * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
 *
 * Return the minimum cost to make the grid have at least one valid path.
 *
 * Solution:
 * Explore the grid starting from (0,0) position without changing any direction.
 * Maintain a DP[m][n] table to store cost to reach at any position.
 *
 * Keep the reachable nodes/position in a queue.
 * Then continue until the queue is empty:
 *  - increase cost
 *  - For each nodes/position in the queue
 *      - explore all reachable nodes
 */
public class MinCostToCreateAValidPathInGrid {
    int[][] direction = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
    int nRows = -1, nCols = -1;
    int INF = -1;

    public int minCost(int[][] grid) {
        nRows = grid.length;
        nCols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dpTable = new int[nRows][nCols];
        for(int[] dpRow: dpTable) {
            Arrays.fill(dpRow, INF);
        }

        int cost =  0;
        exploreDFS(grid, 0, 0, queue, cost, dpTable);

        while(!queue.isEmpty()) {
            // we are setting this cost in  the DP table to the
            // cell which is not explored yet.
            cost++;
            for(int i=queue.size(); i>0; i--) {
                int[] next = queue.poll();
                // try exploring all 4 direction
                for(int j=0; j<4; j++) {
                    exploreDFS(grid, next[0]+direction[j][0], next[1]+direction[j][1], queue, cost, dpTable);
                }
            }
        }

        return dpTable[nRows-1][nCols-1];
    }

    /**
     *  -- -- --
     *  i: 1
     *  -- -- --
     *  i: 2
     *  i: 1
     *  -- -- --
     *  i: 2
     *  i: 1
     *  -- -- --
     *  i: 1
     * @param grid
     * @param row
     * @param col
     * @param queue
     * @param cost
     * @param dpTable
     */

    public void exploreDFS(int[][] grid, int row, int col, Queue<int[]> queue, int cost, int[][] dpTable) {
        // explore only if the position hasn't been explored before.
        if (row < 0 || row >= nRows || col < 0 || col >= nCols || dpTable[row][col] != INF) {
            return;
        }
        dpTable[row][col] = cost;
        queue.offer(new int[]{row, col});
        // explore next in the same direction as long as possible
        // for example if [0,0]=1, means left to right, deducting 1 will
        // keep moving the same direction (0,1), row+0 = same row, col+1 = next column
        int nextMove = grid[row][col] - 1;
        exploreDFS(grid, row+direction[nextMove][0], col+direction[nextMove][1], queue, cost, dpTable);
    }

    public static void main(String args[]) {

        MinCostToCreateAValidPathInGrid ob  = new MinCostToCreateAValidPathInGrid();
        //System.out.println("\nInput: [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]] \nOutput: "+ob.minCost(new int[][]{{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}}));

        //System.out.println("\nInput: [[1,1,1,3],[3,2,2,2],[1,1,1,3],[2,2,2,2]] \nOutput: "+ob.minCost(new int[][]{{1,1,1,3},{3,2,2,2},{1,1,1,3},{2,2,2,2}}));

        System.out.println("\nInput: [[2,2,2],[2,2,2]] \nOutput: "+ob.minCost(new int[][]{{2,2,2},{2,2,2}}));

    }
}
