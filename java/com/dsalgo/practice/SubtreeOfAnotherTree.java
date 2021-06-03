package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

/**
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree
 * of root with the same structure and node values of subRoot and false otherwise.
 *
 * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this
 * node's descendants. The tree tree could also be considered as a subtree of itself.
 *
 * Input: root = [3,4,5,1,2], subRoot = [4,1,2]
 * Output: true
 *
 * Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * Output: false
 */
public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) { // if
            return false;
        }
        if (compare(s,t)){ // if both s and t same
            return true;
        }
        // if s and t are not the same then
        // we can compare left subtree of s with t and right subtree of s with t
        return isSubtree(s.left, t) ||  isSubtree(s.right, t);
    }

    public boolean compare(TreeNode s, TreeNode t) {
        if(s == null && t == null) { // both of the nodes are null
            return true;
        }
        if (s == null || t == null) { // one of the node is null
            return false;
        }
        if (s.value != t.value) { //
            return false;
        }
        // both left child and right child of both trees has to be same
        return compare(s.left, t.left) && compare(s.right, t.right);
    }
    public static void main(String args[]) {
        SubtreeOfAnotherTree obj = new SubtreeOfAnotherTree();
        TreeNode s = new TreeNode(8, new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(5));
        TreeNode t = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        System.out.println(obj.isSubtree(s ,t));
    }
}
