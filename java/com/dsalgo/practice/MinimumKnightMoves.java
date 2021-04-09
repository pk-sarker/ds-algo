package com.dsalgo.practice;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 *
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction,
 * then one square in an orthogonal direction.
 *
 * Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.
 */
public class MinimumKnightMoves {
    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);

        if(x==0 && y==0) {
            return 0;
        }

        int[][] directions = new int[][]{{2,1}, {1,2}, {-1,2}, {-2,1}, {-1,-2}, {-2,-1}, {1,-2}, {2,-1}};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0}); // root node

        int steps = 0;
        Set<String> visited = new HashSet<>();
        visited.add("0,0");

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i =0;i<size;i++) {
                int[] node = queue.remove();
                if(node[0] == x && node[1] == y) {
                    return steps;
                }

                for(int[] dir : directions) {
                    int newX = node[0]+dir[0], newY = node[1]+dir[1];
                    if (!visited.contains(newX + "," + newY) && newX >= -1 && newY >= -1) {
                        queue.add(new int[] {newX, newY});
                        visited.add(newX + "," + newY);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static void main(String args[]) {
        System.out.println("\nx=5, y=5\nOutput: " + MinimumKnightMoves.minKnightMoves(5,5));

        System.out.println("\nx=2, y=1\nOutput: " + MinimumKnightMoves.minKnightMoves(2,1));

        System.out.println("\nx=2, y=112\nOutput: " + MinimumKnightMoves.minKnightMoves(2,112));
    }
}
