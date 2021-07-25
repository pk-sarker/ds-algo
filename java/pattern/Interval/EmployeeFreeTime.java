package pattern.Interval;


import Common.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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
