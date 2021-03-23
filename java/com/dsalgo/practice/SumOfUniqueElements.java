package com.dsalgo.practice;

import java.util.HashMap;

/**
 * You are given an integer array nums. The unique elements of an array are the elements that
 * appear exactly once in the array.
 *
 * Return the sum of all the unique elements of nums.
 *
 * Input: nums = [1,2,3,2]
 * Output: 4
 * Explanation: The unique elements are [1,3], and the sum is 4.
 *
 * Input: nums = [1,1,1,1,1]
 * Output: 0
 * Explanation: There are no unique elements, and the sum is 0.
 *
 * Input: nums = [1,2,3,4,5]
 * Output: 15
 * Explanation: The unique elements are [1,2,3,4,5], and the sum is 15.
 */
public class SumOfUniqueElements {

    public static int sumOfUnique(int[] nums) {
        int sum = 0;

        HashMap<Integer, Integer>  numMap = new HashMap<>();
        for(int i=0; i<nums.length; i++) {

            if (numMap.containsKey(nums[i])) {
                if (numMap.get(nums[i]) == 1) {
                    sum -= nums[i];
                }
                numMap.put(nums[i], numMap.get(nums[i])+1);
                continue;
            } else {
                sum += nums[i];
                numMap.put(nums[i], 1);
            }
        }
        return sum;
    }
    public static void main(String args[]) {
        System.out.println("\nInput:  [1,2,3,2] \nOutput: " +  SumOfUniqueElements.sumOfUnique(new int[]{1,2,3,2}));
        System.out.println("\nInput:  [2,2,2,2,2] \nOutput: " +  SumOfUniqueElements.sumOfUnique(new int[]{2,2,2,2,2}));
    }
}
