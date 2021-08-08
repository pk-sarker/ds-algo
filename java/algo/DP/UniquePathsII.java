package algo.DP;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'R' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach
 * the bottom-right corner of the grid (marked 'D' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids, marked O. How many unique paths would there be?
 *
 * An obstacle and space is marked as 1 and 0 respectively in the grid.
 * |---------------------
 * |  R   |      |      |
 * |---------------------
 * |      |  O   |      |
 * |---------------------
 * |      |      |  D   |
 * |---------------------
 *
 * Solution:
 * Same as Unique path without obstacles, only difference is in initializing the first row and first column.
 *
 * The point is if there is a obstacle in first row at column c, then we can't reach all other column in the first row
 * after c because there were only one path to reach all the columns in first row. So in the initialization
 * if we find a obstacle in first row at col c, then we set obstacle to oll the columns right of c.
 *
 * Similarly we do the same when we initialize the first column.
 */
public class UniquePathsII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        // first find the start corner
        // obstracle at the top left corner
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        obstacleGrid[0][0] = 1;
        // [0,0,0]   [0,0,0]
        // [1,0,0]   [1,0,0]
        // [0,0,0]   [1,0,0]
        //           [0,0,0]
        for (int row = 1; row < m; row++) {
            if (obstacleGrid[row][0] == 0 && obstacleGrid[row-1][0] == 1) {
                obstacleGrid[row][0] = 1;
            } else {
                obstacleGrid[row][0] = 0;
            }
        }

        // [0,1,0]   [0,1,1,0]
        // [0,0,0]   [0,0,0,0]
        // [0,0,0]   [0,0,0,0]
        for (int col = 1; col < n; col++) {
            if (obstacleGrid[0][col] == 0 && obstacleGrid[0][col-1] == 1) {
                obstacleGrid[0][col] = 1;
            } else {
                obstacleGrid[0][col] = 0;
            }
        }

        for (int row = 1; row < m; row++) {
            for(int col = 1; col< n; col++) {
                if (obstacleGrid[row][col] == 1) {
                    obstacleGrid[row][col] = 0;
                } else {
                    obstacleGrid[row][col] = obstacleGrid[row-1][col] + obstacleGrid[row][col-1];
                }
            }
        }
        return obstacleGrid[m-1][n-1];
    }
    public static void main(String args[]) {
        UniquePathsII obj = new UniquePathsII();

        System.out.println(obj.uniquePathsWithObstacles(new int[][]{
                {0,0,0},{0,1,0},{0,0,0}
        }));

        System.out.println(obj.uniquePathsWithObstacles(new int[][]{
                {0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,1,0,0},
                {1,0,0,0,0},
                {0,0,0,0,0}
        }));
    }
}
