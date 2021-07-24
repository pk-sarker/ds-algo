package ds.Hash;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums and an integer k, return true if there are two distinct
 * indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 *
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 *
 */
public class ContainsDuplicateII {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> numSet = new HashSet<>();
        for(int i=0;i<nums.length;i++) {
            if (numSet.contains(nums[i])) {
                return true;
            }

            numSet.add(nums[i]);
            if (numSet.size() > k) {
                numSet.remove(nums[i-k]);
            }
        }

        return false;
    }

    public static void main(String args[]) {
        System.out.println(ContainsDuplicateII.containsNearbyDuplicate(new int[]{1,2,3,1}, 3));
        System.out.println(ContainsDuplicateII.containsNearbyDuplicate(new int[]{1,2,3,4,5}, 1));
        System.out.println(ContainsDuplicateII.containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
    }
}
