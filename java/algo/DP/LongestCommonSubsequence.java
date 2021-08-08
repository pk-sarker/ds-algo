package algo.DP;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with
 * some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 *
 */
public class LongestCommonSubsequence {

    // Memoization

    /**
     * Recursively break the original problem down into subproblems. Consider the greedy algorithm.
     *
     * Consider if a character of the string is part of optimal solution ?
     *  - if the character is part of optimal solution then try finding longest subsequence of the remaining
     *  - if the character is not part of optimal solution then move to next character
     * For example, str1 = "actgattag", and str2 = "gtgtga"
     *
     * define function LCS(text1, text2):
     *     # If either string is empty there, can be no common subsequence.
     *     if length of text1 or text2 is 0:
     *         return 0
     *
     *     letter1 = the first letter in text1
     *     firstOccurence = first position of letter1 in text2
     *
     *     # The case where the line *is not* part of the optimal solution
     *     case1 = LCS(text1.substring(1), text2)
     *
     *     # The case where the line *is* part of the optimal solution
     *     case2 = 1 + LCS(text1.substring(1), text2.substring(firstOccurence + 1))
     *
     *     return maximum of case1 and case2
     */

    private int[][] memo;
    private String text1;
    private String text2;


    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        if (m == 0 || n == 0){
            return 0;
        }

        this.memo = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                this.memo[i][j] = -1;
            }
        }
        this.text1 = text1;
        this.text2 = text2;
        return memoSolve(0, 0);
    }

    private int memoSolve(int p1, int p2) {
        // Check whether or not we've already solved this subproblem.
        // This also covers the base cases where p1 == text1.length
        // or p2 == text2.length.
        if (memo[p1][p2] != -1) {
            return memo[p1][p2];
        }

        // Option 1: we don't include text1[p1] in the solution.
        int option1 = memoSolve(p1 + 1, p2);

        // Option 2: We include text1[p1] in the solution, as long as
        // a match for it in text2 at or after p2 exists.
        int firstOccurence = text2.indexOf(text1.charAt(p1), p2);
        int option2 = 0;
        if (firstOccurence != -1) {
            option2 = 1 + memoSolve(p1 + 1, firstOccurence + 1);
        }

        // Add the best answer to the memo before returning it.
        memo[p1][p2] = Math.max(option1, option2);
        return memo[p1][p2];
    }

    public static void main(String args[]) {

    }
}
