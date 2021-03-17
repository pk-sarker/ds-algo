package com.dsalgo.practice;

import com.dsalgo.practice.common.ListNode;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 */
public class ReverseSubLinkedList {


    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) {
            return head;
        }
        ListNode orgHead = head;
        ListNode startNode = head;
        ListNode nodeBeforeStart = null, previous = null;
        while(startNode != null) {
            if (startNode.val == m) {
                break;
            }
            nodeBeforeStart = startNode;
            startNode = startNode.next;
        }
        previous = nodeBeforeStart;
        ListNode lastNodeOfSubList = startNode;
        ListNode current = startNode;

        while(current != null) {
            ListNode temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;

            if (previous.val == n) {
                break;
            }
        }

        // connect with the first part
        if (nodeBeforeStart != null) {
            nodeBeforeStart.next = previous; // 'previous' is now the first node of the sub-list
        } else {
            orgHead = previous;
        }

        // connect with the last part
        lastNodeOfSubList.next = current;
        return orgHead;
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
        ReverseSubLinkedList obj = new ReverseSubLinkedList();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7,  new ListNode(9))))))));
        obj.printLinkedList(head);
        ListNode res = obj.reverseBetween(head, 3, 6);
        obj.printLinkedList(res);


        ListNode head2 = new ListNode(5, new ListNode(9));
        // ListNode head2 = new ListNode(5, new ListNode(7, new ListNode(9)));
        obj.printLinkedList(head2);
        ListNode res2 = obj.reverseBetween(head2, 5, 9);
        obj.printLinkedList(res2);

    }
}
