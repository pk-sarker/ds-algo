package ds.Array;

import java.util.*;

/***
 * Problem:
 * There is a strange printer with the following two special requirements:
 *
 * On each turn, the printer will print a solid rectangular pattern of a single color on the grid. This will cover up the existing colors in the rectangle.
 * Once the printer has used a color for the above operation, the same color cannot be used again.
 * You are given a m x n matrix targetGrid, where targetGrid[row][col] is the color in the position (row, col) of the grid.
 *
 * Return true if it is possible to print the matrix targetGrid, otherwise, return false.
 *
 *
 * Solution approach:
 * Find each color and it's edge index(top left conner and bottom right corner).
 * Then paint this color from [top, left] to [bottom, right].
 * Here by painting we will erase the color from grid.
 *
 * If in the rectangle, all the colors are either the same or 0,
 * we mark all of them to 0.
 *
 * If we can mark the whole grid to 0, it means the target if printable.
 */
public class StrangePrinterII {
    public boolean isPrintable(int[][] targetGrid) {
        Map<Integer, int[]> pos = new HashMap<>();
        int n = targetGrid.length;
        int m = targetGrid[0].length;
        // i=0
        //      j=0
        //      <grid[0][0]> = [n, m, -1, -1],
        //      <grid[0][0]> = [min(n, i),]

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pos.putIfAbsent(targetGrid[i][j], new int[]{n, m, -1, -1});
                int[] coord = pos.get(targetGrid[i][j]);
                coord[0] = Math.min(coord[0], i);
                coord[1] = Math.min(coord[1], j);
                coord[2] = Math.max(coord[2], i);
                coord[3] = Math.max(coord[3], j);
            }
        }

        System.out.println("Colored rectangles:");
        for(int key : pos.keySet()) {
            System.out.print(key + " -> ");
            System.out.println(Arrays.toString(pos.get(key)));
        }

        Set<Integer> colors = new HashSet<>(pos.keySet());
        while (!colors.isEmpty()) {
            Set<Integer> next = new HashSet<>();
            for (int color : colors) {
                if (!erase(targetGrid, pos.get(color), color)) {
                    next.add(color);
                }
            }
            if (colors.size() == next.size()) {
                return false;
            }
            colors = next;
        }
        return true;
    }

    private boolean erase(int[][] targetGrid, int[] coord, int color) {
        for (int i = coord[0]; i <= coord[2]; i++) {
            for (int j = coord[1]; j <= coord[3]; j++) {
                if (targetGrid[i][j] > 0 && targetGrid[i][j] != color) {
                    return false;
                }
            }
        }
        for (int i = coord[0]; i <= coord[2]; i++) {
            for (int j = coord[1]; j <= coord[3]; j++) {
                targetGrid[i][j] = 0;
            }
        }
        return true;
    }

    void printGrid(int[][] grid) {
        System.out.println(Arrays.toString(grid[0]));
        System.out.println(Arrays.toString(grid[1]));
        System.out.println(Arrays.toString(grid[2]));
        System.out.println(Arrays.toString(grid[3]));
    }
    public static void main(String args[]) {
        StrangePrinterII obj = new StrangePrinterII();
        int[][] grid = new int[][]{{1,1,1,1},{1,2,2,1},{1,2,2,1},{1,1,1,1}};
        obj.printGrid(grid);
        System.out.println("Grid is Printable ? " + obj.isPrintable(grid));
        System.out.print("- - - - - - - - - - - - - - - - - - - - \n");
        int[][] grid2 = new int[][]{{1,1,1,1},{1,1,3,3},{1,1,3,4},{5,5,1,4}};
        obj.printGrid(grid2);
        obj.isPrintable(grid2);
        System.out.println("Grid is Printable ? " + obj.isPrintable(grid));
    }
}