package algo.DP;

import java.util.Arrays;

/**
 * You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to
 * maximize the number of points you can get from the matrix.
 *
 * To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will
 * add points[r][c] to your score.
 *
 * However, you will lose points if you pick a cell too far from the cell that you picked in the
 * previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells
 * at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
 *
 * Return the maximum number of points you can achieve.
 *
 * abs(x) is defined as:
 * x for x >= 0.
 * -x for x < 0.
 *
 * Input: points = [[1,2,3],[1,5,1],[3,1,1]]
 * Output: 9
 *
 * Input: points = [[1,5],[2,3],[4,2]]
 * Output: 11
 *
 * Solution:
 *  dp[row][col] = Max(dp[row-1][k] + points[row][col] - abs(col-k))
 *
 *  here points[row][col], and col is fixed for a cell, the variable is k,
 *  so we can just try to find the max of the variables which are associated with k
 *
 *  dp[row][col] = Max(dp[row-1][k] - k) + points[row][col] + col, col <= k <= column length
 *
 */
public class MaximumNumberOfPointsWithCost {

    /**
     * Complexity: O(n * m * m), where n is number of rows, m = number of columns
     * @param points
     * @return
     */
    public static long maxPoints(int[][] points) {
        int nRows = points.length, nCols = points[0].length;

        // dp[i][j] means the maximum points in row i and col j.
        int[][] dp = new int[nRows][nCols];

        // initialization
        for(int i=0; i< nCols; i++) {
            dp[0][i] = points[0][i];
        }

        int max = -1;
        for(int row=1; row<nRows; row++) {
            for(int col=0; col<nCols; col++) {
                for(int k=0; k<nCols; k++) {
                    dp[row][col] = Math.max(dp[row][col], dp[row-1][k] + points[row][col] - Math.abs(col-k));
                }
                max = Math.max(max, dp[row][col]);
            }
        }
        return max;
    }

    public static long maxPointsOpt(int[][] points) {
        int nRows = points.length, nCols = points[0].length;
        long max = -1;
        // if there is only one row then just find the maximum number in the row
        if (nRows == 1 ) {
            for(int num : points[0]) {
                max = Math.max(max, num);
            }
            return max;
        }
        // dp[i][j] means the maximum points in row i and col j.
        long[][] dp = new long[nRows][nCols];
        // Recurrence
        // dp[row][col] = Max(dp[row-1][k] + points[row][col] + abs(k-col)); 0 <= col <= k <= # columns
        // here for a cell, [row][col], k is the variable, so maximum value
        // will be determined by K.
        // So we can change the recurrence to
        //      dp[row][col] = Max(dp[row-1][k] +/- k) + points[row][col] -/+ col;
        // Here +/- k depends on the position of maximum value.
        // - if Maximum is left of current position then, k < col, col=current position
        //.  in this case
        // Max(dp[row-1][k] + points[row][col] + abs(k-col)) = Max(dp[row-1][k] - k) + points[row][col] + col
        // - if Maximum is right of current position then, k > col
        // in this case
        // Max(dp[row-1][k] + points[row][col] + abs(k-col)) = Max(dp[row-1][k] + k) + points[row][col] - col

        // initialization
        for(int i=0; i< nCols; i++) {
            dp[0][i] = points[0][i];
        }


        for(int row=1; row<nRows; row++) {
            long[] leftDP = new long[nCols];
            Arrays.fill(leftDP, -1);
            long[] rightDP = new long[nCols];
            Arrays.fill(leftDP, -1);

            leftDP[0] = dp[row-1][0];
            for(int k=1; k<nCols; k++) {
                leftDP[k] = Math.max(leftDP[k-1], dp[row-1][k] + k);
            }

            rightDP[nCols-1] = dp[row-1][nCols-1] - (nCols-1);
            for(int k=nCols-2; k>=0; k--) {
                rightDP[k] = Math.max(rightDP[k+1], dp[row-1][k] - k);
            }

            for(int col=0; col<nCols; col++) {
                dp[row][col] = Math.max(leftDP[col]-col, rightDP[col]+col) + points[row][col];
                max = Math.max(max, dp[row][col]);
            }


        }
        return max;
    }

    public static long maxPointsOpt2(int[][] p) {
        long[] cur_row = new long[p[0].length], prev_row = new long[p[0].length];
        for (var row : p) {
            long run_max = 0;
            for (int j = 0; j < row.length; ++j) {
                run_max = Math.max(run_max - 1, prev_row[j]);
                cur_row[j] = run_max;
            }
            for (int j = row.length - 1; j >= 0; --j) {
                run_max = Math.max(run_max - 1, prev_row[j]);
                cur_row[j] = Math.max(cur_row[j], run_max) + row[j];
            }
            System.out.println(Arrays.toString(cur_row));
            prev_row = cur_row;
        }
        return Arrays.stream(prev_row).max().getAsLong();
    }

    public static void main(String args[]) {
        System.out.println("Input: [[1,2,3],[1,5,1],[3,1,1]] \nOutput: " + MaximumNumberOfPointsWithCost.maxPoints( new int[][]{{1,2,3},{1,5,1},{3,1,1}}));
        System.out.println("Input: [[1,2,3],[1,5,1],[3,1,1]] \nOutput: " + MaximumNumberOfPointsWithCost.maxPointsOpt( new int[][]{{1,2,3},{1,5,1},{3,1,1}}));
        //System.out.println("Input: [[1,2,3],[1,5,1],[3,1,1]] \nOutput: " + MaximumNumberOfPointsWithCost.maxPointsOpt2( new int[][]{{1,2,3},{1,5,1},{3,1,1}}));
    }
}
