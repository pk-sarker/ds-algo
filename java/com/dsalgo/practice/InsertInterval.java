package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of non-overlapping intervals sorted by their start time, insert a given interval at the correct
 * position and merge all necessary intervals to produce a list that has only mutually exclusive intervals.
 *
 * Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,6]
 * Output: [[1,3], [4,7], [8,12]]
 * Explanation: After insertion, since [4,6] overlaps with [5,7], we merged them into one [4,7].
 *
 * Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,10]
 * Output: [[1,3], [4,12]]
 * Explanation: After insertion, since [4,10] overlaps with [5,7] & [8,12], we merged them into [4,12].
 *
 * Input: Intervals=[[2,3],[5,7]], New Interval=[1,4]
 * Output: [[1,4], [5,7]]
 * Explanation: After insertion, since [1,4] overlaps with [2,3], we merged them into one [1,4].
 */
public class InsertInterval {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        // List<Interval> mergedIntervals = new ArrayList<>();
        //int[][] res = new int[intervals.length][2];
        List<int[]> res = new ArrayList<>();
        //TODO: Write your code here

        // first add the intervals which starts before new interval
        int counter = 0;
        while(counter <  intervals.length && intervals[counter][1] < newInterval[0]) {
            res.add(intervals[counter]);
            counter++;
        }

        // merge overlaps
        while(counter < intervals.length
                && ((intervals[counter][0] <= newInterval[0] && newInterval[1] <= intervals[counter][1])
                || (intervals[counter][0] > newInterval[0] && intervals[counter][0] <= newInterval[1]))) {
            newInterval[0] = Math.min(intervals[counter][0],newInterval[0]);
            newInterval[1] = Math.max(intervals[counter][1],newInterval[1]);
            counter++;
        }

        res.add(newInterval);

        // append remaining intervals
        while(counter <  intervals.length) {
            res.add(intervals[counter++]);
        }

        return res.toArray(new int[res.size()][]);
    }

    public static void main(String args[]) {
        System.out.println("\nInput: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,6] \nOutput: " + Arrays.deepToString(InsertInterval.insert(new int[][]{{1,3}, {5,7}, {8,12}}, new int[]{4, 6})));
        System.out.println("\nInput: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,10] \nOutput: " + Arrays.deepToString(InsertInterval.insert(new int[][]{{1,3}, {5,7}, {8,12}}, new int[]{4, 10})));
    }
}
