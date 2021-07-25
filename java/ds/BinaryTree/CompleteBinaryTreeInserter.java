package ds.BinaryTree;

import Common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled,
 * and all nodes are as far left as possible.
 *
 * Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following
 * operations:
 *
 *  - CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
 *  - CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree
 *      remains complete, and returns the value of the parent of the inserted TreeNode;
 *  - CBTInserter.get_root() will return the head node of the tree.
 *
 * Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
 * Output: [null,1,[1,2]]
 *
 * Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
 * Output: [null,3,4,[1,2,3,4,5,6,7,8]]
 *
 *                   1
 *                 /   \
 *                2     3
 *               / \   / \
 *              4  5  6   7
 *             / \
 *            8  9
 *
 *  insert (10)
 *                   1
 *                 /   \
 *                2     3
 *               / \   / \
 *              4   5 6   7
 *             / \ /
 *            8  9 10
 */
public class CompleteBinaryTreeInserter {
    TreeNode root;
    Deque<TreeNode> dequeue = new LinkedList<>();

    public CompleteBinaryTreeInserter(TreeNode root) {
        this.root = root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // In the queue we will keep only the nodes that doesn't have two child, because
        // The idea is to add child with insert operation.

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null || node.right == null) {
                dequeue.offerLast(node); // add node to queue if it doesn't have two children
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }



    public int insert(int v) {
        TreeNode node = dequeue.peekFirst(); // head of the queue
        dequeue.offerLast(new TreeNode(v));
        if (node.left == null){
            node.left = dequeue.peekLast();
        } else {
            node.right = dequeue.peekLast();
            dequeue.pollFirst();
        }
        return node.val;
    }

    public TreeNode get_root() {
        return this.root;
    }


    public static void main(String args[]) {

        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4, new TreeNode(8), new TreeNode(9)), new TreeNode(5)), new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        CompleteBinaryTreeInserter obj = new CompleteBinaryTreeInserter(root);
        System.out.println("Insert 10 : " + obj.insert(10));
        System.out.println("Insert 11 : " + obj.insert(11));
        System.out.println("Insert 12 : " + obj.insert(12));
    }
}
