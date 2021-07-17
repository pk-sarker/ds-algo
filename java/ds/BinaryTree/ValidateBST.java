package ds.BinaryTree;

import Common.TreeNode;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * - The left subtree of a node contains only nodes with keys less than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - Both the left and right subtrees must also be binary search trees.
 *
 */
public class ValidateBST {

    public boolean isValidBST(TreeNode root) {
        return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValid(TreeNode root, int lowerBound, int upperBound) {
        if (root.val < lowerBound || root.val > upperBound) {
            return false;
        }
        if (root.left != null && !isValid(root.left, lowerBound, root.val)) {
            return false;
        }
        if (root.right != null && !isValid(root.right, root.val, upperBound)) {
            return false;
        }
        return true;
    }

    public static void main(String args[]) {
        ValidateBST obj = new ValidateBST();
        /**
         *               7
         *             /  \
         *            4    9
         *           / \  / \
         *          1  5 8  11
         */
        TreeNode root = new TreeNode(7, new TreeNode(4, new TreeNode(1), new TreeNode(5)), new TreeNode(9, new TreeNode(8), new TreeNode(11)));
        System.out.println(obj.isValidBST(root));
    }
}
