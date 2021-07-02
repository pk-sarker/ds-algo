package ds.Array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * Input: nums = [2,4,3,11,8,5,15], target = 9
 * Output: [1,5]
 * Output: Because nums[1] + nums[5] == 9, we return [1, 5].
 *
 */
public class SumOfTwo {
    /**
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numMap = new HashMap<>();
        int n = nums.length;
        for(int i=0; i<n; i++) {
            int diff = target - nums[i];
            if (numMap.containsKey(diff)) {
                return new int[]{i, numMap.get(diff)};
            } else {
                numMap.put(nums[i], i);
            }
        }
        return new int[]{};
    }

    /**
     * Return the numbers that sum to the target
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(1)
     *
     * Sort the array, O (n log n), then do a binary search
     * to find the combination
     */
    public static int[] twoSumSpaceOpt(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length-1;
        while(left < right) {
            int currentSum = nums[left] + nums[right];
            if (currentSum == target) {
                return new int[]{nums[left], nums[right]};
            }
            if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }

    public static void main(String args[]) {
        System.out.println("Input: [2,4,3,11,8,5,15], target=9 \nOutput: " + Arrays.toString(SumOfTwo.twoSumBruteForce(new int[]{2,4,3,11,8,5,15}, 9)));
        System.out.println("Input: [2,4,3,11,8,5,15], target=9 \nOutput: " + Arrays.toString(SumOfTwo.twoSum(new int[]{2,4,3,11,8,5,15}, 9)));
        System.out.println("Input: [2,4,3,11,8,5,15], target=9 \nOutput: " + Arrays.toString(SumOfTwo.twoSumSpaceOpt(new int[]{2,4,3,11,8,5,15}, 9)));

        System.out.println("Input: [11,15,7,-6,1], target=9 \nOutput: " + Arrays.toString(SumOfTwo.twoSum(new int[]{11,15,7,-6,1}, 9)));
        System.out.println("Input: [11,15,7,-6,1], target=9 \nOutput: " + Arrays.toString(SumOfTwo.twoSumSpaceOpt(new int[]{11,15,7,-6,1}, 9)));
    }
}
