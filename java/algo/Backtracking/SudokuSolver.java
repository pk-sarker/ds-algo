package algo.Backtracking;

import java.util.Arrays;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 *  - Each of the digits 1-9 must occur exactly once in each row.
 *  - Each of the digits 1-9 must occur exactly once in each column.
 *  - Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 *
 * The '.' character indicates empty cells.
 *
 * Constraints:
 *      - board.length == 9
 *      - board[i].length == 9
 *      - board[i][j] is a digit or '.'.
 *      - It is guaranteed that the input board has only one solution.
 *
 * Example:
 * Input: board = [
 *      ["5","3",".",".","7",".",".",".","."],
 *      ["6",".",".","1","9","5",".",".","."],
 *      [".","9","8",".",".",".",".","6","."],
 *      ["8",".",".",".","6",".",".",".","3"],
 *      ["4",".",".","8",".","3",".",".","1"],
 *      ["7",".",".",".","2",".",".",".","6"],
 *      [".","6",".",".",".",".","2","8","."],
 *      [".",".",".","4","1","9",".",".","5"],
 *      [".",".",".",".","8",".",".","7","9"]]
 * Output: [
 *      ["5","3","4","6","7","8","9","1","2"],
 *      ["6","7","2","1","9","5","3","4","8"],
 *      ["1","9","8","3","4","2","5","6","7"],
 *      ["8","5","9","7","6","1","4","2","3"],
 *      ["4","2","6","8","5","3","7","9","1"],
 *      ["7","1","3","9","2","4","8","5","6"],
 *      ["9","6","1","5","3","7","2","8","4"],
 *      ["2","8","7","4","1","9","6","3","5"],
 *      ["3","4","5","2","8","6","1","7","9"]]
 */
public class SudokuSolver {
    int subBoxSize = 3;
    int boxSize = subBoxSize*subBoxSize;
    boolean isSudokuSolved = false;
    int[][] rows = new int[boxSize][boxSize+1];
    int[][] columns = new int[boxSize][boxSize+1];
    int[][] boxes = new int[boxSize][boxSize+1];
    char[][] board;

    public void placeNextNumber(int row, int col) {
        // check if reached at the last cell
        if (row == boxSize-1 && col == boxSize-1) {
            isSudokuSolved = true;
            return;
        }
        if (col == boxSize-1){
            backtrack(row+1, 0);
        } else {
            backtrack(row, col+1);
        }
    }

    public void removeNumber(int num, int row, int col){
        int boxIndex = (row / subBoxSize ) * subBoxSize + col / subBoxSize;
        rows[row][num]--;
        columns[col][num]--;
        boxes[boxIndex][num]--;
        board[row][col] = '.';
    }
    public boolean canPlaceNumber(int num, int row, int col) {
        int boxIndex = (row/subBoxSize)*subBoxSize + (col/subBoxSize);

        return rows[row][num] + columns[col][num] + boxes[boxIndex][num] == 0;
    }
    public void backtrack(int row, int col) {
        if (board[row][col] == '.') {
            // need to place a number
            // iterate over 1 to 9
            for (int num=1; num <=9; num++) {
                if (canPlaceNumber(num, row, col)) {
                    placeNumber(num, row, col);
                    placeNextNumber(row, col);

                    if (!isSudokuSolved) removeNumber(num, row, col);
                }
            }
        } else {
            // backtrack from next cell
            placeNextNumber(row, col);
        }
    }

    public void placeNumber(int num, int row, int col){
        int subBoxId = (row/subBoxSize)*subBoxSize + (col/subBoxSize);
        rows[row][num]++;
        columns[col][num]++;
        boxes[subBoxId][num]++;
        board[row][col] = (char)(num + '0');
    }

    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int row=0; row<boxSize;row++) {
            for(int col=0; col<boxSize; col++) {
                if (board[row][col]!='.') {
                    int num = Character.getNumericValue(board[row][col]);
                    placeNumber(num, row, col);
                }
            }
        }
        backtrack(0,0);
    }

    public static void main(String args[]) {
        SudokuSolver obj = new SudokuSolver();
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        obj.solveSudoku(board);

        for(int i=0; i<9; i++) {
            System.out.println(Arrays.toString(obj.board[i]));
        }

    }
}
