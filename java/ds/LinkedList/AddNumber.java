package ds.LinkedList;

import Common.ListNode;

public class AddNumber {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode res = preHead;
        int carry = 0;
        while(l1 != null || l2!= null) {
            int n1 = l1 != null ? l1.val: 0;
            int n2 = l2 != null ? l2.val: 0;

            int sum = carry + n1+ n2;

            carry = sum/10;

            res.next = new ListNode(sum % 10);
            res = res.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            res.next = new ListNode(carry);
        }

        return preHead.next;
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
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        printLL(l1);
        printLL(l2);
        ListNode sumL =  addTwoNumbers(l1, l2);
        printLL(sumL);
    }
}
