package algo.DP;

import java.util.Arrays;

/**
 * There is a strange printer with the following two special properties:
 *
 * The printer can only print a sequence of the same character each time.
 * At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
 * Given a string s, return the minimum number of turns the printer needed to print it.
 *
 */
public class StrangePrinter {

    /**
     * Recursive Solution
     * In the recursive solution we split the problem
     * For each character in the string, s
     *  - if s is a string with single character, len(s) = 1
     *      - then we need 1 turn to print
     *  - if s contains more than 1 character
     *      - Partition the string to in two parts and find turns needed for each substring
     *          - Then merge the solution, turns needed = turns needed for (substring 1) + turns needed for (substring 2)
     *      - As we want the smallest number of the turns
     *          - we want to pick the index k that will give us the partition that will give use the smallest turns
     *      - Another case
     *          - if first character is equal to the last character
     *              - Then we have to decrement the number of turns by 1, as we have over counted
     *   - We keep track of start and end of the input string
     */
    public int strangePrinter(String s) {
        return helper(s, 0,  s.length()-1);
    }

    public int helper(String s, int start, int end) {
        // if is only one character then it will take single turn to print
        if (start == end) {
            return 1;
        }
        // if there are more that 1 character, then we will split the string
        // and find the turns needed to print each sub string and add the result and get the minimum
        // splitIndex, k = s[start:k], s[k+1:end]
        // min = min(previous min, helper(s[start:k], start, k) +  helper(s[k+1:end], k+1, end))
        int minTurn = Integer.MAX_VALUE;

        for (int k = start; k< end; k++) {
            minTurn = Math.min(minTurn, helper(s, start, k) + helper(s, k+1, end));
        }
        // We added 1 turn for each character,
        // if both first and last character is same then we need to subtrack
        // 1 from computed minimum turns
        return s.charAt(start) == s.charAt(end) ? minTurn - 1 : minTurn;
    }


    /**
     * Improved version of recursion: we can keep the result of the already computed substring turns
     * and used it.
     */

    public int strangePrinterRecursionImproved(String s) {
        int[][] memo = new int[s.length()][s.length()];
        //Arrays.fill(memo, new int[]{});
        return helperImproved(s, 0,  s.length()-1, memo);
    }

    public int helperImproved(String s, int start, int end, int[][] memo) {
        // if is only one character then it will take single turn to print
        if (start == end) {
            return 1;
        }
        if (memo[start][end] > 0) {
            return memo[start][end];
        }
        // if there are more that 1 character, then we will split the string
        // and find the turns needed to print each sub string and add the result and get the minimum
        // splitIndex, k = s[start:k], s[k+1:end]
        // min = min(previous min, helper(s[start:k], start, k) +  helper(s[k+1:end], k+1, end))
        int minTurn = Integer.MAX_VALUE;

        for (int k = start; k< end; k++) {
            minTurn = Math.min(minTurn, helperImproved(s, start, k, memo) + helperImproved(s, k+1, end, memo));
        }
        // We added 1 turn for each character,
        // if both first and last character is same then we need to subtrack
        // 1 from computed minimum turns
        return s.charAt(start) == s.charAt(end) ? minTurn - 1 : minTurn;
    }

    /**
     * Solution with Dynamic Programing
     * We will use bottom up approach.
     * The base case:
     *  - for 1 Character, the last character,  it will need 1 turn
     *  - then we consider the previous one, (last index - 1)-th character,
     *      - if its same as the next one then the turn to print (last index - 1)-th to last character
     *      is same as the last character, because both characters are the same.
     *          - s[end], dp[end][end] = 1
     *          - s[end] == s[end-1], then dp[end-1][end] = dp[end][end]
     *      - Minimum turn to print characters between (start, end), start < end, is
     *          - minTurn(start, end) = min(dp[start][end], dp[start][k] + dp[k+1][end]), where start <= k <= end
     */

    public int strangePrinterWithDP(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1 ; i >= 0; i--) {
            for (int j = 0; j < s.length(); j++) {
                if (i > j) {
                    continue;
                }
                if (i == j) {
                    dp[i][j] = 1; // same character, it will take at-least 1 turn to print
                    continue;
                }
                // now i < j
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j]--;
                }
            }
        }

        return dp[0][n - 1];
    }
}
