package algo.Searching.BinarySearch;

/***
 * You are given two 0-indexed arrays nums and cost consisting each of n positive integers.
 *
 * You can do the following operation any number of times:
 *
 * Increase or decrease any element of the array nums by 1.
 * The cost of doing one operation on the ith element is cost[i].
 *
 * Return the minimum total cost such that all the elements of the array nums become equal.
 *
 * Solution:
 * We can find upper bound and lower bound of the numbers and use those as BinarySearch range.
 * Then for each mid point(middle number) we calculate cost to make all numbers equal to middle number,
 * and cost to make all number to middle + 1 number.
 * We move to the direction where the cost is low.
 *
 */
public class MinimumCostToMakeArrayEqual {

    public long calculateCost(int targetNumber, int[] nums, int[] costs) {
        long totalCost = 0L;
        for(int i=0; i<nums.length; i++) {
            totalCost =  totalCost + 1L* Math.abs(nums[i]-targetNumber) * costs[i];
        }
        return totalCost;
    }

    public long minCost(int[] nums, int[] cost) {
        // Find Minimum and Maximum number, which will be lower and upper bound
        // of the Binary search
        int max = 0, min = 1000001;
        for (int i=0; i< nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        long minimumCost = calculateCost(nums[0], nums, cost);
        int left = min, right = max;
        while(left < right) {
            int mid = (left + right)/2;
            long cost1 = calculateCost(mid, nums, cost);
            long cost2 = calculateCost(mid+1, nums, cost);
            if (cost1 > cost2) {
                left = mid+1;
            } else {
                right = mid;
            }
            minimumCost = Math.min(cost1,cost2);
        }
        return minimumCost;
    }

    public static void main(String args[]) {
        MinimumCostToMakeArrayEqual obj = new MinimumCostToMakeArrayEqual();
        long res = obj.minCost(new int[]{1,3,5,2}, new int[]{2,3,1,14});
        System.out.println("nums=[1,3,5,2], costs=[2,3,1,14]");
        System.out.println("Minimum Cost: " + res);

        long res2 = obj.minCost(new int[]{2,2,2,2,2}, new int[]{4,2,8,1,3});
        System.out.println("nums=[2,2,2,2,2], costs=[4,2,8,1,3]");
        System.out.println("Minimum Cost: " + res2);
    }
}
