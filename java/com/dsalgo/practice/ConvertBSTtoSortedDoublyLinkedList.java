package com.dsalgo.practice;

import com.dsalgo.practice.common.Node;

/**
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 *
 * You can think of the left and right pointers as synonymous to the predecessor and successor
 * pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the
 * first element is the last element, and the successor of the last element is the first element.
 *
 * We want to do the transformation in place. After the transformation, the left pointer of the
 * tree node should point to its predecessor, and the right pointer should point to its successor.
 * You should return the pointer to the smallest element of the linked list.
 *
 *             4
 *            / \
 *           2  5
 *         / \
 *        1  3
 *
 * Solution: the idea is the find the left most node, which is going to be the smallest, then point both
 * head and tail to the smallest/leftmost node.
 *
 * then for each node we extend the tail, replace the tail with new node
 *
 *          2
 *        /  \
 *       1   3
 * In this case where we are at node 1, Then both head and tail points to 1,
 *  -> then we traverse 2, now our tail points to 2: 1 -> 2
 *  -> then we traverse 3, now our tail points to 3: 1 -> 2 -> 3
 *
 */
public class ConvertBSTtoSortedDoublyLinkedList {

    Node head = null;
    Node tail = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        converter(root);
        tail.right = head;
        head.left = tail;
        return head;
    }
    
    public void converter(Node node) {
        if (node == null) {
            return ;
        }
        // traverse left child
        converter(node.left);

        // traverse node
        if (tail != null) {
            // link the tail node / previous node with the current node
            tail.right = node;
            node.left = tail; // current node's previous pointer points to the tail node
        } else {
            head = node; // executed only once
        }


        tail = node; // updated the tail node to current node.
        // traverse right child
        converter(node.right);
    }

    public static void printLL(Node head) {
        Node cur = head;
        StringBuilder sb = new StringBuilder();
        while(cur != null) {

            sb.append(cur.val);
            if (cur.left != null) {
                sb.append(" -> ");
            }
            cur = cur.right;
            if (cur.val == head.val) {
                break;
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String args[]) {
        Node root = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5));
        ConvertBSTtoSortedDoublyLinkedList obj = new ConvertBSTtoSortedDoublyLinkedList();

        Node node = obj.treeToDoublyList(root);
        printLL(node);
    }
}
