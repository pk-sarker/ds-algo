package com.dsalgo.practice;

/**
 * Given an array of numbers sorted in an ascending order, find the ceiling of a given number ‘key’.
 * The ceiling of the ‘key’ will be the smallest element in the given array greater than or equal to
 * the ‘key’.
 *
 * Write a function to return the index of the ceiling of the ‘key’. If there isn’t any ceiling
 * return -1.
 *
 * Input: [4, 6, 10], key = 6
 * Output: 1
 * Explanation: The smallest number greater than or equal to '6' is '6' having index '1'.
 *
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: 4
 * Explanation: The smallest number greater than or equal to '12' is '15' having index '4'.
 *
 * Input: [4, 6, 10], key = 17
 * Output: -1
 * Explanation: There is no number greater than or equal to '17' in the given array.
 *
 * Input: [4, 6, 10], key = -1
 * Output: 0
 * Explanation: The smallest number greater than or equal to '-1' is '4' having index '0'.
 *
 */
public class CeilingOfANumber {

    public static int searchCeilingOfANumber(int[] arr, int key) {
        // out of range
        if (key > arr[arr.length-1]) {
            return -1;
        }

        int left = 0, right = arr.length-1;

        while(left <= right) {
            int mid = left + (right - left)/2;
            if (key < arr[mid]) {
                right = mid - 1;
            } else if (key > arr[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }


        }
        // since the loop is running until 'start <= end', so at the end of the while loop, 'start == end+1'
        // we are not able to find the element in the given array, so the next big number will be arr[start]

        return left;
    }
    public static void main(String args[]) {
        System.out.println("\nInput: [1, 3, 8, 10, 15] key=12\nOutput: " + CeilingOfANumber.searchCeilingOfANumber(new int[]{1, 3, 8, 10, 15}, 12));

        System.out.println("\nInput: [1, 3, 8, 10, 15] key=8\nOutput: " + CeilingOfANumber.searchCeilingOfANumber(new int[]{1, 3, 8, 10, 15}, 8));

        System.out.println("\nInput: [1, 3, 8, 10, 15] key=-1\nOutput: " + CeilingOfANumber.searchCeilingOfANumber(new int[]{1, 3, 8, 10, 15}, -1));
    }
}
