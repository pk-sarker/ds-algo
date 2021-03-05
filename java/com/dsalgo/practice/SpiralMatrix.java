package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    public static List<Integer> spiralOrder1(int[][] matrix) {
        int nr = matrix.length, nc = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        int rowStart = 0, rowEnd = nr-1, colStart = 0, colEnd = nc-1;

        while(rowStart <= rowEnd && colStart <= colEnd) {
            // left to right
            for(int col =colStart; col <= colEnd; col++) {
                res.add(matrix[rowStart][col]);
            }
            // top to bottom
            for(int row = rowStart+1; row <= rowEnd; row++)  {
                res.add(matrix[row][colEnd]);
            }
            if (rowStart < rowEnd && colStart < colEnd) {
                // right to left
                for(int col=colEnd-1; col > colStart; col--)  {
                    res.add(matrix[rowEnd][col]);
                }
                // bottom to top
                for(int row=rowEnd; row>rowStart; row--)  {
                    res.add(matrix[row][colStart]);
                }
            }
            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        }
        return res;
    }

    public static List <Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0)
            return ans;
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }
    public static void main(String args[]) {
        //System.out.println("\nInput: [[1,2,3],[4,5,6],[7,8,9]] \nOutput: " +  SpiralMatrix.spiralOrder(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}}));

        //System.out.println("\nInput: [[1,2,3,4],[5,6,7,8],[9,10,11,12]] \nOutput: " +  SpiralMatrix.spiralOrder(new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}}));
        System.out.println("\nInput: [[1,2,3,4],[5,6,7,8],[9,10,11,12]] \nOutput: " +  SpiralMatrix.spiralOrder1(new int[][]{{1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}}));
        System.out.println("\nInput: [[1,2,3,4],[5,6,7,8],[9,10,11,12]] \nOutput: " +  SpiralMatrix.spiralOrder(new int[][]{{1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}}));
    }
}
