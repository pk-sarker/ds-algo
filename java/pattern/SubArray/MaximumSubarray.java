package pattern.SubArray;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 */
public class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n<=0) {
            return 0;
        }
        if (n==1) {
            return nums[0];
        }

        int currentSum = nums[0], maxSum = nums[0];
        for(int i=1;i<n;i++) {
            currentSum = Math.max(nums[i], currentSum+nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;

    }
    public static void main(String args[]) {
        System.out.println(MaximumSubarray.maxSubArray(new int[]{1,12,-5,-6,50,3}));
        System.out.println(MaximumSubarray.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
