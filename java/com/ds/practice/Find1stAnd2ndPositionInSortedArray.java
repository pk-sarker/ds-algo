package com.ds.practice;

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

    public static int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int[] res = new int[]{-1, -1};
        if (n <= 1) {
            return n == 0 ? res: nums[0] == target ? new int[]{0,0}:res;
        }
        if (n == 2) {
            res[0] = nums[0] == target ? 0: nums[1]==target ? 1:-1;
            res[1] = nums[1] == target ? 1: nums[1]==target ? 1:-1;
            for(int i=0;i<2; i++) {
                if (res[0] == -1 && nums[i] == target) {
                    res[0] = i;
                }

                if (nums[i] == target) {
                    res[1] = i;
                }
            }
            return res;
        }
        if (nums[n-1] == target) {
            res[1] = n-1;
        }
        int low = 0, high = n-1, mid=0;

        while(low < high) {
            mid = low + (high - low)/2;
            //System.out.println("low: " + low + " high: "+high + " mid: " + mid + " midn: " + nums[mid]);
            if (mid == 0 ) {

            }
            if (nums[mid] == target) {
                if (mid >=1 && nums[mid-1] < target) {
                    res[0] = mid;
                } else  {
                    for(int i=mid; i>=low; i--) {
                        if (nums[i] == target) {
                            res[0] = i;
                        } else {
                            break;
                        }
                    }
                }
                if (mid <= n-2 && nums[mid+1] > target) {
                    res[1] = mid;
                } else {
                    for(int i=mid+1; i<=high; i++) {
                        if (nums[i] == target) {
                            res[1] = i;
                        } else {
                            break;
                        }
                    }
                }
                if (mid == n-1) {
                    res[1] = mid;
                }
                break;
            } else if ( nums[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        if (res[1] >=0 && res[0] <0) {
            res[0] = res[1];
        }
        return res;
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

        System.out.println("\nInput: [1,2,3] target=3\nOutput: " + Arrays.toString(Find1stAnd2ndPositionInSortedArray.searchRange(new int[]{1,2,3}, 3)));

    }
}
