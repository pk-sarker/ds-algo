package com.dsalgo.practice;

/**
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product,
 * and return the product.
 *
 * It is guaranteed that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 */
public class MaximumProductSubarray {
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = maxProduct;
        for(int i = 1; i < nums.length; i++) {
            int temp_max = Math.max(nums[i], Math.max(minProduct * nums[i], maxProduct * nums[i]));
            minProduct = Math.min(nums[i], Math.min(minProduct * nums[i], maxProduct * nums[i]));

            maxProduct = temp_max;
            result = Math.max(maxProduct, result);
        }
        return result;
    }
    public static void main(String args[]) {
        System.out.println("\narr=[1,2,3,4,5]\nOutput: " + MaximumProductSubarray.maxProduct(new int[]{1,2,3,4,5}));

        System.out.println("\narr=[1,2,-3,-7,4,5]\nOutput: " + MaximumProductSubarray.maxProduct(new int[]{1,2,-3,4,5}));
    }
}
