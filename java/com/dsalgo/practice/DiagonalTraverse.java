package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] matrix) {
        int nr = matrix.length, nc = matrix[0].length;
        int[] res = new int[nr*nc];

        List<Integer> diagonals = new ArrayList<>();
        int r = 0, c = 0, k =0;
        for(int d=0;d<(nc+nr-1); d++) {
            diagonals.clear();

            r = d < nc ? 0: d-nc+1;
            c = d < nc ? d: nc-1;

            while(r < nr && c >-1) {
                diagonals.add(matrix[r][c]);
                r++;
                c--;
            }
            if (d%2==0) {
                Collections.reverse(diagonals);
            }

            for (int i = 0; i < diagonals.size(); i++) {
                res[k++] = diagonals.get(i);
            }
        }
        return res;
    }

    public static void main(String args[]) {
        DiagonalTraverse obj = new DiagonalTraverse();
        System.out.println(Arrays.toString(obj.findDiagonalOrder(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}})));
    }
}
