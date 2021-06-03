package com.dsalgo.practice;

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
            // if there is obstacle in the row above in the same column then
            // we can reach the cell just below the obstacle as the robot can only
            // move to right and down
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
            // if there is obstacle in the column left in the same row then
            // we can reach the cell just after the obstacle as the robot can only
            // move to right and down
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

        System.out.println(obj.uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}));
    }
}
