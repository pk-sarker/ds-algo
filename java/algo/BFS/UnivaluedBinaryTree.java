package algo.BFS;

import Common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A binary tree is uni-valued if every node in the tree has the same value.
 *
 * Given the root of a binary tree, return true if the given tree is uni-valued, or false otherwise.
 *
 * Input: root = [1,1,1,1,1,null,1]
 * Output: true
 *
 * Input: root = [2,2,2,5,2]
 * Output: false
 */
public class UnivaluedBinaryTree {

    public boolean isUnivalTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int value = root.val;

        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                if (node.val != value) {
                    return false;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return true;
    }
    int value = -1;
    public boolean isUnivalTreeDFS(TreeNode root) {

        if (root == null) {
            return true;
        }

        if (value <0) {
            value = root.val;
        }
        return root.val == value && isUnivalTreeDFS(root.left) && isUnivalTreeDFS(root.right);
    }



    public static void main(String args[]) {

    }
}
