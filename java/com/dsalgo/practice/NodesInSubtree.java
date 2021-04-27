package com.dsalgo.practice;

import java.util.*;

public class NodesInSubtree {

    // Tree Node
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
            val = 0;
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    static class Query {
        int u;
        char c;
        Query(int u, char c) {
            this.u = u;
            this.c = c;
        }
    }
    HashMap<Integer, HashMap<Character, Integer>> map = new HashMap<>();
    HashMap<Integer, Character> nodeToChar = new HashMap<>();
    int[] countOfNodes(Node root, List<Query> queries, String s) {
        this.map.clear();
        this.nodeToChar.clear();
        // Write your code here
        for(int i=0; i<s.length(); i++) {
            nodeToChar.put(i+1, s.charAt(i));
        }
        helper(root, -1);
        int[] res = new int[queries.size()];
        for(int i=0;i<queries.size();i++) {
            Query q = queries.get(i);

            res[i] = this.map.get(q.u).getOrDefault(q.c, 0);
        }
        return res;
    }

    public HashMap<Character, Integer>  helper(Node root, int parent) {
        HashMap<Character, Integer> charCount = new HashMap<>();
        charCount.put(nodeToChar.get(root.val), 1);
        if (root.children.isEmpty()) {
            this.map.put(root.val, charCount);
            return charCount;
        }

        for(int i=0; i<root.children.size();i++) {
            HashMap<Character, Integer> childCharCount = helper(root.children.get(i), root.val);
            for(char key : childCharCount.keySet()) {
                charCount.put(key, charCount.getOrDefault(key, 0) + childCharCount.get(key));
            }
        }
        this.map.put(root.val, charCount);
        return charCount;
    }

    public static void main(String args[]) {
        NodesInSubtree obj = new NodesInSubtree();
        int n_1 = 3, q_1 = 1;
        String s_1 = "aba";
        Node root_1 = new Node(1);
        root_1.children.add(new Node(2));
        root_1.children.add(new Node(3));
        ArrayList<Query> queries_1 = new ArrayList<>();
        queries_1.add(new Query(1, 'a'));
        int[] output_1 = obj.countOfNodes(root_1, queries_1, s_1);
        System.out.println(Arrays.toString(output_1));


        int n_2 = 7, q_2 = 3;
        String s_2 = "abaacab";
        Node root_2 = new Node(1);
        root_2.children.add(new Node(2));
        root_2.children.add(new Node(3));
        root_2.children.add(new Node(7));
        root_2.children.get(0).children.add(new Node(4));
        root_2.children.get(0).children.add(new Node(5));
        root_2.children.get(1).children.add(new Node(6));
        ArrayList<Query> queries_2 = new ArrayList<>();
        queries_2.add(new Query(1, 'a'));
        queries_2.add(new Query(2, 'b'));
        queries_2.add(new Query(3, 'a'));
        int[] output_2 = obj.countOfNodes(root_2, queries_2, s_2);
        System.out.println(Arrays.toString(output_2));
    }
}
