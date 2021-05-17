package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

public class RangeSumOfBST {

    int totalSum = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        dfs(root, low, high);
        return totalSum;
    }

    public void dfs(TreeNode node, int low, int high) {
        if (node == null) {
            return;
        }

        if (node.value >= low && node.value <= high) {
            totalSum += node.value;
        }

        if (node.value > low) {
            dfs(node.left, low, high);
        }
        if (node.value < high) {
            dfs(node.right, low, high);
        }
    }

    public static void main(String args[]) {
        RangeSumOfBST obj = new RangeSumOfBST();

        TreeNode root = new TreeNode(10, new TreeNode(5, new TreeNode(3), new TreeNode(7)), new TreeNode(15, null, new TreeNode(18)));

        System.out.println("Sum: " + obj.rangeSumBST(root, 7, 15));
    }
}
