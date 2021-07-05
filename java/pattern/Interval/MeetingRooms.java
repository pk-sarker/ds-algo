package pattern.Interval;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms {
    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] i1, int[] i2){
                return i1[0]-i2[0];
            }
        });

        Arrays.sort(intervals, (a,b) -> a[0]-b[0]);

        for(int i=0; i<intervals.length-1;i++) {
            // if next meeting is starting before end of current meeting
            if (intervals[i+1][0]<intervals[i][1]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String args[]) {
        System.out.println(MeetingRooms.canAttendMeetings(new int[][]{{0,30}, {5,10}, {15,20}}));
    }
}
