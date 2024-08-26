package pattern.SlidingWindow;

import java.util.ArrayList;
import java.util.List;

/***
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 *
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 *
 * Example 1:
 * Input: low = 100, high = 300
 * Output: [123,234]
 *
 * Example 2:
 * Input: low = 1000, high = 13000
 * Output: [1234,2345,3456,4567,5678,6789,12345]
 *
 * Solution:
 *
 * All integers that have sequential digits are substrings of the string "123456789".
 * Hence, to generate all such integers of a given length, just move the window of
 * that length along the "123456789" string.
 *
 * The advantage of this method is that it will generate the integers that are
 * already in the sorted order.
 *
 *
 * Time complexity: O(1)\mathcal{O}(1)O(1). The length of the sample string is 9, and the lengths of low and high are between 2 and 9
 */
public class SequentialDigits {

    public List<Integer> sequentialDigits(int low, int high) {
        String sequence = "123456789";
        List<Integer> result = new ArrayList<Integer>();

        int minLen = String.valueOf(low).length();
        int maxLen = String.valueOf(high).length();

        for (int length=minLen; length<maxLen+1; length++) {
            for (int i=0; i< 10-length; i++) {
                int number = Integer.parseInt(sequence.substring(i, i+length));

                if (number >= low && number <= high) {
                    result.add(number);
                }
            }
        }
        return result;
    }

    public static void main(String args[]) {
        SequentialDigits obj = new SequentialDigits();
        List<Integer> res = obj.sequentialDigits(124,1000);
        System.out.println("low=124, high=1000");
        System.out.println(res);

        List<Integer> res1 = obj.sequentialDigits(235,1000);
        System.out.println("low=235, high=1000");
        System.out.println(res1);
    }
}
