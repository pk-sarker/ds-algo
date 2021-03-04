package com.ds.practice;

import java.util.Arrays;

/**
 * Given a non-empty integer array of size n, find the minimum number of moves required to make
 * all array elements equal, where a move is incrementing n - 1 elements by 1.
 *
 * Input: [1,2,3]
 * Output: 3
 * [1,2,3] -> [2,3,3] -> [3,4,3] -> [4,4,4]
 *
 * Input: [2,2,3]
 * Output: 1
 * [2,2,3] -> [3,3,3]
 *
 * Input: [4,1,2]
 * Output: 4
 * [4,1,2] -> [4,2,3] -> [4,3,4] -> [4,4,5] -> [5,5,5]
 *
 */
public class MinimumMovesToEqualArray {

    public static int minMovesBruteForce(int[] nums) {
        int n = nums.length, count = 0;
        while(true) {
            // find maximum and minimum number
            int max = 0, min = 0;
            for(int i=0; i<n; i++) {
                if (nums[i] < nums[min] ) {
                    min = i;
                }
                if (nums[i] > nums[max] ) {
                    max = i;
                }
            }
            if (nums[max] == nums[min]){
                break;
            }
            for(int i=0; i<n; i++) {
                if (i != max) {
                    nums[i]++;
                }
            }
            count++;
        }
        return count;
    }

    public static int minMovesBruteForceOptimized(int[] nums) {
        int n = nums.length, count = 0;
        while(true) {
            // find maximum and minimum number
            int max = 0, min = 0;
            for(int i=0; i<n; i++) {
                if (nums[i] < nums[min] ) {
                    min = i;
                }
                if (nums[i] > nums[max] ) {
                    max = i;
                }
            }

            if (nums[max] == nums[min]){
                break;
            }
            int diff = nums[max] - nums[min];
            for(int i=0; i<n; i++) {
                if (i != max) {
                    nums[i] += diff;
                }
            }
            count += diff;
        }
        return count;
    }

    public static int minMovesWithSorting(int[] nums) {
        int n = nums.length, count = 0;
        Arrays.sort(nums);
        for (int i=0; i<n; i++) {
            count += nums[i] - nums[0];
        }
        return count;
    }

    public static int minMoves(int[] nums) {
        int n = nums.length, count = 0, min = Integer.MAX_VALUE;
        for (int i=0; i<n; i++) {
            min = Math.min(nums[i], min);
        }
        for (int i=0; i<n; i++) {
            count += nums[i] - min;
        }
        return count;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: [1,2,3] \nOutput: " + MinimumMovesToEqualArray.minMovesBruteForce(new int[]{1,2,3}));
        System.out.println("\nInput: [1,2,3] \nOutput: " + MinimumMovesToEqualArray.minMovesBruteForceOptimized(new int[]{1,2,3}));
        System.out.println("\nInput: [1,2,4] \nOutput: " + MinimumMovesToEqualArray.minMovesBruteForce(new int[]{1,2,4}));
        System.out.println("\nInput: [2,2,3] \nOutput: " + MinimumMovesToEqualArray.minMovesBruteForce(new int[]{2,2,3}));
        System.out.println("\nInput: [2,2,2] \nOutput: " + MinimumMovesToEqualArray.minMovesBruteForce(new int[]{2,2,2}));
        System.out.println("\nInput: [4,1,2] \nOutput: " + MinimumMovesToEqualArray.minMovesBruteForce(new int[]{4,1,2}));
        System.out.println("\nInput: [2,1,4] \nOutput: " + MinimumMovesToEqualArray.minMovesBruteForce(new int[]{2,1,4}));
        System.out.println("\nInput: [2,1,4] \nOutput: " + MinimumMovesToEqualArray.minMovesBruteForceOptimized(new int[]{2,1,4}));
        System.out.println("\nInput: [2,1,4] \nOutput: " + MinimumMovesToEqualArray.minMovesWithSorting(new int[]{2,1,4}));

        System.out.println("\nInput: [2,1,4] \nOutput: " + MinimumMovesToEqualArray.minMoves(new int[]{2,1,4}));


    }
}
