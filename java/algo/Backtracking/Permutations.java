package algo.Backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations.
 * You can return the answer in any order.
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 *
 * Input: nums = [1]
 * Output: [[1]]
 *
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> numList = new ArrayList<>();
        for(int num: nums) {
            numList.add(num);
        }
        dfsTraverse(numList, nums.length, 0, result);
        return result;
    }

    public void dfsTraverse(List<Integer> nums, int size, int start, List<List<Integer>> result) {
        if (start == size) {
            result.add(new ArrayList<Integer>(nums));
        }
        for (int i=start; i<size;i++) {
            // swap: 0 0, 0 1, 0 2
            Collections.swap(nums, start, i);
            this.dfsTraverse(nums, size, start+1, result); // start => 0 -> 1 -> 2
            // reset/backtrack
            Collections.swap(nums, start, i);
        }
    }

    public static void main(String args[]) {
        Permutations obj = new Permutations();
        System.out.println(obj.permute(new int[]{1,2,3}).toString());
    }
}
