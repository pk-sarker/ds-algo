package algo.Searching.BinarySearch;

public class SplitArrayLargestSum {

    private int result;
    private int m,n;
    public void dfs(int[] nums, int pos, int subArrayCount, int currentSubarraySum, int currentMaxSum) {

        // if you reach at the last element of given array and
        // sub array splits also reached 'm'
        if (pos == nums.length && subArrayCount == this.m) {
            this.result = Math.min(this.result, currentMaxSum);
            return;
        }
        // if reached to the last element but don't have 'm' splits
        if (pos == nums.length) {
            return;
        }

        // Add elements to current subarray
        if (pos > 0) {
            dfs(nums, pos+1, subArrayCount, currentSubarraySum+nums[pos], Math.max(currentMaxSum, currentSubarraySum+nums[pos]));
        }
        // Create a new subarray
        if (subArrayCount < this.m) {
            dfs(nums, pos+1, subArrayCount+1, nums[pos], Math.max(currentMaxSum,nums[pos]));
        }
    }
    /**
     * We can try to find all possible splits and observe the sum. DFS can be used to
     * generate all possible splits.
     * @param nums
     * @param m
     * @return
     */
    public int splitArrayBruteforce(int[] nums, int m) {
        this.n = nums.length;
        this.m = m;
        this.result = Integer.MAX_VALUE;

        this.dfs(nums, 0, 0, 0, 0);

        return this.result;

    }

    public static void main(String args[]) {
        SplitArrayLargestSum obj = new SplitArrayLargestSum();
        System.out.println("nums[]=[7,2,5,10,8], m=2\nMin max subarray sum: " + obj.splitArrayBruteforce(new int[]{7,2,5,10,8}, 2));
        System.out.println("nums[]=[7,2,5,10,8], m=3\nMin max subarray sum: " + obj.splitArrayBruteforce(new int[]{7,2,5,10,8}, 3));

    }
}
