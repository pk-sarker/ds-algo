package com.dsalgo.practice;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n]
 * inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 * Find that duplicate number without using any extra space.
 *
 * Input: [2, 1, 3, 3, 5, 4]
 * Output: 3
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Input: nums = [4,4,4,4]
 * Output: 4
 */
public class FindDuplicateNumber {
    public static int findDuplicate(int[] nums) {
        int dup = -1;
        int i=0;
        while(i < nums.length) {
            int j = nums[i]-1;
            if (nums[i] != nums[j]) {
                int t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
            } else {
                i++;
            }
        }

        for(i=0;i<nums.length;i++) {
            if (nums[i] != i+1) {
                return nums[i];
            }
        }
        return dup;
    }


    public static void main(String args[]) {
        int[] nums = new int[]{2, 1, 3, 3, 5, 4};
        System.out.println("\nInput: [2, 1, 3, 3, 5, 4]  \nOutput: " +FindDuplicateNumber.findDuplicate(nums));

        int[] nums1 = new int[]{4,4,4,4};
        System.out.println("\nInput: [4,4,4,4]  \nOutput: " +FindDuplicateNumber.findDuplicate(nums1));
    }
}
