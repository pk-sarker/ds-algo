package com.ds.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ##################
 * #####  EASY  #####
 * ##################
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers such
 * that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same
 * element twice.
 *
 * You can return the answer in any order.
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
public class TwoSumLE {
    public static int[] twoSum(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{};
        }
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            int diff = target - nums[i];
            if (hashMap.containsKey(diff)) {
                return new int[]{hashMap.get(diff), i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String args[]) {
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long startTime = System.currentTimeMillis();
        System.out.println("\nInput: [11, 15, 7, 1, 2] target = 9, \nOutput:" + Arrays.toString(TwoSumLE.twoSum(new int[]{11, 15, 7, 1, 2}, 9)));

        System.out.println("\nInput: [11, 15, 7, 1, -2] target = 9, \nOutput:" + Arrays.toString(TwoSumLE.twoSum(new int[]{11, 15, 7, 1, -2}, 9)));
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long stopTime = System.currentTimeMillis();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        System.out.println("\nMemory Used: " + actualMemUsed);
        long elapsedTime = stopTime - startTime;
        System.out.println("\nTime: " + elapsedTime);
    }
}
