package ds.Hash;

import java.util.HashMap;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Input: s = "abcabcbef"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String str) {
        int maxLen = Integer.MIN_VALUE;
        HashMap<Character, Integer> charMap = new HashMap<>();
        int start = 0;
        for(int i=0; i< str.length(); i++) {
            if (charMap.containsKey(str.charAt(i))) {
                start = Math.max(start, charMap.get(str.charAt(i))+1);
            }
            charMap.put(str.charAt(i),  i);
            maxLen = Math.max(maxLen, i - start + 1);
        }

        return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
    }


    public static void main(String args[]) {
        System.out.println("\nInput: s=abcabcbb \nOutput: " + LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("abcabcbb"));
        System.out.println("\nInput: s=bbbbb \nOutput: " + LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("bbbbb"));
        System.out.println("\nInput: s=pwwkew \nOutput: " + LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("pwwkew"));


        System.out.println("\nInput: s=\" \" \nOutput: " + LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(" "));
        System.out.println("\nInput: s=dvdf \nOutput: " + LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("dvdf"));

    }
}
