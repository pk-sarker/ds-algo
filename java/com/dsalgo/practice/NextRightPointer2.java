package com.dsalgo.practice;

import com.dsalgo.practice.common.NodeV2;

/**
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node,
 * the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 */
public class NextRightPointer2 {

    public NodeV2 connect(NodeV2 root) {
        if (root == null) {
            return null;
        }
        NodeV2 prev, leftmost;

        /**
         * We need to keep track of the first node of each level. That mean we need to set the first node of
         * next level while processing nodes at current level. We call leftmost as the first node in a level
         *
         * So each node may not have both left and right child, for example,
         *          a
         *         / \
         *        b   c
         *       / \   \
         *      d  e   f
         *        / \
         *       g  h
         * here for node e, the next pointer should point to f, which is the right child of c
         * Also there may not be any more node at the same level, like for node h, the right pointer
         * should point to null
         *
         * So the idea is we will keep a variable, prev, which will store the last visited node,
         * but next pointer is not set. When we visit the next available node then we set next pointer of
         * the previous node to current node.
         */

        leftmost = root;
        while(leftmost != null) {

            NodeV2 current = leftmost;
            leftmost = null;
            prev = null;

            while(current != null) {

                if (current.left != null) {
                    if (prev == null) {
                        leftmost = current.left;
                    } else {
                        prev.next = current.left;
                    }
                    prev = current.left;
                }
                if (current.right != null) {
                    if (prev == null)    {
                        leftmost = current.right;
                    } else {
                        prev.next = current.right;
                    }
                    prev = current.right;
                }
                current = current.next;
            }
        }
        return root;
    }
    public  static void main(String args[]) {
        NextRightPointer2 obj = new NextRightPointer2();
        NodeV2 root = new NodeV2(1, new NodeV2(2, new NodeV2(4), new NodeV2(5), null), new NodeV2(3, new NodeV2(6), new NodeV2(7), null), null);
        NodeV2 node = obj.connect(root);
    }
}
