package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 *
 * For each node at position (row, col), its left and right children will be at positions
 * (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 *
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for
 * each column index starting from the leftmost column and ending on the rightmost column.
 * There may be multiple nodes in the same row and same column. In such a case, sort these
 * nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * Column -2: Only node 4 is in this column.
 * Column -1: Only node 2 is in this column.
 * Column 0: Nodes 1, 5, and 6 are in this column.
 *           1 is at the top, so it comes first.
 *           5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
 * Column 1: Only node 3 is in this column.
 * Column 2: Only node 7 is in this column.
 */
public class TreeTraversalVerticalOrder2 {

    public class Node<CT, RT, VT> {
        public final  CT column;
        public final RT row;
        public final VT value;

        public Node(CT col, RT row, VT value) {
            this.column = col;
            this.row = row;
            this.value = value;
        }
    }

    // List<Node<Integer,Integer,Integer>> nodeList = new ArrayList<>();
    List<int[]> nodeList = new ArrayList<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // traverse all the nodes and create
        DFS(root, 0, 0);
        Collections.sort(nodeList, new Comparator<int[]>() {
            public int compare(int[] node1, int[] node2) {
                if (node1[0] == node2[0]) {
                    if (node1[1] == node2[1]) {
                        return node1[2] - node2[2];
                    } else {
                        return node1[1] - node2[1];
                    }
                } else {
                    return node1[0] - node2[0];
                }
            }
        });

        System.out.println(nodeList.toString());

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> columns = new ArrayList<>();
        Integer currentColumn = nodeList.get(0)[0];
        for(int[] node : nodeList) {
            if(currentColumn == node[0]) {
                columns.add(node[2]);
            } else {
                result.add(columns);
                currentColumn = node[0];
                columns = new ArrayList<>();
                columns.add(node[2]);
            }
        }
        result.add(columns);
        return  result;
    }

    public void DFS(TreeNode node, int row, int col) {
        if (node == null) {
            return;
        }
        nodeList.add(new int[]{col, row, node.value});

        if (node.left != null) {
            DFS(node.left, row+1, col-1);
        }
        if (node.right != null) {
            DFS(node.right, row+1, col+1);
        }
    }

    public static void main(String args[]) {
        TreeTraversalVerticalOrder2 obj = new TreeTraversalVerticalOrder2();
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(6)), new TreeNode(3, new TreeNode(5), new TreeNode(7)));

        System.out.println(obj.verticalTraversal(root).toString());
    }
}
