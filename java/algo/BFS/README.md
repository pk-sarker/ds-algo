# BFS

- [Count Good Nodes in Binary Tree](#count-good-nodes-in-binary-tree) - [Code](./CountGoodNodes.java)
- [Pacific Atlantic Water Flow](./PacificAtlanticWaterFlow.java)
- [Shortest Path in a Grid with Obstacles Elimination](./ShortestPathInAGrid.java)
- [Shortest Path in Binary Matrix](./ShortestPathInBinaryMatrix.java)

#### Count Good Nodes in Binary Tree
Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.


Example:
```
Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.
```

[Implementation](./CountGoodNodes.java)

