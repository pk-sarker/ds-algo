package com.dsalgo.practice;

import java.util.Arrays;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 *
 */
public class MatrixRotation {

    public int[][] rotate(int[][] matrix) {
        int n = matrix.length;
        int rotateCycle = n / 2;

        for(int i=0; i<rotateCycle; i++) {
            int end = n-1 - i*2;
            for(int j=0; j<end; j++) {
                int lt_r = i, lt_c = i+j;
                int rt_r = i+j, rt_c = n-1-i;
                int rb_r = n-1-i, rb_c = n-1-i-j;
                int lb_r = n-1-i-j, lb_c = i;
                int temp = matrix[lt_r][lt_c];
                System.out.println("LT corner: (i,j)=("+lt_r+","+lt_c+") => " +  matrix[lt_r][lt_c]);
                System.out.println("RT corner: (i,j)=("+rt_r+","+rt_c+") => " +  matrix[rt_r][rt_c]);
                System.out.println("RB corner: (i,j)=("+rb_r+","+rb_c+") => " +  matrix[rb_r][rb_c]);
                System.out.println("LB corner: (i,j)=("+lb_r+","+lb_c+") => " +  matrix[lb_r][lb_c]);
                matrix[lt_r][lt_c] = matrix[lb_r][lb_c];
                matrix[lb_r][lb_c] = matrix[rb_r][rb_c];
                matrix[rb_r][rb_c] = matrix[rt_r][rt_c];
                matrix[rt_r][rt_c] = temp;
            }
        }

        return matrix;
    }

    public static void main(String args[]) {
        MatrixRotation ob  = new MatrixRotation();
        int[][] res = ob.rotate(new int[][]{{1,2,3,4},{12,13,14,5},{11,16,15,6},{10,9,8,7}});
        System.out.println(Arrays.toString(res[0]));
        System.out.println(Arrays.toString(res[1]));
        System.out.println(Arrays.toString(res[2]));
        System.out.println(Arrays.toString(res[3]));


        int[][] res1 = ob.rotate(new int[][]{{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20}, {21,22,23,24,25}});
        System.out.println(Arrays.toString(res1[0]));
        System.out.println(Arrays.toString(res1[1]));
        System.out.println(Arrays.toString(res1[2]));
        System.out.println(Arrays.toString(res1[3]));
        System.out.println(Arrays.toString(res1[4]));
    }
}
