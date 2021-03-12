package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two lists of intervals, find the intersection of these two lists.
 * Each list consists of disjoint intervals sorted on their start time.
 *
 * Input: arr1=[[1, 3], [5, 6], [7, 9]], arr2=[[2, 3], [5, 7]]
 * Output: [2, 3], [5, 6], [7, 7]
 * Explanation: The output list contains the common intervals between the two lists.
 *
 * Input: arr1=[[1, 3], [5, 7], [9, 12]], arr2=[[5, 10]]
 * Output: [5, 7], [9, 10]
 * Explanation: The output list contains the common intervals between the two lists.
 *
 *
 */
public class IntervalsIntersection {

    public static int[][] intersect(int[][] ar1, int[][] ar2) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(ar1, (a, b) -> a[0]-b[0]);
        Arrays.sort(ar2, (a, b) -> a[0]-b[0]);
        int n = ar1.length, m = ar2.length, i = 0, j=0;

        while(i < n && j < m) {
            // continue if not overlapped
            if (ar1[i][1] < ar2[j][0] || ar2[j][1] < ar1[i][0]) {
                if (ar1[i][1] < ar2[j][1]) {
                    i++;
                } else {
                    j++;
                }
                continue;
            } else {
                int[] overlap = new int[2];
                overlap[0] = Math.max(ar1[i][0], ar2[j][0]);
                overlap[1] = Math.min(ar1[i][1], ar2[j][1]);
                res.add(overlap);
            }

            if (ar1[i][1] < ar2[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    public static void main(String args[]) {
        System.out.println("\nInput: arr1=[[1, 3], [5, 6], [7, 9]], arr2=[[2, 3], [5, 7]] \nOutput: " + Arrays.deepToString(IntervalsIntersection.intersect(new int[][]{{1, 3}, {5, 6}, {7, 9}}, new int[][]{{2, 3}, {5, 7}})));
        System.out.println("\nInput: arr1=[[1, 3], [5, 7], [9, 12]], arr2=[[5, 10]] \nOutput: " + Arrays.deepToString(IntervalsIntersection.intersect(new int[][]{{1, 3}, {5, 7}, {9, 12}}, new int[][]{{5, 10}})));
    }
}
