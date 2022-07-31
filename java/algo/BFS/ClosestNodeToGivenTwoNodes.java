package algo.BFS;

import java.util.Arrays;

/**
 * You are given a directed graph of n nodes numbered from 0 to n - 1, where each node
 * has at most one outgoing edge.
 *
 * The graph is represented with a given 0-indexed array edges of size n, indicating
 * that there is a directed edge from node i to node edges[i]. If there is no outgoing
 * edge from i, then edges[i] == -1.
 *
 * You are also given two integers node1 and node2.
 *
 * Return the index of the node that can be reached from both node1 and node2, such
 * that the maximum between the distance from node1 to that node, and from node2 to
 * that node is minimized. If there are multiple answers, return the node with the
 * smallest index, and if no possible answer exists, return -1.
 *
 * Note that edges may contain cycles.
 *
 * Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
 * Output: 2
 *
 * Input: edges = [1,2,-1], node1 = 0, node2 = 2
 * Output: 2
 */
public class ClosestNodeToGivenTwoNodes {

    public static int closestMeetingNode(int[] edges, int node1, int node2) {
        int res = -1, minDist = Integer.MAX_VALUE;
        int[] dist1 = new int[edges.length]; // as there are one outgoing edge only, that means number of nodes = edges
        int[] dist2 = new int[edges.length];
        Arrays.fill(dist1, -1); // dist1[i] = distance from node1 to i-th node, -1 if not reachable
        Arrays.fill(dist2, -1);

        for(int i = node1, dist = 0; i != -1 && dist1[i] == -1; i=edges[i]) {
            dist1[i] = dist++;
        }
        for(int i=node2, dist = 0; i!= -1 && dist2[i] == -1; i=edges[i]) {
            dist2[i] = dist++;
            if(dist1[i] >= 0 && Math.max(dist1[i], dist2[i]) <= minDist) {
                // dist1[i] >= 0 - means i-th node is reachable from node1
                // and dist1[i] is already > 0. So if dist1[i]>=0 and dist1[i]>=0 means
                // i-th node is reachable from both nodes.
                // And Math.max(dist1[i], dist2[i]) <= minDist, if current max distances
                // are bigger than already found minimum distance then we can discart that.

                res = Math.max(dist1[i], dist2[i]) == minDist ? Math.min(i, res) : i;
                // if current max distance is same as previous minDistance then
                // reachable node will be the one with lowest node value(Math.min(i, res)).

                minDist = Math.max(dist1[i], dist2[i]);
            }
        }
        return res;
    }

    public static void main(String args[]) {
        System.out.println("Input: edges = [2,2,3,-1], node1 = 0, node2 = 1 \nOutput: " + ClosestNodeToGivenTwoNodes.closestMeetingNode(new int[]{2,2,3,-1}, 0, 1));
        System.out.println("Input: edges = [1,2,-1], node1 = 0, node2 = 2 \nOutput: " + ClosestNodeToGivenTwoNodes.closestMeetingNode(new int[]{1,2,-1}, 0, 2));
    }
}
