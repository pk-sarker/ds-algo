package com.ds.practice;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Input: "aba"
 * Output: True
 *
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 *
 */
public class ConvertAStringToPalindrome {

    public static boolean isPalindrome(String s, int start, int end) {
        int midIdx = start + (end-start)/2;
        for(int i=start; i<=midIdx; i++) {
            if (s.charAt(i) != s.charAt(end-(i-start))) {
                return false;
            }
        }
        return true;
    }
    public static boolean validPalindrome(String s) {
        int n = s.length();
        if (n==1) {
            return true;
        }

        for(int i=0; i<n/2; i++) {
            if (s.charAt(i) != s.charAt(n-i-1)) {
                // two posibilities
                // removing i-th character will make it palindrome or removing (n-i-1)-th char
                return ConvertAStringToPalindrome.isPalindrome(s, i+1, n-i-1) || ConvertAStringToPalindrome.isPalindrome(s, i, n-i-2);
            }
        }
        return true;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: aba \nOutput: " + ConvertAStringToPalindrome.validPalindrome("aba"));

        System.out.println("\nInput: abca \nOutput: " + ConvertAStringToPalindrome.validPalindrome("abca"));

        System.out.println("\nInput: abcca \nOutput: " + ConvertAStringToPalindrome.validPalindrome("abcca"));
    }
}
