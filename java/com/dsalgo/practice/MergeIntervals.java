package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 */


public class MergeIntervals {
    public static int[][] merge1(int[][] intervals) {
        int n = intervals.length;
        // Arrays.sort(intervals, new Comparator((int[] a, int[] b) -> a[0]-b[0]));
        Arrays.sort(intervals, (a, b) -> a[0]-b[0]);
        List<List<Integer>> res = new ArrayList<>();
        int count = 0;
        List<Integer> first = new ArrayList<>();
        first.add(intervals[0][0]);
        first.add(intervals[0][1]);
        res.add(count, first);
        for(int i=1; i<n; i++) {
            List<Integer> intv = res.get(count);
            //if (intv.get(1) >= intervals[i][0] && intv.get(1) <= intervals[i][1]) {
            if (intervals[i][0] >= intv.get(0) && intervals[i][0] <= intv.get(1)) {
                intv.set(1, Math.max(intv.get(1), intervals[i][1]));
                res.set(count, intv);
            } else {
                count++;
                List<Integer> newIntv = new ArrayList<>();
                newIntv.add(intervals[i][0]);
                newIntv.add(intervals[i][1]);
                res.add(newIntv);
            }
        }
        int[][] result = new int[res.size()][2];
        for(int i=0; i<res.size(); i++) {
            result[i][0] = res.get(i).get(0);
            result[i][1] = res.get(i).get(1);
        }
        return result;
    }

    public static int[][] merge(int[][] intervals) {
        int n = intervals.length;
        LinkedList<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        res.add(intervals[0]);

        for(int i = 1; i<n; i++) {
            int[] last = res.getLast();

            if (last[1] < intervals[i][0]) {
                res.add(intervals[i]);
            } else {
                last[1] = Math.max(last[1], intervals[i][1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
    public  static void main(String args[])  {
        //int[][] res = MergeIntervals.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        System.out.println("\nInput: [[1,3],[2,6],[8,10],[15,18]] \nOutput:"+Arrays.deepToString(MergeIntervals.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}})));
        System.out.println("\nInput: [[1,4],[4,5]] \nOutput:"+Arrays.deepToString(MergeIntervals.merge(new int[][]{{1,4},{4,5}})));
        System.out.println("\nInput: [[1,5],[2,4]] \nOutput:"+Arrays.deepToString(MergeIntervals.merge(new int[][]{{1,5},{2,4}})));
    }
}
