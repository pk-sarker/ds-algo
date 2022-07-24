package pattern.BitManipulation;

public class BitManipulation2 {

    public static int reverseSign(int num) {
        int twosComplement = ~num;
        twosComplement = twosComplement | 1;
        return twosComplement;
    }

    public static int multiply(int num, int n) {
        int nShifts = (int) (n/2);
        int rem = n - nShifts*2;
        int result = num << nShifts;

        while (rem>0) {
            result += num;
            rem--;
        }

        return result;
    }

    public static int divide(int dividend, int divisor) {
        int res = divisor;
        int count = 0;
        while (dividend-res >= divisor) {
            count++;
            res =  res << 1;
        }
        int quotient = count*2;
        if (res > dividend) {
            quotient--;
        }
        return quotient;
    }

    public static int sum(int num1, int num2) {
        while (num2 != 0) {
            int carryOver = (num1 & num2) << 1;
            num1 ^= num2; // num1 = num1 + num2, without the carry over.
            num2 = carryOver; // set carry to num2, and sum with num1 which is the sum of num1 and num2 without carry
        }
        return num1;
    }

    public static void main(String args[]) {
        System.out.println("Inverse Sign: +15 to " + BitManipulation2.reverseSign(15));
        System.out.println("Inverse Sign: -15 to " + BitManipulation2.reverseSign(-15));
        System.out.println("Multiply 16 by 2 = " + BitManipulation2.multiply(16, 2));
        System.out.println("Multiply 10 by 5 = " + BitManipulation2.multiply(10, 5));
        System.out.println("Divide 10 by 5 = " + BitManipulation2.divide(10, 5));
        System.out.println("Divide 13 by 5 = " + BitManipulation2.divide(13, 5));
        System.out.println("Divide 16 by 5 = " + BitManipulation2.divide(16, 5));
        System.out.println("Divide 21 by 5 = " + BitManipulation2.divide(21, 5));
        System.out.println("Sum 5+3=" + BitManipulation2.sum(5,3));
    }
}
