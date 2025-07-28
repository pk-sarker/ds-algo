package Common;

import java.util.Stack;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int val, TreeNode left) {
        this.val = val;
        this.left = left;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void pintTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {

            // Traverse to the leftmost node, pushing nodes onto the stack
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            // Pop the top node (which is the current node in inorder sequence)
            node = stack.pop();
            sb.append("->");
            sb.append(node.val);

            // Visited the node and its left subtree.
            // Now, it's right subtree's turn
            node = node.right;
        }
        System.out.println(sb.toString());
    }
}
