package math;

import java.util.ArrayList;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication,
 * division, and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 *
 * Note: Assume we are dealing with an environment that could only store integers within the
 * 32-bit signed integer range: [−231, 231 − 1]. For this problem, assume that your function
 * returns 231 − 1 when the division result overflows.
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = truncate(3.33333..) = 3.
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = truncate(-2.33333..) = -2.
 *
 * Input: dividend = 0, divisor = 1
 * Output: 0
 *
 * Input: dividend = 1, divisor = 1
 * Output: 1
 */
public class DivideTwoIntegers {
    // 32-bit integer range 2^-31 to 2^32-1
    // half of min range 2^-31/2 = 1073741824
    private static int HALF_INT_MIN = -1073741824;

    public int divide(int dividend, int divisor) {

        // Special case: overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        /* We need to convert both numbers to negatives.
         * Also, we count the number of negatives signs. */
        int negatives = 2;
        if (dividend > 0) {
            negatives--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negatives--;
            divisor = -divisor;
        }

        ArrayList<Integer> doubles = new ArrayList<>();
        ArrayList<Integer> powersOfTwo = new ArrayList<>();

        /* Nothing too exciting here, we're just making a list of doubles of 1 and
         * the divisor. This is pretty much the same as Approach 2, except we're
         * actually storing the values this time. */
        int powerOfTwo = -1;
        while (divisor >= dividend) {
            doubles.add(divisor);
            powersOfTwo.add(powerOfTwo);
            // Prevent needless overflows from occurring...
            if (divisor < HALF_INT_MIN) {
                break;
            }
            divisor += divisor;
            powerOfTwo += powerOfTwo;
        }

        int quotient = 0;
        /* Go from largest double to smallest, checking if the current double fits.
         * into the remainder of the dividend. */
        for (int i = doubles.size() - 1; i >= 0; i--) {
            if (doubles.get(i) >= dividend) {
                // If it does fit, add the current powerOfTwo to the quotient.
                quotient += powersOfTwo.get(i);
                // Update dividend to take into account the bit we've now removed.
                dividend -= doubles.get(i);
            }
        }

        /* If there was originally one negative sign, then
         * the quotient remains negative. Otherwise, switch
         * it to positive. */
        if (negatives != 1) {
            return -quotient;
        }
        return quotient;
    }
    public static void main(String args[]) {
        DivideTwoIntegers obj = new DivideTwoIntegers();
        System.out.println("93706/157 = " + obj.divide(93706, 157));

    }
}
