package com.ds.practice;

/**
 * Given a string s and an integer k. You should construct k non-empty palindrome strings
 * using all the characters in s.
 *
 * Return True if you can use all the characters in s to construct k palindrome strings
 * or False otherwise.
 *
 * Input: s = "annabelle", k = 2
 * Output: true
 * Explanation: You can construct two palindromes using all characters in s.
 * Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
 *
 * Input: s = "true", k = 4
 * Output: true
 * Explanation: The only possible solution is to put each character in a separate string.
 *
 * Input: s = "yzyzyzyzyzyzyzy", k = 2
 * Output: true
 * Explanation: Simply you can put all z's in one string and all y's in the other string. Both strings will be palindrome.
 *
 * Input: s = "cr", k = 7
 * Output: false
 * Explanation: We don't have enough characters in s to construct 7 palindromes.
 */
public class ConstructKPalindromeStrings {

    public static boolean canConstruct(String s, int k) {
        if (s.length() < k) {
            return false;
        }
        int[] charCount = new int[26];

        for(int i=0;i<s.length(); i++) {
            charCount[s.charAt(i)-'a']++;
        }
        int oddLetterCount =  0;
        for(int i=0; i<charCount.length; i++) {
            if (charCount[i] % 2 == 1) {
                oddLetterCount++;
            }
        }
        if (k < oddLetterCount) {
            return false;
        }

        return true;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: \"annabelle\", k=2 \nOutput: " + ConstructKPalindromeStrings.canConstruct("annabelle", 2));
        System.out.println("\nInput: \"aabbbcddeeeff\" k=3\nOutput: "+ConstructKPalindromeStrings.canConstruct("aabbbcddeeeff", 3));
        System.out.println("\nInput: \"aabbbcddeeeff\" k=2\nOutput: "+ConstructKPalindromeStrings.canConstruct("aabbbcddeeeff", 2));
        System.out.println("\nInput: \"good\" k=4\nOutput: "+ConstructKPalindromeStrings.canConstruct("good", 4));
        System.out.println("\nInput: \"good\" k=6\nOutput: "+ConstructKPalindromeStrings.canConstruct("good", 6));
    }
}
