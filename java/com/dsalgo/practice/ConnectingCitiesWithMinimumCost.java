package com.dsalgo.practice;

import java.util.Arrays;

/**
 * There are N cities numbered from 1 to N.
 *
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to
 * connect city1 and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the
 * same as connecting city2 and city1.)
 *
 * Return the minimum cost so that for every pair of cities, there exists a path of connections
 * (possibly of length 1) that connects those two cities together.  The cost is the sum of the connection
 * costs used. If the task is impossible, return -1.
 *
 * Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
 * Output: 6
 * Explanation:
 * Choosing any 2 edges will connect all cities so we choose the minimum 2.
 *
 * Input: N = 4, connections = [[1,2,3],[3,4,4]]
 * Output: -1
 * Explanation:
 * There is no way to connect all cities even if all edges are used.
 */
public class ConnectingCitiesWithMinimumCost {
    int[] parent;
    int n;

    /**
     * The operation Union(x, y) replaces the set containing x and the set containing y with their union.
     * Union first uses Find to determine the roots of the trees containing x and y. If the roots are the
     * same, there is nothing more to do. Otherwise, the two trees must be merged. This is done by either
     * setting the parent pointer of x to y, or setting the parent pointer of y to x.
     *
     * @param x
     * @param y
     */
    private void union(int x, int y) {
        // Replace nodes by roots
        int px = find(x);
        int py = find(y);

        // if root of x and y  is not same that means they belong to different set
        // then you need to change one of  its parent
        if (px != py) {
            parent[px] = py;
            n--;
        }
        // else x and y are already in the same set
    }

    /**
     * The Find operation follows the chain of parent pointers from a specified query node x until
     * it reaches a root element. This root element represents the set to which x belongs and may
     * be x itself. Find returns the root element it reaches.
     *
     * @param x
     * @return
     */
    private int find(int x) {
        if (parent[x] == x) {
            return parent[x];
        }
        parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    public int minimumCost(int N, int[][] connections) {
        parent = new int[N + 1];
        n = N;
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        // sort by weight
        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));

        int res = 0;

        for (int[] c : connections) {
            int x = c[0], y = c[1];
            if (find(x) != find(y)) {
                res += c[2];
                union(x, y);
            }
        }

        return n == 1 ? res : -1;
    }
    public static void main(String args[]) {
        ConnectingCitiesWithMinimumCost ob = new ConnectingCitiesWithMinimumCost();
        System.out.println("Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]] \nOutput: " + ob.minimumCost(3, new int[][]{{1,2,5},{1,3,6},{2,3,1}}));
    }
}
