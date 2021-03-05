package com.dsalgo.practice;

import com.dsalgo.practice.common.ListNode;

/**
 * Reverse a singly linked list.
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode previous = null;
        ListNode current = head;

        while(current != null) {
            ListNode temp = previous;
            ListNode curNext = current.next;
            previous = current;
            previous.next = temp;
            current = curNext;
        }

        return previous;
    }

    public ListNode reverseListRecursion(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode previous = null;
        ListNode current = head;

        return reverse(current, previous);
    }

    public ListNode reverse(ListNode head, ListNode previous) {
        if (head == null) {
            return previous;
        }
        ListNode current = head;
        ListNode curNext  = current.next;
        ListNode prev = previous;
        previous = current;
        previous.next = prev;
        current = curNext;
        return reverse(current, previous);
    }

    public void printLinkedList(ListNode head) {
        ListNode current = head;
        StringBuilder sb = new StringBuilder();
        while(current != null){
            sb.append(current.val);
            if (current.next!= null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String args[]) {
        ReverseLinkedList obj = new ReverseLinkedList();
        ListNode head = new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(7,  new ListNode(9)))));
        obj.printLinkedList(head);

        ListNode rev = obj.reverseListRecursion(head);

        obj.printLinkedList(rev);

    }
}
