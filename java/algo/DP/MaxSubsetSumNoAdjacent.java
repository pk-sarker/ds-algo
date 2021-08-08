package algo.DP;

/**
 *
 * Write a function that takes in an array of positive integers and returns the
 * maximum sum of non-adjacent elements in the array.
 * If the input array is empty, the function should return 0.
 *
 * Input:  = [75, 105, 120, 75, 90, 135]
 * Output: 330 // 75 + 120 + 135
 */
public class MaxSubsetSumNoAdjacent {

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param nums
     * @return
     */
    public static int maxSubsetSumNoAdjacent (int[] nums) {
        // check invalid input
        if (nums.length == 0) {
            return 0;
        } else if(nums.length == 1) {
            return nums[0];
        }

        // max sum of non-adjacent elements till i-th index
        int[] maxSums = nums.clone();
        // initialization
        // for position 0, we set nums[0] to be max sum
        // for position 1, we can consider maximum of nums[0] or nums[1]
        maxSums[1] = Math.max(nums[0], nums[1]);
        for(int i=2; i<nums.length;i++) {
            maxSums[i] = Math.max(maxSums[i-2]+nums[i], maxSums[i-1]);
        }

        return maxSums[nums.length-1];
    }

    /**
     * Optimized space by keeping last to max sums in two variables
     */
    public static int maxSubsetSumNoAdjacentSpaceOpt (int[] nums) {
        // check invalid input
        if (nums.length == 0) {
            return 0;
        } else if(nums.length == 1) {
            return nums[0];
        }

        int prevPrev = nums[0];
        int prev = Math.max(nums[0], nums[1]);
        for(int i=2; i<nums.length;i++) {
            int currentMax = Math.max(prevPrev+nums[i], prev);
            prevPrev = prev;
            prev = currentMax;
        }

        return prev;
    }
    public static void main(String args[]) {
        System.out.println(MaxSubsetSumNoAdjacent.maxSubsetSumNoAdjacent(new int[]{75, 105, 120, 75, 90, 135}));
        System.out.println(MaxSubsetSumNoAdjacent.maxSubsetSumNoAdjacentSpaceOpt(new int[]{75, 105, 120, 75, 90, 135}));
    }
}
