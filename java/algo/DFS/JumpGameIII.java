package algo.DFS;

/**
 * Given an array of non-negative integers arr, you are initially
 * positioned at start index of the array. When you are at index i,
 * you can jump to i + arr[i] or i - arr[i], check if you can reach
 * to any index with value 0.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 *
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 *
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 */
public class JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        // check if the start index is valid or not
        if (start >=0 && start < arr.length && arr[start]>=0) {

            if (arr[start] == 0) {
                return true;
            }
            // Mark the node as visited
            arr[start] = -arr[start];
            boolean reachFromLeft = canReach(arr, start-arr[start]);
            boolean reachFromRight = canReach(arr, start+arr[start]);

            return (reachFromLeft || reachFromRight);
        }
        return false;
    }

    public static void main(String args[]) {
        JumpGameIII obj = new JumpGameIII();
        System.out.println("Input: [4,2,3,0,3,1,2] start=5 \nOutput: " + obj.canReach(new int[]{4,2,3,0,3,1,2}, 5));
        System.out.println("Input: [4,2,3,0,3,1,2] start=5 \nOutput: " + obj.canReach(new int[]{4,2,3,0,3,1,2}, 0));
        System.out.println("Input: [3,0,2,1,2] start=5 \nOutput: " + obj.canReach(new int[]{3,0,2,1,2}, 2));
    }
}
