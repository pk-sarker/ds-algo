package ds.BinaryTree;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 *
 * The whole input represents a binary tree. It contains an integer followed by zero,
 * one or two pairs of parenthesis. The integer represents the root's value and a pair
 * of parenthesis contains a child binary tree with the same structure.
 *
 * You always start to construct the left child node of the parent first if it exists.
 *
 * Input: s = "4(2(3)(1))(6(5))"
 * Output: [4,2,6,3,1,5]
 *
 * Input: s = "4(2(3)(1))(6(5)(7))"
 * Output: [4,2,6,3,1,5,7]
 *
 * Input: s = "-4(2(3)(1))(6(5)(7))"
 * Output: [-4,2,6,3,1,5,7]
 *
 */
public class BinaryTreeFromString {

    public TreeNode str2tree(String s) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode parent = null, curNode = null;
        int sign = 1, index = 0;

        // keep pushing the nodes until ')' is found
        // when ')' is found then pop the top node
        // top node will the a child node,
        // and next top node will be the parent
        while (index < s.length()) {
            if (s.charAt(index) == ')') {
                curNode = stack.pop();
                parent = stack.peek();
                if (parent.left != null) {
                    parent.right = curNode;
                } else {
                    parent.left = curNode;
                }
                index++;
            } else if (s.charAt(index) == '-') {
                sign = -1;
                index++;
            } else if (s.charAt(index) == '(') {
                index++;
            } else {
                int num = 0;
                while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                    num = num * 10 + s.charAt(index) - '0';
                    index++;
                }
                num *= sign;
                sign = 1;
                stack.push(new TreeNode(num));
            }
        }

        if (!stack.isEmpty()) {
            return stack.peek();
        }

        return parent;
    }

    List<List<Integer>> recResult = new ArrayList<>();
    public List<List<Integer>> levelOrderRecursive(TreeNode root) {
        traverse(root, 0);
        return recResult;
    }

    public void traverse(TreeNode node, int  level) {
        if (node==null) {
            return;
        }
        if (recResult.size() == level) {
            recResult.add(new ArrayList<>());
        }
        recResult.get(level).add(node.value);
        traverse(node.left, level+1);
        traverse(node.right, level+1);
    }

    public static void main(String args[]) {
        BinaryTreeFromString obj = new BinaryTreeFromString();
        TreeNode result =  obj.str2tree("4(2(3)(1))(6(5))");
        List<List<Integer>> res = obj.levelOrderRecursive(result);
        System.out.println(res.toString());


    }
}
