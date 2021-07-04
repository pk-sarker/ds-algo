package pattern.SubArray;

/**
 * Given an array nums of positive integers, call a (contiguous, not necessarily distinct) subarray 
 * of nums good if the number of different integers in that subarray is exactly k.
 *
 * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 *
 * Return the number of good subarrays of nums.
 *
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 *
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 */
public class SubarraysWithKDifferentIntegers {

    public static int subarraysWithKDistinct(int[] A, int K) {
        int res = 0, prefix = 0;
        int[] m = new int[A.length + 1];
        for (int i = 0, j = 0, cnt = 0; i < A.length; ++i) {
            if (m[A[i]]++ == 0) ++cnt;

            if (cnt > K) {
                --m[A[j++]]; --cnt; prefix = 0;
            }
            while (m[A[j]] > 1) {
                ++prefix; --m[A[j++]];
            }
            if (cnt == K) res += prefix + 1;
        }
        return res;
    }


    public static void main(String args[]) {
        System.out.println("Input: [1,2,1,2,3] k=2 \nOutput: " + SubarraysWithKDifferentIntegers.subarraysWithKDistinct(new int[]{1,2,1,2,3}, 2));
    }
}
