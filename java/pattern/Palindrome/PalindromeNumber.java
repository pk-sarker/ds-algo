package pattern.Palindrome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer x, return true if x is palindrome integer.
 *
 * An integer is a palindrome when it reads the same backward as forward.
 * For example, 121 is palindrome while 123 is not.
 *
 * Input: x = 121
 * Output: true
 *
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 *
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 *
 * Input: x = -101
 * Output: false
 */
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
        return orgX == revertedNumber;
    }

    public static int birthdayCakeCandles(List<Integer> candles) {
        // Write your code here
        Map<Integer, Integer> hashMap = new HashMap<>();
        int max = candles.get(0);
        hashMap.put(candles.get(0), 1);
        for(int i=1; i<candles.size(); i++)
        {
            //hashMap.computeIfAbsent(candles.get(i), 0)++;
            if (hashMap.containsKey(candles.get(i))) {
                hashMap.put(candles.get(i), hashMap.get(candles.get(i))+1);
            } else {
                hashMap.put(candles.get(i), 1);
            }
            max = Math.max(max, candles.get(i));
        }
        return hashMap.get(max);
    }
    public static void main(String args[]) {
        System.out.println(PalindromeNumber.isPalindrome(121));
        System.out.println(PalindromeNumber.isPalindrome(1001));
        System.out.println(PalindromeNumber.isPalindrome(10301));
        List<Integer> candles = new ArrayList<Integer>();
        candles.add(3);
        candles.add(2);
        candles.add(1);
        candles.add(3);
        System.out.println(PalindromeNumber.birthdayCakeCandles(candles));
    }
}
