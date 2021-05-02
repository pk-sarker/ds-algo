package com.dsalgo.practice;

import java.util.*;

/**
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 *
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * Output: -1
 *
 */
public class BusRoutes {

    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        HashMap<Integer, List<Integer>> stopMap = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        int busCount = 0;
        for(int i=0;i<routes.length;i++) {
            for(int j=0;j<routes[i].length; j++) {
                List<Integer> buses = stopMap.getOrDefault(routes[i][j], new ArrayList<>());
                buses.add(i);
                stopMap.put(routes[i][j], buses);
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        while(!queue.isEmpty()) {
            busCount++;
            int size = queue.size();
            for(int i=0; i<size; i++) {
                Integer stop = queue.poll();
                List<Integer> buses = stopMap.get(stop);
                for(Integer bus: buses) {
                    if (visited.contains(bus)) {
                        continue;
                    }
                    visited.add(bus);
                    for(int j=0; j<routes[bus].length;j++) {
                        if (routes[bus][j] == target) {
                            return busCount;
                        }
                        queue.offer(routes[bus][j]);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        //System.out.println(BusRoutes.numBusesToDestination(new int[][]{{1,2,7}, {3,6,7}}, 1, 6));

        //System.out.println(BusRoutes.numBusesToDestination(new int[][]{{1,2,7}, {3,6,7}}, 1, 6));

        System.out.println(BusRoutes.numBusesToDestination(new int[][]{{7,12},{4,5,15},{6},{15,19},{9,12,13}}, 15, 12));



    }
}
