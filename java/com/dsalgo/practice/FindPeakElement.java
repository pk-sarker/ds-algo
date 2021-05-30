package com.dsalgo.practice;

/**
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element, and return its index.
 * If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the
 * peak element is 2, or index number 5 where the peak element is 6.
 */
public class FindPeakElement {

    /**
     * Linear solution, O(n) and constant space O(1)
     * Check every pair of elemets
     */
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        // if the array is in ascending order then the last number will be the peek
        return nums.length - 1;
    }

    /**
     * Recursive Binary Search
     * There are 3 cases:
     * 1) numbers are in ascending order, peek will be the last element
     * 2) numbers are in descending order, peek will be the first element
     * 3) numbers are in mixed order, peek will be in the middle
     *
     * find the mid,
     * if num[mid] in ascending slop, num[mid] < num[mid+1]
     *  then the peek will be right of mid
     *
     * if num[mid] in descending slop, num[mid] > num[mid+1]
     *  then the peek will be left of mid
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(log n)
     */
    public int findPeakElement2(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    int search(int[] nums, int left, int right) {
        if (left == right) {
            return left;
        }
        int mid = left + (right-left)/2;

        if (nums[mid] > nums[mid+1]) {
            return search(nums, left, mid);
        }
        return search(nums, mid+1, right);
    }

    /**
     * Binary search (Optimized) with iterative approach
     */
    public int findPeakElement3(int[] nums) {
        int left = 0, right = nums.length-1;
        int mid = -1;
        while(left<right) {
            mid = left + (right-left)/2;
            if (nums[mid] > nums[mid+1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    public static void main(String args[]) {
        FindPeakElement obj = new FindPeakElement();
        System.out.println("Input: [1,2,1,3,5,6,4] \nPeek: " + obj.findPeakElement(new int[]{1,2,1,3,5,6,4}));
        System.out.println("\nBinary Search\nInput: [1,2,1,3,5,6,4] \nPeek: " + obj.findPeakElement2(new int[]{1,2,1,3,5,6,4}));
        System.out.println("Input: [1,2,3,4,5,6] \nPeek: " + obj.findPeakElement2(new int[]{1,2,3,4,5,6}));
        System.out.println("Input: [6,5,4,3,2,1] \nPeek: " + obj.findPeakElement2(new int[]{6,5,4,3,2,1}));

        System.out.println("\nBinary Search (Optimized)\nInput: [1,2,1,3,5,6,4] \nPeek: " + obj.findPeakElement3(new int[]{1,2,1,3,5,6,4}));
        System.out.println("Input: [1,2,3,4,5,6] \nPeek: " + obj.findPeakElement3(new int[]{1,2,3,4,5,6}));
        System.out.println("Input: [6,5,4,3,2,1] \nPeek: " + obj.findPeakElement3(new int[]{6,5,4,3,2,1}));
    }
}
