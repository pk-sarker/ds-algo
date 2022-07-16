package algo.Greedy;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 *
 * Solution:
 * First thing that comes to my mind, maximize the jumps to minimize the number of jumps.
 * From a position, p, we can jump to a range, [p, p+nums[p]]. The question is where to
 * jump in the range. With the initial intuition we will jump to a position from where
 * we can make the biggest jump among that range.
 * As every time we will be selecting a position that gives biggest next jump, the number
 * of jumps will the minimum.
 */
public class JumpGameII {

    public int jump(int[] nums) {
        int jumpCount = 0, currentJumpEnd = 0, farthestJump = 0;

        for(int i=0; i<nums.length-1; i++) {
            farthestJump = Math.max(farthestJump, i + nums[i]);
            if (i == currentJumpEnd) {
                jumpCount++;
                currentJumpEnd = farthestJump;
            }
        }
        return jumpCount;
    }

    public static void main(String args[]) {
        JumpGameII obj = new JumpGameII();

        System.out.println("Input: [2,3,1,1,4] \nOutput: " + obj.jump(new int[]{2,3,1,1,4}));
        System.out.println("Input: [2,3,0,1,4] \nOutput: " + obj.jump(new int[]{2,3,0,1,4}));
    }
}
