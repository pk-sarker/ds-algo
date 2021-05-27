package com.dsalgo.practice;

public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int maxSize = 0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if (grid[i][j]==1) {
                    maxSize = Math.max(maxSize, dfs(grid, i,j));
                }
            }
        }

        return maxSize;
    }

    public int dfs(int[][] grid, int r, int c) {
        if (r<0 || c < 0 || r>grid.length-1 || c>grid[0].length-1 || grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 0;
        return 1 + dfs(grid, r-1, c) + dfs(grid, r+1, c) + dfs(grid, r, c-1) + dfs(grid, r, c+1);
    }
    public static void main(String args[]) {
        MaxAreaOfIsland obj  = new MaxAreaOfIsland();

        System.out.println(obj.maxAreaOfIsland(new int[][] {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}}));
    }
}
