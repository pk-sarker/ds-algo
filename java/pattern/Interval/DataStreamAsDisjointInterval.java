package pattern.Interval;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an,
 * summarize the numbers seen so far as a list of disjoint intervals.
 *
 * Implement the SummaryRanges class:
 *
 * SummaryRanges() Initializes the object with an empty stream.
 * void addNum(int val) Adds the integer val to the stream.
 * int[][] getIntervals() Returns a summary of the integers in the
 * stream currently as a list of disjoint intervals [starti, endi].
 *
 *
 */
public class DataStreamAsDisjointInterval {

    TreeMap<Integer, Integer> stream;
    public DataStreamAsDisjointInterval() {
        stream = new TreeMap<>();
    }

    public void addNum(int val) {
        if (stream.isEmpty()) {
            stream.put(val, val);
            return;
        }
        if (stream.containsKey(val)) {
            return;
        }

        Map.Entry<Integer, Integer> floor = stream.floorEntry(val);
        Map.Entry<Integer, Integer> ceiling = stream.ceilingEntry(val);

        if (floor != null && ceiling != null && floor.getValue()+1 == val && val+1 == ceiling.getKey()) {
            stream.remove(floor.getKey());
            stream.remove(ceiling.getKey());
            stream.put(floor.getKey(), ceiling.getValue());
        }
        else if(floor != null && floor.getValue()+1>=val) {
            stream.remove(floor.getKey());
            stream.put(floor.getKey(), Math.max(val, floor.getValue()));

        } else if(ceiling != null && val+1==ceiling.getKey()) {
            stream.remove(ceiling.getKey());
            stream.put(val, ceiling.getValue());
        } else {
            stream.put(val, val);
        }
    }

    public int[][] getIntervals() {
        int[][] intervals = new int[stream.size()][2];
        int i = 0;
        for(Map.Entry<Integer, Integer> interval : stream.entrySet()) {
            intervals[i++] = new int[]{interval.getKey(), interval.getValue()};
        }
        return intervals;
    }

    public static void main(String args[]) {
        DataStreamAsDisjointInterval obj = new DataStreamAsDisjointInterval();
        obj.addNum(1);
        System.out.println(Arrays.deepToString(obj.getIntervals()));

        obj.addNum(3);
        System.out.println(Arrays.deepToString(obj.getIntervals()));
        obj.addNum(2);
        System.out.println(Arrays.deepToString(obj.getIntervals()));

        obj.addNum(6);
        System.out.println(Arrays.deepToString(obj.getIntervals()));

        obj.addNum(8);
        System.out.println(Arrays.deepToString(obj.getIntervals()));
        obj.addNum(4);
        System.out.println(Arrays.deepToString(obj.getIntervals()));
    }
}
