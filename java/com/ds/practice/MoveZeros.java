package com.ds.practice;

import java.util.Arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements.
 *
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 */
public class MoveZeros {

    public static void moveZeroes(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }
        int nonZeroPtr = 0;
        for(int i=0; i<n; i++) {
            if (nums[i] != 0) {
                int t = nums[nonZeroPtr];
                nums[nonZeroPtr] = nums[i];
                nums[i] = t;
                nonZeroPtr++;
            }
        }
    }

    public static void main(String args[]) {
        int[] nums = new int[]{0,1,0,3,12};
        MoveZeros.moveZeroes(nums);
        System.out.println("\nInput: [0,1,0,3,12]\nOutput: " + Arrays.toString(nums));
    }
}
