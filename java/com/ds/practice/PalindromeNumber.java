package com.ds.practice;

public class PalindromeNumber {

    public static boolean isPalindrome(int x) {
        /**
         * If x is negative then its not a palindrome number, because minus sign will make the difference
         * If last digit is 0 and the number is not 0 then it won't be a palindrome number
         */
        if (x < 0 || (x % 10 == 0 && x != 0) ) {
            return false;
        }
        int revertedNumber = 0;
        int orgX = x;
        while(x > 0) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x = x / 10;
        }
        System.out.println("Org X: " + orgX + " x: " + x + "  revertedNumber: " + revertedNumber);
        return orgX == revertedNumber;
    }

    public static void main(String args[]) {
        System.out.println(PalindromeNumber.isPalindrome(121));
        System.out.println(PalindromeNumber.isPalindrome(1001));
        System.out.println(PalindromeNumber.isPalindrome(10301));
    }
}
