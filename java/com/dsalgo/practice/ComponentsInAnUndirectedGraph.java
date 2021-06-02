package com.dsalgo.practice;

import java.util.*;

/**
 * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi]
 * indicates that there is an edge between ai and bi in the graph.
 *
 * Return the number of connected components in the graph.
 *
 * Example:
 *  0 -- 1   4 -- 5
 *  |    |   |
 *  2    3   6
 *  Connected Components: 2
 *
 *  Example:
 *  0 -- 1 -- 4 -- 5
 *  |    |    |
 *  2    3    6
 *  Connected Components: 1
 *
 *
 *  Solution:
 *  We can create adjacency list from the edges. Then for each edge, run DFS from both nodes.
 *  DFS will explore all the reachable nodes. We keep a counter which we will increase as soon as
 *  start a new DFS
 *
 *  At the end, the counter variable will contain the number of connected components in the undirected graph.
 *
 *  Time Complexity: O(E+V)
 *  Building the adjacency list will take O(E) operations, as we iterate over the list of edges once, and insert each edge into two lists.
 *  During the DFS traversal, each vertex will only be visited once. This is because we mark each vertex as visited as soon as we see it,
 *  and then we only visit vertices that are not marked as visited. In addition, when we iterate over the edge list of each vertex, we
 *  look at each edge once. This has a total cost of O(E+V).
 *
 *  Space complexity: O(E+V)
 */
public class ComponentsInAnUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        int component = 0;
        int[] visited = new int[n];

        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        // create adjacency list
        for (int i = 0; i < edges.length; i++) {
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }

        // Traverse using DFS from each node
        for(int i=0;i<n;i++) {
            if (visited[i] == 0) {
                dfs(i, adj, visited);
                component++;
            }
        }
        return component;
    }

    public void dfs(int node, List<Integer>[] adj, int[] visited) {
        // already visited
        if (visited[node] == 1) {
            return;
        }
        visited[node] = 1;
        for(int neighbor: adj[node]) {
            if (visited[neighbor] == 0) { // if neighbor is not visited
                dfs(neighbor, adj, visited);
            }
        }
    }

    public static void main(String args[]) {
        ComponentsInAnUndirectedGraph obj = new ComponentsInAnUndirectedGraph();

        System.out.println("Nodes: 5, edges: [[0,1],[1,2],[3,4]]\n" + obj.countComponents(5, new int[][]{{0,1}, {1,2}, {3,4}}));

        System.out.println("Nodes: 5, edges: [[0,1],[1,2],[2,3],[3,4]]\n" + obj.countComponents(5, new int[][]{{0,1}, {1,2}, {2,3}, {3,4}}));

        System.out.println("Nodes: 4, edges: [[2,3],[1,2],[1,3]]\n" + obj.countComponents(5, new int[][]{{2,3}, {1,2}, {1,3}}));



    }
}
