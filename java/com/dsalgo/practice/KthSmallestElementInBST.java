package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary search tree, and an integer k, return the kth (1-indexed) smallest element in the tree.
 *
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 */
public class KthSmallestElementInBST {

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> nodeList = new ArrayList<>();
        inorder(root, nodeList);
        return nodeList.get(k-1);

    }

    private List<Integer> inorder(TreeNode node, List<Integer> nodeList) {
        if (node == null) {
            return nodeList;
        }
        inorder(node.left, nodeList);
        nodeList.add(node.value);
        inorder(node.right, nodeList);
        return nodeList;
    }

    public int kthSmallestItr(TreeNode root, int k) {
        LinkedList<TreeNode> stack =  new LinkedList<>();

        while(true) {
            while(root!= null) {
                stack.offer(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (--k == 0) {
                return root.value;
            }
           // System.out.println( " > " + root.value);

            root = root.right;

            if (root.right != null) {
                root = root.right;
            }
        }

    }

    public static void main(String args[]) {
        KthSmallestElementInBST obj = new KthSmallestElementInBST();
        TreeNode root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));
        System.out.println(obj.kthSmallest(root, 2));
        //TreeNode root1 = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));
        System.out.println(obj.kthSmallestItr(root, 2));

    }
}
