package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NaryTreeLevelOrderTraversal {
    public static class Node {
        int value;
        List<Node> child;

        public Node(int value) {
            this.value = value;
            this.child = new ArrayList<>();
        }
        public Node(int value, List<Node> child) {
            this.value = value;
            this.child = child;
        }
    }
    public static List<List<Integer>> levelOrder(Node root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {

            List<Integer> nodesPerLevel = new ArrayList<>();
            int size = queue.size();
            for(int i=0; i<size; i++) {
                Node node = queue.poll();
                nodesPerLevel.add(node.value);
                queue.addAll(node.child);
            }
            result.add(nodesPerLevel);
        }
        return result;
    }

    public static void main(String args[]) {
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node3 = new Node(3, new ArrayList<Node>(){{
            add(node5);
            add(node6);
        }});

        Node root = new Node(1, new ArrayList<Node>(){{
            add(node3);
            add(new Node(2));
            add(new Node(4));
        }});

        System.out.println("\nOutput: " + NaryTreeLevelOrderTraversal.levelOrder(root));
    }
}
