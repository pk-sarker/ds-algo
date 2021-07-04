package ds.Array;

import java.util.Arrays;

/**
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 *
 * Return the running sum of nums.
 *
 * Input: nums = [1,2,3,4]
 * Output: [1,3,6,10]
 * Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
 */
public class RunningSum {
    public static int[] runningSum(int[] nums) {
        int prevSum = 0;
        for(int i=0;i<nums.length;i++) {
            nums[i] = nums[i]+prevSum;
            prevSum = nums[i];
        }
        return nums;
    }

    public static void main(String args[]) {
        System.out.println(Arrays.toString(RunningSum.runningSum(new int[]{1,2,3,4,5,6})));
    }
}
