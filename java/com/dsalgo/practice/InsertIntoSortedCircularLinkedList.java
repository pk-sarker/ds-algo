package com.dsalgo.practice;

import com.dsalgo.practice.common.ListNode;

public class InsertIntoSortedCircularLinkedList {

    public ListNode insert(ListNode head, int insertVal) {
        if (head == null) {
            head = new ListNode(insertVal);
            head.next = head;
            return  head;
        }
        ListNode node = new ListNode(insertVal);
        ListNode current = head.next;
        ListNode previous =  head;
        boolean insert = false;
        do {
            if (insertVal >= previous.val && insertVal <= current.val) {
                insert = true;
            } else if (previous.val > current.val) { // loop break
                if (insertVal >= previous.val || insertVal <= current.val) {
                    insert = true;
                }
            }
            if (insert) {
                node.next = current;
                previous.next = node;
                return head;
            }
            previous = current;
            current = current.next;
        } while(previous != head);
        previous.next = new ListNode(insertVal, current);
        return head;
    }

    public String printLL(ListNode head) {
        ListNode cur = head;
        ListNode end = head;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(cur.val);
            if (cur.next != null) {
                sb.append(" -> ");
            }
            cur = cur.next;
        } while(cur != null && cur != end);
        return sb.toString();
    }
    public static void main(String args[]) {
        InsertIntoSortedCircularLinkedList obj = new InsertIntoSortedCircularLinkedList();
        ListNode head = new ListNode(1);
        ListNode tail = new ListNode(9);
        tail.next = head;
        ListNode rem = new ListNode(3, new ListNode(5, new ListNode(7, tail)));
        head.next = rem;
        //head.next = 1ail
        System.out.println(obj.printLL(head));
        ListNode res = obj.insert(head, 6);
        System.out.println(obj.printLL(res));

        ListNode res2 = obj.insert(head, 10);
        System.out.println(obj.printLL(res2));

        ListNode res3 = obj.insert(head, -1);
        System.out.println(obj.printLL(res3));
    }
}
