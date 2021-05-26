package com.dsalgo.practice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 *
 * An island is a 4-directionally connected group of 1s.
 *
 * Solution
 * 1,1,1,0
 * 0,1,0,1
 * 1,0,1,0
 * 1,0,1,1
 * 1,1,0,0
 *
 * col ->  0 1 2 3
 *     0  [2,2,2,0]  2 -> 4
 *     1  [0,2,0,3]  3 -> 1
 * row 2  [4,0,5,0]  4 -> 4
 * --> 3  [4,0,5,5]  5 -> 3
 *     4  [4,4,0,0]
 *
 * for (0,3) => 1 + <(0,2)> + <(1,3)>
 *           => 1 + <2> + <3>
 *           => 1 + 4 + 1 = 6
 * for (1,0) => 1 + <(0,0)> + <(1,1)> + <(2,0)>
 *           => 1 + <2> + <2> + <4> | USE SET TO COUNT NON DUPLICATE
 *           => 1 + 4 + 4 = 9
 * for (2,1) => 1 + <(2,0)> + <(1,1)> + <(2,2)> + <(3,1)>
 *           => 1 + <4> + <2> + <5> + <0> | USE SET TO COUNT NON DUPLICATE
 *           => 1 + 4 + 4 + 3 = 12
 *
 *
 *
 */
public class MakingALargeIsland {

    public int largestIsland(int[][] grid) {

        int maxSize = 0;
        int nr = grid.length, nc = grid[0].length;
        Map<Integer, Integer> componentMap = new HashMap<>();
        componentMap.put(0, 0);
        int componentNum = 2; // 0 and 1 is already in the grid, so we start from 2

        for(int r=0;r<nr;r++) {
            for(int c=0;c<nc;c++) {
                if (grid[r][c] == 1) {
                    componentMap.put(componentNum, dfs(grid, r, c, componentNum));
                    componentNum++;
                }
            }
        }
        maxSize = componentMap.getOrDefault(2, 0);
        for(int r=0;r<nr;r++) {
            for(int c=0;c<nc;c++) {
                if (grid[r][c] == 0) {
                    Set<Integer> components = new HashSet<>();
                    if (r > 0) {
                        components.add(grid[r-1][c]);
                    }
                    if (r < nr-1) {
                        components.add(grid[r+1][c]);
                    }
                    if (c > 0) {
                        components.add(grid[r][c-1]);
                    }
                    if (c < nc-1) {
                        components.add(grid[r][c+1]);
                    }
                    int currentSize = 1;
                    for(int component: components) {
                        currentSize +=  componentMap.get(component);
                    }
                    maxSize = Math.max(maxSize, currentSize);
                }
            }
        }
        return maxSize;
    }
    public int dfs(int[][] grid, int row, int col, int component) {
        if (row < 0 || row > grid.length-1 || col < 0 || col > grid[0].length-1 || grid[row][col] != 1) {
            return 0;
        }
        grid[row][col] = component;
        return 1 + dfs(grid, row, col-1, component) + dfs(grid, row, col+1, component)
                + dfs(grid, row-1, col, component) + dfs(grid, row+1, col, component);
    }
    public static void main(String args[]) {
        MakingALargeIsland obj = new MakingALargeIsland();
        System.out.println(obj.largestIsland(new int[][]{{0,1},{1,0}}));
        System.out.println(obj.largestIsland(new int[][]{{1,1},{1,0}}));
        System.out.println(obj.largestIsland(new int[][]{{1,1},{1,1}}));
        System.out.println(obj.largestIsland(new int[][]{{1,1,1,0},{0,1,0,1},{1,0,1,0},{1,0,1,1},{1,1,0,0}}));
    }
}
