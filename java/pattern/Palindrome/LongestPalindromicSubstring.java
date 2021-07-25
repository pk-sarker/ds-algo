package pattern.Palindrome;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * Input: s = "ac"
 * Output: "a"
 *
 * Input: s = "a"
 * Output: "a"
 */
public class LongestPalindromicSubstring {

    public static String longestPalindrome(String s) {

        int n = s.length();
        if (n<=0) {
            return s;
        }
        boolean dp[][] = new boolean[n][n];
        int start = 0;
        int maxLength = 0;
        for(int i=0; i<n; i++) {
            dp[i][i] = true;
        }
        maxLength = 1;

        for(int i=0; i<n-1; i++) {
            dp[i][i+1] = false;
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
                maxLength = 2;
                start = i;
            }
        }

        for(int l = 3; l <=n; l++) {
            for(int i = 0; i<n-l+1; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
                    dp[i][j] = true;

                    if ( l > maxLength) {
                        maxLength = l;
                        start = i;
                    }

                }
            }
        }

        return s.substring(start, start+maxLength);
    }
    public static void main(String args[]) {
        System.out.println(LongestPalindromicSubstring.longestPalindrome("babad"));
    }
}
