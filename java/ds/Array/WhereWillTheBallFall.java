package ds.Array;

import java.util.Arrays;

/**
 * You have a 2-D grid of size m x n representing a box, and you have n balls. The box is open on the
 * top and bottom sides.
 *
 * Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball
 * to the right or to the left.
 *
 * A board that redirects the ball to the right spans the top-left corner to the bottom-right corner and
 * is represented in the grid as 1.
 * A board that redirects the ball to the left spans the top-right corner to the bottom-left corner and
 * is represented in the grid as -1.
 * We drop one ball at the top of each column of the box. Each ball can get stuck in the box or fall out
 * of the bottom. A ball gets stuck if it hits a "V" shaped pattern between two boards or if a board redirects
 * the ball into either wall of the box.
 *
 * Return an array answer of size n where answer[i] is the column that the ball falls out of at the bottom
 * after dropping the ball from the ith column at the top, or -1 if the ball gets stuck in the box.
 *
 * Input: grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
 * Output: [1,-1,-1,-1,-1]
 * Explanation: This example is shown in the photo.
 * Ball b0 is dropped at column 0 and falls out of the box at column 1.
 * Ball b1 is dropped at column 1 and will get stuck in the box between column 2 and 3 and row 1.
 * Ball b2 is dropped at column 2 and will get stuck on the box between column 2 and 3 and row 0.
 * Ball b3 is dropped at column 3 and will get stuck on the box between column 2 and 3 and row 0.
 * Ball b4 is dropped at column 4 and will get stuck on the box between column 2 and 3 and row 1.
 *
 * Input: grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
 * Output: [0,1,2,3,4,-1]
 *
 * Input: grid = [[-1]]
 * Output: [-1]
 * Explanation: The ball gets stuck against the left wall.
 */
public class WhereWillTheBallFall {

    public int[] findBall(int[][] grid) {
        int m = grid.length, n = grid[0].length, res[] = new int[n];
        for (int i = 0; i < n; ++i) {  // for each column
            int i1 = i, i2;
            for (int j = 0; j < m; ++j) { // for each row
                // i2 = next column after roling
                //    = current column + diagonal value
                //.for diagoal from left top to bottom right, its 1 and -1 for
                // top right to bottom left.
                // So if the diagonal is from left top to bottom right then after roling
                // the ball will go to next column, similarly if the diagonal is
                // top right to bottom left then after rolling the ball will be in previous column.
                i2 = i1 + grid[j][i1]; // next column after rolling.
                if (i2 < 0 || i2 >= n || grid[j][i2] != grid[j][i1]) {
                    i1 = -1;
                    break;
                }
                i1 = i2;
            }
            res[i] = i1;
        }
        return res;
    }

    public static void main(String args[]) {
        WhereWillTheBallFall obj = new WhereWillTheBallFall();
        System.out.println(Arrays.toString(obj.findBall(new int[][]{{1,1,1,-1,-1}, {1,1,1,-1,-1}, {-1,-1,-1,1,1}, {1,1,1,1,-1}, {-1,-1,-1,-1,-1}})));
    }
}
