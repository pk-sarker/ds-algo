package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BalanceABinarySearchTree {
    List<TreeNode> sortedNodes = new ArrayList<>();
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        inOrderTraverse(root);
        TreeNode node = sortedNodesToBST(0, sortedNodes.size()-1);
        return node;
    }

    public void inOrderTraverse(TreeNode root) {
        if (root == null) {
            return ;
        }
        inOrderTraverse(root.left);
        sortedNodes.add(root);
        inOrderTraverse(root.right);
    }

    public TreeNode sortedNodesToBST(int start, int end) {
        if (start > end) return null;
        int mid = start + ((end-start) / 2);
        TreeNode root = sortedNodes.get(mid); // get the middle node
        root.left = sortedNodesToBST(start, mid - 1);
        root.right = sortedNodesToBST(mid + 1, end);
        return root;
    }

    public static void main(String args[]) {
        BalanceABinarySearchTree obj = new BalanceABinarySearchTree();
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(5, null, new TreeNode(7, null, new TreeNode(9))))));
        TreeNode root2 = obj.balanceBST(root);
    }
}
