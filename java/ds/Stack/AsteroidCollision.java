package ds.Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents
 * its direction (positive meaning right, negative meaning left). Each asteroid moves
 * at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the
 * smaller one will explode. If both are the same size, both will explode. Two asteroids
 * moving in the same direction will never meet.
 *
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 *
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 *
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 * Input: asteroids = [-2,-1,1,2]
 * Output: [-2,-1,1,2]
 * Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right. Asteroids moving the same direction never meet, so no asteroids will meet each other.
 *
 *
 */
public class AsteroidCollision {


    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for(int ast : asteroids) {
            // push to stack without any check
            if(ast > 0) {
                stack.push(ast);
            } else {
                // if the value is negative
                // check if there is a collision
                while(!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -ast) {
                    stack.pop();
                }
                // if stack is empty or peek is negative then
                // push to stack, -> ->
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(ast);
                }
                // if equal value
                if (stack.peek() == -ast) {
                    stack.pop(); // remove both
                }
            }
        }

        int len = stack.size();
        int[] res = new int[len];

        for(int i=len-1;i>=0;i--) {
            res[i] = stack.pop();
        }
        return res;
    }

    public static void main(String args[]) {
        System.out.println(Arrays.toString(AsteroidCollision.asteroidCollision(new int[]{-2,2,-1,-2})));
    }
}
