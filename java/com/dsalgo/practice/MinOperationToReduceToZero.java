package com.dsalgo.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array nums and an integer x. In one operation, you can either
 * remove the leftmost or the rightmost element from the array nums and subtract its value
 * from x. Note that this modifies the array for future operations.
 *
 * Return the minimum number of operations to reduce x to exactly 0 if it's possible,
 * otherwise, return -1.
 *
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 *
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 *
 * @Solution:
 * If there exists a solution then, there exists a subarray whose sum is `totalSum - x`
 * And we need to find maximum length subarray whose sum is `totalSum - x`, then we will find
 * minimum operations to reduce x to 0.
 */
public class MinOperationToReduceToZero {

    // sum[0...left] + sum[right...last] = x
    // we need to find left and right
    public static int minOperations(int[] nums, int x) {

        int totalSum = 0;
        for(int i=0;i<nums.length;i++) {
            totalSum += nums[i];
        }
        int transformedTarget = totalSum - x;

        if (transformedTarget == 0) {
            return nums.length; // total sum == x
        }
        Map<Integer, Integer> hmap = new HashMap<>();
        hmap.put(0,-1);

        int sum = 0;
        int result = Integer.MIN_VALUE;

        for(int i=0;i<nums.length;i++) {
            sum += nums[i];

            if (hmap.containsKey(sum-transformedTarget)) {
                result = Math.max(result, i-hmap.get(sum - transformedTarget));
            }
            // no need to check containsKey since sum is unique
            hmap.put(sum, i);
        }

        return result == Integer.MIN_VALUE ? -1:nums.length-result;
    }

    public static void main(String args[]) {
        System.out.println("Input: [1,1,4,2,3], x=5\nOutput: " + MinOperationToReduceToZero.minOperations(new int[]{1,1,4,2,3}, 5));

        System.out.println("Input: [1,4,6,7,1,2,2], x=5\nOutput: " + MinOperationToReduceToZero.minOperations(new int[]{1,4,6,7,1,2,2}, 5));
    }
}
