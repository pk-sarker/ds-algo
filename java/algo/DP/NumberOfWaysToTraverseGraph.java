package algo.DP;

/**
 *
 You're given two positive integers representing the width and height of a
 grid-shaped, rectangular graph. Write a function that returns the number of
 ways to reach the bottom right corner of the graph when starting at the top
 left corner. Each move you take must either go down or right. In other words,
 you can never move up or left in the graph.

 For example, given the graph illustrated below, with
 width = 2 and height = 3, there are three ways to
 reach the bottom right corner when starting at the top left corner:
  _ _
 |_|_|
 |_|_|
 |_|_|
 1. Down, Down, Right
 2. Right, Down, Down
 3. Down, Right, Down


 Note: you may assume that width * height >= 2, . In other words,
 the graph will never be a 1x1 grid.
 */
public class NumberOfWaysToTraverseGraph {

    public int numberOfWaysToTraverseGraph(int width, int height) {
        // Write your code here.
        int[][] dp = new int[height][width];

        for(int i=0; i<width; i++) {
            dp[0][i] = 1;
        }
        for(int i=0; i<height; i++) {
            dp[i][0] = 1;
        }

        for(int row=1; row<height; row++) {
            for(int col=1; col<width; col++) {
                dp[row][col] = dp[row][col-1] + dp[row-1][col];
            }
        }
        return dp[height-1][width-1];
    }
}
