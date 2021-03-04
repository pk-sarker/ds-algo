package com.ds.practice.common;

public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }
    public TreeNode(int val) {
        this.value = val;
        this.left = null;
        this.right = null;
    }
    public TreeNode(int val, TreeNode left) {
        this.value = val;
        this.left = left;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.value = val;
        this.left = left;
        this.right = right;
    }
}
