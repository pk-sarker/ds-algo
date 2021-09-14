package algo.BFS;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 *
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 *
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 *
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 *
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 */
public class ShortestPathInBinaryMatrix {

    static int[][] directions = new int[][]{{-1,-1}, {-1,0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0,-1}};

    public static int getShortestPathInBinaryMatrix(int[][] grid) {
        int nr = grid.length, nc = grid[0].length;

        // check if starting and ending cells are 0
        if (grid[0][0] != 0 || grid[nr-1][nc-1] != 0) {
            return -1;
        }
        // Setup BFS
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        grid[0][0] = 1; // set start to 1 so that we don’t create loop

        while(! queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0], col = cell[1];
            // check if we reached to end
            if (row == nr-1 && col == nc-1) {
                return grid[row][col];
            }
            // visit neighbors
            for(int[] dir : directions) {
                int newRow = row + dir[0],
                        newCol = col + dir[1];
                if (newRow <0 || newRow >= nr || newCol < 0 ||
                        newCol >= nc || grid[newRow][newCol] != 0) {
                    continue;
                }

                queue.offer(new int[]{newRow, newCol});
                grid[newRow][newCol] = grid[row][col]+1;


            }
        }
        return -1;
    }

    public static void main(String args[]) {
        System.out.println(ShortestPathInBinaryMatrix.getShortestPathInBinaryMatrix(new int[][]{{0,1},{1,0}}));
        System.out.println(ShortestPathInBinaryMatrix.getShortestPathInBinaryMatrix(new int[][]{{0,0,0},{1,1,0},{1,1,0}}));
    }
}
