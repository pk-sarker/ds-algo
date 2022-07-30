package algo.DFS;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LongestPathWithDifferentAdjacentCharacters {
    int longestPathLen = 0;
    public int longestPath(int[] parent, String s) {
        /*
            parents=[-1,0,0,0,1,1,2,3,3], s = "abacbedfg"
            node 0, i=0, patent = -1
            node 1, i=1, parent = 0
            node 2, i=2, parent = 0
            node 3, i=3, parent = 0
            node 4, i=4, parent = 1
            number of nodes = parent.length
            index is node and value is parent of that node
                     0 a
                   / | \
                  1  2  3  b a c
                 /\  |  /|
                4  5 6 7 8 b e d f g
         */
        int n = parent.length;
        // Create the adjacency list first
        List<Integer>[] adj = new ArrayList[n];
        for(int node=0;node<n;node++) {
            adj[node] = new ArrayList<>();
        }
        for(int node=1;node<n;node++) {
            adj[parent[node]].add(node);
        }
        dfs(adj, s, 0);
        return longestPathLen;
    }

    public int dfs(List<Integer>[] adj, String s, int node) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b-a);
        // for each child of current node we find the max length
        // and store in a MaxHeap
        for(int child : adj[node]) {
            int cur = dfs(adj, s, child);
            if (s.charAt(node) != s.charAt(child)) {
                queue.offer(cur); // add the path length to queue if node and child has different characters
            }
        }
        int largest = queue.isEmpty() ? 0: queue.poll();
        int secondLargest = queue.isEmpty() ? 0: queue.poll();
        // path length = max path length from child A + max path length from child B + 1(for currnet node/root)
        longestPathLen = Math.max(longestPathLen, largest+secondLargest+1);
        return largest + 1; // return the largest path length among all the childrens path lengths
    }

    public static void main(String args[]) {
        LongestPathWithDifferentAdjacentCharacters obj = new LongestPathWithDifferentAdjacentCharacters();
        System.out.println("Input: [-1,0,0,1,1,2] s=\"abacbe\"\nOutput: "+obj.longestPath(new int[]{-1,0,0,1,1,2}, "abacbe"));

        System.out.println("Input: [-1,0,0,0,1,1,2,3,3] s=\"abacbedfg\"\nOutput: "+obj.longestPath(new int[]{-1,0,0,0,1,1,2,3,3}, "abacbedfg"));

    }
}
