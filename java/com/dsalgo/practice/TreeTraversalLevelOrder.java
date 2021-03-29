package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeTraversalLevelOrder {

    List<List<Integer>> levels = new ArrayList<>();

    public List<List<Integer>> levelOrderTraversalWithRecursion(TreeNode root) {
        helper(root, 0);
        return levels;
    }

    public void helper(TreeNode node, int level) {
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(node.value);

        if (node.left != null) {
            this.helper(node.left, level+1);
        }
        if (node.right != null) {
            this.helper(node.right, level+1);
        }
    }


    public List<List<Integer>> levelOrderIterative(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            List<Integer> nodesInLevel = new ArrayList<>();
            int qSize = queue.size();
            for(int i=0; i<qSize; i++) {
                TreeNode node = queue.poll();
                nodesInLevel.add(node.value);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            result.add(nodesInLevel);
        }

        return result;
    }

    public static void main(String args[]) {
        TreeTraversalLevelOrder obj = new TreeTraversalLevelOrder();
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        List<List<Integer>> res = obj.levelOrderTraversalWithRecursion(root);
        System.out.println(res.toString());

        TreeNode root1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(obj.levelOrderIterative(root1).toString());
    }
}
