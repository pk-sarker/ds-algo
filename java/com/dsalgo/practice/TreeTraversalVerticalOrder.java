package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.*;

public class TreeTraversalVerticalOrder {
    public class Pair {
        TreeNode node;
        Integer column;
        public Pair(TreeNode node, Integer column) {
            this.node = node;
            this.column = column;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        if (root == null) {
            return result;
        }
        HashMap<Integer, ArrayList> columnMap = new HashMap();
        Queue<Pair> queue = new LinkedList<>();
        int column = 0;
        queue.offer(new Pair(root, column));
        int minColumn = 0, maxColumn = 0;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            root = p.node;
            column = p.column;

            if (root != null) {
                if (!columnMap.containsKey(column)) {
                    columnMap.put(column, new ArrayList<Integer>());
                }
                columnMap.get(column).add(root.value);
                minColumn = Math.min(minColumn, column);
                maxColumn = Math.max(maxColumn, column);

                queue.add(new Pair(root.left, column - 1));
                queue.add(new Pair(root.right, column + 1));
            }
        }

        for(int i = minColumn; i < maxColumn + 1; ++i) {
            result.add(columnMap.get(i));
        }
        return result;
    }

    public static void main(String args[]) {
        TreeTraversalVerticalOrder obj = new TreeTraversalVerticalOrder();
        TreeNode root = new TreeNode(3, new TreeNode(9,  new TreeNode(2), new TreeNode(1)), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(obj.verticalOrder(root).toString());

        TreeNode root1 = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(obj.verticalOrder(root1).toString());

        TreeNode root2 = new TreeNode(3, new TreeNode(9,  new TreeNode(4), new TreeNode(0, null, new TreeNode(2))), new TreeNode(8, new TreeNode(1, new TreeNode(5), null), new TreeNode(7)));
        System.out.println(obj.verticalOrder(root2).toString());
    }
}
