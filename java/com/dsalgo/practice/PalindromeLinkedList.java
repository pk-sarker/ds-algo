package com.dsalgo.practice;

import com.dsalgo.practice.common.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Input: 1->2
 * Output: false
 *
 * Input: 1->2->2->1
 * Output: true
 *
 * Input: 1 -> 2 -> 3 -> 1
 * Output: false
 *
 * Input: 1 -> 3 -> 2 -> 3 -> 1
 * Output: true
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // find the middle
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the 2nd half
        ListNode secondHalfHead = reverse(slow);
        while(head != null && secondHalfHead!= null) {
            if (head.val != secondHalfHead.val) {
                return false;
            }
            head = head.next;
            secondHalfHead = secondHalfHead.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode previous = null, current = head;
        while(current != null) {
            ListNode temp = previous;
            ListNode curNext  = current.next;
            previous = current;
            previous.next = temp;
            current = curNext;
        }
        return previous;
    }

    public StringBuilder printLinkedList(ListNode head) {
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
    public static void main(String args[]) {
        PalindromeLinkedList obj = new PalindromeLinkedList();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(1)));
        System.out.println("\nInput: "+obj.printLinkedList(head).toString()+"\nOutput: " + obj.isPalindrome(head));

        head = new ListNode(1, new ListNode(2, new ListNode(3)));
        System.out.println("\nInput: "+obj.printLinkedList(head).toString()+"\nOutput: " + obj.isPalindrome(head));

        head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        System.out.println("\nInput: "+obj.printLinkedList(head).toString()+"\nOutput: " + obj.isPalindrome(head));

        head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(1))));
        System.out.println("\nInput: "+obj.printLinkedList(head).toString()+"\nOutput: " + obj.isPalindrome(head));

        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        System.out.println("\nInput: "+obj.printLinkedList(head1).toString()+"\nOutput: " + obj.isPalindrome(head1));

        head1 = new ListNode(1, new ListNode(3, new ListNode(2, new ListNode(1))));
        System.out.println("\nInput: "+obj.printLinkedList(head1).toString()+"\nOutput: " + obj.isPalindrome(head1));

        ListNode head2 = new ListNode(1, new ListNode(3, new ListNode(2)));
        System.out.println("\nInput: "+obj.printLinkedList(head2).toString()+"\nOutput: " + obj.isPalindrome(head2));

        head2 = new ListNode(1, new ListNode(3, new ListNode(2, new ListNode(3, new ListNode(1)))));
        System.out.println("\nInput: "+obj.printLinkedList(head2).toString()+"\nOutput: " + obj.isPalindrome(head2));


        head2 = new ListNode(1, new ListNode(1, new ListNode(1)));
        System.out.println("\nInput: "+obj.printLinkedList(head2).toString()+"\nOutput: " + obj.isPalindrome(head2));

        head2 = new ListNode(1);
        System.out.println("\nInput: "+obj.printLinkedList(head2).toString()+"\nOutput: " + obj.isPalindrome(head2));
    }
}
