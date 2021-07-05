package pattern.SlidingWindow;

import java.util.HashMap;

/**
 * Given a string with lowercase letters only, if you are allowed to replace no more than ‘k’ letters
 * with any letter, find the length of the longest substring having the same letters after replacement.
 *
 * Input: String="aabccbb", k=2
 * Output: 5
 * Explanation: Replace the two 'c' with 'b' to have a longest repeating substring "bbbbb".
 *
 * Input: String="abbcb", k=1
 * Output: 4
 * Explanation: Replace the 'c' with 'b' to have a longest repeating substring "bbbb".
 *
 * Input: String="abccde", k=1
 * Output: 3
 * Explanation: Replace the 'b' or 'd' with 'c' to have the longest repeating substring "ccc".
 */
public class LongestSubstringWithSameLettersAfterReplacement {
    public static int findLength(String str, int k) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int start = 0, maxLen = Integer.MIN_VALUE, n = str.length();
        HashMap<Character, Integer> charFrequency = new HashMap<>();
        int maxRepeatChar = 0;

        for(int end=0; end<n; end++) {
            char c = str.charAt(end);
            charFrequency.put(c, charFrequency.getOrDefault(c, 0)+1);
            maxRepeatChar = Math.max(maxRepeatChar, charFrequency.get(c));

            // check if replacement is more than k in current window
            // total element in current window = end-start+1
            // max repeated char =  maxRepeatChar
            // char to change to make all the char same = end-start+1 - maxRepeatChar
            if (end-start+1-maxRepeatChar > k) {
                char leftChar = str.charAt(start);
                charFrequency.put(leftChar, charFrequency.get(leftChar)-1);
                start++;
            }
            maxLen = Math.max(maxLen, end-start+1);
        }
        return maxLen == Integer.MIN_VALUE ? 0:maxLen;
    }

    public static void main(String[] args) {
        System.out.println(LongestSubstringWithSameLettersAfterReplacement.findLength("aabccbb", 2));
        System.out.println(LongestSubstringWithSameLettersAfterReplacement.findLength("abbcb", 1));
        System.out.println(LongestSubstringWithSameLettersAfterReplacement.findLength("abccde", 1));
    }
}
