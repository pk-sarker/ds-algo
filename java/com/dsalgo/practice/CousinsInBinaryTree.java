package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.*;

/**
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 *
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 *
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 *
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 *
 *
 */
public class CousinsInBinaryTree {

    public static boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        HashMap<Integer, Integer> parent = new HashMap<>();
        while(!queue.isEmpty()) {
            int size = queue.size();

            boolean foundX = false, foundY = false;

            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();

                if (node.value == x) {
                    foundX = true;
                }
                if (node.value == y) {
                    foundY = true;
                }
                if (node.left != null) {
                    queue.add(node.left);
                    parent.put(node.left.value, node.value);
                }
                if (node.right != null) {
                    queue.add(node.right);
                    parent.put(node.right.value, node.value);
                }
            }
            if ((foundX && !foundY) || (!foundX && foundY) ) {
                return false;
            }

            if (foundX && foundY && parent.get(x) != parent.get(y)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1, new TreeNode(2, null, new TreeNode(4)), new TreeNode(3, null, new TreeNode(5)));
        System.out.println("Output: " + CousinsInBinaryTree.isCousins(root, 4,5));


        TreeNode root1 = new TreeNode(1, new TreeNode(2, null, new TreeNode(4)), new TreeNode(3, null, new TreeNode(5, null, new TreeNode(6))));
        System.out.println("Output: " + CousinsInBinaryTree.isCousins(root1, 4,6));
    }
}
