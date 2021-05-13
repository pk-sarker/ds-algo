package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

public class PathSum {
    boolean found = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        helper(root, sum);
        return found;
    }

    public void helper(TreeNode node, int sum) {
        // check if leaf node

        if (node == null || found == true) {
            return ;
        }

        if (node.left == null && node.right == null && sum == node.value) {
            found = true;
            return;
        }
        helper(node.left, sum-node.value);
        helper(node.right, sum-node.value);
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(3, new TreeNode(3), new TreeNode(2)));
        PathSum obj = new PathSum();

        System.out.println("Path Sum 7 found: " + obj.hasPathSum(root, 7));
    }
}
