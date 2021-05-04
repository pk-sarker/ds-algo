package com.dsalgo.practice;

public class BitManipulation1 {
    private static boolean isEven(int n) {
        if ((n & 1) != 0) {
            return false;
        }

        return true;
    }

    private static boolean checkOppositeSign(int m, int n) {
        if ((m ^ n) < 0) {
            return true;
        }
        return false;
    }

    private static void swap(int a, int b) {
        System.out.println("Before swap: a: " + a + " b: " + b);
        a = a ^ b; // all opposite bits will be 1
        b = a ^ b; // bits that was 1 in b and 0 in a will be 0 then the results will be same bits in a
        a = a ^ b; // now b contains the bits of original a, xor with a will be the bits in b.
        System.out.println("After swap: a: " + a + " b: " + b);
    }
    public static void main(String args[]) {
        System.out.println("21 is even ? " + BitManipulation1.isEven(21));
        System.out.println("20 is even ? " + BitManipulation1.isEven(20));
        System.out.println("Is 4 & -7 opposite sign  ? " + BitManipulation1.checkOppositeSign(4, -7));
        System.out.println("Is 4 & 7 opposite sign  ? " + BitManipulation1.checkOppositeSign(4, 7));
        BitManipulation1.swap(5,9);
    }
}
