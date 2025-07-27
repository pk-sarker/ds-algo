package algo.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 * <p>
 * You may return the answer in any order.
 * <p>
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 * <p>
 * []
 * [1]              [2]         [3]         [4]
 * 2     3      4      3    4         4
 * [1,2] [1,3] [1,4]   [2,3] [2,4]    [3, 4]
 * <p>
 * Solution:
 * The possible pairs are like, (1,2)(1,3)(1,4),(2,3)
 * So keep the first digit fixed change the next one based on available list of numbers.
 * Like for 1, we first consider 2, and make a pair (1,2)
 * Now try another from the available list of numbers. Remove the last one 2, and try 3, (1,3)
 */
public class  Combinations {
    int n;
    int k;
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;

        backtrack(new ArrayList<>(), 1);
        return result;
    }

    public void backtrack(List<Integer> curr, int num) {
        System.out.println("backtrack: num=" + num);
        // if current list size is same as k the add it the result
        if (curr.size() == this.k) {
            result.add(new ArrayList<>(curr));
            return;
        }
        int need = this.k - curr.size();
        int remaining = n - num + 1; // Number of digits left
        int available = remaining - need;
        System.out.println("\t available=" + available);
        if (available <= 0) {
            return;
        }
        //for(int i=num; i <= num+available; i++) {
        for (int i = num; i <= n; i++) {
            curr.add(i);
            System.out.println("\t add(" + i + ") b(" + (i + 1) + ")");
            backtrack(curr, i + 1);
            curr.remove(curr.size() - 1); // remove the last added number
        }
    }

    public static void main(String args[]) {
        Combinations obj = new Combinations();
        List<List<Integer>> res = obj.combine(4, 2);
        System.out.println(res);
    }
}