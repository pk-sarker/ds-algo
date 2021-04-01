package com.dsalgo.practice;

import java.util.HashMap;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving
 * the order of characters. No two characters may map to the same character, but a character may map to itself.
 *
 * Input: s = "egg", t = "add"
 * Output: true
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 *
 * Input: s = "aabb", t = "abab"
 * Output: false
 *
 * Input: s = "1223", t = "7992"
 * Output: true
 */
public class IsomorphicStrings {

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] sCount = new int[256];
        int[] tCount = new int[256];

        HashMap<Character, Character> charMap = new HashMap<>();
        for(int i=0; i < s.length(); i++) {
            if (!charMap.containsKey(s.charAt(i))) {
                charMap.put(s.charAt(i), t.charAt(i));
                //System.out.println(s.charAt(i) + " = " + t.charAt(i));
            } else if (charMap.get(s.charAt(i)) != t.charAt(i)){
                return false;
            }

            sCount[(int) s.charAt(i)]++;
            tCount[(int) t.charAt(i)]++;

            if (sCount[(int) s.charAt(i)] != tCount[(int) t.charAt(i)]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: s=\"egg\", t=\"add\"\nOutput: " + IsomorphicStrings.isIsomorphic("egg", "add"));
        System.out.println("\nInput: s=\"aabb\", t=\"xyxy\"\nOutput: " + IsomorphicStrings.isIsomorphic("aabb", "xyxy"));
        System.out.println("\nInput: s=\"aabb\", t=\"yyxx\"\nOutput: " + IsomorphicStrings.isIsomorphic("aabb", "yyxx"));
        System.out.println("\nInput: s=\"1223\", t=\"7992\"\nOutput: " + IsomorphicStrings.isIsomorphic("1223", "7992"));
    }
}
