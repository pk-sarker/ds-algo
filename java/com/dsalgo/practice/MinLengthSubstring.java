package com.dsalgo.practice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Minimum Length Substrings
 * You are given two strings s and t. You can select any substring of string s
 * and rearrange the characters of the selected substring. Determine the minimum
 * length of the substring of s such that string t is a substring of the selected substring.
 * Signature
 * int minLengthSubstring(String s, String t)
 * Input
 * s and t are non-empty strings that contain less than 1,000,000 characters each
 * Output
 * Return the minimum length of the substring of s. If it is not possible, return -1
 * Example
 * s = "dcbefebce"
 * t = "fd"'
 * output = 5
 * Explanation:
 * Substring "dcbef" can be rearranged to "cfdeb", "cefdb", and so on. String t is a substring of "cfdeb".
 * Thus, the minimum length required is 5.
 */
public class MinLengthSubstring {

    public static int minLengthSubstring(String s, String t) {
        int sn = s.length(), tn = t.length();
        if (sn < tn) {
            return -1;
        }
        int minLen = Integer.MAX_VALUE;
        int start = -1;
        int charFoundInSubStr = 0;
        Map<Character, Integer> tmap = new HashMap<>();
        for(int i=0; i<tn; i++) {
            tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0) + 1);
        }
        Map<Character, Integer> smap = new HashMap<>();
        // "matchPos" is used to store matching character position,
        // after finding a substring, instead of moving one character from last start position we can just
        // jump to the next matching character after start
        List<Integer> matchPos = new ArrayList<>();
        for(int i=0; i<sn; i++) {
            smap.put(s.charAt(i), smap.getOrDefault(s.charAt(i), 0) + 1);
            if (tmap.containsKey(s.charAt(i))) {
                if (start == -1) {
                    start = i;
                }
                matchPos.add(i);
                if (tmap.get(s.charAt(i)) == smap.get(s.charAt(i))) {
                    charFoundInSubStr++;
                    while(charFoundInSubStr == tn) {
                        char sc = s.charAt(start);
                        if (minLen == Integer.MAX_VALUE ||  minLen > i-start+1) {
                            minLen = i-start+1;
                        }
                        smap.put(sc, smap.get(sc)-1);
                        if (tmap.containsKey(sc) && smap.get(sc) < tmap.get(sc)) {
                            charFoundInSubStr--;
                        }
                        start++;
                    }
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? -1: minLen;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: s=\"dcbefebce\" t=\"fd\" \nOutput: " + com.dsalgo.fb.String.MinLengthSubstring.minLengthSubstring("dcbefebce","fd"));
        System.out.println("\nInput: s=\"dcbefedce\" t=\"fd\" \nOutput: " + com.dsalgo.fb.String.MinLengthSubstring.minLengthSubstring("dcbefedce","fd"));
        System.out.println("\nInput: s=\"adacefboodcefxedceb\" t=\"bcd\" \nOutput: " + com.dsalgo.fb.String.MinLengthSubstring.minLengthSubstring("adacefboodcefxedceb","bcd"));
        System.out.println("\nInput: s=\"acabd\" t=\"ab\" \nOutput: " + com.dsalgo.fb.String.MinLengthSubstring.minLengthSubstring("acabd","ab"));
        System.out.println("\nInput: s=\"ababccd\" t=\"cba\" \nOutput: " + com.dsalgo.fb.String.MinLengthSubstring.minLengthSubstring("ababccd","cba"));
    }
}