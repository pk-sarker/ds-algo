package ds.Heap;

import com.dsalgo.practice.common.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees,
 * also in sorted order.
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals,
 * not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and
 * schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer,
 * as they have zero length.
 *
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 *
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 */
public class EmployeeFreeTime {

    /**
     * The idea is to just add all the intervals to the priority queue.
     * (NOTE that it is not matter how many different people are there for the algorithm.
     * becuase we just need to find a gap in the time line.
     *
     * 1. priority queue - sorted by start time, and for same start time sort by either largest end time or smallest (it is not matter).
     * 2. Everytime you poll from priority queue, just make sure it doesn't intersect with previous interval.
     * This mean that there is no coomon interval. Everyone is free time.
     */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        List<Interval> result = new ArrayList<>();
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.start - b.start);
        schedule.forEach(e -> pq.addAll(e));

        Interval lastInterval = pq.poll();
        while(!pq.isEmpty()) {
            if(lastInterval.end < pq.peek().start) { // no intersect
                result.add(new Interval(lastInterval.end, pq.peek().start));
                lastInterval = pq.poll(); // becomes the next temp interval
            }else { // intersect or sub merged
                // if last interval end before current interval then
                //  new last interval will be current which has max end time
                // otherwise keep current last interval
                lastInterval = lastInterval.end < pq.peek().end ? pq.peek() : lastInterval;
                pq.poll();
            }
        }
        return result;
    }
    public static void main(String args[]) {

        EmployeeFreeTime obj = new EmployeeFreeTime();
        List<List<Interval>> sch = new ArrayList<>();
        List<Interval> emp1 = new ArrayList<>(){{
            add(new Interval(1,2));
            add(new Interval(5,6));
        }};
        sch.add(emp1);
        List<Interval> emp2 = new ArrayList<>(){{
            add(new Interval(1,3));
        }};
        sch.add(emp2);
        List<Interval> emp3 = new ArrayList<>(){{
            add(new Interval(4,10));
        }};
        sch.add(emp3);

        List<Interval> res = obj.employeeFreeTime(sch);
        StringBuilder  sb = new StringBuilder();
        for(Interval intv: res) {
            sb.append("["+intv.start+","+intv.end+"] ");
        }
        System.out.println(sb);

    }
}
