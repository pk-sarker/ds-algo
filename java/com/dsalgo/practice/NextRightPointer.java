package com.dsalgo.practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Follow up:
 *
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 */
public class NextRightPointer {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };


    public static Node connect(Node root) {
        if (root  == null)  {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;
            for(int i=0;i<size;i++) {
                Node node = queue.poll();
                node.next = prev;

                if (node.right != null) {
                    queue.offer(node.right);
                }

                if (node.left != null ) {
                    queue.offer(node.left);
                }
                prev = node;
            }
        }
        return root;
    }

    /**
     * The idea is to traverse the tree and set the next pointer while traversing
     *           a
     *         /  \
     *        b    c
     *      /  \  / \
     *     d   e f   g
     * Next pointer should set to the immediate right node, There are two cases
     * 1) the immediate right node belongs to the same parent of current node,
     *    so right child of the current nodes parent. like [b a c]
     * 2) the immediate right node belongs to different parent, for example node e and f
     *    e's parent is b and f's parent is c. In this case we should have already set the next pointer
     *    of current nodes' parent  if not root node. So we can just go next of current nodes parent and then
     *    left child of that parent, right node of e: e->b, b->c, c->f
     *
     *
     *
     */
    public static Node connectSpaceOpt(Node root) {
        if (root == null) {
            return null;
        }
        Node leftmost = root;
        while(leftmost.left != null) {
            Node head = leftmost;
            while(head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                // go to next right node
                head = head.next;
            }
            // proceed to next level
            leftmost = leftmost.left;
        }
        return root;
    }

    public  static void main(String args[]) {
        Node root = new Node(1, new Node(2, new Node(4), new Node(5), null), new Node(3, new Node(6), new Node(7), null), null);
        Node node = NextRightPointer.connect(root);
    }
}

