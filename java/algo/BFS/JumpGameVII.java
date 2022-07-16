package algo.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a 0-indexed binary string s and two integers minJump and maxJump.
 * In the beginning, you are standing at index 0, which is equal to '0'.
 * You can move from index i to index j if the following conditions are fulfilled:
 *    - i + minJump <= j <= min(i + maxJump, s.length - 1), and
 *    - s[j] == '0'.
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 */
public class JumpGameVII {

    public boolean canReach(String s, int minJump, int maxJump) {
        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        char[] arr = s.toCharArray();
        Boolean[] visited = new Boolean[arr.length];
        for (int i=0; i< arr.length; i++) {
            visited[i] = false;
        }

        int maxPos = 0;
        while(!q.isEmpty()) {
            int index = q.poll();

            for(int j=Math.max(index+minJump, maxPos+1); j<=Math.min(index+maxJump, arr.length-1); j++) {
                if (visited[j]) {
                    continue;
                }

                if (arr[j] == '0') {
                    if (j == arr.length-1) {
                        return true;
                    }
                    q.add(j);
                    visited[j] = true;
                }
            }
            maxPos = Math.max(maxPos, index + maxJump);
        }

        return false;
    }
    public static void main(String args[]) {
        JumpGameVII obj = new JumpGameVII();
        System.out.println("Input: [011010] minJump=2, maxJump=3\nOutput: " + obj.canReach("011010", 2, 3));

        System.out.println("Input: [011010101100111001010101010] minJump=1, maxJump=4\nOutput: " + obj.canReach("011010101100111001010101010", 1, 4));
    }
}
