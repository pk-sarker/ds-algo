package com.dsalgo.practice;

public class FindPivotIndex {

    public static int pivotIndex(int[] nums) {
        // total sum - sum till now = sum till now
        int totalSum = 0;
        for(int i=0;i<nums.length;i++) {
            totalSum += nums[i];
        }
        int curSum = 0;
        for(int i=0;i<nums.length;i++) {
            //System.out.println("Total: " + totalSum +" cur: " + curSum);
            if (totalSum - nums[i] - curSum == curSum) {
                return i;
            }
            curSum += nums[i];
        }
        return -1;
    }
    public static void main(String args[]) {
        System.out.println("Input: [1,7,3,6,5,6] \nOutput: " + FindPivotIndex.pivotIndex(new int[]{1,7,3,6,5,6}));
    }
}
