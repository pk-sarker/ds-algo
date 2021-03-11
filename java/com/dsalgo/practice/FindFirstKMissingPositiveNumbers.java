package com.dsalgo.practice;

import java.util.*;

/**
 * Given an unsorted array containing numbers and a number ‘k’, find the first ‘k’ missing positive
 * numbers in the array.
 *
 * Input: [3, -1, 4, 5, 5], k=3
 * Output: [1, 2, 6]
 * Explanation: The smallest missing positive numbers are 1, 2 and 6.
 *
 * Input: [2, 3, 4], k=3
 * Output: [1, 5, 6]
 * Explanation: The smallest missing positive numbers are 1, 5 and 6.
 *
 * Input: [-2, -3, 4], k=2
 * Output: [1, 2]
 * Explanation: The smallest missing positive numbers are 1 and 2.
 *
 */
public class FindFirstKMissingPositiveNumbers {

    public static List<Integer> findNumbers(int[] nums, int k) {
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
        System.out.println(Arrays.toString(nums));
        List<Integer> missingNumbers = new ArrayList<>();
        Set<Integer> extraNumbers = new HashSet<>();
        for (i = 0; i < nums.length && missingNumbers.size() < k; i++)
            if (nums[i] != i + 1) {
                missingNumbers.add(i + 1);
                extraNumbers.add(nums[i]);
            }

        // add the remaining missing numbers
        for (i = 1; missingNumbers.size() < k; i++) {
            int candidateNumber = i + nums.length;
            // ignore if the array contains the candidate number
            if (!extraNumbers.contains(candidateNumber))
                missingNumbers.add(candidateNumber);
        }

        return missingNumbers;
    }

    public static void main(String args[]) {
        int[] nums = new int[]{3, -1, 4, 5, 5};
        System.out.println("\nInput: [3, -1, 4, 5, 5]  k=3\nOutput: " +FindFirstKMissingPositiveNumbers.findNumbers(nums, 3));

        int[] nums1 = new int[]{2, 3, 4};
        System.out.println("\nInput: [2, 3, 4]  k=3\nOutput: " +FindFirstKMissingPositiveNumbers.findNumbers(nums1, 3));
    }
}
