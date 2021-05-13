package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.LinkedList;

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

    public boolean hasPathSumItr(TreeNode root, int sum) {
        LinkedList<TreeNode> nodeStack = new LinkedList<>();
        LinkedList<Integer> sumStack = new LinkedList<>();
        nodeStack.offerLast(root);
        sumStack.offerLast(0);

        while(!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pollLast();
            Integer currentSum = sumStack.pollLast();
            if (currentSum+node.value == sum && node.left == null && node.right == null) {
                return true;
            }

            if (node.right != null) {
                nodeStack.offerLast(node.right);
                sumStack.offerLast(currentSum+node.value);
            }
            if (node.left != null) {
                nodeStack.offerLast(node.left);
                sumStack.offerLast(currentSum+node.value);
            }
        }

        return false;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(3, new TreeNode(3), new TreeNode(2)));
        PathSum obj = new PathSum();

        System.out.println("Path Sum 7 found: " + obj.hasPathSum(root, 7));

        TreeNode root2 = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(3, new TreeNode(3), new TreeNode(2)));
        System.out.println("Path Sum 7 found: " + obj.hasPathSumItr(root2, 7));
    }
}
