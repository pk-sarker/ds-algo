package com.dsalgo.practice;

import java.util.HashMap;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according
 * to the following rules:
 *
 * - Each row must contain the digits 1-9 without repetition.
 * - Each column must contain the digits 1-9 without repetition.
 * - Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 *
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 *
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8.
 * Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 */
public class ValidSudoku {

    public static boolean isValidSudoku(char[][] board) {
        // count numbers in row
        HashMap<Integer, Integer>[] rowMap = new HashMap[9];
        // count numbers in columns
        HashMap<Integer, Integer>[] colMap = new HashMap[9];
        // count numbers in 3X3 box
        HashMap<Integer, Integer>[] subBoxMap = new HashMap[9];

        for(int i=0; i<9; i++) {
            rowMap[i] = new HashMap<Integer, Integer>();
            colMap[i] = new HashMap<Integer, Integer>();
            subBoxMap[i] = new HashMap<Integer, Integer>();
        }

        for(int row=0; row<9; row++) {
            for(int col=0; col<9; col++) {
                char numChar = board[row][col];

                if (numChar != '.') {
                    int num = (int) numChar;
                    int boxNum = (row/3) * 3 + (col/3);
                    rowMap[row].put(num, rowMap[row].getOrDefault(num, 0)+1);
                    colMap[col].put(num, colMap[col].getOrDefault(num, 0)+1);
                    subBoxMap[boxNum].put(num, subBoxMap[boxNum].getOrDefault(num, 0)+1);

                    if (rowMap[row].get(num) > 1 || colMap[col].get(num) > 1 || subBoxMap[boxNum].get(num) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String args[]) {
        System.out.println("\n"+ValidSudoku.isValidSudoku(new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}}));

    }
}
