package com.dsalgo.practice;

/**
 * Given a n * n matrix grid of 0's and 1's only. We want to represent the grid with a Quad-Tree.
 *
 * Return the root of the Quad-Tree representing the grid.
 *
 * Notice that you can assign the value of a node to True or False when isLeaf is False, and both are accepted in the answer.
 *
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
 *
 * val: True if the node represents a grid of 1's or False if the node represents a grid of 0's.
 * isLeaf: True if the node is leaf node on the tree or False if the node has the four children.
 * class Node {
 *     public boolean val;
 *     public boolean isLeaf;
 *     public Node topLeft;
 *     public Node topRight;
 *     public Node bottomLeft;
 *     public Node bottomRight;
 * }
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 *
 * If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
 * If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
 * Recurse for each of the children with the proper sub-grid.
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
 * Explanation: The explanation of this example is shown below:
 * Notice that 0 represnts False and 1 represents True in the photo representing the Quad-Tree.
 *
 * Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
 * Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
 * Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
 * The topLeft, bottomLeft and bottomRight each has the same value.
 * The topRight have different values so we divide it into 4 sub-grids where each has the same value.
 *
 */
public class QuardTree {

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node(){}
        public Node(boolean val, boolean isLeaf, Node tl, Node tr, Node bl, Node br) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = tl;
            this.topRight = tr;
            this.bottomLeft = bl;
            this.bottomRight = br;
        }
    }

    public Node construct(int[][] grid) {
        return this.helper(grid, 0, 0,  grid.length);
    }

    // row - starting row index of the grid
    // col - starting column index of the  grid
    public Node helper(int[][] grid, int row, int col, int gridLen) {
        if (gridLen == 1) {
            return new Node(grid[row][col] != 0, true, null, null, null, null);
        }

        Node node = new Node();
        Node topLeft = helper(grid, row, col, gridLen/2);
        Node topRight = helper(grid, row, col + gridLen/2, gridLen/2);
        Node bottomLeft = helper(grid, row + gridLen/2, col, gridLen/2);
        Node bottomRight = helper(grid, row+gridLen/2, col + gridLen/2, gridLen/2);

        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf &&
        topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) {
            node.isLeaf = true;
            node.val = topLeft.val;
        } else {
            node.topLeft = topLeft;
            node.topRight = topRight;
            node.bottomLeft = bottomLeft;
            node.bottomRight = bottomRight;
        }
        return node;
    }
    public static void main(String args[]) {
        QuardTree ob = new QuardTree();
        Node res = ob.construct(new int[][]{
                {0,1},{1,0}
        });
        System.out.println(">> " + res.val + " TL:"+res.topLeft.val + " TR:"+res.topRight.val + " BL:"+res.bottomLeft.val+ " BR:"+res.bottomRight.val);

    }
}
