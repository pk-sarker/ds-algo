package ds.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * You are given n tasks labeled from 0 to n - 1 represented by a 2D integer
 * array tasks, where tasks[i] = [enqueueTime_i, processingTime_i] means that the i-th task will be available
 * to process at enqueueTime_i and will take processingTime_i to finish processing.
 *
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 *
 * - If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 * - If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time.
 *      If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
 * - Once a task is started, the CPU will process the entire task without stopping.
 * - The CPU can finish a task then start a new one instantly.
 *
 * Return the order in which the CPU will process the tasks.
 *
 *
 * Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
 * Output: [0,2,3,1]
 * Explanation: The events go as follows:
 * - At time = 1, task 0 is available to process. Available tasks = {0}.
 * - Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
 * - At time = 2, task 1 is available to process. Available tasks = {1}.
 * - At time = 3, task 2 is available to process. Available tasks = {1, 2}.
 * - Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
 * - At time = 4, task 3 is available to process. Available tasks = {1, 3}.
 * - At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
 * - At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
 * - At time = 10, the CPU finishes task 1 and becomes idle.
 */
public class SingleThreadedCPU {

    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[] res = new int[n];
        int[][] extTasks = new int[n][3];
        for(int i = 0; i < n; i++) {
            extTasks[i][0] = i;
            extTasks[i][1] = tasks[i][0]; // start time
            extTasks[i][2] = tasks[i][1]; // duration
        }
        Arrays.sort(extTasks, (a, b)->a[1] - b[1]);  // order by start time

        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[2] == b[2] ? a[0] - b[0] : a[2] - b[2]);
        int time = 0;
        int resIdx = 0;  // result index
        int taskIdx = 0; // task index
        while(resIdx < n) {
            while(taskIdx < n && extTasks[taskIdx][1] <= time) {
                pq.offer(extTasks[taskIdx++]);
            }
            if(pq.isEmpty()) {
                time = extTasks[taskIdx][1];
                continue;
            }
            int[] bestFit = pq.poll();
            res[resIdx++] = bestFit[0];
            time += bestFit[2];  // current time + time to complete current task
        }
        return res;
    }

    public static void main(String args[]) {
        SingleThreadedCPU obj = new SingleThreadedCPU();
        System.out.println(Arrays.toString(obj.getOrder(new int[][]{{1,2},{2,4},{3,2},{4,1}})));

    }
}
