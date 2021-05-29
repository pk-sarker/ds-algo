package com.dsalgo.practice;

import java.util.Arrays;
import java.util.Stack;

public class BipartiteGraph {

    public boolean isBipartite(int[][] graph) {
        int nodes = graph.length;
        int[] colored = new int[nodes];
        Arrays.fill(colored, -1); // mark all nodes uncolored.

        for(int node = 0; node<nodes;node++) {
            // check if node is colored or not
            if (colored[node] == -1) {
                Stack<Integer> stack = new Stack<>();
                stack.push(node);
                colored[node] = 0; // mark node visited

                while(!stack.isEmpty()) {
                    int currentNode = stack.pop();

                    for(int neighbor: graph[currentNode]) {
                        if (colored[neighbor] == -1) {
                            stack.push(neighbor);
                            //System.out.println( ">> " + colored[currentNode] + " >>" + (colored[currentNode] ^ 1));
                            colored[neighbor] = colored[currentNode] ^ 1;

                        } else if(colored[neighbor] == colored[currentNode]) {
                            // if neighbor color is same as node color then
                            // it means that the neighbor is already colored
                            // and same a nodes color. That mean both will be
                            // in same set.
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String args[]) {
        BipartiteGraph obj = new BipartiteGraph();
        // [[1,2,3],[0,2],[0,1,3],[0,2]]
        System.out.println(obj.isBipartite(new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}}));

        System.out.println(obj.isBipartite(new int[][]{{1,2},{0,3},{0,3},{1,2}}));

        System.out.println("" + (1 ^ 1) + " " + (0 ^ 1)  + " " + (2 ^ 1) + " " + (3 ^ 1) + " " + (4 ^ 1) );
    }
}
