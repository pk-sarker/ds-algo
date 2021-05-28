package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class VerticalOrderTraversal {
    public class TNode {
        public TreeNode node;
        public int level;
        public int col;

        TNode(TreeNode node, int level, int col) {
            this.node = node;
            this.level = level;
            this.col = col;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {

        Queue<TNode> queue = new LinkedList<>();
        int level = 0;
        int col = 0;
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        queue.add(new TNode(root, level, col));
        while(!queue.isEmpty()) {
            TNode tn = queue.poll();
            System.out.println("> " + tn.node.value + " ("+tn.level+", " + tn.col+") ");
            if (tn.node != null) {
                res.add(new ArrayList<>(){{ add(tn.level); add(tn.col);}});
                if (tn.node.left != null) {
                    queue.add(new TNode(tn.node.left, level+1, col-1));
                }
                if (tn.node.right != null) {
                    queue.add(new TNode(tn.node.right, level+1, col+1));
                }
            }



        }



        return res;
    }

    public static void main(String args[]) {
        VerticalOrderTraversal obj = new VerticalOrderTraversal();
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        List<List<Integer>> result = obj.verticalTraversal(root);
    }
}
