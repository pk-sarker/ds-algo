package com.dsalgo.practice;

import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint
 * stopping you from robbing each of them is that adjacent houses have security systems
 * connected and it will automatically contact the police if two adjacent houses were
 * broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the
 * maximum amount of money you can rob tonight without alerting the police.
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber {
    int[] memo = new int[200];
    public int rob(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        }
        Arrays.fill(memo, Integer.MIN_VALUE);
        return inspectAndRob(nums, 0);
    }

    public int inspectAndRob(int[] nums, int houseIdx) {
        if (houseIdx >= nums.length) {
            return 0;
        }
        // if already inspected
        if (memo[houseIdx] > Integer.MIN_VALUE) {
            return memo[houseIdx];
        }

        int maxMoney = Math.max(inspectAndRob(nums, houseIdx + 1), inspectAndRob(nums, houseIdx+2) + nums[houseIdx]);
        memo[houseIdx] = maxMoney; // store for future use
        return maxMoney;
    }

    public static void main(String args[]) {
        HouseRobber obj = new HouseRobber();
        System.out.println("\nInput: [1,3,0,5,4,2,8] \nOutput: " + obj.rob(new int[]{1,3,0,5,4,2,8}));
    }
}
