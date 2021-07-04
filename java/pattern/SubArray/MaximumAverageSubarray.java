package pattern.SubArray;

/**
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is equal to k that has the maximum average
 * value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
 *
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 *
 * Input: nums = [5], k = 1
 * Output: 5.00000
 */
public class MaximumAverageSubarray {




    public static double findMaxAverage(int[] nums, int k) {
        double sum=0;
        for(int i=0;i<k;i++) {
            sum+=nums[i];
        }

        double res=sum;
        for(int i=k;i<nums.length;i++){
            sum += nums[i]-nums[i-k];
            res=Math.max(res,sum);
        }
        return res/k;
    }

    public static void main(String args[]) {
        System.out.println(MaximumAverageSubarray.findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4));
        System.out.println(MaximumAverageSubarray.findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4));
    }
}
