package math;

/**
 *
 */
public class Base7 {
    public static String convertToBase7(int num) {
        if (num < 0) {
            return "-" + convertToBase7(-num);
        }
        if (num < 7) {
            return Integer.toString(num);
        }
        return convertToBase7(num / 7) + Integer.toString(num % 7);
    }


    public static void main(String args[]) {
        System.out.println("Base 7 of 100 => "+Base7.convertToBase7(100));
        System.out.println("Base 7 of 200 => "+Base7.convertToBase7(200));
        System.out.println("Base 7 of 300 => "+Base7.convertToBase7(300));
    }
}
