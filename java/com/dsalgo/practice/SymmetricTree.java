package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

/**
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }

        if (leftNode == null || rightNode == null) {
            return false;
        }
        if (leftNode.value != rightNode.value) {
            return false;
        }
        return compare(leftNode.left, rightNode.right) && compare(leftNode.right, rightNode.left);
    }
    public static void main(String args[]) {
        SymmetricTree obj = new SymmetricTree();
        TreeNode root = new TreeNode(5, new TreeNode(3, new TreeNode(1), new TreeNode(2)), new TreeNode(3, new TreeNode(2), new TreeNode(1)));

        System.out.println(obj.isSymmetric(root));

        TreeNode root1 = new TreeNode(5, new TreeNode(3, new TreeNode(1), new TreeNode(2)), new TreeNode(3, new TreeNode(2), new TreeNode(1, new TreeNode(-1))));

        System.out.println(obj.isSymmetric(root1));
    }
}
