package com.dsalgo.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class MedianInSlidingWindow {

    public double[] medianSlidingWindow(int[] nums, int k) {

        Comparator<Integer> comparator = new Comparator<>(){
            public int compare(Integer a, Integer b) {
                if (nums[a] != nums[b]) {
                    return Integer.compare(nums[a], nums[b]); // compare numbers
                } else {
                    return a - b; // compare indices
                }
            }
        };
        TreeSet<Integer> leftSet = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> rightSet = new TreeSet<>(comparator);
        double[] medians = new double[nums.length-k+1];
        int wEndPos = 0;
        for(; wEndPos<k; wEndPos++) {
            leftSet.add(wEndPos);
        }
        balance(leftSet, rightSet);
        medians[0] = calculateMedian(k, nums, leftSet, rightSet);
        int medianCount = 1;
        for(; wEndPos < nums.length; wEndPos++) {

            if (!leftSet.remove(wEndPos - k)) {
               rightSet.remove(wEndPos - k);
            }

            rightSet.add(wEndPos);
            leftSet.add(rightSet.pollFirst());
            balance(leftSet, rightSet);
            medians[medianCount] = calculateMedian(k, nums, leftSet, rightSet);
            medianCount++;
        }
        return medians;
    }

    private void balance(TreeSet<Integer> leftSet, TreeSet<Integer> rightSet) {
        while (leftSet.size()>rightSet.size()) {
            rightSet.add(leftSet.pollFirst());
        }
    }


    private double calculateMedian(int k, int[] nums, TreeSet<Integer> leftSet, TreeSet<Integer> rightSet) {
        if (k%2==0) {
            return (double) ((double) nums[leftSet.first()] + (double) nums[rightSet.first()])/2;
        } else {
            return  (double) nums[rightSet.first()];
        }
    }

    public static void main(String args[]) {
        MedianInSlidingWindow obj = new MedianInSlidingWindow();
        System.out.println("\nInput: [1,3,-1,-3,5,3,6,7], k=3 \nOutput: " + Arrays.toString(obj.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
        System.out.println("\nInput: [1,3,-1,-3,5,3,6,7], k=4 \nOutput: " + Arrays.toString(obj.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 4)));
        System.out.println("\nInput: [2147483647,2147483647], k=2 \nOutput: " + Arrays.toString(obj.medianSlidingWindow(new int[]{2147483647,2147483647}, 2)));

    }
}
