package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

/**
 * Given the root of a binary search tree and a target value,
 * return the value in the BST that is closest to the target.
 *
 * Input: root = [4,2,5,1,3], target = 3.714286
 * Output: 4
 *
 * Input: root = [1], target = 4.428571
 * Output: 1
 *
 */
public class ClosestBinarySearchTreeValue {

    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return -1;
        }
        int closest = root.value;
        TreeNode node = root;
        while(node != null) {
            closest = Math.abs(node.value-target) < Math.abs(closest-target) ? node.value : closest;
            node = target < node.value ? node.left: node.right;
        }
        return closest;
    }
    
    public static void main(String args[]) {
        ClosestBinarySearchTreeValue obj = new ClosestBinarySearchTreeValue();
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(5));
        System.out.println(obj.closestValue(root, 0.5d));
    }
}
