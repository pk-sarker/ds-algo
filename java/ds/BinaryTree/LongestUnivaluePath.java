package ds.BinaryTree;

import Common.TreeNode;

/**
 * Given the root of a binary tree, return the length of the longest path, where each node
 * in the path has the same value. This path may or may not pass through the root.
 *
 * The length of the path between two nodes is represented by the number of edges between them.
 *
 * Input: root = [5,4,5,1,1,5]
 * Output: 2
 *
 * Input: root = [1,4,5,4,4,5]
 * Output: 2
 */

public class LongestUnivaluePath {
    private int maxLen;
    public int longestUnivaluePath(TreeNode root) {
        this.maxLen = 0;
        getLength(root);
        return this.maxLen;
    }
    public int getLength(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getLength(node.left);
        int right = getLength(node.right);
        int leftLen = 0, rightLen = 0;
        if (node.left != null && node.left.val == node.val) {
            leftLen += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            rightLen += right + 1;
        }
        this.maxLen = Math.max(this.maxLen, leftLen + rightLen);
        return Math.max(leftLen, rightLen);
    }
}

