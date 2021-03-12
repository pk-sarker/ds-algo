package com.dsalgo.practice;

/**
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 *
 * Find the kth positive integer that is missing from this array.
 *
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 *
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 *
 */
public class KthMissingPositiveNumber {
    public static int findKthPositive(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int pivot = left + ((right - left) / 2);
            // If number of positive integers
            // which are missing before arr[pivot]
            // is less than k -->
            // continue to search on the right.
            if (nums[pivot] - pivot - 1 < k) {
                left = pivot + 1;
                // Otherwise, go left.
            } else {
                right = pivot - 1;
            }
        }

        // At the end of the loop, left = right + 1,
        // and the kth missing is in-between arr[right] and arr[left].
        // The number of integers missing before arr[right] is
        // arr[right] - right - 1 -->
        // the number to return is
        // arr[right] + k - (arr[right] - right - 1) = k + left
        return left + k;
    }

    public static void main(String args[]) {
        int[] nums = new int[]{2,3,4,7,11};
        System.out.println("\nInput: [2,3,4,7,11]  k=5\nOutput: " +KthMissingPositiveNumber.findKthPositive(nums, 5));

        int[] nums1 = new int[]{1,2,3,4};
        System.out.println("\nInput: [1,2,3,4]  k=2\nOutput: " +KthMissingPositiveNumber.findKthPositive(nums1, 2));
    }
}
