package algo.Backtracking;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note: The solution set must not contain duplicate combinations.
 * <p>
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * <p>
 * Solution: Backtracking
 */
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort the candidates
        Arrays.sort(candidates);
        // LinkedList is using as we have to add and remove the last one
        ArrayList<Integer> combination = new ArrayList<>();
        // from where to start, whats added so far,
        backtrack(target, candidates, 0, combination, result);
        return result;
    }

    public void backtrack(int remainingTarget, int[] candidates, int start, ArrayList<Integer> combination, List<List<Integer>> result) {

        if (remainingTarget == 0) { // a valid combination is found
            result.add(new ArrayList<>(combination));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // skip the same candidates
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            // if current candidate value is more than the remaining target then
            // it won't make a valid combination
            if (candidates[i] > remainingTarget) {
                break;
            }
            // adding current candidate in the combination
            combination.add(candidates[i]);
            backtrack(remainingTarget - candidates[i], candidates, i + 1, combination, result);
            combination.removeLast();
        }

    }

    public static void main(String args[]) {
        CombinationSumII obj = new CombinationSumII();

        System.out.println(obj.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }
}
