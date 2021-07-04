package pattern.SubArray;

/**
 * Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in
 * ascending order, then the whole array will be sorted in ascending order.
 *
 * Return the shortest such subarray and output its length.
 *
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 *
 * Input: nums = [1,2,3,4]
 * Output: 0
 *
 * Input: nums = [1]
 * Output: 0
 */
public class ShortestUnsortedContinuousSubarray {
    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        if (n==1) {
            return 0;
        }
        int low = 0, high = n - 1;
        // find the first occurrence of ordering issue from left
        while(low < n - 1 && nums[low] <= nums[low+1]) {
            low++;
        }
        // array is already sorted
        if (low == n - 1) {
            return 0;
        }

        // find the first occurrence of ordering issue from right
        while(high > 0 && nums[high] >= nums[high-1]) {
            high--;
        }

        // now low points the the previous number where there is a ordering issue from left and
        // high points to the previous number where there is a ordering issue from right

        // find the maximum and minimum of the subarray where ordering issue is present
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for(int i = low; i<=high; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        // expand window
        while(low>0 && nums[low-1] > min) {
            low--;
        }
        while(high<n-1 && nums[high+1] < max) {
            high++;
        }
        return high - low + 1;
    }

    public static void main(String args[]) {
        System.out.println(ShortestUnsortedContinuousSubarray.findUnsortedSubarray(new int[]{2,6,4,8,10,9,15}));
        System.out.println(ShortestUnsortedContinuousSubarray.findUnsortedSubarray(new int[]{1,2,3,4}));
    }
}
