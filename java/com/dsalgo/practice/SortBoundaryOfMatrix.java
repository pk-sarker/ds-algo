package com.ds.practice;

import java.util.Arrays;

/**
 * Sorting boundary elements of a matrix
 * Given a matrix mat[][] of size M*N, the task is to sort only the border elements of the matrix
 * in the clockwise direction and print the matrix after sorting again.
 *
 * Input: M = 4, N = 5, Below is the given matrix:
 *   1 2 3 4 0
 *   1 1 1 1 2
 *   1 2 2 2 4
 *   1 9 3 1 7
 * Output:
 *   0 1 1 1 1
 *   9 1 1 1 1
 *   7 2 2 2 2
 *   4 4 3 3 2
 */
public class SortBoundaryOfMatrix {
    public int[][] sort(int[][] mat) {
        int nr = mat.length, nc = mat[0].length;
        int[] bound = new int[ nr*2 + (nc-2)*2];
        int level = 0;
        int count = 0;
        while(level <1) {
            for(int col=0; col<nc; col++) {
                bound[count++] = mat[0][col];
            }
            for(int row=1; row<nr; row++) {
                bound[count++] = mat[row][nc-1];
            }
            for(int col=nc-2; col>0; col--) {
                bound[count++] = mat[nr-1][col];
            }
            for(int row=nr-1; row>0; row--) {
                bound[count++] = mat[row][0];
            }
            level++;
        }
        Arrays.sort(bound);
        count = 0;
        for(int col=0; col<nc; col++) {
            mat[0][col] = bound[count++];
        }
        for(int row=1; row<nr; row++) {
            mat[row][nc-1] = bound[count++];
        }
        for(int col=nc-2; col>0; col--) {
            mat[nr-1][col] = bound[count++];
        }
        for(int row=nr-1; row>0; row--) {
            mat[row][0] = bound[count++];
        }
        return mat;
    }
    public static void main(String args[]) {
        SortBoundaryOfMatrix ob = new SortBoundaryOfMatrix();
        int[][] res = ob.sort(new int[][]{{1,2,3,4,0}, {1,1,1,1,2}, {1,2,2,2,4}, {1,9,3,1,7}});
        System.out.println(Arrays.deepToString(res));
    }
}
