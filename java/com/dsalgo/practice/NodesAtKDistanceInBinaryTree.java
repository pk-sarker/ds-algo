package com.dsalgo.practice;

import com.dsalgo.practice.common.TreeNode;

import java.util.*;

public class NodesAtKDistanceInBinaryTree {

    HashMap<TreeNode, TreeNode> parents = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        if (root == null || target == null) {
            return result;
        }
        // Find parent node of each node and store in a hash map
        findParents(root, null);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(null); // level separator
        queue.add(target);

        // from each node we will traverse in three directions
        // 1) to its left 2)  to its right 3) to its above-parent
        // so there will be some overlaping, to discard duplicate
        // processing we store the visited nodes in hash set and
        // check before processing.
        HashSet<TreeNode> visited = new HashSet<>();
        visited.add(target);
        visited.add(null);
        int distance = 0;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) { // all the nodes of any specific level has been visited
                if (distance == K) {
                    for(TreeNode lnode : queue) {
                        result.add(lnode.value);
                    }
                    return result;
                }
                distance++;
                queue.add(null);
            } else {
                if (!visited.contains(node.left)) {
                    queue.add(node.left);
                    visited.add(node.left);
                }
                if (!visited.contains(node.right)) {
                    queue.add(node.right);
                    visited.add(node.right);
                }
                if (!visited.contains(parents.get(node))) {
                    queue.add(parents.get(node));
                    visited.add(parents.get(node));
                }
            }
        }

        return result;
    }

    public void findParents(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        parents.put(node, parent);
        findParents(node.left, node);
        findParents(node.right, node);
    }

    public static void main(String args[]) {
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n7 = new TreeNode(7, n8, n9);
        TreeNode n2 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4, n2, n7);
        TreeNode n13 = new TreeNode(13, null, new TreeNode(14));
        TreeNode root = new TreeNode(10, n4, new TreeNode(15, n13, new TreeNode(17)));

        NodesAtKDistanceInBinaryTree obj = new NodesAtKDistanceInBinaryTree();

        System.out.println(obj.distanceK(root, n4, 2));
        obj.parents.clear();
        System.out.println(obj.distanceK(root, n7, 2));
        obj.parents.clear();
        System.out.println(obj.distanceK(root, n8, 6));
        obj.parents.clear();
        System.out.println(obj.distanceK(root, n8, 5));

    }
}
