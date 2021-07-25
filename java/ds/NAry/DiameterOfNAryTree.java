package ds.NAry;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.
 *
 * The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree.
 * This path may or may not pass through the root.
 *
 * (Nary-Tree input serialization is represented in their level order traversal, each group of
 * children is separated by the null value.)
 *
 * Input:
 *              1
 *            / | \
 *           3  2  4
 *          /\      \
 *         5 6      7
 *                   \
 *                    8
 *  Output: 5, 5|6->3->1->4->7->8
 */
public class DiameterOfNAryTree {
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    int diameter = 0;
    public int diameter(Node root) {
        height(root);
        return this.diameter;
    }

    private int height(Node root) {
        if (root == null || root.children.size() ==0) {
            return 0;
        }

        int maxHeight = 0, secondMaxHeight = 0;

        for(int i=0;i<root.children.size(); i++) {
            Node child = root.children.get(i);

            int presentHeight = height(child)+1;

            if (presentHeight > maxHeight) {
                secondMaxHeight = maxHeight;
                maxHeight = presentHeight;
            } else if (presentHeight > secondMaxHeight){
                secondMaxHeight = presentHeight;
            }
            this.diameter = Math.max(this.diameter, (maxHeight+secondMaxHeight));
        }
        return maxHeight;
    }

    public static void main(String args[]) {
        Node naryRoot = new Node(1);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n3 = new Node(3);
        n3.children.add(n5);
        n3.children.add(n6);
        Node n2 = new Node(2);
        Node n4 = new Node(4);
        Node n8 = new Node(8);
        Node n7 = new Node(7);
        n7.children.add(n8);
        n4.children.add(n7);
        naryRoot.children.add(n3);
        naryRoot.children.add(n2);
        naryRoot.children.add(n4);
        DiameterOfNAryTree obj = new DiameterOfNAryTree();
        System.out.println(obj.diameter(naryRoot));
    }
}
