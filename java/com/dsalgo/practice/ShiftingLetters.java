package com.dsalgo.practice;

/**
 * We have a string S of lowercase letters, and an integer array shifts.
 *
 * Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
 *
 * For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
 *
 * Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.
 *
 * Return the final string after all such shifts to S are applied.
 *
 * Input: S = "abc", shifts = [3,5,9]
 * Output: "rpl"
 * Explanation:
 * We start with "abc".
 * After shifting the first 1 letters of S by 3, we have "dbc".
 * After shifting the first 2 letters of S by 5, we have "igc".
 * After shifting the first 3 letters of S by 9, we have "rpl", the answer.
 */
public class ShiftingLetters {

    public static String shiftingLetters(String S, int[] shifts) {
        int totalShift = 0;
        for(int shift: shifts) {
            totalShift += shift;
            totalShift = totalShift % 26;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<S.length();i++) {
            sb.append((char)((S.charAt(i) - 'a' + totalShift) %26 + 97));
            totalShift = Math.floorMod(totalShift - shifts[i], 26);
        }

        return sb.toString();
    }
    public static void main(String args[]) {
        System.out.println("\nInput: s=\"abc\" shifts=[3,5,9]\nOutput: " + ShiftingLetters.shiftingLetters("abc", new int[]{3,5,9}));
    }
}
