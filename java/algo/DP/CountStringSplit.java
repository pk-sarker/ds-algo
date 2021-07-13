package algo.DP;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given a string s, a split is called good if you can split s into 2 non-empty
 * strings p and q where its concatenation is equal to s and the number of distinct
 * letters in p and q are the same.
 *
 * Return the number of good splits you can make in s.
 *
 * Input: s = "aacaba"
 * Output: 2
 * Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
 * ("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
 *
 * Input: s = "abcd"
 * Output: 1
 * Explanation: Split the string as follows ("ab", "cd").
 *
 * Input: s = "aaaaa"
 * Output: 4
 * Explanation: All possible splits are good.
 *
 * Input: s = "acbadbaada"
 * Output: 2
 */
public class CountStringSplit {

    public static int numSplits(String s) {
        int n = s.length();
        int[] perfix = new int[n];
        int[] suffix = new int[n];
        Set<Character> leftCharSet = new HashSet<>();
        Set<Character> rightCharSet = new HashSet<>();
        for(int i=0;i<n;i++) {
            leftCharSet.add(s.charAt(i));
            perfix[i] = leftCharSet.size();

            rightCharSet.add(s.charAt(n-1-i));
            suffix[n-1-i] = rightCharSet.size();
        }
        int count = 0;
        for(int i=1; i<n;i++) {
            if (perfix[i-1] == suffix[i]) {
                count++;
            }
        }

        return count;
    }

    public static int numSplitsOpt(String s) {
        int l[] = new int[26], r[] = new int[26], d_l = 0, d_r = 0, res = 0;
        char[] chAr = s.toCharArray();
        for (char ch : chAr) {
            d_r += ++r[ch - 'a'] == 1 ? 1 : 0;
        }

        for (int i = 0; i < chAr.length; ++i) {
            d_l += ++l[chAr[i] - 'a'] == 1 ? 1 : 0;
            d_r -= --r[chAr[i] - 'a'] == 0 ? 1 : 0;
            res += d_l == d_r ? 1 : 0;
        }
        return res;
    }


    public static void main(String args[]) {
        System.out.println( "Input: \"aacaba\" \nOutput: " + CountStringSplit.numSplits("aacaba"));
        System.out.println( "Input: \"aacaba\" \nOutput: " + CountStringSplit.numSplitsOpt("aacaba"));
        System.out.println( "Input: \"abcd\" \nOutput: " + CountStringSplit.numSplits("abcd"));
        System.out.println( "Input: \"aaaaa\" \nOutput: " + CountStringSplit.numSplits("aaaaa"));
        System.out.println( "Input: \"acbadbaada\" \nOutput: " + CountStringSplit.numSplits("acbadbaada"));
    }
}
