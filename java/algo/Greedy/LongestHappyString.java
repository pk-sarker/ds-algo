package algo.Greedy;

/**
 * Problem:
 * A string s is called happy if it satisfies the following conditions:
 *
 *  - s only contains the letters 'a', 'b', and 'c'.
 *  - s does not contain any of "aaa", "bbb", or "ccc" as a substring.
 *  - s contains at most a occurrences of the letter 'a'.
 *  - s contains at most b occurrences of the letter 'b'.
 *  - s contains at most c occurrences of the letter 'c'.
 *
 * Given three integers a, b, and c, return the longest possible happy string.
 * If there are multiple longest happy strings, return any of them. If there is
 * no such string, return the empty string "".
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * Solution:
 * We consider the numbers are in increasing order, a >= b >= c.
 * So we can't use more than 2 of the same character, that means at each
 * selection we can use maximum 2 of the same character. If we pick 2 from
 * "a" and remaining number of "a" is more than number of "b" then we take 1 "b"
 * and repeat the step.
 *
 * For example, a=5,b=2,c=1
 * 1st Iteration
 *      pick 2 "a"s, remaining "a" = 5-2 = 3
 *      pick 1 "b" as remaining "a" >= number of "b", 3>= 2
 *      Call next iteration with (remaining a, remaining b, remaining c) = (3,1,1)
 *
 * 2nd Iteration
 *      pick 2 "a"s, remaining "a" = 3-2 = 1
 *      pick 1 "b" as remaining "a" >= number of "b", 1>= 1
 *      Call next iteration with (remaining a, remaining b, remaining c) = (1,0,1)
 *
 * 2nd Iteration
 *      sort a,b,c => now (a,c,b)
 *      and repeat the same process
 *
 */
public class LongestHappyString {

    public String longestString(int number_of_a, int number_of_b, int number_of_c, String aa, String bb, String cc) {
        if (number_of_b> number_of_a) {
            return longestString(number_of_b, number_of_a, number_of_c, bb, aa, cc);
        }
        if (number_of_c>number_of_b) {
            return longestString(number_of_a, number_of_c, number_of_b, aa, cc, bb);
        }

        if (number_of_b == 0) {
            return aa.repeat(Math.min(2, number_of_a));
        }

        int a_used = Math.min(2, number_of_a), b_used = 0;
        if ((number_of_a - a_used) >= number_of_b) {
            b_used = 1;
        }
        return aa.repeat(a_used) + bb.repeat(b_used) + longestString(number_of_a- a_used, number_of_b - b_used, number_of_c, aa, bb, cc);
    }

    public String longestDiverseString(int a, int b, int c) {
        return longestString(a, b, c, "a", "b", "c");
    }

    public static void main(String args[]) {
        System.out.println(new LongestHappyString().longestDiverseString(7,3,2));
    }
}

