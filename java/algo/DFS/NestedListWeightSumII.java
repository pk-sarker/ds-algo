package algo.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose
 * elements may also be integers or other lists.
 *
 * The depth of an integer is the number of lists that it is inside of. For example, the
 * nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth. Let maxDepth be
 * the maximum depth of any integer.
 *
 * The weight of an integer is maxDepth - (the depth of the integer) + 1.
 *
 * Return the sum of each integer in nestedList multiplied by its weight.
 *
 * Example:
 *             [[ 1, 2 ], 2, [3, [1, 2]], 4 ]
 * Depth:         2  2    1   2   3  3    1
 * Max Depth: Max(2  2    1   2   3  3    1) = 3
 * Weight:     (3-2+1)(3-2+1)(3-1+1)(3-2+1)(3-3+1)(3-3+1)(3-1+1)
 *                2  2    3   2   1  1    3
 * Total:       1x2 + 2x2 + 2x3 + 3x2 + 1x1 + 2x1 + 4x3
 *              = 33
 *
 *  Solution:
 *  Used DFS to find the max depth in the list and also store depth of each number while traversing.
 *  Then iterating over them to calculate total weighted sum.
 */
public class NestedListWeightSumII {
    static class NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        int singleValue;
        boolean isInt;
        List<NestedInteger> nestedIntegerList;

        NestedInteger() {
            this.nestedIntegerList = new ArrayList<>();
            this.isInt = false;
        }
        public NestedInteger(int value) {
            this.singleValue = value;
            this.isInt = true;
        }

        public boolean isInteger() {
            return this.isInt;
        }
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return this.singleValue;
        }
        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
            this.singleValue = value;
        }
        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {
            this.nestedIntegerList.add(ni);
        }
        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return this.nestedIntegerList;
        }
    }
    List<int[]> numbers = new ArrayList<>();
    int maxDepth = 1;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        dfs(nestedList, 1);
        int total = 0;
        for (int[] num: numbers) {
            int weight = maxDepth - num[1] + 1;
            total += (num[0] * weight);
        }
        return total;
    }


    public void dfs(List<NestedInteger> nestedList, int depth) {
        for(NestedInteger nestedInt : nestedList) {
            if (nestedInt.isInteger()) {
                maxDepth = Math.max(maxDepth, depth);
                numbers.add(new int[]{nestedInt.getInteger(), depth});
            } else {
                dfs(nestedInt.getList(), depth+1);
            }
        }
    }

    public static void main(String args[]) {

        NestedListWeightSumII obj = new NestedListWeightSumII();
        List<NestedInteger> nestedList = new ArrayList<>();
        NestedInteger nestedIntSub = new NestedInteger();
        nestedIntSub.add(new NestedInteger(1));
        nestedIntSub.add(new NestedInteger(2));
        nestedList.add(nestedIntSub);
        nestedList.add(new NestedInteger(2));

        NestedInteger nestedIntSub2 = new NestedInteger();
        nestedIntSub2.add(new NestedInteger(3));
        NestedInteger nestedIntSub3 = new NestedInteger();
        nestedIntSub3.add(new NestedInteger(1));
        nestedIntSub3.add(new NestedInteger(2));
        nestedIntSub2.add(nestedIntSub3);
        nestedList.add(nestedIntSub2);
        nestedList.add(new NestedInteger(4));

        int sum = obj.depthSumInverse(nestedList);
        System.out.println("Sum: " + sum);
    }
}
