package algo.DFS;

import java.util.*;

/**
 *You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of
 * edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi
 * in the graph.
 *
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 *
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 *
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 * Output: false
 */
public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        // create adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0;i<n;i++) {
            adjList.add(i, new ArrayList<Integer>());
        }
// for undirected edges, adjacency list of a will contain b and
// adjacency list of b will contain a
        for(int[] edge: edges ){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

// for tree there will be parent child relationship
// <child, parent>
        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0,-1); // 0 to be root node, whose parent is -1/null

// initiate stack for iterative approach
        Stack<Integer> stack =  new Stack<>();
        stack.push(0);

        while(!stack.isEmpty()) {
            int node = stack.pop();
            // explore node’s child
            for(int neighbour: adjList.get(node)) {
                if (parent.get(node) == neighbour) {
                    continue;
                }
// Cycle check, if neighbour is already visited
// then it will be in parent map,
                if (parent.containsKey(neighbour)) {
                    return false;
                }
                stack.push(neighbour);
                parent.put(neighbour, node);
            }
        }

// check if we have visited all the nodes. There suppose to be
// n number of parents in parent set
//
        return parent.size() == n;
    }


    public static void main(String args[]) {

    }
}
