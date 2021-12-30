"""
Find Leaves of Binary Tree
Given the root of a binary tree, collect a tree's nodes as if you were doing this:
    - Collect all the leaf nodes.
    - Remove all the leaf nodes.
    - Repeat until the tree is empty.

Solution: Leaves are the outer most nodes of the tree that doesn't have any child.
So first set of leaves will be the outer most nodes, outer most nodes are the ones
that is far away from the root. We can call the distance as height.
     a     Here we use height in reverse way. The height of node "d" suppose to be 3 and
    / \    the height of node "a" suppose to be 0. But for the organization of the result
   b  c    we will use the concept that leaves will have height 0 and height will increase
  /        as we go up.
 d

We will use DFS to traverse the tree, so
height of a node = max(height of left subtree, height of right subtree) + 1
If node is None/Null then we return -1, this is for the leaf nodes.
"""
from typing import Optional, List

from common.tree import TreeNode


class Solution:
    def __init__(self):
        self._result = []

    def find_leaves(self, root: Optional[TreeNode]) -> List[List[int]]:
        self._find_height(root)
        return self._result

    def _find_height(self, root: Optional[TreeNode]) -> int:
        if root == None:
            return -1

        # find the height of the left subtree and right subtree
        left_height = self._find_height(root.left)
        right_height = self._find_height(root.right)

        # Height of current node is the maximum height of left and right subtree
        current_height = max(left_height, right_height) + 1

        if len(self._result) == current_height:
            self._result.append([])
        self._result[current_height].append(root.val)
        return current_height


if __name__ == "__main__":
    print("Find Leaves of Binary Tree")
    #             5
    #           /   \
    #          3     8
    #         / \   / \
    #        1   2 7   9
    #           /   \
    #          4     6
    n2 = TreeNode(2, TreeNode(4))
    n3 = TreeNode(3, TreeNode(1), n2)
    n7 = TreeNode(7, None, TreeNode(6))
    n8 = TreeNode(8, n7, TreeNode(9))
    n5 = TreeNode(5, n3, n8)

    sol = Solution()
    res = sol.find_leaves(n5)
    print(res)
