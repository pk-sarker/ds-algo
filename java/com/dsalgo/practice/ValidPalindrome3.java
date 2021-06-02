package com.dsalgo.practice;

/**
 * Given a string s and an integer k, return true if s is a k-palindrome.
 *
 * A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 *
 * Input: s = "abcdeca", k = 2
 * Output: true
 * Explanation: Remove 'b' and 'e' characters.
 *
 * Input: s = "abbababa", k = 1
 * Output: true
 */
public class ValidPalindrome3 {

    Integer[][] memo;
    public boolean isValidPalindrome(String s, int k) {
        memo = new Integer[s.length()][s.length()];
        return isValid(s, 0, s.length()-1) <= k;
    }
    public int isValid(String s, int ptrLeft, int ptrRight) {
        // base case if both pointer points to same element then
        if (ptrLeft == ptrRight) {
            return 0;
        }
        // for 2 letters
        if (ptrLeft == ptrRight-1) {
            return  s.charAt(ptrLeft) != s.charAt(ptrRight) ? 1:0;
        }

        // check if it has previous value
        if (memo[ptrLeft][ptrRight] != null) {
            return memo[ptrLeft][ptrRight];
        }

        // Case 1: Character at `ptrLeft` equals character at `ptrRight`
        if (s.charAt(ptrLeft) == s.charAt(ptrRight))
            return memo[ptrLeft][ptrRight] = isValid(s, ptrLeft + 1, ptrRight - 1);

        // Case 2: Character at `i` does not equal character at `j`.
        // Either delete character at `i` or delete character at `j`
        // and try to match the two pointers using recursion.
        // We need to take the minimum of the two results and add 1
        // representing the cost of deletion.
        return memo[ptrLeft][ptrRight] = 1 + Math.min(isValid(s, ptrLeft + 1, ptrRight), isValid(s, ptrLeft, ptrRight-1));
    }
    public static void main(String args[]) {
        ValidPalindrome3 obj = new ValidPalindrome3();
        System.out.println(obj.isValidPalindrome("abcdeca", 2));

        System.out.println(obj.isValidPalindrome("abcdeca", 1));
    }
}
