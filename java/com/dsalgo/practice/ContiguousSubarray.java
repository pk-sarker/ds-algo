package com.dsalgo.practice;

import java.util.Arrays;

/**
 * Contiguous Subarrays
 * You are given an array arr of N integers. For each index i, you are required to determine the number of contiguous subarrays that fulfill the following conditions:
 * The value at index i must be the maximum element in the contiguous subarrays, and
 * These contiguous subarrays must either start from or end on index i.
 * Signature
 * int[] countSubarrays(int[] arr)
 * Input
 * Array arr is a non-empty list of unique integers that range between 1 to 1,000,000,000
 * Size N is between 1 and 1,000,000
 * Output
 * An array where each index i contains an integer denoting the maximum number of contiguous subarrays of arr[i]
 * Example:
 * arr = [3, 4, 1, 6, 2]
 * output = [1, 3, 1, 5, 1]
 * Explanation:
 * For index 0 - [3] is the only contiguous subarray that starts (or ends) with 3, and the maximum value in this subarray is 3.
 * For index 1 - [4], [3, 4], [4, 1]
 * For index 2 - [1]
 * For index 3 - [6], [6, 2], [1, 6], [4, 1, 6], [3, 4, 1, 6]
 * For index 4 - [2]
 * So, the answer for the above input is [1, 3, 1, 5, 1]
 */
public class ContiguousSubarray {

    public static int[] countSubarrays(int[] arr) {
        if (arr.length <= 0) {
            return new int[]{};
        }
        int[] result = new int[arr.length];
        Arrays.fill(result, 0);
        int index = 0;
        for(int i = 0; i< arr.length; i++) {
            result[index]++;
            if (i-1 >= 0) {
                int j = i-1;
                while(j>=0) {
                    if (arr[j] > arr[i]) {
                        break;
                    }
                    result[index]++;
                    j--;
                }
            }

            if (i+1 < arr.length) {
                int j = i+1;
                while(j<arr.length) {
                    if (arr[j] > arr[i]) {
                        break;
                    }
                    result[index]++;
                    j++;
                }
            }
            index++;
        }

        return result;
    }
    public static void main(String args[]) {
        System.out.println("\nInput: [3, 4, 1, 6, 2] \nOutput: " + Arrays.toString(ContiguousSubarray.countSubarrays(new int[]{3, 4, 1, 6, 2})));
    }
}
