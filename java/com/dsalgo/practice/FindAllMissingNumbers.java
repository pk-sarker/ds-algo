package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice
 * and others appear once.
 *
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does
 * not count as extra space.
 *
 * Input: [2, 3, 1, 8, 2, 3, 5, 1]
 * Output: 4, 6, 7
 * Explanation: The array should have all numbers from 1 to 8, due to duplicates 4, 6, and 7 are missing.
 *
 * Input: [2, 4, 1, 2]
 * Output: 3
 *
 * Input: [2, 3, 2, 1]
 * Output: 4
 *
 */
public class FindAllMissingNumbers {

    public static List<Integer> findMissingNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int i = 0;
        while(i < nums.length) {
            int j =  nums[i]-1;
            if (nums[i] != nums[j]) {
                int t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
            } else {
                i++;
            }
        }

        for(i=0; i<nums.length;i++) {
            if (nums[i] != i+1) {
                result.add(i+1);
            }
        }
        return result;
    }
    public static void main(String args[]) {
        int[] nums = new int[]{2, 3, 1, 8, 2, 3, 5, 1};
        System.out.println("\nInput: [2, 3, 1, 8, 2, 3, 5, 1]  \nOutput: " +FindAllMissingNumbers.findMissingNumbers(nums));

        int[] nums1 = new int[]{2, 4, 1, 2};
        System.out.println("\nInput: [2, 4, 1, 2]  \nOutput: " +FindAllMissingNumbers.findMissingNumbers(nums1));
    }
}
