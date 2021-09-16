package ds.Array;

import java.util.*;

/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must appear as many times as it shows in both arrays and
 * you may return the result in any order.
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 *
 * Follow up:
 *
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class IntersectionOfTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();

        // count number occurrence
        for(int i=0;i<nums1.length;i++) {
            map1.put(nums1[i], map1.getOrDefault(nums1[i], 0)+1);
        }
        int j = 0;
        for(int i=0;i<nums2.length;i++) {
            if (map1.containsKey(nums2[i]) && map1.get(nums2[i]) > 0) {
                nums1[j++] = nums2[i];
                map1.put(nums2[i], map1.get(nums2[i])-1);
            }
        }

        return Arrays.copyOfRange(nums1, 0, j);
    }

    /**
     * If given arrays are sorted then use two pointer approach
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                ++i;
            } else if (nums1[i] > nums2[j]) {
                ++j;
            } else {
                nums1[k++] = nums1[i];
                ++i;
                ++j;
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }

    /**
     * What if the given array is already sorted? How would you optimize your algorithm?
     *  - We can use either Approach 2 or Approach 3, dropping the sort of course. It will give us linear time and constant memory complexity.
     *
     * What if nums1's size is small compared to nums2's size? Which algorithm is better?
     *  - Approach 1 is a good choice here as we use a hash map for the smaller array.
     *
     * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
     *  - If nums1 fits into the memory, we can use Approach 1 to collect counts for nums1 into a hash map. Then, we can sequentially load and process nums2.
     *
     * If neither of the arrays fit into the memory, we can apply some partial processing strategies:
     *  - Split the numeric range into subranges that fits into the memory. Modify Approach 1 to collect counts only within a given subrange, and call the method multiple times (for each subrange).
     *      Use an external sort for both arrays. Modify Approach 2 to load and process arrays sequentially.
     *
     * @param args
     */


    public static void main(String args[]) {
        IntersectionOfTwoArraysII obj = new IntersectionOfTwoArraysII();
    }
}
