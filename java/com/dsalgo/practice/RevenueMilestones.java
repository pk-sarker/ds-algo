package com.dsalgo.practice;

import java.util.Arrays;
import java.util.HashMap;

public class RevenueMilestones {
    public static int[] getMilestoneDays(int[] revenues, int[] milestones) {
        // Write your code here
        if (milestones.length ==0 ) {
            return new int[]{};
        }

        HashMap<Integer, Integer> milestoreMap = new HashMap<>();
        for(int i=0;i<milestones.length;i++) {
            milestoreMap.put(milestones[i], i);
        }

        Arrays.sort(milestones);

        int totalRev = 0;
        int milestoneIdx = 0;
        int[] milestoneDays = new int[milestones.length];
        Arrays.fill(milestoneDays, -1);

        for(int i=0; i<revenues.length;i++)  {
            totalRev += revenues[i];
            int tempRev = totalRev;
            while(milestoneIdx < milestones.length && tempRev >= milestones[milestoneIdx]) {
                milestoneDays[milestoreMap.get(milestones[milestoneIdx])] = i+1;
                milestoneIdx++;
            }
        }

        return milestoneDays;
    }

    public static void main(String args[]) {
        System.out.println(Arrays.toString(RevenueMilestones.getMilestoneDays(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100}, new int[]{100, 200, 500})));

        System.out.println(Arrays.toString(RevenueMilestones.getMilestoneDays(new int[]{700, 800, 600, 400, 600, 700}, new int[]{3100, 2200, 800, 2100, 1000})));

    }
}
