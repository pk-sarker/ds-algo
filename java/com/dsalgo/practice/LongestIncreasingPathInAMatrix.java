package com.dsalgo.practice;

public class LongestIncreasingPathInAMatrix {

    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int maxPath = 0;

        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++) {
                maxPath = Math.max(maxPath, explore(matrix, i, j, cache));
            }
        }
        return maxPath;
    }

    public int explore(int[][] grid, int r, int c, int[][] cache) {
        if (cache[r][c] != 0) {
            return cache[r][c]; // already computed the max path form grid[r][c]
        }
        for (int[] d : dirs) {
            int newRow = r + d[0], newCol = c + d[1];
            if (0 <= newRow && newRow < grid.length && 0 <= newCol && newCol < grid[0].length && grid[newRow][newCol] > grid[r][c]) {
                cache[r][c] = Math.max(cache[r][c], explore(grid, newRow, newCol, cache));
            }
        }
        return ++cache[r][c]; // incrementing to count current position as well
    }

    public static void main(String args[]) {
        LongestIncreasingPathInAMatrix obj = new LongestIncreasingPathInAMatrix();
        System.out.println("Longest increasing path: " + obj.longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}}));

    }
}
