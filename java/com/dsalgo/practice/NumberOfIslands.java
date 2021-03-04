package com.ds.practice;

public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int nr = grid.length, nc = grid[0].length;

        int count  =0 ;
        for(int i=0; i<nr; i++) {
            for (int j=0; j<nc; j++) {
                if (grid[i][j]=='1') {
                    count++;
                    explore(grid, i, j);
                }
            }
        }
        return count;
    }

    private void explore(char[][] grid, int row, int col) {

        if (row < 0 || row  >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] =='0') {
            return;
        }

        grid[row][col] = '0';
        explore(grid, row, col+1);
        explore(grid, row, col-1);
        explore(grid, row-1, col);
        explore(grid, row+1, col);
    }

    public static void main(String args[]) {
        NumberOfIslands ob = new NumberOfIslands();
        char[][] chAr = new char[][] {{'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};
        System.out.println(ob.numIslands(chAr));

        char[][] chAr1 = new char[][] {{'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
        System.out.println(ob.numIslands(chAr1));
    }
}
