package ds.BinaryTree;

import Common.ListNode;
import Common.TreeNode;

/**
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it
 * to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth
 * of the two subtrees of every node never differ by more than 1.
 *
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
 *
 * Input: head = []
 * Output: []
 *
 * Input: head = [0]
 * Output: [0]
 *
 * Input: head = [1,3]
 * Output: [3,1]
 */
public class ConvertSortedListToBinarySearchTree {
    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }

    ListNode head;
    public TreeNode sortedListToBST(ListNode head) {
        this.head = head;
        int size = this.findSize(head);
        return convert(0, size-1);
    }

    private TreeNode convert(int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end-start)/2;
        TreeNode left = convert(start, mid-1);
        TreeNode node = new TreeNode(head.val);
        node.left = left;

        head = head.next;
        TreeNode right = convert(mid+1, end);
        node.right = right;
        return node;
    }

    public static void main(String args[]) {
        ConvertSortedListToBinarySearchTree obj = new ConvertSortedListToBinarySearchTree();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7,new ListNode(9, new ListNode(11)))))))));

        TreeNode res = obj.sortedListToBST(head);
        System.out.println(res.left.val + " " + res.val + " " + res.right.val);

    }
}
