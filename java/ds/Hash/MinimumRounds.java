package ds.Hash;

import java.util.HashMap;
import java.util.Set;

/**
 * Problem:
 * You are given a 0-indexed integer array tasks, where tasks[i] represents
 * the difficulty level of a task. In each round, you can complete either 2 or 3 tasks
 * of the same difficulty level.
 *
 * Return the minimum rounds required to complete all the tasks, or -1 if it is
 * not possible to complete all the tasks.
 *
 * Solution:
 * To minimize the round we need to maximize the number of tasks each round ,
 * that means try to complete 3 tasks each round.
 * First count frequency of each number, use a hash map to store.
 * Now we need to round number of round for a given frequency.
 * If frequency = 1, then we can complete it as we can only take a batch of 2 or 3 tasks
 * If frequency = 2, then it will take 1 round, we can take 2 task in 1 round
 * If frequency = 3, then it will take 1 round, we can take 3 task in 1 round
 * If frequency = 4, then it will take 2 round, we can take 2 task in each round
 * If frequency = 5, then it will take 2 round, we can take 3 task in 1st round and 2 task in 2nd round.
 * If frequency = 6, then it will take 2 round, 3 tasks in each round
 *
 * Now lets try to optimize so that we can calculate the round from one operation
 * frequency = 3k, it will need k rounds
 * frequency = 3k+1 = 3(k-1) + 3 + 1 = 3(k-1) + 2 + 2, it will need k + 1 round
 * frequency = 3k+2 = 3*k + 2, it will need k + 1 round
 */
public class MinimumRounds {
    public int minimumRounds(int[] tasks) {
        HashMap<Integer, Integer> taskDifficultyCount = new HashMap<>();

        for(int i=0; i< tasks.length; i++) {
            taskDifficultyCount.put(tasks[i], taskDifficultyCount.getOrDefault(tasks[i], 0)+1);
        }
        int round = 0;
        Set<Integer> keys = taskDifficultyCount.keySet();
        for(Integer key: keys){
            int value = taskDifficultyCount.get(key);
            if (value == 1) return -1;

            round += (value + 2)/3;
        };
        return round;
    }

    public static void main(String args[]) {
        MinimumRounds obj = new MinimumRounds();
        int res = obj.minimumRounds(new int[]{2,2,3,3,2,4,4,4,4,4});
        System.out.println("Input: [2,2,3,3,2,4,4,4,4,4]\nResult: " + res);
        int res2 = obj.minimumRounds(new int[]{1,1,1,1});
        System.out.println("\nInput: [1,1,1,1]\nResult: " + res2);
    }


}
