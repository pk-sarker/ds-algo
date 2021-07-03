package ds.Array;

/**
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by
 * modifying at most one element.
 *
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
 *
 */
public class NonDecreasingArray {

    public static boolean checkPossibility(int[] nums) {

        int index = -1;
        for(int i=0; i<nums.length-1;i++) {
            if (nums[i] > nums[i+1]) {
                if (index == -1) {
                    index = i;
                } else {
                    return false;
                }
            }
        }
        if (index == -1 || index == 0 || index == nums.length-2 || nums[index+2] >= nums[index] || nums[index+1] >= nums[index-1]) {
            return true;
        }

        return false;
    }

    public static void main(String args[]) {
        System.out.println("Input: [4,2,3] \nOutput: "+NonDecreasingArray.checkPossibility(new int[]{4,2,3}));

        System.out.println("Input: [2,3,4,3] \nOutput: "+NonDecreasingArray.checkPossibility(new int[]{2,3,4,3}));
    }
}
