package algo.DP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * A frog is crossing a river. The river is divided into some number of units, and at each unit,
 * there may or may not exist a stone. The frog can jump on a stone, but it must not jump into
 * the water.
 *
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog
 * can cross the river by landing on the last stone. Initially, the frog is on the first stone
 * and assumes the first jump must be 1 unit.
 *
 * If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units.
 * The frog can only jump in the forward direction.
 *
 * Input: stones = [0,1,3,5,6,8,12,17]
 * Output: true
 * Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then
 * 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone,
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 *
 * Input: stones = [0,1,2,3,4,8,9,11]
 * Output: false
 * Explanation: There is no way to jump to the last stone as the gap between the 5th and
 * 6th stone is too large.
 */
public class FrogJump {

    HashSet<Integer> stonePosSet = new HashSet<>();
    public boolean canCrossBF(int[] stones) {
        for(int pos: stones) {
            stonePosSet.add(pos);
        }
        return jump(stones, 0, 0);
    }
    private boolean jump(int[] stones, int currentPos, int prevJumpSize) {
        System.out.println(">> currentPos: " + currentPos + " prevJumpSize: " + prevJumpSize);
        if (currentPos == stones.length-1) {
            return true;
        }
        if (currentPos >= stones.length) {
            return false;
        }
        List<Integer> currentJumpSizes = new ArrayList<>();
        if (prevJumpSize>0) {
            currentJumpSizes.add(prevJumpSize);
            //currentJumpSizes.add(prevJumpSize-1);
        }
        if (prevJumpSize>1) {
            currentJumpSizes.add(prevJumpSize-1);
        }

        currentJumpSizes.add(prevJumpSize+1);

        System.out.println(currentJumpSizes.toString());
        boolean ret = false;
        for(Integer jumpSize: currentJumpSizes) {
            int newPos = currentPos+jumpSize;
            if (!stonePosSet.contains(newPos)) {
                continue;
            }
            if (newPos < stones.length) {
                System.out.println(">>>> JUMP("+newPos+", "+jumpSize+")");
                ret = jump(stones, newPos, jumpSize);
                if (ret) {
                    return true;
                }
            }


        }
        return false;
    }

    public boolean canCross(int[] stones) {
        return can_Cross(stones, 0, 0);
    }
    public boolean can_Cross(int[] stones, int ind, int jumpsize) {
        System.out.println(">>> currentPos: " + ind + " prevJumpSize: " + jumpsize);
        for (int i = ind + 1; i < stones.length; i++) {
            int gap = stones[i] - stones[ind];
            if (gap >= jumpsize - 1 && gap <= jumpsize + 1) {
                if (can_Cross(stones, i, gap)) {
                    return true;
                }
            }
        }
        return ind == stones.length - 1;
    }
    public static void main(String args[]) {
        FrogJump obj = new FrogJump();
        System.out.println("\nInput: [0,1,3,5,6,8,12,17] \nOutput: "+obj.canCrossBF(new int[]{0,1,3,5,6,8,12,17}));
       // System.out.println("\nInput: [0,1,3,5,6,8,12,17] \nOutput: "+obj.canCross(new int[]{0,1,3,5,6,8,12,17}));
        //System.out.println("\nInput: [0,1,2,3,4,8,9,11] \nOutput: "+obj.canCrossBF(new int[]{0,1,2,3,4,8,9,11}));
    }
}
