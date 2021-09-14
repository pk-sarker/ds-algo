package algo.DFS;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are
 * horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Input: board = [
 *      ["A","B","C","E"],
 *      ["S","F","C","S"],
 *      ["A","D","E","E"]
 * ], word = "ABCCED"
 * Output: true
 *
 *
 * Input: board = [
 *      ["A","B","C","E"],
 *      ["S","F","C","S"],
 *      ["A","D","E","E"]
 * ], word = "SEE"
 * Output: true
 *
 *
 *
 * Solution: For each letter in the word, start from index 0,
 *  - first find a cell in the board that matched letter in current word index
 *  - then mark current cell as explored, and explore neighbors, up,right,down,left
 *  - after exploration reset the value in explored cell to original
 *
 *
 *
 */
public class WordSearch {
    private char[][] board;
    private int ROWS;
    private int COLS;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.ROWS = board.length;
        this.COLS = board[0].length;

        for (int row = 0; row < this.ROWS; ++row) {
            for (int col = 0; col < this.COLS; ++col) {
                if (this.backtrack(row, col, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean backtrack(int row, int col, String word, int index) {
        /* Step 1). Check if word is matched all the letters */
        if (index >= word.length()) {
            return true;
        }

        /* Step 2).
            Check if row column index is out of bound
            or letter in the current cell in the board doesn't match
            the letter in the word at current index
        * */
        if (row < 0 || row == this.ROWS || col < 0 || col == this.COLS
                || this.board[row][col] != word.charAt(index))
            return false;

        /* Step 3). explore the neighbors in DFS */
        boolean ret = false;
        // mark the path before the next exploration
        this.board[row][col] = '#';

        int[] rowOffsets = {0, 1, 0, -1}; // right, down, left, up
        int[] colOffsets = {1, 0, -1, 0}; // right, down, left, up
        for (int d = 0; d < 4; ++d) {
            ret = this.backtrack(row + rowOffsets[d], col + colOffsets[d], word, index + 1);
            if (ret) {
                break;
            }
        }

        /* Step 4). clean up and return the result. */
        this.board[row][col] = word.charAt(index);
        return ret;
    }


    public static void main(String args[]) {
        WordSearch obj = new WordSearch();

        System.out.println(obj.exist(new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        }, "ABCCED"));
        System.out.println(obj.exist(new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        }, "SEE"));

        System.out.println(obj.exist(new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        }, "ABCB"));



    }
}
