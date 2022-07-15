package algo.Backtracking;

/**
 * You are given an integer array nums. You are initially positioned at the
 * array's first index, and each element in the array represents your
 * maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        return jump(0, nums);
    }

    public boolean jump(int position, int[] nums) {
        if (position == nums.length-1) {
            return true;
        }
        int maxIndex = Math.min(position + nums[position], nums.length-1);

        for(int nextPos = position + 1; nextPos <= maxIndex; nextPos++) {
            if (jump(nextPos, nums)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        JumpGame obj = new JumpGame();
        System.out.println("Input: [2,3,1,1,4] \nOutput: " + obj.canJump(new int[]{2,3,1,1,4}));
        System.out.println("Input: [3,2,1,0,4] \nOutput: " + obj.canJump(new int[]{3,2,1,0,4}));
    }
}
