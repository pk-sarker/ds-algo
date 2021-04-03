package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 */
public class RightSideViewOfBinaryTree {

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {

            int qSize = queue.size();
            for(int i=0; i<qSize; i++) {
                TreeNode node = queue.poll();
                if (node.left!= null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (i==qSize-1) {
                    result.add(node.value);
                }
            }
        }
        return result;
    }
    public static void printList(List<Integer> result) {
        StringBuilder sb = new StringBuilder();
        result.forEach(node -> {
            sb.append(node);
            sb.append(",");
        });
        sb.setLength(sb.length()-1);
        System.out.println("\nOutput: [" + sb.toString() + "]");
    }
    public static void main(String args[])  {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4)), new TreeNode(3));
        List<Integer> result = RightSideViewOfBinaryTree.rightSideView(root);
        System.out.println("Input:\n"+
                "         1\n" +
                "       /  \\\n" +
                "      2    3\n" +
                "     /\n"+
                "    4\n");
        RightSideViewOfBinaryTree.printList(result);

        TreeNode root1 = new TreeNode(1, new TreeNode(2, null, new TreeNode(4, new TreeNode(6))), new TreeNode(3, new TreeNode(5), null));
        List<Integer> result1 = RightSideViewOfBinaryTree.rightSideView(root1);
        System.out.println("Input:\n"+
                "         1\n" +
                "       /  \\\n" +
                "      2    3\n" +
                "     /    /\n"+
                "    4    5\n"+
                "   /\n"+
                "  6\n");
        RightSideViewOfBinaryTree.printList(result1);
    }
}
