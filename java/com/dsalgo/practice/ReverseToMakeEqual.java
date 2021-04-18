package com.dsalgo.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Reverse to Make Equal
 * Given two arrays A and B of length N, determine if there is a way to make A equal to B by reversing any subarrays from array B any number of times.
 * Signature
 * bool areTheyEqual(int[] arr_a, int[] arr_b)
 * Input
 * All integers in array are in the range [0, 1,000,000,000].
 * Output
 * Return true if B can be made equal to A, return false otherwise.
 * Example
 * A = [1, 2, 3, 4]
 * B = [1, 4, 3, 2]
 * output = true
 * After reversing the subarray of B from indices 1 to 3, array B will equal array A.
 */
public class ReverseToMakeEqual {

    public static boolean areTheyEqual(int[] array_a, int[] array_b) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<array_a.length; i++) {
            map.put(array_a[i], map.getOrDefault(array_a[i], 0) + 1);

            map.put(array_b[i], map.getOrDefault(array_b[i], 0) - 1);
        }
        System.out.println(map.toString());
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }

        return true;
    }
    public static void main(String args[]) {
        System.out.println("\nInput: \n\tA=[1, 2, 3, 4], \n\tB=[1, 4, 3, 2]\nOutput: " + ReverseToMakeEqual.areTheyEqual(new int[]{1, 2, 3, 4}, new int[]{1, 4, 3, 2}));

        System.out.println("\nInput: \n\tA=[1, 2, 3, 1], \n\tB=[1, 3, 1, 2]\nOutput: " + ReverseToMakeEqual.areTheyEqual(new int[]{1, 2, 3, 1}, new int[]{1, 3, 1, 2}));

        System.out.println("\nInput: \n\tA=[1, 2, 3, 1], \n\tB=[1, 3, 2, 2]\nOutput: " + ReverseToMakeEqual.areTheyEqual(new int[]{1, 2, 3, 1}, new int[]{1, 3, 2, 2}));

        System.out.println("\nInput: \n\tA=[1, 2, 3, 4], \n\tB=[1, 3, 4, 5]\nOutput: " + ReverseToMakeEqual.areTheyEqual(new int[]{1, 2, 3, 4}, new int[]{1, 3, 4, 5}));
    }
}
