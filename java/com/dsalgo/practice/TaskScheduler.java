package com.dsalgo.practice;

import java.util.Arrays;

public class TaskScheduler {

    public static int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];

        for(char task: tasks) {
            count[task-'A']++;
        }
        Arrays.sort(count);
        int maxFrq = count[25];
        int idleTime = (maxFrq - 1) * n;


        for(int i=24; i>=0; i--) {
            idleTime -= Math.min(maxFrq-1, count[i]);
        }

        idleTime = idleTime < 0 ? 0:idleTime;

        return tasks.length + idleTime;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: ['X','X','X','Z','Z','Z'], n=2 \nOutput: " + TaskScheduler.leastInterval(new char[]{'X','X','X', 'Z', 'Z', 'Z'}, 2));

        System.out.println("\nInput: ['X','X','X','Z','Z','Y','W'], n=2 \nOutput: " + TaskScheduler.leastInterval(new char[]{'X','X','X','Z','Z','Y','W'}, 2));
    }
}
