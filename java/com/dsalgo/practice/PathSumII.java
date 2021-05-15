package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    List<List<Integer>> matchingPaths = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> path = new ArrayList<>();
        dfs(root, sum, path);
        return matchingPaths;
    }

    public void dfs(TreeNode node, int remainingSum, List<Integer> path) {
        if (node == null) {
            return ;
        }
        path.add(node.value);
        if (node.left == null && node.right == null && remainingSum == node.value) {
            matchingPaths.add(new ArrayList<>(path));
        } else {
            if (node.left != null) {
                dfs(node.left, remainingSum - node.value, path);
            }

            if (node.right != null) {
                dfs(node.right, remainingSum - node.value, path);
            }
        }

        // We need to remove the node once we are done processing all of it's subtrees.
        path.remove(path.size() - 1);
    }
    public static void main(String args[]) {
        TreeNode root = new TreeNode(2, new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(3))), new TreeNode(8, new TreeNode(10), new TreeNode(4, new TreeNode(6), new TreeNode(3))));
        PathSumII obj = new PathSumII();
        List<List<Integer>> result = obj.pathSum(root, 20);
        System.out.println(result.toString());
    }
}
