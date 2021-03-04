package com.ds.practice;

/**
 * Given an array of integers. Find number of elements equal to mean of neighbor elements.
 *
 * Input: [2,4,6,6,3]
 * Output: 3
 * 2, 4, 3
 * 0 + 4 / 2 =  2
 * 2 + 6 / 2 =  4
 * 6 + 0 / 2 =  3
 */
public class MeanOfNeighbor {
    public static int countMean(int[] nums) {
        int count = 0, n = nums.length;
        if (n < 2) {
            return 0;
        }
        if (nums[1]/2 == nums[0]) {
            count++;
        }
        for(int i  = 1; i<n-1; i++) {
            if ((nums[i-1] + nums[i+1]) / 2 == nums[i]) {
                count++;
            }
        }
        int ln = (nums[n-2]/2), l = nums[n-1];
        if (ln == l) {
            count++;
        }
        return count;
    }
    public static void main(String args[]) {
        System.out.println("\nInput: [2,4,6,6,3] \nOutput: " + MeanOfNeighbor.countMean(new int[]{2,4,6,6,3}));
    }
}
