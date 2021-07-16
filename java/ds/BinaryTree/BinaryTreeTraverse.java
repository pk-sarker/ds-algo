package ds.BinaryTree;

import Common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeTraverse {

    /**
     *  Left - Root - Right
     * @param root
     * @return
     */
    public List<Integer> traverseInOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrderHelper(root, result);
        return result;
    }

    public void inOrderHelper(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inOrderHelper(root.left, result);
        result.add(root.val);
        inOrderHelper(root.right, result);
    }

    /**
     * Root - Left - Right
     * @param root
     * @return
     */
    public List<Integer> traversePreOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrderHelper(root, result);
        return result;
    }

    public void preOrderHelper(TreeNode root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        preOrderHelper(root.left, result);
        preOrderHelper(root.right, result);
    }

    /**
     * Left - Right - Root
     * @param root
     * @return
     */
    public List<Integer> traversePostOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrderHelper(root, result);
        return result;
    }

    public void postOrderHelper(TreeNode root, List<Integer> nodeList) {
        if (root == null) return ;

        if (root.left != null) {
            postOrderHelper(root.left, nodeList);
        }
        if (root.right != null) {
            postOrderHelper(root.right, nodeList);
        }
        nodeList.add(root.val);
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(5, new TreeNode(7, null, new TreeNode(9, new TreeNode(11), new TreeNode(8))), new TreeNode(10, new TreeNode(3, null, new TreeNode(4)), new TreeNode(6, new TreeNode(2), new TreeNode(1))));

        System.out.println("                   5\n" +
                "                 /    \\\n" +
                "                7     10\n" +
                "                \\    /  \\\n" +
                "                9   3    6\n" +
                "               / \\   \\  / \\\n" +
                "              11 8   4 2  1");
        BinaryTreeTraverse obj = new BinaryTreeTraverse();
        System.out.println("In Order: " + obj.traverseInOrder(root).toString());
        System.out.println("Pre Order: " + obj.traversePreOrder(root).toString());
        System.out.println("Post Order: " + obj.traversePostOrder(root).toString());
    }
}
