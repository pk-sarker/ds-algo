package algo.DP;

import Common.Pair;

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
    private HashMap<Pair<Integer, Integer>, Integer> memo;

    public int numDistinct(String s, String t) {
        this.memo = new HashMap<Pair<Integer, Integer>, Integer>();
        return this.recurse(s, t, 0, 0);
    }

    private int recurse(String s, String t, int si, int ti) {
        int m = s.length(), n = t.length();

        if (si == m || ti == n || m - si < n - ti) { // m - si < n - ti  if the is less letter after si
            return ti == n ? 1:0;
        }

        Pair<Integer, Integer> key = new Pair<Integer, Integer>(si, ti);

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

    public static void main(String args[]) {
        DistinctSubsequences obj = new DistinctSubsequences();
        System.out.println(obj.numDistinct("rabbbit", "rabbit"));
        System.out.println(obj.numDistinct("babgbag", "bag"));
    }
}
