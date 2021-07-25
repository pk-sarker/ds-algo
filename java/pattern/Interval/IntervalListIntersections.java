package pattern.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi]
 * and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a < b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a
 * closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 *
 * Input: firstList = [], secondList = [[4,8],[10,12]]
 * Output: []
 *
 * Input: firstList = [[1,7]], secondList = [[3,10]]
 * Output: [[3,7]]
 */
public class IntervalListIntersections {

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int n = firstList.length, m = secondList.length;
        int i = 0, j = 0;

        List<int[]> res = new ArrayList<>();
        while(i<n && j<m) {
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);
            if (start <= end) {
                res.add(new int[]{start, end});
            }
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
    public static void main(String args[]) {
        //int[][] res = IntervalListIntersections.intervalIntersection(new  int[][]{{1,3}}, new int[][]{{2,4}});
        System.out.println("\nInput: l1=[[1,3]] l2=[[2,4]] \nOutput: " + Arrays.deepToString(IntervalListIntersections.intervalIntersection(new  int[][]{{1,3}}, new int[][]{{2,4}})));

        System.out.println("\nInput: l1=[[0,2],[5,10],[13,23],[24,25]] l2=[[1,5],[8,12],[15,24],[25,26]] \nOutput: " + Arrays.deepToString(IntervalListIntersections.intervalIntersection(new  int[][]{{0,2},{5,10},{13,23},{24,25}}, new int[][]{{1,5},{8,12},{15,24},{25,26}})));

    }
}
