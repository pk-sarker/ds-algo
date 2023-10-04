package pattern.SlidingWindow;

/***
 * Longest Nice Subarray
 *
 * You are given an array nums consisting of positive integers.
 *
 * We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different positions in the subarray is equal to 0.
 *
 * Return the length of the longest nice subarray.
 *
 * A subarray is a contiguous part of an array.
 *
 * Note that subarrays of length 1 are always considered nice.
 *
 * Example 1:
 * Input: nums = [1,3,8,48,10]
 * Output: 3
 * Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
 * - 3 AND 8 = 0.
 * - 3 AND 48 = 0.
 * - 8 AND 48 = 0.
 * It can be proven that no longer nice subarray can be obtained, so we return 3.
 *
 * Example 2:
 * Input: nums = [3,1,5,11,13]
 * Output: 1
 * Explanation: The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.
 *
 * Solution:
 * Sliding window is the appropriate approach, tracking used bits as andResult.
 * Use OR to combine bits as we move forward when AND of two numbers 0.
 * If the next number has a conflicting bit (andResult & nums[end]) != 0) then we
 * shrink the window until there are no conflict. Use XOR to remove the bits as
 * the window shrinks.
 */
public class LongestNiceSubarray {
    public static int longestNiceSubarray(int[] nums) {
        int andResult = 0;
        int start = 0, result = 0;

        for (int end=0; end < nums.length; ++end) {

            while((andResult & nums[end]) != 0) {
                andResult ^= nums[start++]; // Bitwise exclusive OR with 0 will be the number in nums[start++]
            }
            andResult |= nums[end]; // Bitwise inclusive OR, keeping all the set bits
            result = Math.max(result, end-start+1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Array [1,3,8,48,10] \n Nice Subarray Length: " + LongestNiceSubarray.longestNiceSubarray(new int[]{1,3,8,48,10}));
        System.out.println("Array [3,1,5,11,13] \n Nice Subarray Length: " + LongestNiceSubarray.longestNiceSubarray(new int[]{3,1,5,11,13}));
    }
}
