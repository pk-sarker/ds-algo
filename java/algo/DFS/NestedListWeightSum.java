package algo.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose
 * elements may also be integers or other lists.
 *
 * The depth of an integer is the number of lists that it is inside of. For example, the
 * nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 *
 * Return the sum of each integer in nestedList multiplied by its depth.
 *
 * Input: nestedList = [[2,1],3,[1,[2]]]
 * Output: 17
 * Explanation: Depth of the numbers: [2, 2, 1, 2, 3], Sum = 2*2 + 1*2 + 3*1 + 1*2 + 2*3 = 17.
 */
public class NestedListWeightSum {

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

    public int depthSum(List<NestedInteger> nestedList) {
        return this.dfs(nestedList, 1);
    }

    public int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;

        for (NestedInteger nested: nestedList) {
            if (nested.isInteger()) {
                sum += nested.getInteger() * depth;
                System.out.println("Number: "+nested.getInteger()+" depth: " + depth);
            } else {
                sum += this.dfs(nested.getList(), depth+1);
            }
        }
        return sum;
    }


    public static void main(String args[]) {
        NestedListWeightSum obj = new NestedListWeightSum();
        List<NestedInteger> nestedList = new ArrayList<>();
        NestedInteger nestedInt = new NestedInteger();
        NestedInteger nestedIntSub = new NestedInteger();
        nestedIntSub.add(new NestedInteger(2));
        nestedIntSub.add(new NestedInteger(1));
        nestedInt.add(nestedIntSub);
        nestedInt.add(new NestedInteger(3));

        NestedInteger nestedIntegerSub2 = new NestedInteger();
        nestedIntegerSub2.add(new NestedInteger(1));
        NestedInteger nestedIntegerSub3 = new NestedInteger();
        nestedIntegerSub3.add(new NestedInteger(2));
        nestedIntegerSub2.add(nestedIntegerSub3);
        nestedInt.add(nestedIntegerSub2);
        nestedList.add(nestedInt);

        int sum = obj.depthSum(nestedList);
        System.out.println("Sum: " + sum);

    }
}
