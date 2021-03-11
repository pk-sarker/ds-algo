package com.dsalgo.practice;

/**
 * Given n numbers in a list. Numbers are between 0 to n, Since the array has only n numbers out of the
 * total n+1 numbers, find the missing number.
 *
 * Input: [4, 0, 3, 1]
 * Output: 2
 *
 * Input: [8, 3, 5, 2, 4, 6, 0, 1]
 * Output: 7
 *
 */
public class FindMissingNumber {

    public static int missingNumber(int[] nums) {

        int ptr1 = 0;
        while(ptr1 < nums.length)  {
            if (nums[ptr1] < nums.length && nums[ptr1] !=  nums[nums[ptr1]]) {
                int t = nums[nums[ptr1]];
                nums[nums[ptr1]] = nums[ptr1];
                nums[ptr1] = t;
            } else {
                ptr1++;
            }
        }

        for(int i=0; i<nums.length;i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String args[]) {
        int[] nums = new int[]{3,0,1};
        System.out.println("\nInput: [3,0,1]  \nOutput: " +FindMissingNumber.missingNumber(nums));
    }
}
