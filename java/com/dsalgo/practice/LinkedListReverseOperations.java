package com.dsalgo.practice;

import com.dsalgo.fb.common.ListNode;

/**
 * You are given a singly-linked list that contains N integers. A subpart of the list is a contiguous set of even elements,
 * bordered either by either end of the list or an odd element. For example, if the list is [1, 2, 8, 9, 12, 16], the subparts of the list are [2, 8] and [12, 16].
 * Then, for each subpart, the order of the elements is reversed. In the example, this would result in the new list, [1, 8, 2, 9, 16, 12].
 * The goal of this question is: given a resulting list, determine the original order of the elements.
 *
 * [1, 2, 8, 9, 12, 16]
 * [1, 8, 2, 9, 16, 12]
 *
 * 1 -> 2 -> 8 -> 9 -> 12 -> 16
 * 2 => start 2, prev and current
 * 8 even ? Y -> if there are more than 1 nodes reverse
 * - if even node count > 1:
 *      - put current node before head,
 *      - point previous node to currents nodes next
 * 1 -> 8 -> 2 -> 9 -> 12 -> 16
 *
 * for example 1 -> 2 -> 8 -> 10 -> 9 -> 12 -> 16
 *  1 -> 8 -> 2 -> 10 -> 9 -> 12 -> 16
 *
 *  1 -> 10 -> 8 -> 2 -> 10 -> 9 -> 12 -> 16
 *
 */
public class LinkedListReverseOperations {

    public static ListNode reverse(ListNode head) {

        ListNode start = null, prev =  null, preStart = null, current = head;
        int evenNodeCount = 0;
        boolean isPrevNodeEven = false;
        while(current != null) {
            boolean isCurNodeEven = current.val % 2 == 0;
            if (isCurNodeEven) {
                evenNodeCount++;
                if (start == null) {
                    start = current;
                    isPrevNodeEven = true;
                    preStart = prev;

                } else {
                    ListNode tempNext = current.next;
                    if (preStart == null) {
                        prev.next = tempNext;
                        current.next = start;
                        head = current;
                        start = current;
                    } else {
                        preStart.next = current;
                        current.next = start;
                        start = preStart.next;
                        prev.next = tempNext;
                    }
                    current = prev;
                }
            }

            if (isPrevNodeEven && !isCurNodeEven) {
                // reset the flags/pointers
                start = null;
                preStart = null;
                evenNodeCount = 0;
                isPrevNodeEven = false;
            }
            prev = current;
            current = current.next;
        }

        return head;
    }

    public static void printLL(ListNode head) {
        ListNode cur = head;
        StringBuilder sb = new StringBuilder();
        while(cur != null) {
            sb.append(cur.val);
            if (cur.next != null) {
                sb.append(" -> ");
            }
            cur = cur.next;
        }
        System.out.println(sb.toString());
    }
    public static void main(String ars[]) {
        // 1, 2, 8, 9, 12, 16
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(8, new ListNode(9, new ListNode(12, new ListNode(16))))));
        LinkedListReverseOperations.printLL(head);
        LinkedListReverseOperations.printLL(LinkedListReverseOperations.reverse(head));


        // 2, 18, 24, 3, 5, 7, 9, 6, 12
        ListNode head1 = new ListNode(2, new ListNode(18, new ListNode(24, new ListNode(3, new ListNode(5, new ListNode(7, new ListNode(9, new ListNode(6, new ListNode(12)))))))));
        LinkedListReverseOperations.printLL(head1);
        LinkedListReverseOperations.printLL(LinkedListReverseOperations.reverse(head1));



    }
}
