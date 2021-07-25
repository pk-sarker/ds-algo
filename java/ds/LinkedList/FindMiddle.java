package ds.LinkedList;

import Common.ListNode;

public class FindMiddle {

    public static int findMid(ListNode head) {

        if (head == null) {
            return -1;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.val;
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
        System.out.println("Middle: " + FindMiddle.findMid(head));

    }
}
