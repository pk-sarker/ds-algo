package ds.Array;

import java.util.Arrays;

/**
 * Given an array nums of non-negative integers, return an array consisting of all the even elements of nums,
 * followed by all the odd elements of nums.
 *
 * You may return any answer array that satisfies this condition.
 *
 * Input: nums = [3,1,2,4]
 * Output: [2,4,3,1]
 * The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 */
public class SortArrayByParity {

    public static int[] sortArrayByParity(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {

            if (nums[i] % 2 > nums[j] % 2) {
                // if nums[i] is odd and nums[j] is even
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }

            if (nums[i] % 2 == 0) {
                i++;
            }
            if (nums[j] % 2 == 1) {
                j--;
            }
        }

        return nums;
    }
    public static void main(String args[]) {
        System.out.println(Arrays.toString(SortArrayByParity.sortArrayByParity(new int[]{6,3,1,2,5,4})));
    }
}
