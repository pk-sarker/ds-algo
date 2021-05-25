package com.dsalgo.practice;

import com.dsalgo.practice.common.ListNode;

public class RemoveLinkedListElements {

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode current = head;
        ListNode prev = head;
        while(current != null) {
            if (current.val == val) {
                if (head.val == current.val) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
            } else {
                prev = current;
            }
            current = current.next;
        }
        return head;
    }

    public static String printLL(ListNode head) {
        ListNode cur = head;
        StringBuilder sb = new StringBuilder();
        while(cur != null) {
            sb.append(cur.val);
            if (cur.next != null) {
                sb.append(" -> ");
            }
            cur = cur.next;
        }
        return sb.toString();
    }
    public static void main(String args[]) {
        // ,6,1,3,2,2,7,2,4,9,1,2
        ListNode head = new ListNode(2, new ListNode(6, new ListNode(1, new ListNode(3, new ListNode(2, new ListNode(2, new ListNode(7, new ListNode(2))))))));
        System.out.println("Input: " + printLL(head));
        ListNode res = RemoveLinkedListElements.removeElements(head, 2);
        System.out.println("Output: " + printLL(res));

    }
}
