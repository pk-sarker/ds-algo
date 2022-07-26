package algo.BFS;

import Common.TreeNode;

import java.util.*;

/**
 * In a binary tree, a lonely node is a node that is the only child of its parent node.
 * The root of the tree is not lonely because it does not have a parent node.
 *
 * Given the root of a binary tree, return an array containing the values of all lonely
 * nodes in the tree. Return the list in any order.
 *
 * Input: root = [1,2,3,null,4]
 * Output: [4]
 *
 * Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2]
 * Output: [6,2]
 */
public class FindLonelyNodes {
    public static List<Integer> getLonelyNodes(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> lonelyNodes = new ArrayList<>();

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    continue;
                }
                if (node.left == null && node.right != null) {
                    lonelyNodes.add(node.right.val);
                    queue.add(node.right);
                    continue;
                }
                if (node.left != null && node.right == null) {
                    lonelyNodes.add(node.left.val);
                    queue.add(node.left);
                    continue;
                }
                queue.add(node.right);
                queue.add(node.left);
            }
        }
        return lonelyNodes;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2, null, new TreeNode(4));
        root.left = node2;
        root.right = new TreeNode(3);
        System.out.println("\nInput: root = [1,2,3,null,4]\nOutput:" + FindLonelyNodes.getLonelyNodes(root));
        /**
         *           7
         *          / \
         *         1   4
         *        /   /
         *       6   5
         *            \
         *             3
         *              \
         *               2
         *    [7,1,4,6,null,5,null,null,null,null,3,null,2]
         */
        TreeNode root2 = new TreeNode(7);
        TreeNode node1 = new TreeNode(1, new TreeNode(6), null);
        root2.left = node1;
        TreeNode node3 = new TreeNode(3, null, new TreeNode(2));
        TreeNode node4 = new TreeNode(4, new TreeNode(5, null, node3));
        root2.right = node4;
        System.out.println("\nInput: root = [7,1,4,6,null,5,null,null,null,null,3,null,2] \nOutput:" + FindLonelyNodes.getLonelyNodes(root2));
    }
}
