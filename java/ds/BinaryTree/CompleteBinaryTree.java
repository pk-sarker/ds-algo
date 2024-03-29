package ds.BinaryTree;


import Common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, determine if it is a complete binary tree.
 *
 * In a complete binary tree, every level, except possibly the last, is completely filled,
 * and all nodes in the last level are as far left as possible. It can have
 * between 1 and 2h nodes inclusive at the last level h.
 *
 * Input: root = [1,2,3,4,5,6]
 *              1
 *            /   \
 *           2     3
 *          / \   /
 *         4  5  6
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with
 * node-values {1} and {2, 3}), and all nodes in the
 * last level ({4, 5, 6}) are as far left as possible.
 *
 * Input: root = [1,2,3,4,5,null,7]
 * Output: false
 *              1
 *            /   \
 *           2     3
 *          / \     \
 *         4  5     7
 * Explanation: The node with value 7 isn't as far left as possible.
 *
 * Solution: Do a BFS level order traversal, and stop when first null node found
 * Now start polling nodes from the queue as long as the peek of the queue is null and
 * queue is not empty.
 * The queue should be empty if the tree is complete binary tree.
 */
public class CompleteBinaryTree {

    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(queue.peek() != null) {
            TreeNode node = queue.poll();
            queue.offer(node.left);
            queue.offer(node.right);
        }

        while (!queue.isEmpty() && queue.peek() == null) {
            queue.poll();
        }

        return queue.isEmpty();
    }

    public boolean isCompleteTree2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (true) {
            TreeNode node = queue.poll();
            // if left node is null then break,
            if (node.left == null) {
                // if left node is null and right node is not null then it should return false.
                // because then nodes are not all filled up from the left.
                if (node.right != null) {
                    return false;
                }
                break;
            }
            queue.offer(node.left);
            // right node is null then for a complete binary tree there should not be any more node
            if (node.right == null) {
                break;
            }
            queue.offer(node.right);
        }
        // if the level contains all the nodes then  queue will  be empty.
        // if the last level is not complete, then there will be some nodes
        // Those  nodes should be leaf node, both left and right child should be null
        //         1
        //        /  \
        //       2    3
        //      / \  /
        //     4  5 6
        // Queue will have [4,5,6] at the last
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null || node.right != null)
                return false;
        }
        return true;
    }

    public static void main(String args[]) {
        CompleteBinaryTree obj = new CompleteBinaryTree();
        TreeNode root1 = new TreeNode(8,
                            new TreeNode(7,
                                    new TreeNode(4, new TreeNode(5), new TreeNode(6)),
                                    new TreeNode(2, new TreeNode(1), new TreeNode(3))),
                            new TreeNode(9,
                                    new TreeNode(10, new TreeNode(11), null),
                                    new TreeNode(12)));

        TreeNode root2 = new TreeNode(8,
                new TreeNode(7,
                        new TreeNode(4, new TreeNode(5), new TreeNode(6)),
                        new TreeNode(2, new TreeNode(1), new TreeNode(3))),
                new TreeNode(9,
                        new TreeNode(10),
                        new TreeNode(12, new TreeNode(11), null)));

        System.out.println(obj.isCompleteTree(root1));
        System.out.println(obj.isCompleteTree2(root2));
    }
}
