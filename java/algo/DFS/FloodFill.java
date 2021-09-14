package algo.DFS;

import java.util.Arrays;

/**
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel
 * value of the image.
 *
 * You are also given three integers sr, sc, and newColor. You should perform a flood fill on the
 * image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally
 * to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally
 * to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned
 * pixels with newColor.
 *
 * Return the modified image after performing the flood fill.
 *
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel),
 * all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels)
 * are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected to the
 * starting pixel.
 *
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
 * Output: [[2,2,2],[2,2,2]]
 *
 */
public class FloodFill {

    public int[][] floodfill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];

        if (color != newColor) {
            dfs(image, sr, sc, color, newColor);
        }

        return image;
    }

    public void dfs(int[][] image, int row, int col, int color, int newColor) {
        int nr = image.length, nc = image[0].length;
        if (row < 0 || row >= nr || col < 0 || col >= nc  || image[row][col] != color) {
            return;
        }
        image[row][col] = newColor;
        dfs(image, row, col-1, color, newColor);
        dfs(image, row, col+1, color, newColor);
        dfs(image, row-1, col, color, newColor);
        dfs(image, row+1, col, color, newColor);
    }

    public static void main(String args[]) {
        FloodFill obj = new FloodFill();
        System.out.println(Arrays.deepToString(obj.floodfill(new int[][]{
                {1,1,1},{1,1,0},{1,0,1}
        },1,1,2)));
    }
}
