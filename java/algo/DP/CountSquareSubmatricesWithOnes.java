package algo.DP;

/**
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 * Input: matrix =
 * [[0,1,1,1],
 *  [1,1,1,1],
 *  [0,1,1,1]]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 *
 * Input: matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 */
public class CountSquareSubmatricesWithOnes {

    public static int countSquares(int[][] matrix) {
        int count = 0;
        int n = matrix.length, m = matrix[0].length;

        for(int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (matrix[i][j] > 0 && i>0 & j>0) { // (i,j) == 1 & not first row and not first column
                    matrix[i][j] = Math.min(matrix[i-1][j-1], Math.min(matrix[i-1][j], matrix[i][j-1]))+1; // +1 because matrix[i][j] > 0
                }
                count += matrix[i][j];
            }
        }
        return count;
    }

    public static void main(String args[]) {
        System.out.println("[ \n  0,1,1,1 \n  1,1,1,1\n  0,1,1,1 \n]");
        System.out.println(CountSquareSubmatricesWithOnes.countSquares(new int[][]{{0,1,1,1}, {1,1,1,1},{0,1,1,1}}));
        System.out.println("[ \n  1,0,1 \n  1,1,0\n  1,1,0 \n]");
        System.out.println(CountSquareSubmatricesWithOnes.countSquares(new int[][]{{1,0,1}, {1,1,0},{1,1,0}}));

    }
}
