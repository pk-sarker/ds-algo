package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.*;

/**
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (i.e., from left to right, level by level from leaf to root).
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[15,7],[9,20],[3]]
 *
 */
public class BinaryTreeLevelOrderTraversal2 {

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        // do a level order traversal
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            if (result.size() == level) {
                result.add(new ArrayList<>());
            }
            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                result.get(level).add(node.value);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            level++;
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        List<List<Integer>> result = BinaryTreeLevelOrderTraversal2.levelOrderBottom(root);
        System.out.println(result);
    }
}
