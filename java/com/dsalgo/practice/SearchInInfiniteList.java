package com.dsalgo.practice;

/**
 * Given an infinite sorted array (or an array with unknown size), find if a given number ‘key’ is present
 * in the array. Write a function to return the index of the ‘key’ if it is present in the array,
 * otherwise return -1.
 *
 * Input: [4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30], key = 16
 * Output: 6
 * Explanation: The key is present at index '6' in the array.
 *
 * Input: [4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30], key = 11
 * Output: -1
 * Explanation: The key is not present in the array.
 *
 * Input: [1, 3, 8, 10, 15], key = 15
 * Output: 4
 * Explanation: The key is present at index '4' in the array.
 *
 * Input: [1, 3, 8, 10, 15], key = 200
 * Output: -1
 * Explanation: The key is not present in the array.
 *
 */
public class SearchInInfiniteList {

    public static int search(ArrayReader reader, int key) {
        // find the proper bounds first
        int start = 0, end = 1;
        while (reader.get(end) < key) {
            int newStart = end + 1;
            end += (end - start + 1) * 2; // increase to double the bounds size
            start = newStart;
        }
        return binarySearch(reader, key, start, end);
    }

    private static int binarySearch(ArrayReader reader, int key, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < reader.get(mid)) {
                end = mid - 1;
            } else if (key > reader.get(mid)) {
                start = mid + 1;
            } else { // found the key
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        ArrayReader reader = new ArrayReader(new int[] { 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30 });
        System.out.println(SearchInInfiniteList.search(reader, 16));
        System.out.println(SearchInInfiniteList.search(reader, 11));
        reader = new ArrayReader(new int[] { 1, 3, 8, 10, 15 });
        System.out.println(SearchInInfiniteList.search(reader, 15));
        System.out.println(SearchInInfiniteList.search(reader, 200));
    }

}
