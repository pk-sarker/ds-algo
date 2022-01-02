package algo.DP;

import Common.KeyValuePair;

import java.util.HashMap;

/**
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
 *
 * A string's subsequence is a new string formed from the original string by deleting some (can be none)
 * of the characters without disturbing the remaining characters' relative positions.
 * (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * It is guaranteed the answer fits on a 32-bit signed integer.
 *
 * Input: s = "rabbbit", t = "rabbit"
 * Output: 3
 * Explanation:
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * rabbbit
 * rabbbit
 * rabbbit
 *
 * Input: s = "babgbag", t = "bag"
 * Output: 5
 * Explanation:
 * As shown below, there are 5 ways you can generate "bag" from S.
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 *
 * Solution:
 * We have two pointere, each pointing to current character in both strings.
 *
 * There will be two scenerio:
 * 1) if character at position j in t matches i th character in s.
 *      Now we can do two things, move ahead both pointers and continue
 *      or We can move the pointer at s and continue
 *      func(i, j) = func(i + 1, j) + func(i + 1, j + 1)
 *
 * 2) if character at current position doesn't match
 *      Wee have no option otherthen moving s pointer
 *
 */
public class DistinctSubsequences {

    // Dictionary that we will use for memoization
    private HashMap<KeyValuePair<Integer, Integer>, Integer> memo;

    public int numDistinct(String s, String t) {
        this.memo = new HashMap<KeyValuePair<Integer, Integer>, Integer>();
        return this.recurse(s, t, 0, 0);
    }

    private int recurse(String s, String t, int si, int ti) {
        int m = s.length(), n = t.length();

        if (si == m || ti == n || m - si < n - ti) { // m - si < n - ti  if the is less letter after si
            return ti == n ? 1:0;
        }

        KeyValuePair<Integer, Integer> key = new KeyValuePair<Integer, Integer>(si, ti);

        // If already calculated, then return
        if (this.memo.containsKey(key)) {
            return this.memo.get(key);
        }

        // Always calculate this result
        int res = this.recurse(s,t, si+1, ti);

        // If characters match then more recursively check thee next index
        // in both string
        if (s.charAt(si) == t.charAt(ti)) {
            res += this.recurse(s,t,si+1, ti+1);
        }

        this.memo.put(key, res);
        return res;
    }

    /**
     * Dynamic Programming
     *
     * 1) Initialize a 2D array dp of size M×N where M represents the length of string S while N represents
     * the length of string T.
     * 2) An important thing to remember here is what recurseDP(i, j) actually represents. It basically
     * represents the number of distinct subsequences in string s[i....M] that equals the string t[j...N].
     * This is important because we will have our iterative loops based on this idea itself. This implies
     * that we will first calculate the value of recurseDP(i, j) before we can find answers for recurseDP(i - 1, j)
     * or recurseDP(i, j - 1) or recurseDP(i - 1, j - 1).
     * 3) Based on this idea, we will have an outer loop for the index i which will go from M - 1 to 0 and
     * an inner loop for j from N - 1 to 0.
     * 4) We first handle our recursion's base case in outside of our nested loop and here we initialize the last column and the last row of our dp table.
     *      - dp[M][j] represents that we have an empty substring left in String S while we still have T[j...N-1] left.
     *          That means there is no possibility of match, so dp[M][j] = 0
     *      - dp[i][N] represents that we have matched all the letter in T, and now at empty string, so dp[i][N] = 1
     *
     * 5) After that, we simply set dp[i][j] = dp[i + 1][j]. Remember that there was one recursive call that
     *  we need to make irrespective of whether there is a character match or not?
     *
     * 6) Then we check if the characters s[i] and t[j] match or not. If they do, then we add dp[i + 1][j + 1] to dp[i][j].
     *  In the recursion based solution, we were caching this value in the dictionary. Here, the dictionary is replaced by the dp array.
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinctDP(String s, String t) {

        int M = s.length();
        int N = t.length();
        // dp[i][j] = number of distinct subsequences in string s[i....M] that equals the string t[j...N]
        int[][] dp = new int[M + 1][N + 1];

        // Base case initialization
        for (int j = 0; j <= N; j++) {
            dp[M][j] = 0;
        }

        // Base case initialization
        for (int i = 0; i <= M; i++) {
            dp[i][N] = 1;
        }

        // Iterate over the strings in reverse so as to
        // satisfy the way we've modeled our recursive solution
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {

                // Remember, we always need this result
                dp[i][j] = dp[i + 1][j];

                // If the characters match, we add the
                // result of the next recursion call (in this
                // case, the value of a cell in the dp table
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] += dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }


    public static void main(String args[]) {
        DistinctSubsequences obj = new DistinctSubsequences();
        System.out.println(obj.numDistinct("rabbbit", "rabbit"));
        System.out.println(obj.numDistinct("babgbag", "bag"));
        System.out.println(obj.numDistinctDP("babgbag", "bag"));
    }
}
