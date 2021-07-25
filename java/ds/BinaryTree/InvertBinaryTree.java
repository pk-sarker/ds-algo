package ds.BinaryTree;


import Common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 *                  4            4
 *                 / \          / \
 *                2   7   =>   7   2
 *               /\   /\      /\   /\
 *              1 3  6 9     9 6  3 1
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tleft = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tleft);
        return root;
    }

    public TreeNode invertTreeItr(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                continue;
            }
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            queue.offer(current.left);
            queue.offer(current.right);
        }
        return root;
    }
    public static void main(String args[]) {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7, new TreeNode(6), new TreeNode(9)));

        InvertBinaryTree obj = new InvertBinaryTree();
        System.out.println("[ " + root.val + ", " + root.left.val + ", " + root.right.val + " ]");
        obj.invertTree(root);
        System.out.println("Invert \n[ " + root.val + ", " + root.left.val + ", " + root.right.val + " ]");
        obj.invertTreeItr(root);
        System.out.println("Invert Invert \n[ " + root.val + ", " + root.left.val + ", " + root.right.val + " ]");
    }
}
