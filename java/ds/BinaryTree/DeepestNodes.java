package ds.BinaryTree;

import Common.TreeNode;

/**
 * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
 *
 * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
 *
 * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
 *
 * The subtree of a node is tree consisting of that node, plus the set of all descendants of that node.
 *
 * Input:
 *              3
 *            /   \
 *           5     1
 *          / \   / \
 *         6  2  0  8
 *           / \
 *          7  4
 * Output: [2,7,4]
 */
public class DeepestNodes {
    class Result {
        TreeNode node;
        int dist;
        Result(TreeNode n, int d) {
            node = n;
            dist = d;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    // Return the result of the subtree at this node.
    public Result dfs(TreeNode node) {
        if (node == null) return new Result(null, 0);
        Result maxInLeftTree = dfs(node.left),
                maxInRightTree = dfs(node.right);

        System.out.println(" " + (maxInLeftTree.node != null ? maxInLeftTree.node.val: null) + " " + maxInLeftTree.dist + " - " + (maxInRightTree.node != null ? maxInRightTree.node.val:null) + " " +maxInRightTree.dist);
        if (maxInLeftTree.dist > maxInRightTree.dist) return new Result(maxInLeftTree.node, maxInLeftTree.dist + 1);
        if (maxInLeftTree.dist < maxInRightTree.dist) return new Result(maxInRightTree.node, maxInRightTree.dist + 1);

        System.out.println(">> " + node.val + " " + maxInLeftTree.dist);
        // if depth of left subtree and right subtree is same
        return new Result(node, maxInLeftTree.dist + 1);
    }

    public static void main(String args[]) {
        /**
         *              3
         *            /   \
         *           5     1
         *          / \   / \
         *         6  2  0  8
         *           / \
         *          7  4
         */
        TreeNode root = new TreeNode(3, new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7),new TreeNode(4))), new TreeNode(1, new TreeNode(0),new TreeNode(8)));
        DeepestNodes obj = new DeepestNodes();
        TreeNode res = obj.subtreeWithAllDeepest(root);

        TreeNode root1 = new TreeNode(3, new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7),new TreeNode(4))), new TreeNode(1, new TreeNode(0),new TreeNode(8, new TreeNode(9), new TreeNode(10))));
        TreeNode res2 = obj.subtreeWithAllDeepest(root1);

        TreeNode root2 = new TreeNode(3, new TreeNode(5, new TreeNode(6, new TreeNode(8), new TreeNode(9)), new TreeNode(2, new TreeNode(7),new TreeNode(4))), new TreeNode(1, new TreeNode(0),new TreeNode(8)));
        TreeNode res3 = obj.subtreeWithAllDeepest(root2);

    }
}
