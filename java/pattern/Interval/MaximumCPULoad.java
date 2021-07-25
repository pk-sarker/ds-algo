package pattern.Interval;

import java.util.Arrays;

/**
 * We are given a list of Jobs. Each job has a Start time, an End time, and a CPU load when it is running.
 * Our goal is to find the maximum CPU load at any time if all the jobs are running on the same machine.
 *
 * Jobs: [[1,4,3], [2,5,4], [7,9,6]]
 * Output: 7
 * Explanation: Since [1,4,3] and [2,5,4] overlap, their maximum CPU load (3+4=7) will be when both the
 * jobs are running at the same time i.e., during the time interval (2,4).
 *
 * Jobs: [[6,7,10], [2,4,11], [8,12,15]]
 * Output: 15
 * Explanation: None of the jobs overlap, therefore we will take the maximum load of any job which is 15.
 *
 * Jobs: [[1,4,2], [2,4,1], [3,6,5]]
 * Output: 8
 * Explanation: Maximum CPU load will be 8 as all jobs overlap during the time interval [3,4].
 *
 */
public class MaximumCPULoad {

    public int findMaxLoad(int[][] jobs) {
        // sort by start time of the jobs
        Arrays.sort(jobs, (a, b) -> a[0]-b[0]);

        int maxLoad = Integer.MIN_VALUE;
        int currentLoad = 0;
        for(int i=1;i < jobs.length; i++) {
            // check if jobs are overlapped
            currentLoad = jobs[i][2];
            if (jobs[i][0] < jobs[i-1][1]) { // start of current job is less than end of last job
                currentLoad += jobs[i-1][2];
                jobs[i][2] = currentLoad;  // update current max load if it was overlapped,
            }
            maxLoad = Math.max(maxLoad, currentLoad);
        }
        return maxLoad;
    }

    public static void main(String args[]) {
        MaximumCPULoad obj = new MaximumCPULoad();
        System.out.println("\nInput: [[1,4,3], [2,5,4], [7,9,6]]\nOutput: " + obj.findMaxLoad(new int[][]{{1,4,3}, {2,5,4}, {7,9,6}}));
        System.out.println("\nInput: [[6,7,10], [2,4,11], [8,12,15]]\nOutput: " + obj.findMaxLoad(new int[][]{{6,7,10}, {2,4,11}, {8,12,15}}));
        System.out.println("\nInput: [[1,4,2], [2,4,1], [3,6,5]]\nOutput: " + obj.findMaxLoad(new int[][]{{1,4,2}, {2,4,1}, {3,6,5}}));
    }
}
