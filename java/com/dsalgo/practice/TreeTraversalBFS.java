package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeTraversalBFS {
    public List<Integer> traverse(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();

        if (root != null) {
            queue.add(root);
        }
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.value);
            if (node.left!=null) {
                queue.add(node.left);
            }
            if (node.right!= null) {
                queue.add(node.right);
            }
        }
        return result;
    }

    public static void main(String args[]) {
        TreeTraversalBFS obj = new TreeTraversalBFS();
        TreeNode root = new TreeNode(5, new TreeNode(7, null, new TreeNode(9, new TreeNode(11), new TreeNode(8))), new TreeNode(10, new TreeNode(3, null, new TreeNode(4)), new TreeNode(6, new TreeNode(2), new TreeNode(1))));

        System.out.println("                   5\n" +
                "                 /    \\\n" +
                "                7     10\n" +
                "                \\    /  \\\n" +
                "                9   3    6\n" +
                "               / \\   \\  / \\\n" +
                "              11 8   4 2  1");
        System.out.println("In Order: " + obj.traverse(root).toString());
    }
}
