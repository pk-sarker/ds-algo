package ds.LinkedList;

import Common.ListNode;

public class ReverseLinkedList {

    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }

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
        ListNode cur =  head;
        StringBuilder sb = new StringBuilder();

        while(cur != null) {
            sb.append(cur.val);
            if (cur.next != null) {
                sb.append("->");
            }
            cur = cur.next;
        }
        System.out.println(sb.toString());
    }

    public static void main(String args[]) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        printLL(head);
        ListNode revList = ReverseLinkedList.reverse(head);
        printLL(revList);

    }

}