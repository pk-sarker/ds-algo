package com.dsalgo.practice;

import java.util.Arrays;

/**
 * Given an array containing n objects. Each object, when created, was assigned a unique number from 1 to n
 * based on their creation sequence. This means that the object with sequence number 3 was created just
 * before the object with sequence number 4.
 *
 * Write a function to sort the objects in-place on their creation sequence number in O(n) and without
 * any extra space. For simplicity, let’s assume we are passed an integer array containing only
 * the sequence numbers, though each number is actually an object.
 *
 * Input: [3, 1, 5, 4, 2]
 * Output: [1, 2, 3, 4, 5]
 *
 * Input: [2, 6, 4, 3, 1, 5]
 * Output: [1, 2, 3, 4, 5, 6]
 *
 * Input: [1, 5, 6, 4, 3, 2]
 * Output: [1, 2, 3, 4, 5, 6]
 */
public class CyclicSort {

    public static int[] sort(int[] nums) {
        int start = 0;

        while(start < nums.length) {
            int j = nums[start]-1;
            if (nums[start] != nums[j])  {
                int t = nums[start];
                nums[start] = nums[j];
                nums[j] = t;
            } else {
                start++;
            }
        }
        return nums;
    }

    public static void main(String args[]) {
        int[] nums = new int[]{3, 1, 5, 4, 2};
        System.out.println("\nInput: [3, 1, 5, 4, 2]  \nOutput: " + Arrays.toString(CyclicSort.sort(nums)));
    }
}
