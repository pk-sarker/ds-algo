package com.dsalgo.practice;

/**
 * Given an integer array nums which is sorted in ascending order and all of its
 * elements are unique and given also an integer k,
 * return the k-th missing number starting from the leftmost number of the array.
 *
 * Input: nums = [4,7,9,10], k = 1
 * Output: 5
 * Explanation: The first missing number is 5.
 *
 * Input: nums = [4,7,9,10], k = 4
 * Output: 11
 * Explanation: missing numbers are [5,6,8,11,12..], The first missing number is 11.
 *
 * Input: nums = [1,2,4], k = 3
 * Output: 6
 * Explanation: The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 */
public class MissingElementInSortedArray {
    public int missingElement(int[] nums, int k) {
        int n = nums.length;

        // if k-th missing number is greater than maximum value in the array
        if (countMissingNumbers(nums, n-1) < k) {
            // if there are no missing number in the array, then
            // k-th missing number will be exactly k away from the last number
            // if there are some missing number in the array but that number is
            // less than k then we  deduct those missing numbers after finding
            // k missing number after the last number  in the array
            return nums[n-1]+k-countMissingNumbers(nums, n-1);
        }
        int index = 1;
        while(countMissingNumbers(nums, index) < k) {
            index++;
        }

        // k-th missing number is greater than nums[idx - 1]
        // and less than nums[idx]
        return nums[index-1] + k - countMissingNumbers(nums, index-1);

    }

    private int countMissingNumbers(int[] nums, int endIndex) {

        return nums[endIndex]-nums[0] - endIndex;
    }

    public static void main(String args[]) {
        MissingElementInSortedArray obj = new MissingElementInSortedArray();
        System.out.println("Input: [4,7,9,10], k=2 \nOutput: " + obj.missingElement(new int[]{4,7,9,10}, 2));
        System.out.println("Input: [4,7,9,10], k=4 \nOutput: " + obj.missingElement(new int[]{4,7,9,10}, 4));
        System.out.println("Input: [1,2,4], k=3 \nOutput: " + obj.missingElement(new int[]{1,2,4}, 3));
    }
}
