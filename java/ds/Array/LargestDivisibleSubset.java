package ds.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct positive integers nums, return the largest subset answer
 * such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
 *
 *  - answer[i] % answer[j] == 0, or
 *  - answer[j] % answer[i] == 0
 * If there are multiple solutions, return any of them.
 *
 * Input: nums = [1,2,3]
 * Output: [1,2]
 * Explanation: [1,3] is also accepted.
 *
 * Input: nums = [1,2,4,8]
 * Output: [1,2,4,8]
 *
 * Solution:
 * if x % y = 0 that means x is divisible by y, and x > y
 * if y % x = 0 that means y is divisible by x, and y > x
 *
 * lets try to understand how the solution will look like, for example
 * {2,4,8,16} {1,2,6}, {2,4}, {2,6}, {2, 32, 256}
 * {1,3}, {1,3, 6}, {1,3, 9}, {1,3,9,18}, {1,3,6,12}
 * So If we have a subset which is same as the question is asking then
 * we can extend that if a new number, num is divisible by the maximum
 * number in the subset, or the smallest number in the subset is divisible
 * by the new number.
 *
 * We can sort the given list
 *
 */
public class LargestDivisibleSubset {

    /**
     * Complexity: O()
     * @param nums
     * @return
     */
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        // Test case with empty set.
        int n = nums.length;
        if (n == 0) return new ArrayList();

        // Container to keep the largest divisible subset
        //   that ends with each of the nums.
        List<ArrayList> divisibleSubsetList = new ArrayList();
        for (int num : nums) {
            divisibleSubsetList.add(new ArrayList());
        }

        /* Sort the original list in ascending order. */
        Arrays.sort(nums);

        /* Calculate all the values of EDS(X_i) */
        for (int i = 0; i < n; ++i) {
            List<Integer> maxSubset = new ArrayList();

            // Find the largest divisible subset of previous elements.
            for (int k = 0; k < i; ++k) {
                if (nums[i] % nums[k] == 0 && maxSubset.size() < divisibleSubsetList.get(k).size()) {
                    maxSubset = divisibleSubsetList.get(k);
                }
            }

            // Extend the found subset with the element itself.
            divisibleSubsetList.get(i).addAll(maxSubset);
            divisibleSubsetList.get(i).add(nums[i]);
        }
        System.out.println(divisibleSubsetList.toString());
        /* Find the largest of EDS values  */
        List<Integer> ret = new ArrayList();
        for (int i = 0; i < n; ++i) {
            if (ret.size() < divisibleSubsetList.get(i).size()) {
                ret = divisibleSubsetList.get(i);
            }
        }
        return ret;
    }

    public static void main(String args[]) {
        System.out.println("Input: [1,3,8,2,6,16] \nOutput: "+ LargestDivisibleSubset.largestDivisibleSubset(new int[]{1,3,8,2,6,16}).toString());
    }
}
