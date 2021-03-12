package com.dsalgo.practice;

/**
 * Given an unsorted array containing numbers, find the smallest missing positive number in it.
 *
 * Input: [-3, 1, 5, 4, 2]
 * Output: 3
 * Explanation: The smallest missing positive number is '3'
 *
 * Input: [3, -2, 0, 1, 2]
 * Output: 4
 *
 * Input: [3, 2, 5, 1]
 * Output: 4
 */
public class SmallestMissingPositiveNumber {

    public static int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int j = nums[i] - 1;
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[j]) {
                int t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {  // add 1 because 0 base index
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String args[]) {
        int[] nums = new int[]{-3, 1, 5, 4, 2};
        System.out.println("\nInput: [-3, 1, 5, 4, 2]  \nOutput: " +SmallestMissingPositiveNumber.firstMissingPositive(nums));

        int[] nums1 = new int[]{3, -2, 0, 1, 2};
        System.out.println("\nInput: [3, -2, 0, 1, 2]  \nOutput: " +SmallestMissingPositiveNumber.firstMissingPositive(nums1));
    }
}
