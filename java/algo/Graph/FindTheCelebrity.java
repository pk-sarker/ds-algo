package algo.Graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Suppose you are at a party with n people labeled from 0 to n - 1 and among them,
 * there may exist one celebrity. The definition of a celebrity is that all the
 * other n - 1 people know the celebrity, but the celebrity does not know any of them.
 *
 * Now you want to find out who the celebrity is or verify that there is not one.
 * The only thing you are allowed to do is ask questions like: "Hi, A. Do you
 * know B?" to get information about whether A knows B. You need to find out the
 * celebrity (or verify there is not one) by asking as few questions as possible
 * (in the asymptotic sense).
 *
 * You are given a helper function bool knows(a, b) that tells you whether A knows B.
 * Implement a function int findCelebrity(n). There will be exactly one celebrity
 * if they are at the party.
 *
 * Return the celebrity's label if there is a celebrity at the party. If there is
 * no celebrity, return -1.
 *
 */
public class FindTheCelebrity {
    private Map<String, Boolean> cache = new HashMap<>();
    int[][] graph;


    public boolean knowsImp(int i, int j) {
        int row = graph.length, col = graph[0].length;

        if (i < row && j < col) {
            if (this.graph[i][j]==1) {
                return true;
            }
        }
        return false;
    }

    public boolean knows(int i, int j) {
        // update cache
        String key = "" + i + "_" + j;
        if (!cache.containsKey(key)) {
            cache.put(key, knowsImp(i,j));
        }
        return cache.get(key);
    }

    public int findCelebrity(int n, int[][] graph) {
        // Initialize the graph and knows api
        this.graph = graph;
        this.cache.clear();
        int possibleCelebrity = 0;

        for(int i=0; i<n; i++) {
            if (knows(possibleCelebrity, i)) {
                // if possibleCelebrity knows i then possibleCelebrity is not a celebrity
                // and i might be a celebrity
                possibleCelebrity = i;
            }
        }

        if (isCelebrity(possibleCelebrity, n)) {
            return possibleCelebrity;
        }

        return -1;
    }

    public boolean isCelebrity(int possibleCelebrity, int n) {
        for(int i=0; i<n; i++) {
            if (i == possibleCelebrity) {
                continue;
            }

            if (knows(possibleCelebrity, i) || !knows(i, possibleCelebrity)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String args[]) {
        int[][] graph1 = new int[][]{{1,1,0},{0,1,0},{1,1,1}};
        int[][] graph2 = new int[][]{{1,0,1},{1,1,0},{0,1,1}};

        FindTheCelebrity obj = new FindTheCelebrity();

        System.out.println("Input: graph=[[1,1,0],[0,1,0],[1,1,1]] \nCelebrity: "+obj.findCelebrity(3, graph1));
        System.out.println("Input: graph=[[1,0,1],[1,1,0],[0,1,1]] \nCelebrity: "+obj.findCelebrity(3, graph2));
    }
}
