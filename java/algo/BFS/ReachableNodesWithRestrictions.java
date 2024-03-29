package algo.BFS;

import java.util.*;

/**
 * There is an undirected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 *
 * You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree. You are also given an integer array restricted which represents restricted nodes.
 *
 * Return the maximum number of nodes you can reach from node 0 without visiting a restricted node.
 *
 * Note that node 0 will not be a restricted node.
 *
 * Example:
 * Input: n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
 * Output: 4
 * Explanation: The diagram above shows the tree.
 * We have that [0,1,2,3] are the only nodes that can be reached from node 0 without visiting a restricted node.
 *
 * Example:
 * Input: n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
 * Output: 3
 * Explanation: The diagram above shows the tree.
 * We have that [0,5,6] are the only nodes that can be reached from node 0 without visiting a restricted node.
 *
 */
public class ReachableNodesWithRestrictions {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] adj = new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj[i] = new ArrayList<>();
        }
        for(int e[] : edges){
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        Set<Integer> visitedSet = new HashSet<>();
        for(int i : restricted) {
            visitedSet.add(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visitedSet.add(0);
        int res = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while( size > 0){
                res++;
                int curr = queue.remove();
                for(int next : adj[curr])  {
                    if(visitedSet.add(next)) {
                        queue.add(next);
                    }
                }
                size--;
            }
        }
        return res;
    }
    public static void main(String args[]) {
        ReachableNodesWithRestrictions obj = new ReachableNodesWithRestrictions();
        System.out.println(obj.reachableNodes(7, new int[][]{{0,1},{1,2},{3,1},{4,0},{0,5},{5,6}}, new int[]{4, 5}));

    }
}
