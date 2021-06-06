package com.dsalgo.practice;

import java.util.HashMap;

/**
 * Given a string num which represents an integer, return true if num is a strobogrammatic number.
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Input: num = "69"
 * Output: true
 *
 * Input: num = "88"
 * Output: true
 *
 * Input: num = "1"
 * Output: true
 *
 * Input: num = "962"
 * Output: false
 */
public class StrobogrammaticNumber {

    public static boolean isStrobogrammatic(String num) {
        if (num == null || num.isEmpty()) {
            return true;
        }
        char[] digits = num.toCharArray();
        int p1 = 0, p2 = num.length()-1;
        HashMap<Character, Character> map = new HashMap<>(){{
            put('0','0');
            put('1','1');
            put('6','9');
            put('9','6');
            put('8','8');
        }};
        while(p1<=p2) {
            if (!map.containsKey(digits[p1]) || !map.containsKey(digits[p2])) {
                return false;
            }
            if (digits[p2] != map.get(digits[p1]))  {
                return false;
            }

            if (p1==p2) {
                break;
            }
            p1++;
            p2--;
        }

        return true;
    }
    public static void main(String  args[]) {
        System.out.println("\"1\" -> "+StrobogrammaticNumber.isStrobogrammatic("1"));
        System.out.println("\"6\" -> "+StrobogrammaticNumber.isStrobogrammatic("6"));
        System.out.println("\"9\" -> "+StrobogrammaticNumber.isStrobogrammatic("9"));
        System.out.println("\"69\" -> "+StrobogrammaticNumber.isStrobogrammatic("69"));
        System.out.println("\"88\" -> "+StrobogrammaticNumber.isStrobogrammatic("88"));
        System.out.println("\"818\" -> "+StrobogrammaticNumber.isStrobogrammatic("818"));
        System.out.println("\"6699\" -> "+StrobogrammaticNumber.isStrobogrammatic("6699"));
        System.out.println("\"66199\" -> "+StrobogrammaticNumber.isStrobogrammatic("66199"));
        System.out.println("\"661899\" -> "+StrobogrammaticNumber.isStrobogrammatic("661899"));
    }
}
