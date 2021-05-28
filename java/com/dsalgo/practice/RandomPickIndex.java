package com.dsalgo.practice;

import java.util.Random;

/**
 * Given an integer array nums with possible duplicates, randomly output the index of a given target number.
 * You can assume that the given target number must exist in the array.
 *
 * Implement the Solution class:
 *
 * Solution(int[] nums) Initializes the object with the array nums.
 * int pick(int target) Picks a random index i from nums where nums[i] == target. If there are
 * multiple valid i's, then each index should have an equal probability of returning.
 *
 * probability of picking 1 from n item is 1/n
 */
public class RandomPickIndex {
    int[] nums;
    Random rand;
    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }

    public int pick(int target) {
        int counter = 0;
        int index = 0;
        for(int i=0;i<this.nums.length;i++) {
            // if nums[i] is equal to target, i is a potential candidate
            // which needs to be chosen uniformly at random
            if (this.nums[i] == target) {
                counter++;
                // pick the current number with probability 1 / count (reservoir sampling)
                int next = this.rand.nextInt(counter);
                if (next == 0) {
                    index = i;
                }
            }

        }
        return index;
    }

    public static void main(String args[]) {
        RandomPickIndex obj = new RandomPickIndex(new int[]{1, 3, 2, 3, 5, 3, 6, 7, 3});
//        System.out.println("Pick 1: " + obj.pick(1));
//        System.out.println("Pick 2: " + obj.pick(2));
        // 1, 3, 5, 8
        System.out.println("Pick 3-1: " + obj.pick(3));
        System.out.println("Pick 3-2: " + obj.pick(3));
        System.out.println("Pick 3-3: " + obj.pick(3));
        System.out.println("Pick 3-4: " + obj.pick(3));
        System.out.println("Pick 3-5: " + obj.pick(3));

    }
}
