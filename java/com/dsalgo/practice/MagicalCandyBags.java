package com.dsalgo.practice;

import java.util.PriorityQueue;

/**
 * You have N bags of candy. The ith bag contains arr[i] pieces of candy, and each of the bags is magical!
 * It takes you 1 minute to eat all of the pieces of candy in a bag (irrespective of how many pieces of
 * candy are inside), and as soon as you finish, the bag mysteriously refills. If there were x pieces of
 * candy in the bag at the beginning of the minute, then after you've finished you'll find that floor(x/2)
 * pieces are now inside.
 * You have k minutes to eat as much candy as possible. How many pieces of candy can you eat?
 */
public class MagicalCandyBags {

    public static int maxCandies(int[] arr, int k) {
        // max heap
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, (a, b) -> b-a);

        for(int n: arr) {
            heap.offer(n);
        }
        int candyCount = 0;
        for(int i=0; i<k; i++) {
            int candies = heap.poll();
            heap.offer((int) Math.floor(candies/2));
            candyCount += candies;
        }
        return candyCount;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: [2, 1, 7, 4, 2] k=3 \nOutput: " + MagicalCandyBags.maxCandies(new int[]{2, 1, 7, 4, 2}, 3));

        System.out.println("\nInput: [2, 6, 7, 5, 2] k=3 \nOutput: " + MagicalCandyBags.maxCandies(new int[]{2, 6, 7, 5, 2}, 3));

        System.out.println("\nInput: [2, 6, 7, 5, 2] k=4 \nOutput: " + MagicalCandyBags.maxCandies(new int[]{2, 6, 7, 5, 2}, 4));


    }
}
