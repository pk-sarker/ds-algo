package com.dsalgo.practice;

import java.util.Arrays;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 */
public class SortColors {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int redIndex = 0, blueIndex = nums.length-1, currentIndex = 0;

        while(currentIndex < blueIndex) {

            if (nums[currentIndex] == 0) {
                swap(nums, currentIndex, redIndex);
                redIndex++;
            } else if (nums[currentIndex] == 2) {
                swap(nums, currentIndex, blueIndex);
                blueIndex--;
            }
            currentIndex++;
        }
    }
    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    public static void main(String args[]) {
        SortColors obj = new SortColors();
        int[] num1 = new int[]{2,0,2,1,1,0};

        System.out.println("Input: " + Arrays.toString(num1));
        obj.sortColors(num1);
        System.out.println("Output: " + Arrays.toString(num1));
    }
}
