package ds.LinkedList;

import Common.ListNode;

public class LinkedListCycle {

    public static boolean hasCycle(ListNode head) {
        ListNode fastPtr = head, slowPtr = head;
        while (fastPtr != null && slowPtr != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;

            if (fastPtr == slowPtr) {
                return true;
            }
        }
        return false;
    }

    public static int findCycleLength(ListNode head) {
        ListNode fastPtr = head, slowPtr = head;
        while (fastPtr != null && slowPtr != null) {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;

            if (fastPtr == slowPtr) {
                return LinkedListCycle.cycleLength(slowPtr);
            }
        }
        return 0;
    }

    public static int cycleLength(ListNode slow) {
        int length = 0;
        ListNode current = slow;

        do {
            current = current.next;
            length++;
        } while(current != slow);

        return length;
    }
    public static void main(String args[]) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head) + " cycle length: " + LinkedListCycle.findCycleLength(head));


        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head) + " cycle length: " + LinkedListCycle.findCycleLength(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head) + " cycle length: " + LinkedListCycle.findCycleLength(head));

    }
    
}
