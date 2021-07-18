package algo.design;

import Common.TreeNode;

import java.util.ArrayDeque;

public class SerializeDeserializeBST {
    /**
     * Encodes a tree to a list.
     * @param root
     * @param sb
     */
    public void postorder(TreeNode root, StringBuilder sb) {
        if (root == null)
            return;
        postorder(root.left, sb);
        postorder(root.right, sb);
        sb.append(intToString(root.val));
    }

    // Encodes integer to bytes string
    // by right shift we are moving the bytes to right
    // and with AND operation with FF we get the last byte.
    // B =>       |---B1---|---B2---|---B3---|---B4---|
    // B >> 3*8 = B >> 24
    //            |00000000|00000000|00000000|---B1---|
    // B >> 24 && 0XFF
    //            |00000000|00000000|00000000|---B1---|
    //            |00000000|00000000|00000000|11111111|
    //           =|00000000|00000000|00000000|---B1---|
    // B >> 2*8 = B >> 16
    //            |00000000|00000000|---B1---|---B2---|
    // B >> 16 && 0XFF
    //            |00000000|00000000|---B1---|---B2---|
    //            |00000000|00000000|00000000|11111111|
    //           =|00000000|00000000|00000000|---B2---|
    public String intToString(int x) {
        char[] bytes = new char[4];
        for (int i = 3; i > -1; --i) {
            bytes[3 - i] = (char) (x >> (i * 8) & 0xff);
        }
        return new String(bytes); // String length 4 or 4 byte
    }

    /**
     * Encode a tree to a single string.
     * @param root
     * @return String
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postorder(root, sb);
        return sb.toString();
    }

    /**
     * Decodes to Tree
     * @param lowerbound
     * @param upperbound
     * @param nums
     * @return
     */
    public TreeNode deserializeHelper(Integer lowerbound, Integer upperbound, ArrayDeque<Integer> nums) {
        if (nums.isEmpty()) {
            return null;
        }
        int val = nums.getLast(); // Left - Right - Root, last one if the root.
        if (val < lowerbound || val > upperbound) { // not in the range, or null
            return null;
        }
        nums.removeLast();  // remove the root
        TreeNode root = new TreeNode(val); // create node
        root.right = deserializeHelper(val, upperbound, nums);
        root.left = deserializeHelper(lowerbound, val, nums);
        return root;
    }

    /**
     * Decodes bytes string to integer
     *
     * @param bytesStr
     * @return
     */
    public int stringToInt(String bytesStr) {
        int result = 0;
        for (char b : bytesStr.toCharArray()) {
            result = (result << 8) + (int) b; // left shift last data by a byte and add new byte
        }
        return result;
    }

    /**
     * Decodes your encoded data to tree.
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
        int n = data.length();
        for (int i = 0; i < (int) (n / 4); ++i) {
            nums.add(stringToInt(data.substring(4 * i, 4 * i + 4)));
        }

        return deserializeHelper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
    }

    public static void main(String args[]) {
        SerializeDeserializeBST obj = new SerializeDeserializeBST();
        TreeNode root = new TreeNode(7, new TreeNode(3, new TreeNode(2), new TreeNode(4)), new TreeNode(9, new TreeNode(8), new TreeNode(12, null, new TreeNode(14))));
        String serializedTree = obj.serialize(root);
        System.out.println("Serialized Tree: " + serializedTree);
        TreeNode reconstruct = obj.deserialize(serializedTree);
        System.out.println("Serialized Tree 2: " + obj.serialize(reconstruct));


    }
}
