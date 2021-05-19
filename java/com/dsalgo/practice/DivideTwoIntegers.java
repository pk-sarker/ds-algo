package com.dsalgo.practice;

public class DivideTwoIntegers {
    // 32-bit integer range 2^-31 to 2^32-1
    // half of min range 2^-31/2 = 1073741824
    private static int HALF_INT_MIN = -1073741824;
    public int divide(int dividend, int divisor) {

        // Special case: overflow.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int negativeCount = 2;
        if (dividend > 0) {
            negativeCount--;
            dividend = -dividend;
        }
        if (divisor > 0) {
            negativeCount--;
            divisor = -divisor;
        }

        int quotient = 0;
        /* Once the divisor is bigger than the current dividend,
         * we can't fit any more copies of the divisor into it. */
        while(divisor >= dividend) { // -5 > -10, we converted both the divisor and dividend to negative

            /* We know it'll fit at least once as divivend >= divisor.
             * Note: We use a negative powerOfTwo as it's possible we might have
             * the case divide(INT_MIN, -1). */
            int powerOfTwo = -1;

            int tempDivisor = divisor;
            int tempQuotient = 1;
            /* Check if double the current value is too big. If not, continue doubling.
             * If it is too big, stop doubling and continue with the next step */
            while(tempDivisor >= HALF_INT_MIN && tempDivisor+tempDivisor>= dividend) {
                tempQuotient += tempQuotient;
                tempDivisor += tempDivisor;
            }
            quotient += tempQuotient;
            // Remove value so far so that we can continue the process with remainder.
            dividend -= tempDivisor;
        }

        if (negativeCount != 1) {
            return -quotient;
        }

        return quotient;
    }
    public static void main(String args[]) {
        DivideTwoIntegers obj = new DivideTwoIntegers();
        System.out.println("93706/157 = " + obj.divide(93706, 157));

    }
}
