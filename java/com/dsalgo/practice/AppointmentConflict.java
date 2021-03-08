package com.dsalgo.practice;

import java.util.Arrays;

/**
 * Given an array of intervals representing ‘N’ appointments,
 * find out if a person can attend all the appointments.
 *
 * Appointments: [[1,4], [2,5], [7,9]]
 * Output: false
 * Explanation: Since [1,4] and [2,5] overlap, a person cannot attend both of these appointments.
 *
 * Appointments: [[6,7], [2,4], [8,12]]
 * Output: true
 * Explanation: None of the appointments overlap, therefore a person can attend all of them.
 *
 * Appointments: [[4,5], [2,3], [3,6]]
 * Output: false
 * Explanation: Since [4,5] and [3,6] overlap, a person cannot attend both of these appointments.
 */
public class AppointmentConflict {

    public static boolean canAttendAllAppointments(int[][] intervals) {
        // TODO: Write your code here
        Arrays.sort(intervals, (a, b) -> a[0]-b[0]);
        for(int i = 1; i < intervals.length; i++) {
            if (intervals[i-1][1] > intervals[i][0]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: [[1,4], [2,5], [7,9]] \nOutput: " + AppointmentConflict.canAttendAllAppointments(new int[][]{{1,4},{2,5},{7,9}}));
        System.out.println("\nInput: [[6,7], [2,4], [8,12]] \nOutput: " + AppointmentConflict.canAttendAllAppointments(new int[][]{{6,7},{2,4},{8,12}}));
    }
}
