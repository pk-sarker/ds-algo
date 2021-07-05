package pattern.Interval;

import java.util.Arrays;
import java.util.LinkedList;

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
    public static int[][] merge(int[][] intervals) {
        int n = intervals.length;
        LinkedList<int[]> res = new LinkedList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        res.add(intervals[0]);

        for(int i = 1; i<n; i++) {
            int[] last = res.getLast();

            // if two intervals doesn't intersect (end of a interval is less than start of another interval)
            //
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
