package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevelsInBinaryTree {

    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for(int i=0;i<size; i++) {
                TreeNode node = queue.poll();
                sum += node.value;

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            result.add(sum/size);
        }
        return result;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));
        System.out.println("\nOutput: " + AverageOfLevelsInBinaryTree.averageOfLevels(root).toString());

        TreeNode root2 = new TreeNode(2147483647, new TreeNode(2147483647), new TreeNode(2147483647));
        System.out.println("\nOutput: " + AverageOfLevelsInBinaryTree.averageOfLevels(root2).toString());
    }
}
