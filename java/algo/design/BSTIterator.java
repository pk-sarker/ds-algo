package algo.design;

import Common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 *   - BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST
 *     is given as part of the constructor. The pointer should be initialized to a non-existent number
 *     smaller than any element in the BST.
 *   - boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer,
 *     otherwise returns false.
 *   - int next() Moves the pointer to the right, then returns the number at the pointer.
 *
 *  Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will
 *    return the smallest element in the BST.
 *
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in
 *   the in-order traversal when next() is called.
 */
public class BSTIterator {
    private List<Integer> flatList = new ArrayList<>();
    private int pointer = -1;
    public BSTIterator(TreeNode root) {
        traverseInOrder(root);
    }

    private void traverseInOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            traverseInOrder(root.left);
        }
        flatList.add(root.val);
        if (root.right != null) {
            traverseInOrder(root.right);
        }
    }

    /** @return the next smallest number */
    public int next() {
        return this.flatList.get(++this.pointer);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (pointer+1>=flatList.size()) {
            return false;
        }
        return true;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(6));
        BSTIterator obj = new BSTIterator(root);

        System.out.println("hasNext() " + obj.hasNext());
        System.out.println("next: " + obj.next());
        System.out.println("next: " + obj.next());
        System.out.println("next: " + obj.next());
    }
}
