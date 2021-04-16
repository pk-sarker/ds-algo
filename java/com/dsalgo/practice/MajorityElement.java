package com.dsalgo.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 */
public class MajorityElement {

    public static int majorityElement(int[] nums) {
        // count the frequency of each number
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        Map.Entry<Integer, Integer> majority = null;
        for(Map.Entry<Integer, Integer> entity: map.entrySet()) {
            if (majority == null || entity.getValue() > majority.getValue()) {
                majority = entity;
            }
        }
        return majority.getKey();
    }
    public static void main(String args[]) {
        System.out.println("\nInput: [3,1,1,2,3,3,3] \nOutput: " + MajorityElement.majorityElement(new int[]{3,1,1,2,3,3,3}));
    }
}
