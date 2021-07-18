package algo.design;

import Common.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

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

    private static final String Separator = ",";
    private static final String Null = "N";

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        return serializeHelper(root, sb).toString();
    }

    private StringBuilder serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(SerializeDeserializeTree.Null).append(SerializeDeserializeTree.Separator);
        } else {
            sb.append(String.valueOf(root.val)).append(SerializeDeserializeTree.Separator);
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);
        }
        return sb;
    }

    public TreeNode deserialize(String data) {
        Deque<String> queue = new LinkedList<>();

        String[] strAr = data.split(SerializeDeserializeTree.Separator);
        queue.addAll(Arrays.asList(strAr));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Deque<String> queue) {
        String value = queue.remove();
        if (value.equals(SerializeDeserializeTree.Null)){
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(value));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        return node;
    }

    public static void main(String args[]) {
        SerializeDeserializeTree obj = new SerializeDeserializeTree();
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4),  new TreeNode(5)));
        String serializedTree = obj.serialize(root);
        System.out.println("Serialized Tree: " + serializedTree);
        TreeNode droot = obj.deserialize(serializedTree);
        String serializedTree2 = obj.serialize(droot);
        System.out.println("Serialized Tree (check): " + serializedTree2);
    }
}
