package ds.BinaryTree;

import Common.TreeNode;

import java.util.Stack;

/**
 * You are given two binary trees root1 and root2.
 *
 * Imagine that when you put one of them to cover the other, some nodes of the two trees
 * are overlapped while the others are not. You need to merge the two trees into a new
 * binary tree. The merge rule is that if two nodes overlap, then sum node values up as
 * the new value of the merged node. Otherwise, the NOT null node will be used as the node of the new tree.
 *
 * Return the merged tree.
 *
 * Note: The merging process must start from the root nodes of both trees.
 *
 * Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * Output: [3,4,5,5,4,null,7]
 */
public class MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        t1.val = t1.val + t2.val;
        t1.left = mergeTrees(t1.left,t2.left);
        t1.right = mergeTrees(t1.right,t2.right);

        return t1;
    }

    public TreeNode mergeTreesItr(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        Stack<TreeNode[]> stack = new Stack<>();
        stack.push(new TreeNode[]{t1, t2});

        while(!stack.isEmpty()) {
            TreeNode[] nodes = stack.pop();
            if (nodes[0] == null || nodes[1]==null) {
                continue;
            }
            nodes[0].val += nodes[1].val;
            if (nodes[0].left == null) {
                nodes[0].left = nodes[1].left;
            } else {
                stack.push(new TreeNode[]{nodes[0].left, nodes[1].left});
            }
            if (nodes[0].right == null) {
                nodes[0].right = nodes[1].right;
            } else {
                stack.push(new TreeNode[]{nodes[0].right, nodes[1].right});
            }
        }
        return t1;
    }

    public static void main(String args[]) {
        MergeTwoBinaryTrees obj = new MergeTwoBinaryTrees();
        TreeNode roo1 = new TreeNode(3, new TreeNode(1), new TreeNode(4, new TreeNode(5), new TreeNode(6)));
        TreeNode roo2 = new TreeNode(2, new TreeNode(2), new TreeNode(1));

        TreeNode merged = obj.mergeTrees(roo1, roo2);
    }
}
