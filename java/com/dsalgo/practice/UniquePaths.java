package com.dsalgo.practice;

import java.util.Arrays;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to
 * reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 *
 * Input: m = 7, n = 3
 * Output: 28
 *
 * Input: m = 3, n = 3
 * Output: 6
 *
 * Solution:
 * There is only one way to reach the right corner if there is only one row or column.
 *
 * If there are more than 1 row and 1 col, m> 1 & n> 1:
 *  - as the robot can move only right and down to reach some point at diagonal corner
 *      we need to consider
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m== 1 || n == 1) {
            return 1;
        }
        return uniquePaths(m-1, n) + uniquePaths(m, n-1);
    }


    public int uniquePathsDP(int m, int n) {
        if (m== 1 || n == 1) {
            return 1;
        }
        int[][] dp = new int[m][n];
        for(int row=0;row<m;row++) {
            Arrays.fill(dp[row], 1);
        }
        dp[0][0] = 1;

        for(int row=1;row<m;row++) {
            for(int col=1;col<n;col++) {
                dp[row][col] = dp[row-1][col] + dp[row][col-1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String args[]) {
        UniquePaths obj = new UniquePaths();
        System.out.println("m=3, n=2 \n"+obj.uniquePaths(3,2));

        System.out.println("m=7, n=3 \n"+obj.uniquePaths(7,3));

        System.out.println("\nDP\nm=7, n=3 \n"+obj.uniquePathsDP(7,3));
    }
}
