package com.dsalgo.practice;

public class MatrixSetToZero {
    public void setZeroes(int[][] matrix) {
        boolean firstCol = false;
        int nr = matrix.length;
        int nc = matrix[0].length;
        for (int row = 0; row < nr; row++) {

            // if first column of any row contains 0, we mark that
            // and set first column zero if set true
            if (matrix[row][0] == 0) {
                firstCol = true;
            }
            //
            for (int col = 1; col < nc; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0; // use first column of each row as row zero flag
                    matrix[0][col] = 0; // use first row of each column as column zero flag
                }
            }
        }
        for (int row = 1; row < nr; row++) {
            for (int col = 1; col < nc; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }
        if (matrix[0][0] == 0) { // first row set to zero
            for (int col = 0; col < nc; col++) {
                matrix[0][col] = 0;
            }
        }
        if (firstCol) { // first column set to zero
            for (int i = 0; i < nr; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String args[]) {
        MatrixSetToZero obj = new MatrixSetToZero();
        obj.setZeroes(new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}});
    }
}
