package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

/**
 * Given the root of a binary tree, find the maximum value V for which there exist different
 * nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 *
 * A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is
 * an ancestor of B.
 *
 * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 * Explanation: We have various ancestor-node differences, some of which are given below :
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 *
 */
public class MaximumAncestorDifference {
    private int maxDiff = Integer.MIN_VALUE;
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root, root.value, root.value);
        return maxDiff == Integer.MIN_VALUE ? 0: maxDiff;
    }
    private void helper(TreeNode node, int maxVal, int minVal) {
        if (node == null) {
            return;
        }
        int currentMax = Math.max(Math.abs(node.value-maxVal), Math.abs(node.value-minVal));

        int curMax = Math.max(maxVal, node.value);
        int curMin = Math.min(minVal, node.value);
        maxDiff = Math.max(maxDiff, currentMax);
        helper(node.left, curMax, curMin);
        helper(node.right, curMax, curMin);
        return;
    }

    /**
     * As mentioned: given any two nodes on the same root-to-leaf path,
     * they must have the required ancestor relationship.
     *
     * So we just need to find maximum and minimum in a root to leaf path.
     * 
     */
    public int maxAncestorDiffOpt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper2(root, root.value, root.value);
        //return maxDiff == Integer.MIN_VALUE ? 0: maxDiff;
    }

    private int helper2(TreeNode node, int maxVal, int minVal) {
        if (node == null) {
            return maxVal - minVal;
        }
        maxVal = Math.max(maxVal, node.value);
        minVal = Math.min(minVal, node.value);

        int leftMax =  helper2(node.left, maxVal, minVal);
        int rightMax =  helper2(node.right, maxVal, minVal);
        return Math.max(leftMax, rightMax);
    }


    public static void main(String args[]) {
        MaximumAncestorDifference obj = new MaximumAncestorDifference();
        TreeNode root = new TreeNode(8, new TreeNode(3, new TreeNode(1), new TreeNode(6, new TreeNode(4), new TreeNode(7))),new TreeNode(10, null, new TreeNode(14, new TreeNode(13), null)));
        System.out.println(obj.maxAncestorDiff(root));

        System.out.println(obj.maxAncestorDiffOpt(root));
    }
}
