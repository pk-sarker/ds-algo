package ds.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given a list of intervals representing the start and end time of N meetings,
 * find the minimum number of rooms required to hold all the meetings.
 *
 * Meetings: [[1,4], [2,5], [7,9]]
 * Output: 2
 * Explanation: Since [1,4] and [2,5] overlap, we need two rooms to hold these two meetings. [7,9] can
 * occur in any of the two rooms later.
 *
 * Meetings: [[6,7], [2,4], [8,12]]
 * Output: 1
 * Explanation: None of the meetings overlap, therefore we only need one room to hold all meetings.
 *
 * Meetings: [[1,4], [2,3], [3,6]]
 * Output:2
 * Explanation: Since [1,4] overlaps with the other two meetings [2,3] and [3,6], we need two rooms to
 * hold all the meetings.
 *
 * Meetings: [[4,5], [2,3], [2,4], [3,5]]
 * Output: 2
 * Explanation: We will need one room for [2,3] and [3,5], and another room for [2,4] and [4,5].
 *
 */
public class MeetingRoomCount {
    public static int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0]-b[0]);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1]-b[1]);
        minHeap.offer(intervals[0]);
        for(int i=1; i < intervals.length; i++) {
            // start time of current meeting is greater then
            if (intervals[i][0] >= minHeap.peek()[1]) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i]);
        }
        return minHeap.size();
    }

    public static void main(String args[]) {
        System.out.println("\nIntervals = [[0,30],[15,20],[5,10]]\nOutput: " + MeetingRoomCount.minMeetingRooms(new int[][]{{0,30},{15,20},{5,10}}));

        System.out.println("\nIntervals = [13,15],[1,13]]\nOutput: " + MeetingRoomCount.minMeetingRooms(new int[][]{{13,15},{1,13}}));
    }
}
