package com.dsalgo.practice;

import java.util.Arrays;

public class NextPermutation {
    public void nextPermutation(int[] nums) {

        // find lowest increasing number
        int lowIdx = -1;
        for(int i=nums.length-1;i>0;i--){
            if (nums[i] > nums[i-1]) {
                lowIdx = i-1;
                break;
            }
        }
        if (lowIdx>=0) {
            int minDiff = Integer.MAX_VALUE, highIdx=0;
            for(int i=nums.length-1;i>lowIdx;i--){
                if (nums[i] > nums[lowIdx] && (nums[i]-nums[lowIdx] < minDiff)) {
                    highIdx = i;
                    minDiff = nums[i]-nums[lowIdx];
                }
            }

            swap(nums, lowIdx, highIdx);
            int j= nums.length-1;
        }
        int nextLowPos = lowIdx+1, highPos = nums.length-1;
        while(nextLowPos<highPos) {
            swap(nums, nextLowPos,highPos);
            nextLowPos++;
            highPos--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String args[]) {
        NextPermutation obj = new NextPermutation();
        int[] ar1 = new int[]{1,2,3,4};
        obj.nextPermutation(ar1);
        System.out.println(">> [1,2,3,4] -> " + Arrays.toString(ar1));

        int[] ar2 = new int[]{4,3,2,1};
        obj.nextPermutation(ar2);
        System.out.println(">> [4,3,2,1] -> " + Arrays.toString(ar2));
    }
}
