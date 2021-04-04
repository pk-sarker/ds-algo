package com.dsalgo.practice;


import com.dsalgo.practice.common.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class CamerasOnBinaryTree {
    public int count = 0;
    private Set<TreeNode> covered;

    public int minCameras(TreeNode root) {
        if (root ==null) {
            return count;
        }
        covered = new HashSet<>();
        covered.add(null);
        count = 0;
        DFSTraversal(root, null);
        return count;
    }

    public void DFSTraversal(TreeNode node, TreeNode parent) {
        if (node != null) {
            DFSTraversal(node.left, node);
            DFSTraversal(node.right, node);

            if ((parent == null && !covered.contains(node)) || !covered.contains(node.left) || !covered.contains(node.right)){
                System.out.println(">> "  + node.value);
                count++;
                covered.add(node);
                covered.add(parent);
                covered.add(node.left);
                covered.add(node.right);
            }
        }
    }


    public static void main(String args[]) {
        CamerasOnBinaryTree cbt = new CamerasOnBinaryTree();
        TreeNode root1 = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)));
        System.out.println("Minimum cameras: " + cbt.minCameras(root1));


        TreeNode root21 = new TreeNode(0,
                new TreeNode(0, new TreeNode(0), new TreeNode(0, new TreeNode(0), new TreeNode(0))),
                new TreeNode(0, null, new TreeNode(0, new TreeNode(0)))
        );
        TreeNode root2 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5, new TreeNode(7), new TreeNode(8))),
                new TreeNode(3, null, new TreeNode(6, new TreeNode(9)))
        );
        System.out.println("Minimum cameras: " + cbt.minCameras(root2));
    }
}
