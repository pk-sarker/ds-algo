package algo.Greedy;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * We used Greedy approach to solve the Jump Game problem:
 * The idea here is the scan the array from right to left and keep
 * track of last known position from where we can reach/jump to
 * the last index in the array.
 *
 * Example:
 * Lets take an example: [2, 0, 4, 2, 1, 0, 2, 1]
 * In the example last index, 7, and the value is 1. We can always reach to
 * last index form the same position with 0 jump. So index/position 7 is a
 * known position from where we can reach to last index.
 *
 * Now we scan to right, next position/index 6, the value is 2. With 2 at
 * position 6 we can jump maximum 2 ahead, that mean we can reach to [7,8]
 * indices. Here index 7 is already known as a good index.
 * So, as we are moving from left to right, one we flow,
 * from current index if we reach to a index which is known that we can reach
 * to last index then we can reach from current index to last index.
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        // Initialize last position from where we can reach to last index in the array
        // In worse case, if the number in last index is 0 still we can reach at last index.
        int lastKnownPosition = nums.length-1;

        for (int i=nums.length-1; i>=0; i--) {
            // Update last position from left, from where we can jump to/reach to last index,
            // when we can reach to last known position(from where we can reach to last index)
            // i+nums[i] >= lastKnownPosition
            // if lastPos = 5, that means we can reach to last index from position 5
            // So from current position if we can reach to at least position 5 then we can
            // reach to last index as well because its already known that we can reach last index
            // from position 5
            if (i+nums[i] >= lastKnownPosition) {
                lastKnownPosition = i;
            }
        }
        // If lastKnownPosition = 0 means that we can reach to last index in the array from
        // first index(0) of the array
        return lastKnownPosition == 0;
    }

    public static void main(String args[]) {
        JumpGame obj = new JumpGame();
        System.out.println("Input: [2,3,1,1,4] \nOutput: " + obj.canJump(new int[]{2,3,1,1,4}));
        System.out.println("Input: [3,2,1,0,4] \nOutput: " + obj.canJump(new int[]{3,2,1,0,4}));
    }
}
