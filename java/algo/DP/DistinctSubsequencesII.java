package algo.DP;

import java.util.Arrays;

/**
 * Given a string s, return the number of distinct non-empty subsequences of s.
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * A subsequence of a string is a new string that is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (i.e., "ace" is a subsequence
 * of "abcde" while "aec" is not.
 *
 * Input: s = "abc"
 * Output: 7
 * Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
 *
 * Input: s = "aba"
 * Output: 6
 * Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
 *
 * Input: s = "aaa"
 * Output: 3
 * Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 *
 * Solution:
 * Count the number of states dp[k] (distinct subsequences) that use letters S[0], S[1], ..., S[k]
 *
 * dp[k] = number of distinct subsequence count till k-th index of the string.
 *
 * Recurrence:
 *  dp[k] = 2 * dp[k-1] - dp[last[S[k]] - 1]
 *  The number of distinct subsequences ending at S[k], is twice the distinct subsequences
 *  counted by dp[k-1] (all of them, plus all of them with S[k] appended), minus the amount
 *  we double counted, which is dp[last[S[k]] - 1].
 *
 */
public class DistinctSubsequencesII {

    public int distinctSubseq(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;

        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 0; i < n; ++i) {
            int x = s.charAt(i) - 'a';
            dp[i+1] = dp[i] * 2 % MOD;
            if (last[x] >= 0) {
                dp[i+1] -= dp[last[x]];  // "abcefc" -> at 5th position, c has been repeated, dp[5] = dp[4] * 2 - dp[2]
            }
            dp[i+1] %= MOD;  // if the number is too big.
            last[x] = i;  // update last position of a character.
        }

        dp[n]--;
        if (dp[n] < 0) dp[n] += MOD;
        return dp[n];
    }

    public static void main(String args[]) {
        DistinctSubsequencesII obj = new DistinctSubsequencesII();
        System.out.println("abc " + obj.distinctSubseq("abc"));
        System.out.println("aba " + obj.distinctSubseq("aba"));
        System.out.println("aaa " + obj.distinctSubseq("aaa"));
    }
}

