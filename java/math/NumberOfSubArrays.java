package math;

/**
 * Given an integer array nums, return the number of subarrays filled with 0.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * consecutive sub array count = end - start + 1
 */
public class NumberOfSubArrays {

    public static long zeroFilledSubarray(int[] nums) {
        // 0,0,0,2,0,0
        // 1
        // +1+1 = 2
        // +1 +1 +1  = 3
        // 1 =
        // 1 + 1 = 2
        long count = 0;
        int p1 = 0;
        for (int p2=0; p2<nums.length; p2++) {
            // if current number is not '0' then we
            // are reseting the start of 0 subarry to the next position
            // Setting next start is not confirmed but a potential one
            // [1,3,0,0,3,4]
            // p2 =1, nums[p2] = 3 != 0, p1 = the next position, p2+1=2
            if (nums[p2] != 0) {
                p1 = p2+1;
            }
            count += (p2-p1+1);
            // here for consecutive 0s, for each new 0, it will create
            // one subarry for each previous zero in the consecutive sequence.
            // like [0,0,0], on the 3rd 0, it will create one subarray with first 0(00), another with 2nd 0(00), and another one wth both of them, (000)
            // More clear with different number, [1,2,3], on 3, [1,3],[2,3],[1,2,3]
        }
        return count;
    }
    public static void main(String args[]) {
        System.out.println("Input: [1,3,0,0,2,0,0,4] \nOutput: "+NumberOfSubArrays.zeroFilledSubarray(new int[]{1,3,0,0,2,0,0,4}));
        System.out.println("\nInput: [0,0,0,2,0,0,3,0] \nOutput: "+NumberOfSubArrays.zeroFilledSubarray(new int[]{0,0,0,2,0,0,3,0}));
    }
}
