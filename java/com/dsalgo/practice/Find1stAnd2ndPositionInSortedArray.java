package com.dsalgo.practice;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * Follow up: Could you write an algorithm with O(log n) runtime complexity?
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 */
public class Find1stAnd2ndPositionInSortedArray {

    public static int[] findRange(int[] nums, int target) {
        int[] result = new int[] { -1, -1 };
        if (nums.length <=0) {
            return result;
        }

        result[0] = findIndex(nums, target, true);
        if (result[0] == -1) {
            return new int[]{-1,-1};
        }
        result[1] = findIndex(nums, target, false);

        return result;
    }

    public static int findIndex(int[] nums, int target, boolean isFirst) {
        int start = 0, end = nums.length-1;
        int index = -1;
        while(start <= end) {
            int mid = start + (end-start)/2;

            if (nums[mid] == target) {
                if (isFirst) {
                    if (mid == start || nums[mid-1] != nums[mid]) {
                        return mid;
                    }
                    end = mid-1;
                } else {
                    if (mid == end || nums[mid+1] != nums[mid]) {
                        return mid;
                    }
                    start = mid+1;
                }

            } else if (nums[mid] > target){
                end = mid-1;
            } else {
                start = mid + 1;
            }
        }
        return index;
    }


    public static void main(String args[]) {
//        System.out.println("\nInput: [5,7,7,8,8,10] target=8\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.searchRange(new int[]{5,7,7,8,8,10}, 8)));
//
//        System.out.println("\nInput: [5,7,7,8,8,10] target=6\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.searchRange(new int[]{5,7,7,8,8,10}, 6)));
//
//        System.out.println("\nInput: [1,2,2,5,7,7,8,8,8,8,8,10] target=8\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.searchRange(new int[]{1,2,2,5,7,7,8,8,8,8,8,10}, 8)));
//
//        System.out.println("\nInput: [1,2] target=2\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.searchRange(new int[]{1,2}, 2)));
//        System.out.println("\nInput: [1,2] target=1\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.searchRange(new int[]{1,2}, 1)));
//        System.out.println("\nInput: [1,1] target=1\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.searchRange(new int[]{1,1}, 1)));
//        System.out.println("\nInput: [1,2,3] target=2\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.searchRange(new int[]{1,2,3}, 2)));

        //System.out.println("\nInput: [1,2,3] target=3\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.searchRange(new int[]{1,2,3}, 3)));

//        System.out.println("\nInput: [1,2,3] target=3\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.findRange(new int[]{1,2,3}, 3)));
//
//        System.out.println("\nInput: [1,2,3] target=4\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.findRange(new int[]{1,2,3}, 4)));
//
//        System.out.println("\nInput: [5,7,7,8,8,10] target=8\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.findRange(new int[]{5,7,7,8,8,10}, 8)));



        System.out.println("\nInput: [5,5,7,7,7,8,8,10] target=8\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.findRange(new int[]{5,5,7,7,7,8,8,10}, 8)));

    }
}
