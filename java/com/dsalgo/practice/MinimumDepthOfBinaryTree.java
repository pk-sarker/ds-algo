package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 *
 */
public class MinimumDepthOfBinaryTree {

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        boolean minDepthFound = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        depth++;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                TreeNode node = queue.poll();

                // check if current node is leaf node
                if (node.left == null && node.right == null) {
                    minDepthFound = true;
                    break;
                }
                if(node.left!= null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (minDepthFound) {
                break;
            }
            depth++;
        }
        return depth;
    }



    public static void main(String args[]) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println("\nInput: 3 9 null null 20 15 null 7 null \nOutput: " + MinimumDepthOfBinaryTree.minDepth(root));

        TreeNode root1 = new TreeNode(3, new TreeNode(9, new TreeNode(8, null, new TreeNode(7))), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println("\nInput: 3 9 null null 20 15 null 7 null \nOutput: " + MinimumDepthOfBinaryTree.minDepth(root1));
    }
}
