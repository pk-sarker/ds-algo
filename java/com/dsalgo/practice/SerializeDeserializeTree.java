package com.ds.practice;

import com.ds.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection
 * link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how
 * your serialization/deserialization algorithm should work. You just need to ensure that a binary
 * tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 *
 * Input: root = []
 * Output: []
 *
 * Input: root = [1]
 * Output: [1]
 *
 * Input: root = [1,2]
 * Output: [1,2]
 */
public class SerializeDeserializeTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();

        if  (root == null ) {
            return sb.toString();
        }

        // sb = dfs(root, sb);
        // or
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();

            sb.append(node.value+",");

            if (node.right != null) {
                stack.push(node.right);
            } else {
                sb.append("null,");
            }

            if (node.left != null) {
                stack.push(node.left);
            } else {
                sb.append("null,");
            }


        }
        sb.setLength(sb.length()-1);
        return sb.toString();
    }

    // recursion
    public StringBuilder dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
        } else {
            sb.append(String.valueOf(root.value)+",");

            sb = dfs(root.left, sb);
            sb = dfs(root.right, sb);
        }
        return sb;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        List<String> nodeList = new ArrayList<String>(Arrays.asList(nodes));

        return parseTreeString(nodeList);
    }

    public TreeNode parseTreeString(List<String> nodeList)  {
        if (nodeList.get(0) == null || nodeList.get(0).equals("null")) {
            nodeList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(nodeList.get(0)));

        nodeList.remove(0);
        root.left = parseTreeString(nodeList);
        root.right = parseTreeString(nodeList);
        return root;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4),  new TreeNode(5)));
        SerializeDeserializeTree sd = new  SerializeDeserializeTree();
        String ser = sd.serialize(root);
        System.out.println("1,2,null,null,3,4,null,null,5,null,null,");
        System.out.println("Serialize: " + ser);

        sd.deserialize(ser);

    }
}
