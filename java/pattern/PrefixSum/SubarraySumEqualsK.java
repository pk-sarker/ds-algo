package pattern.PrefixSum;

import java.util.HashMap;

/**
 * Given an array of integers nums and an integer k, return the total number of
 * subarrays whose sum equals to k.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */
public class SubarraySumEqualsK {
    /**
     if there exists consecutive numbers whose sum is k then
     sum_i - sum_j = k, i>j
     => -sum_j = k - sum_i
     => sum_j = sum_i - k

     so store all the consecutived sums in hashmap, sum will be the key
     and for each new sum check if there exists a key with sum-k

     */
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);
        for(int i=0;i<nums.length;i++) {
            currentSum += nums[i];
            if (prefixSum.containsKey(currentSum-k)) {
                count += prefixSum.get(currentSum-k);
            }
            // there may be same previous sum, like, 2,3,7,-10, preefixsum = [2,5,12,2]
            prefixSum.put(currentSum, prefixSum.getOrDefault(currentSum, 0)+1);
        }
        return count;
    }

    public static void main(String args[]) {
        System.out.println(SubarraySumEqualsK.subarraySum(new int[]{1,2,3}, 3));
        System.out.println(SubarraySumEqualsK.subarraySum(new int[]{1,1,1}, 2));
    }
}
