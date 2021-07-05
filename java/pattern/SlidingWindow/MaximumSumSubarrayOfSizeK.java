package pattern.SlidingWindow;

/**
 * Given an array of positive numbers and a positive number ‘k,’ find the maximum sum of any
 * contiguous subarray of size ‘k’.
 *
 * Input: [2, 1, 5, 1, 3, 2], k=3
 * Output: 9
 * Explanation: Subarray with maximum sum is [5, 1, 3].
 *
 * Input: [2, 3, 4, 1, 5], k=2
 * Output: 7
 * Explanation: Subarray with maximum sum is [3, 4].
 *
 */
public class MaximumSumSubarrayOfSizeK {
    public static int findMaxSumSubArray(int k, int[] nums) {
        int sum =0, maxSum = 0;

        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            if (i < k) {
                maxSum = sum;
            } else {            //sum: 1 3  5  7 9
                sum -= nums[i-k]; // { 1,[2,3],4,5 } ->  5+4-2 = 7
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    public static int findMaxSumSubArrayOptimized(int k, int[] arr) {
        int windowSum = 0, maxSum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            windowSum += arr[windowEnd]; // add the next element
            // slide the window, we don't need to slide if we've not hit the required window size of 'k'
            if (windowEnd >= k - 1) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart]; // subtract the element going out
                windowStart++; // slide the window ahead
            }
        }

        return maxSum;
    }

    public static void main(String args[]) {
        System.out.println(MaximumSumSubarrayOfSizeK.findMaxSumSubArray(2, new int[]{2, 3, 4, 1, 5}));
        System.out.println(MaximumSumSubarrayOfSizeK.findMaxSumSubArrayOptimized(2, new int[]{2, 3, 4, 1, 5}));
    }
}
