package com.dsalgo.practice;

import com.dsalgo.fb.common.ListNode;

public class ReorderLinkedList {

    public static void reorderList(ListNode head) {

        // find middle of the linked list
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode firstHalf = head;
        // reverse the 2nd half
        ListNode secondHalf = reverse(slow);
        while(firstHalf != null && secondHalf!= null) {
            ListNode temp = firstHalf.next;
            firstHalf.next = secondHalf;
            firstHalf = temp;

            temp = secondHalf.next;
            secondHalf.next = firstHalf;
            secondHalf = temp;
        }
        if (firstHalf !=null) {
            firstHalf.next = null;
        }
    }
    public static ListNode reverse(ListNode head) {
        ListNode previous = null;
        ListNode current = head;
        while(current != null) {
            ListNode temp = previous;
            previous = current;
            current = current.next;
            previous.next = temp;
        }
        return previous;
    }

    public static void printLL(ListNode head) {
        ListNode node = head;
        StringBuilder sb = new StringBuilder();
        while(node != null) {
            sb.append(node.val);
            if (node.next != null) {
                sb.append(" -> ");
            }
            node = node.next;
        }
        System.out.println(sb.toString());
    }
    public static void main(String args[]) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4,  new ListNode(5)))));
        printLL(head);
        ReorderLinkedList.reorderList(head);
        printLL(head);
    }
}
