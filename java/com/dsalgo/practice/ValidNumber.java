package com.dsalgo.practice;

/**
 * A valid number can be split up into these components (in order):
 *
 * A decimal number or an integer.
 * (Optional) An 'e' or 'E', followed by an integer.
 * A decimal number can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One of the following formats:
 * At least one digit, followed by a dot '.'.
 * At least one digit, followed by a dot '.', followed by at least one digit.
 * A dot '.', followed by at least one digit.
 * An integer can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * At least one digit.
 * For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10",
 * "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"],
 * while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3",
 * "95a54e53"].
 *
 * Given a string s, return true if s is a valid number.
 *
 */
public class ValidNumber {

    public static boolean isNumber(String s) {
        int i=0;
        int maxLen = s.length()-1;
        boolean dotFound = false;
        boolean exponentFound = false;
        boolean digitFound = false;
        while(i<=maxLen) {
            char c = s.charAt(i);
            if ((c - '0' >= 0 && c - '0' <= 9)) {
                digitFound = true;
            } else if (c == '+' || c == '-') {
                if (i > 0 && (s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E')) {
                    return false;
                }
            } else if (c == '.') {
                if (dotFound || exponentFound) {
                    return false;
                }
                dotFound = true;
            } else if (c == 'e' || c == 'E') {
                if (exponentFound || !digitFound) {
                    return false;
                }
                exponentFound = true;
                digitFound = false; // after exponent atleast one digit is required
            } else {
                return false;
            }
            i++;
        }
        return digitFound;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: \"0089\"\nOutput: " + ValidNumber.isNumber("0089"));
        System.out.println("\nInput: \"-.9\"\nOutput: " + ValidNumber.isNumber("-.9"));
        System.out.println("\nInput: \"-90E3\"\nOutput: " + ValidNumber.isNumber("-90E3"));
        System.out.println("\nInput: \"+10E-3\"\nOutput: " + ValidNumber.isNumber("+10E-3"));
        System.out.println("\nInput: \"e7\"\nOutput: " + ValidNumber.isNumber("e7"));
        System.out.println("\nInput: \"7e\"\nOutput: " + ValidNumber.isNumber("7e"));

        System.out.println("\nInput: \"10E-3\"\nOutput: " + ValidNumber.isNumber("10E-3"));
        System.out.println("\nInput: \"41\"\nOutput: " + ValidNumber.isNumber("41"));
        System.out.println("\nInput: \"--41\"\nOutput: " + ValidNumber.isNumber("--41"));
        System.out.println("\nInput: \"abc\"\nOutput: " + ValidNumber.isNumber("abc"));
        System.out.println("\nInput: \"1b2\"\nOutput: " + ValidNumber.isNumber("1b2"));

        System.out.println("\nInput: \"15e\"\nOutput: " + ValidNumber.isNumber("15e"));
    }
}
