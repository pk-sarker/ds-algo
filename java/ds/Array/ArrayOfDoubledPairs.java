package ds.Array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array of even length arr, return true if it is possible to reorder arr such that
 * arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.
 *
 * Example 1:
 *
 * Input: arr = [3,1,3,6]
 * Output: false
 * Example 2:
 *
 * Input: arr = [2,1,2,6]
 * Output: false
 * Example 3:
 *
 * Input: arr = [4,-2,2,-4]
 * Output: true
 * Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
 * Example 4:
 *
 * Input: arr = [1,2,4,16,8,4]
 * Output: false
 *
 *
 * If x is currently the array element with the least absolute value, it must pair with 2*x,
 * as there does not exist any other x/2 to pair with it.
 *
 * Assume all interger are positive, for example [2,4,4,8].
 * We have one x = 2, we need to match it with one 2x = 4.
 * Then one 4 is gone, we have the other x = 4.
 * We need to match it with one 2x = 8.
 * Finaly no number left.
 *
 * Time Complexity: O(NlogN), where NN is the length of A.
 * Space Complexity: O(N).
 */
public class ArrayOfDoubledPairs {

    public static boolean canReorderDoubled(int[] arr) {
        // count[x] = the number of occurrences of x in arr
        Map<Integer, Integer> count = new HashMap();
        for (int x: arr)
            count.put(x, count.getOrDefault(x, 0) + 1);

        // B = A as Integer[], sorted by absolute value
        Integer[] B = new Integer[arr.length];
        for (int i = 0; i < arr.length; ++i)
            B[i] = arr[i];
        Arrays.sort(B, Comparator.comparingInt(Math::abs));

        for (int x: B) {
            // If this can't be consumed, skip
            if (count.get(x) == 0) continue;
            // If this doesn't have a doubled partner, the answer is false
            if (count.getOrDefault(2*x, 0) <= 0) return false;

            // Write x, 2*x
            count.put(x, count.get(x) - 1);
            count.put(2*x, count.get(2*x) - 1);
        }

        // If we have written everything, the answer is true
        return true;
    }

    public static void main(String args[]) {

    }
}
