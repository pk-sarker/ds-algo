package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *                    5
 *                 /    \
 *                7     10
 *                \    /  \
 *                9   3    6
 *               / \   \  / \
 *              11 8   4 2  1
 */
public class TreeTraversalDFS {

    /**
     * Left - Root - Right
     * @param root
     * @return
     */
    public List<Integer> traverseInOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    public void inorderHelper(TreeNode root, List<Integer> nodeList) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inorderHelper(root.left, nodeList);
        }
        nodeList.add(root.value);
        if (root.right != null) {
            inorderHelper(root.right, nodeList);
        }
    }

    /**
     *
     * @param root
     * @return
     */
    public List<Integer> traversePreOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }
    public void preorderHelper(TreeNode root, List<Integer> nodeList) {
        if (root == null) {
            return;
        }
        nodeList.add(root.value);
        if (root.left != null) {
            preorderHelper(root.left, nodeList);
        }
        if (root.right != null) {
            preorderHelper(root.right, nodeList);
        }
    }

    public List<Integer> traversePostOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrderHelper(root, result);
        return result;
    }

    public void postOrderHelper(TreeNode root, List<Integer> nodeList) {
        if (root == null) {
            return ;
        }
        if (root.left != null) {
            postOrderHelper(root.left, nodeList);
        }
        if (root.right != null) {
            postOrderHelper(root.right, nodeList);
        }
        nodeList.add(root.value);
    }
    public static void main(String args[]) {
        TreeTraversalDFS obj = new TreeTraversalDFS();

        TreeNode root = new TreeNode(5, new TreeNode(7, null, new TreeNode(9, new TreeNode(11), new TreeNode(8))), new TreeNode(10, new TreeNode(3, null, new TreeNode(4)), new TreeNode(6, new TreeNode(2), new TreeNode(1))));

        System.out.println("                   5\n" +
                "                 /    \\\n" +
                "                7     10\n" +
                "                \\    /  \\\n" +
                "                9   3    6\n" +
                "               / \\   \\  / \\\n" +
                "              11 8   4 2  1");
        System.out.println("In Order: " + obj.traverseInOrder(root).toString());

        System.out.println("Pre-Order: " + obj.traversePreOrder(root).toString());

        System.out.println("Post-Order: " + obj.traversePostOrder(root).toString());
    }
}
