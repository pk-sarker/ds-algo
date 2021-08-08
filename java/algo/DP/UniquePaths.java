package algo.DP;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying
 * to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 * Input: m = 3, n = 7
 * Output: 28
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
 * Solution:
 * The robot can move only to right and down. That means it can't go back :)
 * So from a position robot has two options, either go right if not out of bound, or go down.
 *
 * Then if the robot is at [m,n] cell, then to reach [m,n] the robot may come from top,[m-1,n], and move down to [m,n]
 * or come from left, [m,n-1] to right.
 *
 * Then we can say that total number of ways to reach cell [m,n] = # ways to reach [m-1,n] + # ways to reach [m,n-1]
 *
 * Another observation is that only one path to reach the cells in the first row: right->right->...->right,
 * and one path to reach the first cell in each row, down->down-> ...->down.
 *
 * We create a 2D array, dimension will be [m x n], and initialize first row and first column to 1.
 * Then start filling other cells starting from 2nd cell in each row except first row.
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        // There is only one path to reach the cells in first row
        // and first column in each row.

        // set 1 to first cell in each column
        for(int i=0; i<n;i++) {
            memo[0][i] = 1;
        }
        // set 1 to first cell in each row
        for(int i=0; i<m;i++) {
            memo[i][0] = 1;
        }

        for(int row=1;row<m;row++) {
            for(int col=1; col<n; col++) {
                memo[row][col] = memo[row-1][col] + memo[row][col-1];
            }
        }
        return memo[m-1][n-1];
    }
    public static void main(String args[]) {
        UniquePaths obj = new UniquePaths();
        System.out.println("Input: (row,col) => (3,3) \nOutput:" + obj.uniquePaths(3, 3));
        System.out.println("Input: (row,col) => (3,7) \nOutput:" + obj.uniquePaths(3, 7));
    }
}
