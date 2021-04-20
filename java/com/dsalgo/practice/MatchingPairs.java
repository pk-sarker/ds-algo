package com.dsalgo.practice;

import java.util.*;

/**
 * Given two strings s and t of length N, find the maximum number of possible matching pairs
 * in strings s and t after swapping exactly two characters within s.
 * A swap is switching s[i] and s[j], where s[i] and s[j] denotes the character that is present at
 * the ith and jth index of s, respectively. The matching pairs of the two strings are defined as
 * the number of indices for which s[i] and t[i] are equal.
 * Note: This means you must swap two characters at different indices.
 *
 * Input
 * s and t are strings of length N
 * N is between 2 and 1,000,000
 * Output
 * Return an integer denoting the maximum number of matching pairs
 * Example 1
 * s = "abcd"
 * t = "adcb"
 * output = 4
 * Explanation:
 * Using 0-based indexing, and with i = 1 and j = 3, s[1] and s[3] can be swapped, making it  "adcb".
 * Therefore, the number of matching pairs of s and t will be 4.
 * Example 2
 * s = "mno"
 * t = "mno"
 * output = 1
 * Explanation:
 * Two indices have to be swapped, regardless of which two it is, only one letter will remain the same.
 * f i = 0 and j=1, s[0] and s[1] are swapped, making s = "nmo", which shares only "o" with t.
 */
public class MatchingPairs {
    public static int matchingPairs(String s, String t) {
        int match = 0, misMatchCount = 0;
        int n = s.length();
        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> mismatch = new HashMap<>();
        Set<Character> smatch = new HashSet<>();
        List<Integer> misMatchIndices = new ArrayList<>();
        boolean dupInS = false;
        for(int i=0;i<n;i++) {
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            if (sMap.get(s.charAt(i)) > 1) {
                dupInS = true;
            }
            if(s.charAt(i) != t.charAt(i)) {
                misMatchCount++;
                mismatch.put(s.charAt(i), i);
                misMatchIndices.add(i);
            } else {
                match++;
                smatch.add(s.charAt(i));
            }
        }

        // if all the pairs are matching
        if (match == n) {

            if (dupInS) {
                return match; // if there is any duplicate the we can swap the duplicate to keep thee match count more.
            } else {
                return match - 2; // if no duplicate character in S then the number will reduce by 2
            }
        }
        // Perfect matching, 2,  swap (c1, c3) and (c3, c1)
        Set<String> misMatchPair = new HashSet<>();
        for(int i=0; i<misMatchIndices.size(); i ++) {
            int idx = misMatchIndices.get(i);
            String pair = s.charAt(idx) + " " + t.charAt(idx);
            if (misMatchPair.contains(pair)) {
                return match + 2;
            }
            misMatchPair.add(t.charAt(idx) + " " + s.charAt(idx));
        }

        // If there is only one mismatch
        if (misMatchCount == 1) {
            // if there are duplicate then swapping the duplicate character will keep the matching count same
            // s=abx t=abb, if the character at mismatch position in t is present in s.
            if (dupInS || smatch.contains(t.charAt(misMatchIndices.get(0)))) {
                return match;
            } else {
                return match - 1;
            }
        }

        // mismatch
        for(int i=0; i<misMatchIndices.size(); i++) {
            int idx = misMatchIndices.get(i);
            if (mismatch.containsKey(t.charAt(idx)) && mismatch.get(t.charAt(idx)) != i) {
                return match+1;
            } else if (!mismatch.containsKey(t.charAt(idx))) {
                if (misMatchIndices.size() == 1) {
                    return match-1;
                }
            }

        }
        return match;
    }

    public static void main(String args[]){
        System.out.println("\nInput: s=\"abcde\" t=\"adcbe\" \nOutput: " + MatchingPairs.matchingPairs("abcde", "adcbe"));
        System.out.println("\nInput: s=\"abcd\" t=\"abcd\" \nOutput: " + MatchingPairs.matchingPairs("abcd", "abcd"));

//        System.out.println("\nInput: s=\"abcd\" t=\"abcd\" \nOutput: " + MatchingPairs.matchingPairs("abcd", "abcd"));
//        System.out.println("\nInput: s=\"abcd\" t=\"adcb\" \nOutput: " + MatchingPairs.matchingPairs("abcd", "adcb"));
//
//        System.out.println("\nInput: s=\"abcc\" t=\"abcd: \nOutput: " + MatchingPairs.matchingPairs("abcc", "abcd"));
//        System.out.println("\nInput: s=\"abcd\" t=\"abcc: \nOutput: " + MatchingPairs.matchingPairs("abcd", "abcc"));
////
//        System.out.println("\nInput: s=\"abcx\" t=\"abcd\" \nOutput: " + MatchingPairs.matchingPairs("abcx", "abcd"));
//
//        System.out.println("\nInput: s=\"axb\" t=\"ayb\" \nOutput: " + MatchingPairs.matchingPairs("axb", "ayb"));
//        System.out.println("\nInput: s=\"axa\" t=\"aya\" \nOutput: " + MatchingPairs.matchingPairs("axa", "aya"));
//
//        System.out.println("\nInput: s=\"abx\" t=\"abb\" \nOutput: " + MatchingPairs.matchingPairs("abx", "abb"));
//        System.out.println("\nInput: s=\"abb\" t=\"axb \nOutput: " + MatchingPairs.matchingPairs("abb", "axb"));

//        System.out.println("\nInput: s=\"ax\" t=\"ya\" \nOutput: " + MatchingPairs.matchingPairs("ax", "ya"));
//        System.out.println("\nInput: s=\"ab\" t=\"xy\" \nOutput: " + MatchingPairs.matchingPairs("ab", "xy"));
//
//        System.out.println("\nInput: s=\"aa\" t=\"aa\" \nOutput: " + MatchingPairs.matchingPairs("aa", "aa"));


        // System.out.println("\nInput: s=\"abcde\" t=\"aedcb\" \nOutput: " + MatchingPairs.matchingPairs("abcde", "aedcb"));

        //System.out.println("\nInput: s=\"abcd\" t=\"abcd\" \nOutput:" + MatchingPairs.matchingPairs("abcd", "abcd"));
    }
}
