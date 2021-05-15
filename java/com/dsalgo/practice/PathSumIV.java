package com.dsalgo.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * If the depth of a tree is smaller than 5, then this tree can be represented by an array of three-digit
 * integers. For each integer in this array:
 *
 * The hundreds digit represents the depth d of this node where 1 <= d <= 4.
 * The tens digit represents the position p of this node in the level it belongs to where 1 <= p <= 8.
 * The position is the same as that in a full binary tree.
 * The units digit represents the value v of this node where 0 <= v <= 9.
 * Given an array of ascending three-digit integers nums representing a binary tree with a depth smaller
 * than 5, return the sum of all paths from the root towards the leaves.
 *
 * It is guaranteed that the given array represents a valid connected binary tree.
 */
public class PathSumIV {
    Map<Integer, Integer> nodeMap = new HashMap<>();
    int allPathSum = 0;
    public int pathSum(int[] nums) {

        // store the nodes in hash map
        // we will store depth+position as key and value as value
        for(int i=0;i<nums.length;i++) {
            int key = nums[i]/10;
            int value = nums[i]%10;
            nodeMap.put(key, value);
        }
        dfs(nums[0]/10, 0);
        return allPathSum;
    }

    public void dfs(int node, int previousSum) {
        int depth = node/10, position = node%10;
        // find left and right child of current node
        int leftChild = (depth+1)*10 + (position*2-1);
        int rightChild = (depth+1) * 10 + (position*2);

        int currentSum = previousSum + nodeMap.getOrDefault(node,0);

        // check if current node is leaf node
        if (!nodeMap.containsKey(leftChild) && !nodeMap.containsKey(rightChild)) {
            allPathSum += currentSum;
            return;
        }

        if (nodeMap.containsKey(leftChild)) {
            dfs(leftChild, currentSum);
        }
        if (nodeMap.containsKey(rightChild)) {
            dfs(rightChild, currentSum);
        }
    }
    public static void main(String args[]) {
        PathSumIV obj = new PathSumIV();
        System.out.println(""+ obj.pathSum(new int[]{113,215,221}));
        System.out.println(""+ obj.pathSum(new int[]{111,212,223,314,325,336,347,429,464,532,597}));
    }
}
