package ds.Array;

import java.util.Arrays;

/**
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of
 * each number sorted in non-decreasing order.
 *
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 *
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 * Input: nums = [-3,-2,-1,0,1,2,3]
 * Output: [0,1,1,4,4,9,9]
 *
 * Input: nums = [-3,-2,-1,0,1,2]
 * Output: [0,1,1,4,4,9]
 */
public class SquaresOfASortedArray {

    /**
     *
     */
    public static int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];

        int left = 0, right = nums.length - 1, resCount = nums.length-1, lsq = 0, rsq = 0;

        while(left < right) {
            lsq = nums[left] * nums[left];
            rsq = nums[right] *  nums[right];
            if ( lsq > rsq) {
                result[resCount] = lsq;
                left++;
            } else {
                result[resCount] = rsq;
                right--;
            }
            resCount--;
        }

        return result;
    }
    public static void main(String args[]) {
        System.out.println("\nInput: [-4,-1,0,3,10] \nOutput: " + Arrays.toString(SquaresOfASortedArray.sortedSquares(new  int[]{-4,-1,0,3,10})));
        System.out.println("\nInput: [-7,-3,2,3,11] \nOutput: " + Arrays.toString(SquaresOfASortedArray.sortedSquares(new  int[]{-7,-3,2,3,11})));
        System.out.println("\nInput: [-3,-2,-1,0,1,2] \nOutput: " + Arrays.toString(SquaresOfASortedArray.sortedSquares(new  int[]{-3,-2,-1,0,1,2})));
    }
}
