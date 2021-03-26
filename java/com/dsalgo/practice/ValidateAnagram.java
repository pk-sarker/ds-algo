package com.dsalgo.practice;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * Input: s = "abcd", t = "dcba"
 * Output: true
 *
 * Input: s = "abcc", t = "abbc"
 * Output: false
 */
public class ValidateAnagram {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] sCharCount = new int[26];
        int[] tCharCount = new int[26];

        for(int i=0; i<s.length(); i++) {
            sCharCount[s.charAt(i) - 'a']++;
            tCharCount[t.charAt(i) - 'a']++;
        }
        for(int i=0; i<26; i++) {
            if (sCharCount[i] != tCharCount[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: s=\"abcd\" t=\"dcba\" \nOutput: " + ValidateAnagram.isAnagram("abcd", "dcba"));
        System.out.println("\nInput: s=\"aaaabc\" t=\"baacaa\" \nOutput: " + ValidateAnagram.isAnagram("aaaabc", "baacaa"));

        System.out.println("\nInput: s=\"abc\" t=\"ab\" \nOutput: " + ValidateAnagram.isAnagram("abc", "ab"));
        System.out.println("\nInput: s=\"abcc\" t=\"abbc\" \nOutput: " + ValidateAnagram.isAnagram("abcc", "abbc"));
    }
}
