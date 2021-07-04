package pattern.SubArray;

import java.util.HashMap;

/**
 * Given an integer array nums and an integer k, return true if nums has a
 * continuous subarray of size at least two whose elements sum up to a multiple of k,
 * or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k.
 * 0 is always a multiple of k.
 *
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 *
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 *
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 * Solution:
 * (a+(n*x)) % x = a%x
 */
public class ContinuousSubarraySum {
    public static boolean checkSubarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> leftOver = new HashMap<>();
        leftOver.put(0, -1);
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];

            sum = sum % k;
            if (leftOver.containsKey(sum)) {
                if (i-leftOver.get(sum) > 1) {
                    return true;
                }
            }
            leftOver.put(sum,i);
        }
        return false;
    }
    public static void main(String args[]) {
        System.out.println("Input: [23,2,4,6,7], k=6 \nOutput: " + ContinuousSubarraySum.checkSubarraySum(new int[]{23,2,4,6,7}, 6));

        System.out.println("Input: [23,2,6,4,7], k=6 \nOutput: " + ContinuousSubarraySum.checkSubarraySum(new int[]{23,2,6,4,7}, 6));
    }
}
