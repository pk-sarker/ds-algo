package pattern.SubArray;


/**
 * Given an array of positive integers nums and a positive integer target, return the minimal
 * length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is
 * greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 */
public class MinimumSizeSubarraySum {
    public static int minSubArrayLen(int s, int[] nums) {
        int sum = 0, minLen = Integer.MAX_VALUE;
        int start = 0;
        for(int end=0; end<nums.length;end++) {
            sum += nums[end];

            while(sum>= s) {
                minLen = Math.min(minLen, end-start+1);
                sum -= nums[start];
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0: minLen;
    }

    public static void main(String args[]) {
        System.out.println(MinimumSizeSubarraySum.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
        System.out.println(MinimumSizeSubarraySum.minSubArrayLen(4, new int[]{1,4,4}));
    }
}
