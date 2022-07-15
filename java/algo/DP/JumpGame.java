package algo.DP;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * Example:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 *
 * Solution:
 * Step 1: Initially set all the elements in the `memo` as Unknown/-1. And
 *         Set 1 in the last position
 * Step 2: If the current index is known then
 *     - If its knows then return the value.
 *     - Otherwise perform the backtracking
 * Step 3: Once we determine the value of the current index, we store it in the memo table
 *
 */
public class JumpGame {
    int[] memo;
    public boolean jump(int position, int[] nums) {
        if (memo[position] > -1) {
            return memo[position] == 1 ? true: false;
        }
        int maxJumpIndex = Math.min(position + nums[position], nums.length-1);
        for (int nextIndex = position+1; nextIndex<=maxJumpIndex; nextIndex++) {
            if(jump(nextIndex, nums)) {
                memo[nextIndex] = 1;
                return true;
            }
        }
        memo[position] = 0;
        return false;
    }

    public boolean canJump(int[] nums) {
        memo = new int[nums.length];

        for (int i=0; i< nums.length; i++) {
            memo[i] = -1;
        }
        memo[memo.length-1] = 1;
        return jump(0, nums);
    }

    public boolean canJumpDPBottomUP(int[] nums) {
        int[] memo = new int[nums.length];

        for (int i=0; i<nums.length; i++) {
            memo[i] = -1;
        }
        memo[nums.length-1] = 1;

        for(int i=nums.length-2; i>=0; i--) {
            int maxJumpIndex = Math.min(i + nums[i], nums.length-1);

            for(int j=i+1; j<= maxJumpIndex; j++) {
                if (memo[j] == 1) {
                    memo[i] = 1;
                    break;
                }
            }
        }

        return memo[0] == 1;
    }

    public static void main(String args[]) {
        JumpGame obj = new JumpGame();
        System.out.println("Input: [2,3,1,1,4] \nOutput: " + obj.canJump(new int[]{2,3,1,1,4}));
        System.out.println("Input: [3,2,1,0,4] \nOutput: " + obj.canJump(new int[]{3,2,1,0,4}));
    }
}
