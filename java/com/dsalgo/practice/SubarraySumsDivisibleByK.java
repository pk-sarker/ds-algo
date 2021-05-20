package com.dsalgo.practice;

import java.util.HashMap;

/**
 * Given an array nums of integers, return the number of (contiguous, non-empty)
 * subarrays that have a sum divisible by k.
 *
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 */
public class SubarraySumsDivisibleByK {
    public static int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> sumOccurrence = new HashMap<>();
        sumOccurrence.put(0, 1);
        int sum = 0;
        int count = 0;
        for(int i=0;i<nums.length;i++) {
            sum += nums[i];
            sum = sum % k;
            // to make sure the reminder is positive, for sum = -1, we will keep the reminder k-1
            if (sum < 0) {
                sum += k;
            }
            if (sumOccurrence.containsKey(sum)) {
                count += sumOccurrence.get(sum);
            }
            sumOccurrence.put(sum, sumOccurrence.getOrDefault(sum, 0)+1);
        }
        return count;
    }
    public static void main(String args[]) {
        System.out.println("Input: nums = [4,5,0,-2,-3,1], k = 5, \nOutput: " + SubarraySumsDivisibleByK.subarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5));
    }
}
