package algo.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
 * The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights
 * where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly
 * north, south, east, and west if the neighboring cell's height is less than or equal to the
 * current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain
 * water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 *
 * Example:
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 *
 * Input: heights = [[2,1],[1,2]]
 * Output: [[0,0],[0,1],[1,0],[1,1]]
 *
 * Solution:
 * We can start form each cell and try to find if water flows to both  Pacific Ocean and Atlantic Ocean. This
 * process will be slow, as we have to check the cells from where water doesn't flow either Pacific Ocean and Atlantic Ocean.
 *
 * Lets think in the other way, as water has to flow to Pacific Ocean and Atlantic Ocean, the cells which
 * are directly connected to the Ocean will be potential candidate. Then from the boundary cell we try to
 * explore in words to the island. We will do for both Pacific Ocean and Atlantic Ocean and lastly
 * take the intersection of both.
 *
 * For exploration, we can use either BFS or DFS.
 *
 */
public class PacificAtlanticWaterFlow {


    // Using BFS
    private int nRows = 0;
    private int nCols = 0;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        this.nRows = heights.length;
        this.nCols = heights[0].length;

        // initialize
        // Setup each queue with cells adjacent to their respective ocean
        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();

        for(int row = 0; row<nRows; row++) {
            pacificQueue.offer(new int[]{row,0});
            atlanticQueue.offer(new int[]{row,nCols-1});
        }
        for(int col = 0; col<nCols; col++) {
            pacificQueue.offer(new int[]{0, col});
            atlanticQueue.offer(new int[]{nRows-1,col});
        }

        // Perform a BFS for each ocean to find all cells accessible by each ocean
        boolean[][] reachableCellsFromPacific = explore(heights, pacificQueue);
        boolean[][] reachableCellsFromAtlantic = explore(heights, atlanticQueue);

        // Find all cells that can reach both oceans
        List<List<Integer>> cellIntersection = new ArrayList<>();
        for(int row=0;row<this.nRows;row++) {
            for(int col=0;col<this.nCols;col++) {
                if (reachableCellsFromPacific[row][col] && reachableCellsFromAtlantic[row][col]) {
                    cellIntersection.add(List.of(row, col));
                }
            }
        }

        return cellIntersection;
    }

    private boolean[][] explore(int[][] grid, Queue<int[]> queue){
        boolean[][] reachable = new boolean[this.nRows][this.nCols];

        while(!queue.isEmpty()) {
            int[] cord = queue.poll();

            // This cell is reachable, so mark it
            reachable[cord[0]][cord[1]] = true;

            // explore neighbor, right, down, left, top
            int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Check all 4 directions
            int newRow = 0, newCol = 0;
            for(int[] dir: directions) {
                newRow = cord[0]+dir[0];
                newCol = cord[1]+dir[1];

                // check new cell postion
                if (newRow <0 || newRow>=this.nRows || newCol<0 || newCol >= this.nCols) {
                    continue;
                }
                // if new cell is already explored
                if (reachable[newRow][newCol]) {
                    continue;
                }

                // check heights, neighbor heights has to be greater than or equal to current cell.
                //
                if (grid[newRow][newCol] < grid[cord[0]][cord[1]]) {
                    continue;
                }
                queue.offer(new int[]{newRow, newCol});
            }
        }
        return reachable;
    }

    public static void main(String args[]) {
        PacificAtlanticWaterFlow obj = new PacificAtlanticWaterFlow();

        System.out.println(obj.pacificAtlantic(new int[][]{{1,2,2,3,5}, {3,2,3,4,4}, {2,4,5,3,1}, {6,7,1,4,5}}));
    }
}
