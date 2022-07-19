package math;

import java.util.Arrays;

/**
 * You are given a large integer represented as an integer array digits, where each
 * digits[i] is the ith digit of the integer. The digits are ordered from most
 * significant to least significant in left-to-right order. The large integer
 * does not contain any leading 0's.
 *
 * Increment the large integer by one and return the resulting array of digits.
 *
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 *
 * Input: digits = [1,9,9]
 * Output: [2,0,0]
 *
 * Input: digits = [9,9,9]
 * Output: [1,0,0,0]
 */
public class PlusOne {

    public static int[] plusOne(int[] digits) {
        int numberLen = digits.length;
        for (int index = numberLen-1; index >= 0; index--) {

            if (digits[index] == 9) {
                digits[index] = 0;
                continue;
            } else {
                digits[index] += 1;
                return digits;
            }
        }

        digits = new int[numberLen + 1];
        digits[0] = 1;
        return digits;
    }
    public static void main(String args[]) {
        System.out.println("Input: [1,2,3]\nOutput: " + Arrays.toString(PlusOne.plusOne(new int[]{1,2,3})));
        System.out.println("\nInput: [1,9,9]\nOutput: " + Arrays.toString(PlusOne.plusOne(new int[]{1,9,9})));
        System.out.println("\nInput: [9,9,9]\nOutput: " + Arrays.toString(PlusOne.plusOne(new int[]{9,9,9})));
    }
}
