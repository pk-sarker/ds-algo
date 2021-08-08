package algo.DP;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to
 * convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * - Insert a character
 * - Delete a character
 * - Replace a character
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 */
public class EditDistance {

    public static int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int[][] editCount = new int[n+1][m+1]; // one extra space for empty character

        /**
         * initialize the edit count grid
         * first row and first column
         *
         *       " " "h" "o" "r" "s" "e"
         *    " " 0   1   2   3   4   5
         *    "r" 1   1   2   2   3   4
         *    "o" 2   2   1   2   3   4
         *    "s" 3   3   2   2   2   3
         */
        for(int i=0;i<=n;i++) {
            for(int j=0;j<=m;j++) {
                editCount[i][j] = j; // convert empty string to substrings of word2
            }
            editCount[i][0] = i;  // convert empty string to substrings of word1
        }
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<m+1; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    editCount[i][j] = editCount[i-1][j-1];
                } else {
                    editCount[i][j] = 1 + Math.min(editCount[i-1][j-1], Math.min(editCount[i][j-1], editCount[i-1][j]));
                }
            }
        }

        return editCount[n][m];
    }

    public static void main(String args[]) {
        System.out.println(EditDistance.minDistance("horse", "ros"));
    }
}
