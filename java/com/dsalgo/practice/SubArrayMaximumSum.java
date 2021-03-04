package com.ds.practice;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Input: nums = [1]
 * Output: 1
 *
 * Input: nums = [0]
 * Output: 0
 *
 * Input: nums = [-1]
 * Output: -1
 *
 * Input: nums = [-100000]
 * Output: -100000
 */
public class SubArrayMaximumSum {

    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n==1) {
            return nums[0];
        }
        int sum = nums[0], maxSum = nums[0];

        for(int i=1; i<n; i++) {
            sum = Math.max(nums[i], sum+nums[i]);
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: [-2,1,-3,4,-1,2,1,-5,4] \nOutput: " + SubArrayMaximumSum.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println("\nInput: [-2,1,-3,-1,4,2,1,-5,4] \nOutput: " + SubArrayMaximumSum.maxSubArray(new int[]{-2,1,-3,-1,4,2,1,-5,4}));

        System.out.println("\nInput: [1,1,1,1,1] \nOutput: " + SubArrayMaximumSum.maxSubArray(new int[]{1,1,1,1,1}));
        System.out.println("\nInput: [1,1,1,-1,1] \nOutput: " + SubArrayMaximumSum.maxSubArray(new int[]{1,1,1,-1,1}));
    }
}
