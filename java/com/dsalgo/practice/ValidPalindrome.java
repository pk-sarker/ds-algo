package com.ds.practice;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 *
 * Input: "race a car"
 * Output: false
 *
 * Input: "abc d cba"
 * Output: true
 */
public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <=1) {
            return true;
        }

        for(int i=0, j=s.length()-1; i<j; i++, j--) {
            while(i<j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while(i<j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: \"A man, a plan, a canal: Panama\" \nOutput: " + ValidPalindrome.isPalindrome("A man, a plan, a canal: Panama"));

        System.out.println("\nInput: \"race a car\" \nOutput: " + ValidPalindrome.isPalindrome("race a car"));

        System.out.println("\nInput: \"abc d cba\" \nOutput: " + ValidPalindrome.isPalindrome("abc d cba"));
    }
}
