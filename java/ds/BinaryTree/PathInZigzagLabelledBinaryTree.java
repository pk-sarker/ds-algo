package ds.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * In an infinite binary tree where every node has two children, the nodes are labelled in row order.
 *
 * In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while
 * in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
 *
 * Given the label of a node in this tree, return the labels in the path from the root of the tree
 * to the node with that label.
 *
 * Input: label = 14
 * Output: [1,3,4,14]
 *
 * Input: label = 26
 * Output: [1,2,6,10,26]
 *
 */
public class PathInZigzagLabelledBinaryTree {
    // Iterative
    public static List<Integer> pathInZigZagTree(int label) {

        int level = 0;
        int count = 0;
        while (label > count) {
            count += Math.pow(2, level); // 1  3 7 15, number of nodes at level i = 2^i
            level++;   // 1 2 3 4
        }
        level = level - 1;
        List<Integer> result = new ArrayList<>();

        while (level >= 0) {
            result.add(0, label);
            int min = (int)Math.pow(2, level);
            int max = (int)Math.pow(2, level+1)-1;
            int reversedLabel = min + max - label;
            label = reversedLabel/2;
            level = level - 1;
        }
        return result;
    }

    public static void main(String args[]) {
        System.out.println("Label 1 => " + PathInZigzagLabelledBinaryTree.pathInZigZagTree(1));
        System.out.println("Label 2 => " + PathInZigzagLabelledBinaryTree.pathInZigZagTree(2));
        System.out.println("Label 3 => " + PathInZigzagLabelledBinaryTree.pathInZigZagTree(3));
        System.out.println("Label 4 => " + PathInZigzagLabelledBinaryTree.pathInZigZagTree(4));
        System.out.println("Label 5 => " + PathInZigzagLabelledBinaryTree.pathInZigZagTree(5));
        System.out.println("Label 6 => " + PathInZigzagLabelledBinaryTree.pathInZigZagTree(6));
        System.out.println("Label 7 => " + PathInZigzagLabelledBinaryTree.pathInZigZagTree(7));
        System.out.println("Label 9 => " + PathInZigzagLabelledBinaryTree.pathInZigZagTree(9));
        System.out.println("Label 11 => " + PathInZigzagLabelledBinaryTree.pathInZigZagTree(11));
    }
}
