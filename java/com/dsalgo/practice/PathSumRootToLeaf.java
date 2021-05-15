package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.Stack;


public class PathSumRootToLeaf {

    public class Pair<T1,T2> {
        public T1 key;
        public T2 sum;
        public Pair(T1 key, T2 sum) {
            this.key = key;
            this.sum = sum;
        }
    }
    public int sumNumbersItr(TreeNode root) {
        int totalSum = 0;

        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair(root, 0));

        while(!stack.isEmpty()) {
            Pair<TreeNode, Integer> nodePair = stack.pop();
            TreeNode node = nodePair.key;
            int pathSum = nodePair.sum * 10 + node.value;

            // if leaf node
            if (node.left  == null && node.right == null) {
                totalSum += pathSum;
            }
            if (node.right != null) {
                stack.push(new Pair(node.right, pathSum));
            }
            if (node.left != null) {
                stack.push(new Pair(node.left, pathSum));
            }
        }
        return totalSum;
    }

    int allPathSum = 0;
    public int sumNumbers(TreeNode root) {

        dfsRecur(root, 0);
        return allPathSum;
    }

    public void dfsRecur(TreeNode root, int prevSum) {
        int pathSum = prevSum * 10 + root.value;
        if (root.left == null && root.right ==null) {
            allPathSum += pathSum;
            return;
        }
        if (root.left!=null) {
            dfsRecur(root.left, pathSum);
        }

        if (root.right!=null) {
            dfsRecur(root.right, pathSum);
        }
    }

    public static void main(String args[]) {
        PathSumRootToLeaf obj = new PathSumRootToLeaf();
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Sum 1: " + obj.sumNumbersItr(root));

        TreeNode root1 = new TreeNode(1, new TreeNode(2, null, new TreeNode(4)), new TreeNode(3, new TreeNode(1), null));
        System.out.println("Sum 2: " + obj.sumNumbersItr(root1));


        TreeNode root3 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("\n--Recursive--\nSum 1: " + obj.sumNumbers(root3));
        obj.allPathSum = 0;
        TreeNode root4 = new TreeNode(1, new TreeNode(2, null, new TreeNode(4)), new TreeNode(3, new TreeNode(1), null));
        System.out.println("Sum 2: " + obj.sumNumbers(root4));


    }
}
