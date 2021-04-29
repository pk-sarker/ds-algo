package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;

public class LeftmostColumnWithAtLeastOneOne {
    public static class BinaryMatrix {
        private int[][] matrix;
        public BinaryMatrix(int[][] mat) {
            this.matrix = mat;
        }

        public List<Integer> dimensions() {
            List<Integer> dim = new ArrayList<>();
            dim.add(this.matrix.length);
            dim.add(this.matrix[0].length);
            return dim;
        }
        public int get(int row, int col) {
            return this.matrix[row][col];
        }
    }
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int rows = dim.get(0);
        int cols = dim.get(1);

        int oneMinPos = Integer.MAX_VALUE;
        int currentRow = 0, currentCol = cols-1;
        while(currentRow < rows && currentCol >= 0) {
            if (binaryMatrix.get(currentRow, currentCol) == 1) {
                oneMinPos = Math.min(oneMinPos, currentCol);
                currentCol--;
                continue;
            }
            currentRow++;
        }
        return oneMinPos == Integer.MAX_VALUE ? -1: oneMinPos;
    }

    public static void main(String args[]) {
        LeftmostColumnWithAtLeastOneOne obj = new LeftmostColumnWithAtLeastOneOne();
        BinaryMatrix bm = new BinaryMatrix(new int[][]{{0,0,0,1},{0,0,1,1},{1,1,1,1}});
        System.out.println(obj.leftMostColumnWithOne(bm));
    }
}
