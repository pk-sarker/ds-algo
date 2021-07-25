package math;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 * Input: x = 123
 * Output: 321
 *
 * Input: x = -123
 * Output: -321
 *
 * Input: x = 120
 * Output: 21
 *
 * Input: x = 0
 * Output: 0
 */
public class ReverseInteger {
    public static int reverse(int x) {
        int reversed = 0;
        int imax = Integer.MAX_VALUE/10, imin = Integer.MIN_VALUE/10;
        while (x != 0) {
            int lastDigit  = x % 10;
            x = x/10;

            // checking if the reversed number cross 32-bit integer value, which is -2147483648 to 2147483647
            // here checking positive limit, 2147483647
            if (reversed > imax || (reversed == imax && lastDigit > 7)) {
                return 0;
            }
            // here checking negative limit, -2147483648
            if (reversed < imin || (reversed == imin && lastDigit < -8)) {
                return 0;
            }

            reversed = reversed * 10 + lastDigit;
        }

        return reversed;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: 123 \nOutput: " + ReverseInteger.reverse(123));
        System.out.println("\nInput: -123 \nOutput: " + ReverseInteger.reverse(-123));
        System.out.println("\nInput: 130 \nOutput: " + ReverseInteger.reverse(130));
        System.out.println("\nInput: 1000 \nOutput: " + ReverseInteger.reverse(1000));
        // 2147483647 -
        // 7463847412
        System.out.println("\nInput: "+Integer.MAX_VALUE+ "\nOutput: " + ReverseInteger.reverse(Integer.MAX_VALUE));
        System.out.println("\nInput: "+Integer.MIN_VALUE+ "\nOutput: " + ReverseInteger.reverse(Integer.MIN_VALUE));
    }
}
