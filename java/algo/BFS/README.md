# BFS

- [Count Good Nodes in Binary Tree](#count-good-nodes-in-binary-tree) - [Code](./CountGoodNodes.java)
- [Pacific Atlantic Water Flow](./PacificAtlanticWaterFlow.java)
- [Shortest Path in a Grid with Obstacles Elimination](./ShortestPathInAGrid.java)
- [Shortest Path in Binary Matrix](./ShortestPathInBinaryMatrix.java)
- [Jump Game III](./JumpGameIII.java)

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

#### Pacific Atlantic Water Flow
There is an `m x n` rectangular island that borders both the Pacific Ocean and Atlantic Ocean. 
The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the 
island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an `m x n` integer matrix 
heights where `heights[r][c]` represents the height above sea level of the cell at 
coordinate `(r, c)`.

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, 
south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. 
Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates `result` where `result[i] = [ri, ci]` denotes that rain water 
can flow from cell `(ri, ci)` to both the Pacific and Atlantic oceans.

Example:
```
Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
```

[Implementation](./PacificAtlanticWaterFlow.java)