package com.dsalgo.practice;

public class StringToInteger {

    public static int myAtoi(String s) {
        int index = 0, strLen = s.length();

        // discard leading whitespace
        while(index < strLen && s.charAt(index) == ' ') {
            index++;
        }
        if (index >= strLen) {
            return 0;
        }
        // sign
        int sign = 1;
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = s.charAt(index) == '-' ? -1:1;
            index++;
        }

        int base = 0;
        // discard non-number characters
        while(index < strLen && s.charAt(index)-'0' >= 0 &&  s.charAt(index)-'0' <= 9) {
            // check overflow
            if (base > Integer.MAX_VALUE/10 || (base == Integer.MAX_VALUE/10 && s.charAt(index)-'0' > 7)) {
                if (sign < 0) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }

            base = base * 10 + (s.charAt(index)-'0');
            index++;
        }

        return sign * base;
    }

    public static void main(String args[]) {
        System.out.println("Input: \"42\"\nOutput: " + StringToInteger.myAtoi("42"));

        System.out.println("Input: \"   -42\"\nOutput: " + StringToInteger.myAtoi("   -42"));

        System.out.println("Input: \"4193 with words\"\nOutput: " + StringToInteger.myAtoi("4193 with words"));

        System.out.println("Input: \"4193 with 8 words\"\nOutput: " + StringToInteger.myAtoi("4193 with 8 words"));

        System.out.println("Input: \"words and 987\"\nOutput: " + StringToInteger.myAtoi("words and 987"));
    }
}
