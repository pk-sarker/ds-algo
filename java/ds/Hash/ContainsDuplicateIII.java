package ds.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and two integers k and t, return true if there are two distinct
 * indices i and j in the array such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.
 *
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 *
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 *
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 */
public class ContainsDuplicateIII {
    private static long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long)t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m)){
                return true;
            }

            // check the neighbor buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w) {
                return true;
            }

            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w) {
                return true;
            }

            // now bucket m is empty and no almost duplicate in neighbor buckets
            d.put(m, (long)nums[i]);

            if (i >= k) {
                d.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    public static void main(String args[]) {
        System.out.println(ContainsDuplicateIII.containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0));

        System.out.println(ContainsDuplicateIII.containsNearbyAlmostDuplicate(new int[]{1,0,1,1}, 1, 2));

        System.out.println(ContainsDuplicateIII.containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3));
    }
}
