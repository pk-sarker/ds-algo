package com.dsalgo.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 *
 */
public class DecodingWays {
    Map<Integer, Integer> memo = new HashMap<>();
    public int numDecodings(String s) {
        return helper(0, s);
    }

    public int helper(int index, String s) {
        // If already calculated
        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        // reached the end of the string
        if (index == s.length()-1) {
            return 1;
        }
        // we consider two letters together
        if (index == s.length()) {
            return 1;
        }

        // if starts with '0'
        if (s.charAt(index) == '0') {
            return 0;
        }

        int count = helper(index+1, s);

        if (Integer.parseInt(s.substring(index, index+2))<=26) {
            count += helper(index+2, s);
        }


        memo.put(index, count);

        return count;
    }

    public static void main(String args[]) {
        DecodingWays obj = new DecodingWays();

        System.out.println(obj.numDecodings("226"));
    }
}
