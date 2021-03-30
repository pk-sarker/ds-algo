package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree, return the zigzag level order traversal of
 * its nodes' values. (i.e., from left to right, then right to left for the next level
 * and alternate between).
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 *
 * Input: root = [1]
 * Output: [[1]]
 *
 * Input: root = []
 * Output: []
 */
public class TreeTraversalZigZagOrder {

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        q.addLast(null);
        LinkedList<Integer> level_result = new LinkedList<>();

        boolean order_ltor = true;

        while(q.size()>0) {
            TreeNode node = q.pollFirst();

            if (node != null) {
                if (order_ltor) {
                    level_result.addLast(node.value);
                } else {
                    level_result.addFirst(node.value);
                }
                if (node.left != null) {
                    q.addLast(node.left);
                }
                if (node.right != null) {
                    q.addLast(node.right);
                }
            } else {
                result.add(level_result);
                level_result = new LinkedList<>();

                if (q.size()>0) {
                    q.addLast(null);
                }
                order_ltor = !order_ltor;
            }
        }
        return result;
    }
    
    public static void main(String args[]) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(""+TreeTraversalZigZagOrder.zigzagLevelOrder(root).toString());
    }
}
