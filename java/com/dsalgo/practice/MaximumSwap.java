package com.dsalgo.practice;

/**
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 *
 * Return the maximum valued number you can get.
 *
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 *
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 */
public class MaximumSwap {
    public int maximumSwap(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        int n = digits.length;
        int[] digitPos = new int[10];
        for(int i=0; i<n; i++) {
            digitPos[digits[i] - '0'] = i;
        }
        for(int i=0; i<n; i++) {
            for(int j=9;j>digits[i]-'0';j--) {
                if (digitPos[j] > i ) {
                    char t = digits[i];
                    digits[i] = digits[digitPos[j]];
                    digits[digitPos[j]] = t;
                    return Integer.valueOf(new String(digits));
                }
            }
        }
        return num;
    }
    public static void main(String args[]) {
        MaximumSwap obj = new MaximumSwap();

        System.out.println("1234 > "+obj.maximumSwap(1234));
        System.out.println("991567 > "+obj.maximumSwap(991567));
        System.out.println("991545 > "+obj.maximumSwap(991545));


        System.out.println("11115 > "+obj.maximumSwap(11115));
        System.out.println("10909091 > "+obj.maximumSwap(10909091));
    }
}
