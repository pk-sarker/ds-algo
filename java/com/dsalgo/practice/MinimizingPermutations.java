package com.dsalgo.practice;

/**
 * In this problem, you are given an integer N, and a permutation,
 * P of the integers from 1 to N, denoted as (a_1, a_2, ..., a_N). You want to
 * rearrange the elements of the permutation into increasing order, repeatedly making
 * the following operation:
 * Select a sub-portion of the permutation, (a_i, ..., a_j), and reverse its order.
 * Your goal is to compute the minimum number of such operations required to return the
 * permutation to increasing order.
 */
public class MinimizingPermutations {

    public static int minOperations(int[] arr) {
        // Write your code here
        int n = arr.length;
        boolean sorted = false;
        int start =-1, end = -1;
        // sortedTill will store the index till which the array is sorted
        int sortedTill = -1;
        int count = 0;
        while(!sorted) {
            // find decreasing sequence
            for(int i=0; i<n-1; i++) {
                if (arr[i] > arr[i+1]) {
                    if (start == -1) { // initialization of the decreasing sequence
                        start = i;
                    }
                    end = i+1;
                } else {
                    if (start > -1) { // break if decreasing sequence breakss, ie. 4,3,1,5
                        break;
                    }
                }

                if (arr[i] < arr[i+1] && arr[i+1]-arr[i] == 1) {
                    sortedTill = i+1;
                }
            }
            // breaks the loop if the array is sorted till the last index
            if (sortedTill==n-1) {
                sorted = true;
            }
            // reverse by swapping
            if (start> -1 && end > -1 && end > start) {
                while(start<end) {
                    int t=arr[start];
                    arr[start] = arr[end];
                    arr[end] = t;
                    start++;
                    end--;
                }
                count++;
            }
            start = -1;
            end = -1;

        }
        return count;
    }
    public static void main(String args[]) {
        System.out.println(MinimizingPermutations.minOperations(new int[]{3,1,2}));

        System.out.println(MinimizingPermutations.minOperations(new int[]{1, 2, 5, 4, 3}));

        System.out.println(MinimizingPermutations.minOperations(new int[]{4,5,2,3,1}));
    }
}
