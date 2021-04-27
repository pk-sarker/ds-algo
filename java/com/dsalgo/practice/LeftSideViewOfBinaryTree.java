package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LeftSideViewOfBinaryTree {
    public static List<Integer> leftSideView(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> leftView = new ArrayList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int levelSpan = queue.size();
            for(int i=0; i<levelSpan;i++) {
                TreeNode node = queue.poll();
                if (i==0) {
                    leftView.add(node.value);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return leftView;
    }


    public static List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> rightView = new ArrayList<>();

        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                TreeNode node = queue.poll();
                if (i==size-1) {
                    rightView.add(node.value);
                }
                if (node.left != null ){
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return rightView;
    }


    public static void main(String args[]) {
        TreeNode root = new TreeNode(7, new TreeNode(5, null, new TreeNode(2)), new TreeNode(8, new TreeNode(10, new TreeNode(12), new TreeNode(14))));
        System.out.println(LeftSideViewOfBinaryTree.leftSideView(root));
        System.out.println(LeftSideViewOfBinaryTree.rightSideView(root));

        TreeNode root1 = new TreeNode(7, new TreeNode(5, new TreeNode(2), new TreeNode(4)), new TreeNode(8, new TreeNode(10, new TreeNode(12), new TreeNode(14))));
        System.out.println(LeftSideViewOfBinaryTree.leftSideView(root1));
        System.out.println(LeftSideViewOfBinaryTree.rightSideView(root1));
    }
}
