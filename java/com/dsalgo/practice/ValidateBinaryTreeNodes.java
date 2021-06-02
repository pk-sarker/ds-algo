package com.dsalgo.practice;

import java.util.HashSet;
import java.util.Set;

/**
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children
 * leftChild[i] and rightChild[i], return true if and only if all the given nodes form
 * exactly one valid binary tree.
 *
 * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
 *
 * Note that the nodes have no values and that we only use the node numbers in this problem.
 *
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 * Output: true
 *
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 * Output: false
 *
 * Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
 * Output: false
 *
 * Input: n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
 * Output: false
 *
 * Solution:
 *  We can count number of incoming edges to each node, in-degree.
 *  - In a valid binary tree each node should have exactly 1 in-degree.
 *  - Also there suppose to be a root node where in-degree is 0.
 *  - It has to be connected, there suppose to be only one root node with 0 in-degree.
 *  - Should not contain any cycle, each node is visited once.
 *
 *
 */
public class ValidateBinaryTreeNodes {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] inDegree = new int[n];
        for(int i=0;i<n;i++) {
            if (leftChild[i] != -1) {
                if (inDegree[leftChild[i]]+1 >1 ) {
                    return false;
                }
                inDegree[leftChild[i]]++;
            }
            if (rightChild[i] != -1) {
                if (inDegree[rightChild[i]]+1 >1 ) {
                    return false;
                }
                inDegree[rightChild[i]]++;
            }
        }
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                if (root != -1) {
                    return false; // disconnected, more than 1 root node
                }
                root = i;
            }
        }
        // DFS to check whether the graph is connected (all nodes are reachable from the root).
        // We add all nodes reachable from the root to a HashSet.
        Set<Integer> visited = new HashSet<>();
        dfs(root, leftChild, rightChild, visited);
        return visited.size() == n;
    }

    boolean dfs(int node, int[] leftChild, int[] rightChild, Set<Integer> visited) {
        if (node == -1) return true; // null
        // node is already visited, visiting a already visited node means cycle
        if (visited.contains(node)) {
            return false;
        }
        visited.add(node); // mark the node visited
        // traverse left child and right child
        return dfs(leftChild[node], leftChild, rightChild, visited)
                && dfs(rightChild[node], leftChild, rightChild, visited);
    }

    public static void main(String args[]) {
        ValidateBinaryTreeNodes obj = new ValidateBinaryTreeNodes();
        System.out.println("node=4, left child: [1,-1,3,-1], right child: [2,-1,-1,-1]\n"+ obj.validateBinaryTreeNodes(4, new int[]{1,-1,3,-1}, new int[]{2,-1,-1,-1}));

        System.out.println("node=4, left child: [1,0,3,-1], right child: [-1,-1,-1,-1]\n"+obj.validateBinaryTreeNodes(4, new int[]{1,0,3,-1}, new int[]{-1,-1,-1,-1}));
    }
}
