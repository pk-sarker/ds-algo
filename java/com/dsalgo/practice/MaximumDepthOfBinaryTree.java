package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.LinkedList;

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    public int maxDepthItr(TreeNode root) {
        if (root == null) {
            return 0;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> depths = new LinkedList<>();
        stack.add(root);
        depths.add(1);
        int maxDepth = 0, currentDepth = 0;
        while(!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            currentDepth = depths.pollLast();
            if (root != null) {
                maxDepth = Math.max(maxDepth, currentDepth);
                if (node.left != null) {
                    stack.add(node.left);
                }
                if (node.right != null) {
                    stack.add(node.right);
                }
                depths.add(currentDepth + 1);
                depths.add(currentDepth + 1);
            }


        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(7, new TreeNode(5, null, new TreeNode(2)), new TreeNode(8, new TreeNode(10, new TreeNode(12), new TreeNode(14))));
        MaximumDepthOfBinaryTree obj = new MaximumDepthOfBinaryTree();
        System.out.println("\nDepth: " + obj.maxDepth(root));
    }
}
