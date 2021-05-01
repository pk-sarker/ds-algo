package com.dsalgo.practice;

import java.util.*;

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 * Test case format:
 *
 * For simplicity sake, each node's value is the same as the node's index (1-indexed). For example,
 * the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.
 *
 * Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes
 * the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a
 * reference to the cloned graph.
 */
public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    };


    public Node cloneGraph(Node startNode) {
        if (startNode == null) {
            return startNode;
        }
        HashMap<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(startNode);
        visited.put(startNode, new Node(startNode.val, new ArrayList<>()));


        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for(Node neighbor: node.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.add(neighbor);
                }
                visited.get(node).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(startNode);
    }

    public static void main(String args[]) {
        CloneGraph obj = new CloneGraph();
        Node root = new Node(1, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        Node node3 = new Node(3, new ArrayList<>());
        Node node4 = new Node(4, new ArrayList<>());
        Node node5 = new Node(5, new ArrayList<>());
        Node node6 = new Node(6, new ArrayList<>());
        Node node7 = new Node(7, new ArrayList<>());
        node2.neighbors.add(node5);
        root.neighbors.add(node2);
        root.neighbors.add(node3);
        node4.neighbors.add(new Node(6, new ArrayList<>()));
        node4.neighbors.add(new Node(7, new ArrayList<>()));
        root.neighbors.add(node4);
        Node cloned = obj.cloneGraph(root);
        System.out.println("Original: " + root.val + " Cloned: " + cloned.val);
        cloned.val = -1;
        System.out.println("Set root value to -1 in Cloned graph");
        System.out.println("Original: " + root.val + " Cloned: " + cloned.val);
    }
}
