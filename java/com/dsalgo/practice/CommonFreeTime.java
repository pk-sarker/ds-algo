package com.dsalgo.practice;

import com.dsalgo.practice.common.Interval;
;

import java.util.ArrayList;
import java.util.List;

/**
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees,
 * also in sorted order.
 *
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals,
 * not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0]
 * is not defined). Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 *
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation: There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 *
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 *
 */
public class CommonFreeTime {

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        int cur = 1;
        List<Interval> prevCommon = schedule.get(0);
        while(cur < schedule.size()) {
            List<Interval> common = findCommonInterval(prevCommon, schedule.get(cur));
            if (common.isEmpty()) {
                return common;
            }
            prevCommon.clear();
            prevCommon = common;
            cur++;
        }

        return prevCommon;
    }

    public List<Interval> findCommonInterval(List<Interval> intervals1, List<Interval> intervals2) {
        int ptr1 = 0, ptr2 = 0;
        int m = intervals1.size(), n = intervals2.size(), max = Math.max(m, n);
        List<Interval> common = new ArrayList<>();
        while (ptr1 < max && ptr2 < max) {
            // if intersecting
            Interval intv1 = intervals1.get(ptr1), intv2 = intervals2.get(ptr2);
            if ((intv1.start < intv2.start && intv1.end < intv2.start) || (intv2.start < intv1.start && intv2.end < intv1.start)) {
                if (intv1.start < intv2.start) {
                    ptr1++;
                } else {
                    ptr2++;
                }
            } else {
                common.add(new Interval(Math.max(intv1.start, intv2.start), Math.min(intv1.end, intv2.end)));
                if (intv1.end < intv2.end) {
                    ptr1++;
                } else  {
                    ptr2++;
                }
            }
        }

        return common;
    }

    public void print(List<Interval> res) {
        StringBuilder sb = new StringBuilder();
        for(Interval intv : res) {
            sb.append("["+intv.start+","+ intv.end+"] ");
        }
        System.out.println("Output: " + sb.toString());
    }
    public static void main(String args[]) {
        CommonFreeTime ob = new CommonFreeTime();
        List<List<Interval>> input1 = new ArrayList<>(){{
            add(new ArrayList<Interval>(){{
                add(new Interval(1,2));
                add(new Interval(5,6));
            }});
            add(new ArrayList<Interval>(){{
                add(new Interval(1,3));
                add(new Interval(4,10));
            }});
        }};

//        List<Interval> res = ob.employeeFreeTime(input1);
//
//        System.out.println("\nInput: [[[1,2],[5,6]],[[1,3],[4,10]]]");
//        ob.print(res);

        System.out.println("\nInput: [[[1,2],[5,6]],[[1,3]],[[4,10]]]");
        List<List<Interval>> input2 = new ArrayList<>(){{
            add(new ArrayList<Interval>(){{
                add(new Interval(1,2));
                add(new Interval(5,6));
            }});
            add(new ArrayList<Interval>(){{
                add(new Interval(1,3));
            }});
            add(new ArrayList<Interval>(){{
                add(new Interval(4,10));
            }});
        }};

        List<Interval> res1 = ob.employeeFreeTime(input1);
        ob.print(res1);
    }


}
