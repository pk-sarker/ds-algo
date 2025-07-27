package algo.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * Solution:
 * - All the numbers are unique
 * - Duplicate subsets are like, (1,2) == (2,1), no duplicate
 * <p>
 * Intuition: Pick a number from i-th index, and try to pair will all other number from (i+1)-th index to end
 * Add one index at a time.
 * For example: i=0, num = 1, without any other number, it will be [1]
 * - add index 1, [1,2]
 * - add index 2, [1,2,3]
 * i=1, num = 2, without any other number, it will be [2]
 * - add index 2, [2,3]
 * i=2, num = 3, without any other number, it will be [3]
 * - add index 2, [2,3]
 * <p>
 * Backtracking
 */
public class GenerateSubsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> currentList, List<List<Integer>> result) {
        // Add current subset to result
        result.add(new ArrayList<>(currentList));

        // Generate subsets starting from the current index, start
        for (int i = start; i < nums.length; i++) {
            currentList.add(nums[i]);
            backtrack(nums, i + 1, currentList, result);
            currentList.removeLast();
        }
    }

    public static void main(String args[]) {
        GenerateSubsets obj = new GenerateSubsets();

        System.out.println(obj.subsets(new int[]{1, 2, 3}));
    }
}
