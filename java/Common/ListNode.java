package Common;


public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int value) {
        this.val = value;
    }

    public ListNode(int value, ListNode next) {
        this.val = value;
        this.next = next;
    }

    public static void printNodes(ListNode head) {
        ListNode current = head;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        System.out.println(sb.toString());
    }
    public static void printNodes(ListNode head, String printPrefix) {
        ListNode current = head;
        StringBuilder sb = new StringBuilder();
        sb.append(printPrefix);
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) sb.append(" -> ");
            current = current.next;
        }
        System.out.println(sb.toString());
    }
}
