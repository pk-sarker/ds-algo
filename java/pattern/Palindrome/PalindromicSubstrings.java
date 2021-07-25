package pattern.Palindrome;

/**
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 *
 */
public class PalindromicSubstrings {
    int count = 0;
    public int countSubstrings(String s) {
        for(int i=0; i<s.length(); i++) {
            expandSearch(s, i, i);
            expandSearch(s, i, i+1);
        }
        return count;
    }

    private void expandSearch(String s, int left, int right) {
        // 1) check if left and right is valid index
        // 2) expand the substring by adding one character form left
        //    and one character form right. If both new characters are
        //    same then count palindrom

        // for Example:
        // aba -> (0,0) => a,a count++
        //     -> (0,1) => a,b
        //     -> (1,1) => b,b count++
        //          -> (0,2) -> a,a -> count++
        while(left >=0 && right <= s.length()-1 && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
    }

    public int countSubstringsDP(String s) {
        int n = s.length(), counter = 0;

        if (n <= 0) {
            return 0;
        }
        // dp[i][j] = stores the info that substring from i-th position to j-th position
        // is palindrome.
        boolean[][] dp = new boolean[n][n];

        // Base case: single letter substrings
        for (int i = 0; i < n; ++i) {
            dp[i][i] = true;
            counter++;
        }

        // Base case: double letter substrings
        for (int i = 0; i < n - 1; ++i) {
            dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            counter += (dp[i][i + 1] ? 1 : 0);
        }

        // All other cases: substrings of length 3 to n
        for (int len = 3; len <= n; ++len)
            for (int i = 0, j = i + len - 1; j < n; ++i, ++j) {
                dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                counter += (dp[i][j] ? 1 : 0);
            }

        return counter;
    }
    public static void main(String args[]) {
        PalindromicSubstrings obj = new PalindromicSubstrings();
        System.out.println("\nInput:\"abc\" \nOutput: " + obj.countSubstrings("abc"));
        obj.count = 0;
        System.out.println("\nInput:\"aba\" \nOutput: " + obj.countSubstrings("aba"));
    }
}
