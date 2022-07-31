package algo.DFS;

import java.util.Arrays;

/**
 * You are given a directed graph of n nodes numbered from 0 to n - 1,
 * where each node has at most one outgoing edge.
 *
 * The graph is represented with a given 0-indexed array edges of size n,
 * indicating that there is a directed edge from node i to node edges[i].
 * If there is no outgoing edge from node i, then edges[i] == -1.
 *
 * Return the length of the longest cycle in the graph. If no cycle exists, return -1.
 *
 * A cycle is a path that starts and ends at the same node.
 *
 * Input: edges = [3,3,4,2,3]
 * Output: 3
 *
 * Input: edges = [3,3,4,2,3,10,9,5,7,6,8]
 * Output: 4
 */
public class LongestCycleInAGraph {

    public int longestCycle(int[] edges) {
        // run dfs from each node and see if we arive to the same node again
        int totalNodes = edges.length;
        int maxCycleLength = Integer.MIN_VALUE;

        int[][] visited = new int[totalNodes][2];
        Arrays.fill(visited, new int[]{-1,-1});

        for(int node=0; node<totalNodes; node++) {
            int pathDist = 0;
            int currentNode = node;
            while(currentNode != -1) {
                int[] visitedNode = visited[currentNode];

                if (visitedNode[0] == -1) {
                    visited[currentNode] = new int[]{pathDist++, node};
                } else {
                    if (visitedNode[1] == node) {
                        maxCycleLength = Math.max(maxCycleLength, pathDist - visitedNode[0]);
                    }
                    break;
                }
                currentNode = edges[currentNode];
            }
        }
        return maxCycleLength == Integer.MIN_VALUE ? -1: maxCycleLength;
    }

    public int longestCycle2(int[] edges) {
        // run dfs from each node and see if we arive to the same node again
        int totalNodes = edges.length;
        int maxCycleLength = Integer.MIN_VALUE;
        int[] visited = new int[totalNodes];
        for(int node=0; node<totalNodes; node++) {
            if(edges[node] == -1) {
                continue;
            }
            Arrays.fill(visited, -1);
            int currentNode = node;
            int cycleLen = 1;
            while(visited[currentNode] == -1) {
                visited[currentNode] = 1;
                currentNode = edges[currentNode];
                if (currentNode == -1) {
                    break;
                }
                if (node == currentNode) {
                    maxCycleLength = Math.max(maxCycleLength, cycleLen);
                    break;
                }
                cycleLen++;
            }
        }
        return maxCycleLength == Integer.MIN_VALUE ? -1: maxCycleLength;
    }





    public static void main(String args[]) {
        int[] nums = new int[] { 3,3,4,2,3,10,9,5,7,6,8};

        LongestCycleInAGraph obj = new LongestCycleInAGraph();
        long start = System.nanoTime();
        int len = obj.longestCycle(nums);
        long end = System.nanoTime();
        System.out.println("> Max Length: " + len);
        long execution = end - start;
        System.out.println("Execution time: " + execution + " nanoseconds");
        int len2 = obj.longestCycle2(nums);
        System.out.println("> Max Length: " + len2);

    }
}
