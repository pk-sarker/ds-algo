package com.dsalgo.practice;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * s = "aa", p = "a" false
 * s = "aa", p = "a*" true
 * s = "ab", p = ".*" true
 * s = "aab", p = "c*a*b" true
 * s = "mississippi", p = "mis*is*p*." false
 */
public class RegularExpressionMatching {

    public static boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty(); // if there is no more pattern character then there should not be any text to match
        }
        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    public static void main(String args[]) {
        System.out.println("\nInput: s=aa p=a \nOutput: " + RegularExpressionMatching.isMatch("aa", "a"));
        System.out.println("\nInput: s=ab p=ab \nOutput: " + RegularExpressionMatching.isMatch("ab", "ab"));
        System.out.println("\nInput: s=aa p=a* \nOutput: " + RegularExpressionMatching.isMatch("aa", "a*"));
        System.out.println("\nInput: s=ab p=.* \nOutput: " + RegularExpressionMatching.isMatch("ab", ".*"));
        System.out.println("\nInput: s=aab p=c*a*b \nOutput: " + RegularExpressionMatching.isMatch("aab", "c*a*b"));
    }
}
