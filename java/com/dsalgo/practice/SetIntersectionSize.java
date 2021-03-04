package com.ds.practice;

import java.util.Arrays;

/**
 * An integer interval [a, b] (for integers a < b) is a set of
 * all consecutive integers from a to b, including a and b.
 *
 * Find the minimum size of a set S such that for every integer interval A in intervals,
 * the intersection of S with A has a size of at least two.
 *
 * Input: intervals = [[1,3],[1,4],[2,5],[3,5]]
 * Output: 3
 * Explanation: Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.
 * Also, there isn't a smaller size set that fulfills the above condition.
 * Thus, we output the size of this set, which is 3.
 *
 * Input: intervals = [[1,2],[2,3],[2,4],[4,5]]
 * Output: 5
 * Explanation: An example of a minimum sized set is {1, 2, 3, 4, 5}.
 *
 */
public class SetIntersectionSize {
    public static int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;
        int[][] intervals1 =  intervals;
        //Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0]-a[0]: a[1]-b[1]);

        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(b[0], a[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
System.out.println(Arrays.deepToString(intervals));
//        Arrays.sort(intervals1, (a, b) -> a[0] != b[0] ? a[0]-b[0]: a[1]-b[1]);
//        System.out.println(Arrays.deepToString(intervals1));

        int result = 2;
        int lastPoint = intervals[0][1];
        int prevPoint = intervals[0][1] - 1;

        System.out.println("     lp="+lastPoint+",pr="+prevPoint + " intv: ["+intervals[0][0]+","+intervals[0][1]+"] count: " + result);
        for(int i=0; i<n; i++) {
            // if last interval and current interval is disjoint then
            // we will need 2 numbers from the current interval
            if (lastPoint - intervals[i][0] < 0)  {
                lastPoint = intervals[i][1];
                prevPoint = lastPoint - 1;
                result += 2;
            } else if (lastPoint >= intervals[i][0] && prevPoint < intervals[i][0]) {
                // if  one number  is common, like [1,3][3,6]
                // 3 is common and 3 has be counted when processed [1,3]
                // so to have 2 numbers common in [3,6] we will need one more number between
                // [4-6] as 3 is already counter
                prevPoint = lastPoint;
                lastPoint = intervals[i][1];
                result += 1;
            }

            System.out.println("i=" + i + ", lp="+lastPoint+",pr="+prevPoint + " intv: ["+intervals[i][0]+","+intervals[i][1]+"] count: " + result);
        }

        return result;
    }

    public static int intersectionSizeTwo1(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(b[0], a[0]);
            }

            return Integer.compare(a[1], b[1]);
        });

        int prevPoint = intervals[0][1] - 1;
        int lastPoint = intervals[0][1];
        int answer = 2;

        for (int[] interval : intervals) {
            if (lastPoint - interval[0] < 0) {
                lastPoint = interval[1];
                prevPoint = lastPoint - 1;
                answer += 2;
            } else if (lastPoint >= interval[0] && prevPoint < interval[0]) {
                prevPoint = lastPoint;
                lastPoint = interval[1];
                answer += 1;
            }
        }

        return answer;
    }

    public static void main(String args[]) {
//        System.out.println("\nInput: [[1,3],[1,4],[2,5],[3,5]] \nOutput: " + SetIntersectionSize.intersectionSizeTwo(new int[][]{{1,3},{1,4},{2,5},{3,5}}));
//
//        System.out.println("\nInput: [[1,2],[2,3],[2,4],[4,5]] \nOutput: " + SetIntersectionSize.intersectionSizeTwo(new int[][]{{1,2},{2,3},{2,4},{4,5}}));
//
//        System.out.println("\nInput: [[2,10],[3,7],[3,15],[4,11],[6,12],[6,16],[7,8],[7,11],[7,15],[11,12]] \nOutput: " + SetIntersectionSize.intersectionSizeTwo(new int[][]{{2,10},{3,7},{3,15},{4,11},{6,12},{6,16},{7,8},{7,11},{7,15},{11,12}}));
//        System.out.println("\nInput: [[2,10],[3,7],[3,15],[4,11],[6,12],[6,16],[7,8],[7,11],[7,15],[11,12]] \nOutput: " + SetIntersectionSize.intersectionSizeTwo1(new int[][]{{2,10},{3,7},{3,15},{4,11},{6,12},{6,16},{7,8},{7,11},{7,15},{11,12}}));
//
//
//        System.out.println("\nInput: [[1,3],[3,7],[5,7],[7,8]] \nOutput: " + SetIntersectionSize.intersectionSizeTwo(new int[][]{{1,3},{3,7},{5,7},{7,8}}));

        System.out.println("\nInput: [[1,3],[3,7],[2,7],[2,5],[5,7],[7,8]] \nOutput: " + SetIntersectionSize.intersectionSizeTwo(new int[][]{{1,3},{3,7},{2,7},{2,5},{5,7},{7,8}}));

        System.out.println("\n  Input: [[1,3],[3,7],[2,7],[2,5],[5,7],[7,8]] \nOutput: " + SetIntersectionSize.intersectionSizeTwo1(new int[][]{{1,3},{3,7},{2,7},{2,5},{5,7},{7,8}}));

    }
}
