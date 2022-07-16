package algo.Greedy;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
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
