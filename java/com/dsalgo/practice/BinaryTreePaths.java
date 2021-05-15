package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    List<String> allPaths = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root, new StringBuilder());
        return allPaths;
    }
    public void dfs(TreeNode node, StringBuilder prevPath) {
        if (node == null) {
            return;
        }
        // current path after added the current node to previous path
        if (prevPath.length() > 0) {
            prevPath.append("->");
        }
        prevPath.append(node.value);

        if (node.left == null && node.right == null) {
            allPaths.add(prevPath.toString());
        }
        dfs(node.left, prevPath);
        dfs(node.right, prevPath);

        int nodeLen = ((node.value + "").length()+2);
        if (prevPath.length()>=nodeLen) {
            prevPath.setLength(prevPath.length()-nodeLen);
        }
    }
    public static void main(String args[]) {
        BinaryTreePaths obj = new BinaryTreePaths();
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        System.out.println(obj.binaryTreePaths(root).toString());


        TreeNode root1 = new TreeNode(1, new TreeNode(-200), new TreeNode(300, new TreeNode(-45), new TreeNode(5)));
        System.out.println(obj.binaryTreePaths(root1).toString());

    }
}
