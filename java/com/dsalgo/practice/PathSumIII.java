package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.HashMap;

public class PathSumIII {
    int count = 0;
    HashMap<Integer, Integer> prefixSum = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {
        prefixSum.put(0, 1); // if current sum == targetSum
        dfs(root, 0, targetSum);

        return this.count;
    }

    public void dfs(TreeNode node, int prevSum, int targetSum) {
        if (node == null) {
            return;
        }
        int currentSum = prevSum + node.value;

        if (prefixSum.containsKey(currentSum-targetSum)) {
            this.count += prefixSum.get(currentSum-targetSum);
        }
        prefixSum.put(currentSum, prefixSum.getOrDefault(currentSum,0)+1);

        dfs(node.left, currentSum, targetSum);
        dfs(node.right, currentSum, targetSum);

        prefixSum.put(currentSum, prefixSum.get(currentSum)-1);
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(2, new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(3))), new TreeNode(8, new TreeNode(10), new TreeNode(4, new TreeNode(6), new TreeNode(3))));
        PathSumIII obj = new PathSumIII();
        System.out.println(obj.pathSum(root, 20));
    }
}
