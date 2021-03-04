package com.ds.practice;

import com.ds.practice.common.ListNode;

/**
 * Merge two sorted linked lists and return it as a sorted list.
 * The list should be made by splicing together the nodes of the first two lists.
 *
 * Input: l1 = [1,2,4], l2 = [1,3,4,5]
 * Output: [1,1,2,3,4,4,5]
 *
 * Input: l1 = [], l2 = []
 * Output: []
 *
 * Input: l1 = [], l2 = [0]
 * Output: [0]
 */
public class MergeTwoSortedLinkedList {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode prehead = new ListNode(-1);
        ListNode result = prehead;
        while(l1!= null && l2!=null) {
            if  (l1.val <= l2.val) {
                result.next = l1;
                l1 = l1.next;
            } else {
                result.next = l2;
                l2 = l2.next;
            }

            result = result.next;
        }
        if (l1 != null ) {
            result.next = l1;
        } else if (l2!=null) {
            result.next = l2;
        }
        return prehead.next;
    }

    public static ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = MergeTwoSortedLinkedList.mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = MergeTwoSortedLinkedList.mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }

    public static StringBuilder printLinkedList(ListNode head) {
        ListNode current = head;
        StringBuilder sb = new StringBuilder();
        while(current != null){
            sb.append(current.val);
            if (current.next!= null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb;
    }

    public static  void main(String args[]) {

        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(5))));
        System.out.println("\nInput: \nList 1: " + MergeTwoSortedLinkedList.printLinkedList(l1).toString() + "\nList 2: " + MergeTwoSortedLinkedList.printLinkedList(l2).toString());
        MergeTwoSortedLinkedList.printLinkedList(l1);
        MergeTwoSortedLinkedList.printLinkedList(l2);
        ListNode res = MergeTwoSortedLinkedList.mergeTwoLists(l1, l2);
        System.out.println("Output: " + MergeTwoSortedLinkedList.printLinkedList(res).toString());


        System.out.println("");
        l1 = null;
        l2 = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(5))));
        System.out.println("\nInput: \nList 1: null \nList 2: " + MergeTwoSortedLinkedList.printLinkedList(l2).toString());
        res = MergeTwoSortedLinkedList.mergeTwoLists(l1, l2);
        System.out.println("Output: " + MergeTwoSortedLinkedList.printLinkedList(res).toString());

        System.out.println("");

        System.out.println("\nInput: \nList 1: null \nList 2: null");
        System.out.println("Output: " + MergeTwoSortedLinkedList.printLinkedList(MergeTwoSortedLinkedList.mergeTwoLists(null, null)).toString());

        l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        l2 = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(5))));
        System.out.println("\nRecursive approach \nList 1: 1 -> 2 -> 4 \nList 2: 1 -> 3 -> 4 -> 5\nOutput: " + MergeTwoSortedLinkedList.printLinkedList(MergeTwoSortedLinkedList.mergeTwoListsRecursive(l1, l2)).toString());

    }
}
