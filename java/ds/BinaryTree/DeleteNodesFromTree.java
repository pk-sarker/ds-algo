package ds.BinaryTree;

import Common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 *
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 *
 * Input: root = [1,2,4,null,3], to_delete = [3]
 * Output: [[1,2,4]]
 */
public class DeleteNodesFromTree {

    List<TreeNode> result;
    Set<Integer> delNodeSet;
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        result = new ArrayList<>();
        delNodeSet = new HashSet<>();
        for(int node: to_delete) {
            delNodeSet.add(node);
        }
        findAndDelete(root, true);

        return result;
    }

    private TreeNode findAndDelete(TreeNode node, boolean isRoot) {
        if (node == null) {
            return null;
        }
        boolean delete = delNodeSet.contains(node.val);
        if (isRoot && !delete) {
            result.add(node);
        }
        // update left and right child
        node.left = findAndDelete(node.left, delete); // returns null if node.left is to be deleted
        node.right = findAndDelete(node.right, delete); // returns null if node.right is to be deleted
        return delete ? null:node;
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
        TreeNode root = new TreeNode(3, new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))), new TreeNode(1, new TreeNode(0), new TreeNode(8)));
        DeleteNodesFromTree obj = new DeleteNodesFromTree();
        List<TreeNode> res = obj.delNodes(root, new int[]{5, 1});

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<res.size();i++) {
            TreeNode node = res.get(i);
            sb.append(node.val + ", ");
        }
        System.out.println(sb.toString());
    }
}
