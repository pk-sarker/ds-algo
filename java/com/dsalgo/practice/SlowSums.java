package com.dsalgo.practice;

import java.util.Arrays;

public class SlowSums {

    private static int solve(int[] nums) {
        Arrays.sort(nums); // O(n log n)
        int n = nums.length - 1;
        int sum = 0;
        for(int i=n-1; i>=0; i--) {
            nums[n] = nums[n] + nums[i];
            sum = sum + nums[n];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 2, 1, 3}, nums2 = {2, 3, 9, 8, 4}, nums3 = {2,100,100,1,99};
        System.out.println(solve(nums1));
        System.out.println(solve(nums2));
        System.out.println(solve(nums3));
    }
}
