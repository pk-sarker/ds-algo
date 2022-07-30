package pattern.Interval;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * There is a long and thin painting that can be represented by a number line.
 * You are given a 0-indexed 2D integer array paint of length n,
 * where paint[i] = [starti, endi]. This means that on the ith day you need to paint
 * the area between starti and endi.
 *
 * Painting the same area multiple times will create an uneven painting so you only
 * want to paint each area of the painting at most once.
 *
 * Return an integer array worklog of length n, where worklog[i] is the amount of
 * new area that you painted on the ith day.
 *
 * Input: paint = [[1,4],[4,7],[5,8]]
 * Output: [3,3,1]
 *
 * Input: paint = [[1,4],[5,8],[4,7]]
 * Output: [3,3,1]
 *
 * Input: paint = [[1,5],[2,4]]
 * Output: [4,0]
 *
 * Solution:
 *
 * Jump Line
 *
 * [[1,4],[5,9],[4,10]]
 *
 * -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
 * 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 <- index
 *
 * [1,4]
 * 4  4  4  4                                          painted = 4
 * -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
 *
 * [5,9]
 * 4  4  4  4  5  5  5  5  5                              painted = 4 + 5 = 9
 * -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
 *
 * [4, 10]
 * 4  4  4  10  5  5  5  5 5  10                           painted = 9+1 = 10
 * -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
 *
 */
public class AmountOfNewAreaPaintedEachDay {

    /**
     *
     * Complexity Analysis
     *
     * Time: O(n + m), where m is the length of the fence.
     * Memory: O(n + m).
     */
    public int[] amountPainted(int[][] paint) {
        int[] paintByDay = new int[paint.length];
        Arrays.fill(paintByDay, 0);
        int[] line = new int[50001];
        Arrays.fill(line, 0);

        for(int i=0; i<paint.length; i++) {
            int start = paint[i][0], end = paint[i][1];

            while(start < end){
                // next position to paint/check
                int next = Math.max(start+1, line[start]);
                // if start is not painted before than line[start] will be zero
                // and we can paint that in current day
                if (line[start] == 0){
                    paintByDay[i] += 1;
                }

                line[start] = Math.max(end, line[start]);
                // line[start] will be pointing to the end of a range,
                // if its already painted then it will place the max of the current end and last end
                // For, [1,5],[2,7], while painting 2nd day([2,7]), on line[2] = max(5,7) = 7
                // [1,5] => 5 5 5 5 5 - -
                // [2,7] => 5 7 7 7 7 7 7

                start = next;
            }
        }

        return paintByDay;
    }

    /**
     * Complexity Analysis:
     * Time: O(n log m), where m is the length of the fence.
     * Memory: O(n). We have up to n intervals to store.
     */
    public int[] amountPaintedByMergingIntervals(int[][] paint) {
        int[] paintByDay = new int[paint.length];
        Arrays.fill(paintByDay, 0);
        TreeMap<Integer, Integer> intervals = new TreeMap<>();

        for (int i=0; i<paint.length; i++) {
            int[] currentInterval = paint[i];
            int start = currentInterval[0], end = currentInterval[1];
            int paintRange = end - start;
            Map.Entry<Integer, Integer> floor = intervals.floorEntry(currentInterval[0]);
            if (floor != null) {
                if (floor.getValue() >= end) {
                    // current interval is covered
                    // there is an interval before current one whose end is >= current end
                    // that means current interval is overlapped and painting is already done
                    continue;
                }
                if (floor.getValue() >= start) {
                    // some part of current interval is overlapped with previous one
                    // paintRange = end - floor.getValue();
                    paintRange -= floor.getValue() - start;
                    // example [1,5],[3,10]
                    // for [3,10] = paintRange = 10-3 = 7
                    // paintRange -= (5 - 3) = 5, 2 is overlapped
                    intervals.remove(floor.getKey());
                    start = floor.getKey();
                }
            }
            Map.Entry<Integer, Integer> ceiling = intervals.ceilingEntry(currentInterval[0]);

            // if there is any overlap on the right between end and next intervals
            // [3, 25] -> [4, 8], [10,20], [20, 28]
            while (ceiling != null && ceiling.getKey() <= end) {
                paintRange -= (Math.min(end, ceiling.getValue()) - ceiling.getKey());
                // end - start, for overlap start is the start of next interval/ceiling
                // and end will lowest the current end and next end
                // [3, 15] and [4,8] = overlap = 8 - 4 = 4
                // [3, 15] and [4,20] = overlap = 15 - 4 = 9
                // [3, 12] and [4,20] = overlap = 12 - 4 = 8
                intervals.remove(ceiling.getKey());
                end = Math.max(end, ceiling.getValue());
                // if current end is less than ceiling, [3, 15] and [4,8], end = 15
                // if current end is greater than ceiling, [3, 15] and [4, 20], end = 20
                ceiling = intervals.ceilingEntry(currentInterval[0]);
            }

            paintByDay[i] = paintRange;
            intervals.put(start, end);
        }
        return paintByDay;
    }

    public static void main(String args[]) {
        AmountOfNewAreaPaintedEachDay obj = new AmountOfNewAreaPaintedEachDay();
        System.out.println("Input: paint = [[1,4],[4,7],[5,8]]");
        System.out.println("Output: "+Arrays.toString(obj.amountPaintedByMergingIntervals(new int[][]{{1,4},{4,7},{5,8}})));

        System.out.println("Input: paint = [[1,4],[5,8],[4,7]]");
        System.out.println("Output: "+Arrays.toString(obj.amountPaintedByMergingIntervals(new int[][]{{1,4},{5,8},{4,7}})));
    }
}
