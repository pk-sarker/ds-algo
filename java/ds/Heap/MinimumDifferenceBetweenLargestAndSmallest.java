package ds.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array nums, you are allowed to choose one element of nums and change it by any value in one move.
 *
 * Return the minimum difference between the largest and smallest value of nums after perfoming at most 3 moves.
 *
 * Input: nums = [5,3,2,4]
 * Output: 0
 * Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 * The difference between the maximum and minimum is 2-2 = 0.
 */
public class MinimumDifferenceBetweenLargestAndSmallest {

    public int minDifference(int[] nums) {
        int k = 3;      // This problem only asks for k == 3.
        if (nums == null || nums.length <= k + 1) return 0;   // If we have enough moves to make all values equal then we just return 0.

        PriorityQueue<Integer> minQ = new PriorityQueue<>((a, b) -> Integer.compare(a, b));   // Find the k+1 greatest elements.
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> Integer.compare(b, a));   // Find the k+1 smallest elements.
        for (int num : nums) {
            minQ.add(num);
            if (minQ.size() > k + 1) minQ.remove();
            maxQ.add(num);
            if (maxQ.size() > k + 1) maxQ.remove();
        }

        System.out.println(minQ.toString());
        System.out.println(maxQ.toString());

        int[] greatestValues = new int[minQ.size()];    // Sorted in ascending order.
        int j = 0;
        while (minQ.size() > 0) {
            greatestValues[j++] = minQ.remove();
        }
        int[] smallestValues = new int[maxQ.size()];    // Sorted in ascending order.
        j = maxQ.size() - 1;
        while (maxQ.size() > 0) {
            smallestValues[j--] = maxQ.remove();
        }
        Arrays.toString(greatestValues);
        Arrays.toString(smallestValues);

        // Compute the minimum difference between the smallest and greatest value for all k options and return the difference of the best option (minimum difference).
        int result = greatestValues[greatestValues.length - 1 - (k - 0)] - smallestValues[0];
        for (int i = 1; i <= k; ++i) {
            // Compute minimum difference between smallest and greatest value when getting rid of (imagine setting it equal to the median value) the i smallest values and (k - i) greatest values.
            int currOption = greatestValues[greatestValues.length - 1 - (k - i)] - smallestValues[i];
            result = Math.min(result, currOption);
        }
        return result;
    }
    public static void main(String args[]) {

    }
}
