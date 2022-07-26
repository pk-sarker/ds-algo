package algo.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle).
 * You can move up, down, left, or right from and to an empty cell in one step.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to
 * the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles.
 * If it is not possible to find such walk return -1.
 *
 * Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
 * Output: 6
 *
 * Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
 * Output: -1
 */
public class ShortestPathInGridWithObstaclesElimination {

    public static int shortestPath(int[][] grid, int k) {
        int nRows = grid.length, nCols = grid[0].length;
        int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        boolean[][][] visited = new boolean[nRows][nCols][k+1];
        int minSteps = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0}); // row, column, obstacles eliminated
        visited[0][0][0] = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int[] cell = queue.poll();
                int row = cell[0], col = cell[1], eliminatedObstacles = cell[2];

                if (row == nRows-1 && col == nCols-1) {
                    return minSteps;
                }


                for(int[] dir : dirs) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];
                    int nextK = eliminatedObstacles;

                    if (nextRow>=0 && nextRow < nRows && nextCol >=0 && nextCol < nCols) {
                        if (grid[nextRow][nextCol] == 1) {
                            nextK++;
                        }
                        if (nextK <= k && !visited[nextRow][nextCol][nextK]) {
                            visited[nextRow][nextCol][nextK]= true;
                            queue.add(new int[]{nextRow, nextCol, nextK});
                        }
                    }
                }
            }
            minSteps++;
        }
        return -1;
    }

    public static void main(String args[]) {
        int[][] grid = new int[][]{{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
        System.out.println("Input: Grid: [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1 \nOutput: " + ShortestPathInGridWithObstaclesElimination.shortestPath(grid,1));

        int[][] grid2 = new int[][]{{0,1,1},{1,1,1},{1,0,0}};
        System.out.println("Input: Grid: [[0,1,1],[1,1,1],[1,0,0]], k = 1 \nOutput: " + ShortestPathInGridWithObstaclesElimination.shortestPath(grid2,1));

    }
}
