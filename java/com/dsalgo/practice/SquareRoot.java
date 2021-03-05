package com.dsalgo.practice;

/**
 * Given a non-negative integer x, compute and return the square root of x.
 *
 * Since the return type is an integer, the decimal digits are truncated, and
 * only the integer part of the result is returned.
 *
 * Input: x = 4
 * Output: 2
 *
 * Input: x = 2
 * Output: 1
 *
 * Input: x = 8
 * Output: 2
 *
 * Input: x = 9
 * Output: 3
 */
public class SquareRoot {

    public int mySqrt(int x) {
        if (x==0 || x == 1) {
            return x;
        }

        for(int i = 1; i<x; i++)  {
            double sq = Math.pow(i, 2);
            if ( sq == x || (x > sq && x < Math.pow(i+1, 2))) {
                return i;
            }
        }
        return 0;
    }
    public static void main(String args[]) {
        SquareRoot ob = new SquareRoot();
        System.out.println("\nInput: 4 \nOutput: " + ob.mySqrt(4));
        System.out.println("\nInput: 2 \nOutput: " + ob.mySqrt(2));
        System.out.println("\nInput: 8 \nOutput: " + ob.mySqrt(8));
        System.out.println("\nInput: 9 \nOutput: " + ob.mySqrt(9));
    }
}
