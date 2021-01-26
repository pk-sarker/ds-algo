package com.ds.practice;

import java.util.Arrays;

/**
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 *
 * Note:
 * - The length of both num1 and num2 is < 5100.
 * - Both num1 and num2 contains only digits 0-9.
 * - Both num1 and num2 does not contain any leading zero.
 * - You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class AddTwoNumbers {

    public static String addStrings(String num1, String num2) {

        if (num2 == null || num2.length() ==0) {
            return num1;
        }

        if (num1 == null || num1.length() ==0) {
            return num2;
        }

        int n1 = num1.length(), n2 = num2.length();
        int carry = 0, sum = 0;
        int len = Math.max(n1, n2)+1;
        char[] result = new char[Math.max(n1, n2)+1];

        for(int i=0; i<Math.max(n1, n2);i++) {
            int a =  0, b = 0;
            if (i<n1) {
                char d1 = num1.charAt(n1-i-1);
                a = Character.getNumericValue(d1);
            }
            if (i<n2) {
                char d1 = num2.charAt(n2-i-1);
                b = Character.getNumericValue(d1);
            }
            sum = carry + a + b;
            carry = sum / 10;
            result[len-1] = (char) (sum % 10 + '0');
            len--;
        }
        boolean isCarry = false;
        if (carry > 0) {
            result[len-1] = (char) (carry + '0');
            isCarry = true;
        }
        return new String(Arrays.copyOfRange(result,isCarry ? 0:1,result.length));
    }


    public static String addStrings2(String num1, String num2) {

        if (num2 == null || num2.length() ==0) {
            return num1;
        }

        if (num1 == null || num1.length() ==0) {
            return num2;
        }

        int n1 = num1.length()-1, n2 = num2.length()-1;
        int carry = 0, sum = 0;
        StringBuilder result = new StringBuilder();

        while(n1 >= 0 || n2 >= 0) {
            int a = n1 >= 0 ? Character.getNumericValue(num1.charAt(n1)):0;
            int b = n2 >= 0 ? Character.getNumericValue(num2.charAt(n2)):0;

            sum = carry + a + b;
            carry = sum / 10;
            result.append(sum % 10);
            n1--;
            n2--;
        }
        if (carry > 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }


    public static void main(String args[]) {

        System.out.println("\nInput: num1=9999, num2=111 \nOutput: " + AddTwoNumbers.addStrings("9999", "111"));
        System.out.println("\nInput: num1=0, num2=0 \nOutput: " + AddTwoNumbers.addStrings("0", "0"));
        System.out.println("\nInput: num1=9999, num2=11 \nOutput: " + AddTwoNumbers.addStrings2("9999", "111"));

    }
}
