package algo.BFS;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:
 *
 *  - 0 represents an empty cell,
 *  - 1 represents an obstacle that may be removed.
 * You can move up, down, left, or right from and to an empty cell.
 *
 * Return the minimum number of obstacles to remove so you can move from the
 * upper left corner (0, 0) to the lower right corner (m - 1, n - 1).
 *
 */
public class MinimumObstacleRemovalToReachCorner {

    public static int minimumObstacles(int[][] grid) {
        int nRows = grid.length, nCols = grid[0].length;
        int count = 0;

        int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        Deque<int[]> emptyQueue = new ArrayDeque<>();
        Deque<int[]> obsQueue = new ArrayDeque<>();
        // [row, col] = cell
        emptyQueue.add(new int[]{0, 0});
        while(!emptyQueue.isEmpty() || !obsQueue.isEmpty()) {
            if (emptyQueue.isEmpty()) {
                // emptyQueue is empty means that we are at
                // a position where there are no empty cell around to
                // move on
                // So we have to remove obstacle to move one and increase the count
                //
                ++count;
                emptyQueue.addAll(obsQueue);
                obsQueue.clear();
            }
            int[] curCell = emptyQueue.poll();
            int row =  curCell[0], col = curCell[1];

            if (row == nRows-1 && col == nCols-1) {
                break;
            }

            for(int[] dir : dirs) {
                int nextRow = row + dir[0];
                int nextCol = col + dir[1];
                if (Math.min(nextRow, nextCol) >=0 && nextRow < nRows && nextCol < nCols && grid[nextRow][nextCol]>=0) {
                    // if cell contains obstacle then add that at the end of the queue
                    // otherwise, add at the beginning of the queue. This will ensure
                    // to traverse empty cells first
                    if (grid[nextRow][nextCol] == 1) {
                        obsQueue.addLast(new int[]{nextRow, nextCol});
                    } else {
                        emptyQueue.addFirst(new int[]{nextRow, nextCol});
                    }
                    grid[nextRow][nextCol] = -1;
                }
            }
        }
        return count;
    }

    public static void main(String args[]) {
        int[][] grid = new int[][]{{0,1,1},{1,1,0},{1,1,0}};
        System.out.println("Input: grid = [[0,1,1],[1,1,0],[1,1,0]]\nOutput: " + MinimumObstacleRemovalToReachCorner.minimumObstacles(grid));

        int[][] grid2 = new int[][]{{0,1,0,0,0},{0,1,0,1,0},{0,0,0,1,0}};
        System.out.println("\nInput: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]\nOutput: " + MinimumObstacleRemovalToReachCorner.minimumObstacles(grid2));
    }
}
