package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *
 * 1 -> 2
 * 2 -> 3
 * 3 -> 1
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class Subsets {
    List<List<Integer>> result = new ArrayList<>();
    int n, k;


    public void backtrack(int first, ArrayList<Integer> curr, int[] nums){
        // check if all the combination is done
        if (curr.size()==k) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for(int i = first; i< n; ++i) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integers to complete the combination
            backtrack(i + 1, curr, nums);
            // backtrack
            curr.remove(curr.size() - 1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        n  = nums.length;
        for(k =0; k<nums.length + 1;++k) {
            backtrack(0, new ArrayList<>(), nums);
        }
        return result;
    }

    public static void main(String args[]) {
        Subsets ss = new Subsets();

        System.out.println(ss.subsets(new int[]{1,2,3}));
    }
}
