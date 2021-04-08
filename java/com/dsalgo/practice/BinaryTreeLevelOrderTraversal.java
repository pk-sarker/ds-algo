package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
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

        return result;
    }


    List<List<Integer>> recResult = new ArrayList<>();
    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        traverse(root, 0);
        return recResult;
    }

    public void traverse(TreeNode node, int  level) {
        if (node==null) {
            return;
        }
        if (recResult.size() == level) {
            recResult.add(new ArrayList<>());
        }
        recResult.get(level).add(node.value);
        traverse(node.left, level+1);
        traverse(node.right, level+1);
    }


    public static void main(String args[]) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        List<List<Integer>> result = BinaryTreeLevelOrderTraversal.levelOrder(root);
        System.out.println(result);

        BinaryTreeLevelOrderTraversal obj =  new BinaryTreeLevelOrderTraversal();
        TreeNode root1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        List<List<Integer>> result1 = obj.levelOrderRecursive(root1);
        System.out.println(result1);

    }
}
