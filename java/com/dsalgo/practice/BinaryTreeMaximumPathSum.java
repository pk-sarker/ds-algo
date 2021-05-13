package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

public class BinaryTreeMaximumPathSum {
    int pathMaxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return pathMaxSum;
    }

    public int helper(TreeNode node) {
        // base case
        if (node == null) {
            return 0;
        }

        // find maximum sum of left sub-tree
        // We will discard any negative value from the sub-tree sum,
        // thats why we are taking  max of 0 or the subtree sum.
        // Negative values won't increase the sum.
        int leftMaxSum = Math.max(helper(node.left), 0);

        // find maximum sum of right sub-tree
        int rightMaxSum = Math.max(helper(node.right), 0);

        int sumAtCurrentPath = node.value + leftMaxSum + rightMaxSum;

        pathMaxSum = Math.max(pathMaxSum, sumAtCurrentPath);

        return node.value + Math.max(leftMaxSum, rightMaxSum);
    }


    public static void main(String args[]) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        BinaryTreeMaximumPathSum obj = new BinaryTreeMaximumPathSum();
        System.out.println("> Maximum path sum 6 == " + obj.maxPathSum(root));

        TreeNode root1 = new TreeNode( 0, new TreeNode(-1), new TreeNode(-1));
        System.out.println("> Maximum path sum  0 == " + obj.maxPathSum(root1));

        TreeNode root2 = new TreeNode(-2, new TreeNode(2), new TreeNode(3));
        System.out.println("> Maximum path sum  2 == " + obj.maxPathSum(root2));

        TreeNode root3 = new TreeNode(-10, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println("> Maximum path sum  42 == " + obj.maxPathSum(root3));
    }
}
