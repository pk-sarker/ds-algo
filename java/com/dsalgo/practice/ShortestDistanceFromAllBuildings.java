package com.dsalgo.practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n grid grid of values 0, 1, or 2, where:
 *
 * each 0 marks an empty land that you can pass by freely,
 * each 1 marks a building that you cannot pass through, and
 * each 2 marks an obstacle that you cannot pass through.
 * You want to build a house on an empty land that reaches all buildings in the shortest total travel distance.
 * You can only move up, down, left, and right.
 *
 * Return the shortest travel distance for such a house. If it is not possible to build such a house according to
 * the above rules, return -1.
 *
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 *
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * Output: 7
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
 * So return 7.
 *
 * - BFS each 1 to find out min distance to each 0,
 *      - accumulate this distances to each 0 location: distance[][],
 *      - finally find out min value from distance[][]
 * - In the case of cannot find a house to each all house:
 *     - not all 0s can reach each house: reachCount[][] to count the # of house each 0 can reach,
 *          only >= houseCount valid
 *     - [~~improve speed~~]：not all house can reach each house, in this case, we cannot build such house,
 *         -count reachable house #, if < houseCount, return -1 directly!!!!!
 */
public class ShortestDistanceFromAllBuildings {

    int[][] grid;
    int nr=0;
    int nc=0;
    public int shortestDistance(int[][] grid) {

        if (grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        this.grid = grid;
        this.nr = grid.length;
        this.nc = grid[0].length;

        int[][] distance = new int[this.nr][this.nc];  //accumulated distance of each house (1) to each 0
        int[][] reachCount = new int[this.nr][this.nc]; //count reachable house for each 0
        int houseCount = 0;
        for (int i = 0; i < this.nr; i++) {
            for (int j = 0; j < this.nc; j++) {
                if (grid[i][j] == 1) {
                    houseCount++;
                }
            }
        }
        for (int i = 0; i < this.nr; i++) {
            for (int j = 0; j < this.nc; j++) {
                if (grid[i][j] == 1) {
                    if (!bfs(distance, reachCount, houseCount, i, j)) {
                        return -1;
                    }
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < this.nr; i++) {
            for (int j = 0; j < this.nc; j++) {
                if (grid[i][j] == 0 && reachCount[i][j] == houseCount) {
                    minDistance = Math.min(minDistance, distance[i][j]);
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;

    }

    private boolean bfs(int[][] distance, int[][] reachCount, int houseCount, int row, int col) {
        int[][] visited = new int[this.nr][this.nc];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col});
        int[] dirRow = new int[]{0, 0, -1, 1};
        int[] dirCol = new int[]{1, -1, 0, 0};
        int level = 0;
        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            level++;
            for (int i = 0; i < size; i++) {//level by level
                int[] curr = q.poll();
                for (int k = 0; k < 4; k++) { //visit all neighbors & accumulate distance
                    int nextRow = curr[0] + dirRow[k];
                    int nextCol = curr[1] + dirCol[k];
                    if (nextRow >=0 && nextCol >= 0 && nextRow < this.nr && nextCol < this.nc  && visited[nextRow][nextCol] == 0) {
                        if (grid[nextRow][nextCol] == 0) {
                            distance[nextRow][nextCol] += level;
                            visited[nextRow][nextCol] = 1;
                            reachCount[nextRow][nextCol]++;
                            q.offer(new int[]{nextRow, nextCol});
                        } else if (grid[nextRow][nextCol] == 1) {
                            count++;
                            visited[nextRow][nextCol] = 1; // skip the cells with 1
                        }
                    }
                }
            }
        }
        return count == houseCount;
    }

    public static void main(String args[]) {
        ShortestDistanceFromAllBuildings obj = new ShortestDistanceFromAllBuildings();
        System.out.println(obj.shortestDistance(new int[][]{{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}}));

    }
}
