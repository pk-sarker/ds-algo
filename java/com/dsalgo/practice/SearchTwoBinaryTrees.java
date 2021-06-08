package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given two binary search trees root1 and root2.
 *
 * Return a list containing all the integers from both trees sorted in ascending order.
 *          2               1
 *        /  \             / \
 *       1   4            0  3
 * Input: root1 = [2,1,4], root2 = [1,0,3]
 * Output: [0,1,1,2,3,4]
 *
 */
public class SearchTwoBinaryTrees {
        public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();

            while(root1 != null || root2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {
                // find left most node, as in BST leftmost node will be the lowest value
                // left < node < right
                while(root1!=null) {
                    stack1.push(root1);
                    root1 = root1.left;
                }

                while(root2!=null) {
                    stack2.push(root2);
                    root2 = root2.left;
                }

                if (stack2.isEmpty() || !stack1.isEmpty() && stack1.peek().value <= stack2.peek().value) {
                    TreeNode node = stack1.pop();
                    res.add(node.value);
                    root1 = node.right;
                } else {
                    TreeNode node = stack2.pop();
                    res.add(node.value);
                    root2 = node.right;
                }
            }
            return res;
        }
    public static void main(String args[]) {
        TreeNode root1 = new TreeNode(4, new TreeNode(1, new TreeNode(0), new TreeNode(3)), new TreeNode(9));
        TreeNode root2 = new TreeNode(3, new TreeNode(1, new TreeNode(0), new TreeNode(2)), new TreeNode(5, new TreeNode(4), new TreeNode(7)));

        List<Integer> res = SearchTwoBinaryTrees.getAllElements(root1, root2);
        System.out.println(res.toString());
    }
}
