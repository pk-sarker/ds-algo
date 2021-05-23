package com.dsalgo.practice;

/**
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 *
 * Each node will have a reference to its parent node. The definition for Node is below:
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two
 * nodes p and q in a tree T is the lowest node that has both p and q as descendants
 * (where we allow a node to be a descendant of itself)."
 *
 */
public class LowestCommonAncestor {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int val) {
            this.val = val;
            this.parent = null;
            this.left = null;
            this.right = null;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        public Node(int val, Node parent, Node left, Node right) {
            this.val = val;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

    };

    public Node lowestCommonAncestor(Node p, Node q) {
        Node p1 = p;
        Node p2 = q;

        while(p1 != p2) {
            if (p1!=null) {
                p1 = p1.parent;
            } else {
                p1 = q;
            }
            if (p2!=null) {
                p2 = p2.parent;
            } else {
                p2 = p;
            }
        }

        return p1;
    }

    public static void main(String args[]) {
        /**
         *              3
         *            /   \
         *           5     1
         *         /  \   /  \
         *       6    2  0   8
         *           / \
         *          7  4
         */
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n0 = new Node(0);

        n2.parent = n5;
        n2.left = n7;
        n2.right = n4;
        n7.parent = n2;
        n4.parent = n2;
        n5.right = n2;
        n5.left = n6;
        n6.parent = n5;
        n5.parent = n3;
        n3.left = n5;

        n1.left = n0;
        n1.right = n8;
        n0.parent = n1;
        n8.parent = n1;
        n1.parent = n3;
        n3.right = n1;

        LowestCommonAncestor obj = new LowestCommonAncestor();
        Node lcAncestor = obj.lowestCommonAncestor(n5, n4);
        System.out.println("Lowest Common Ancestor or "+n5.val+" and "+n4.val+" is " + lcAncestor.val);
    }
}
