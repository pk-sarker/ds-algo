package ds.Heap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * You are given an array points containing the coordinates of points on a 2D plane, sorted by the x-values,
 * where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an
 * integer k.
 *
 * Return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and
 * 1 <= i < j <= points.length.
 *
 * It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.
 *
 * Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
 * Output: 4
 * Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate
 * the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition
 * and give a value of 10 + -10 + |5 - 6| = 1.
 * No other pairs satisfy the condition, so we return the max of 4 and 1.
 *
 * Input: points = [[0,0],[3,0],[9,2]], k = 3
 * Output: 3
 * Explanation: Only the first two points have an absolute difference of 3 or less in the x-values,
 * and give the value of 0 + 0 + |0 - 3| = 3.
 *
 * **Solution**
 * Equation: = yi + yj + |xi - xj|, and xi<xj
 *      then |xi - xj| => xj-xi will be always positive
 * => (yj+xj) + (yi-xi), now the max depends on (yi-xi)
 *
 * So we need to find max of (yi-xi) when xj-xi <=k or |xi - xj| <= k
 *
 * We will maintain a priority queue to store the difference, xj-xi, and value as xi
 *
 */
public class MaxValueOfEquation {


    // With Priority Queue, O(n log n)
    public static int findMaxValueOfEquationWithPQ(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0]==b[0] ? a[1]-b[1]: b[0]-a[0]);
        int max = Integer.MIN_VALUE;
        for(int i=0;i<points.length;i++) {
            while(!pq.isEmpty() && points[i][0] - pq.peek()[0] > k) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                max = Math.max(max, pq.peek()[0] + points[i][0] + points[i][1]);
            }
            pq.offer(new int[]{points[i][1]-points[i][0], points[i][0]});
        }
        return max;
    }

    // With queue only, O(n)
    public static int findMaxValueOfEquationWithQueue(int[][] points, int k) {

        Deque<int[]> queue = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        for (int[] point : points) {
            while (!queue.isEmpty() && point[0] - queue.peekFirst()[1] > k) {
                queue.pollFirst();
            }
            if (!queue.isEmpty()) {
                max = Math.max(max, queue.peekFirst()[0] + point[0] + point[1]);
            }
            while (!queue.isEmpty() && point[1] - point[0] > queue.peekLast()[0]) {
                queue.pollLast();
            }
            queue.offerLast(new int[]{point[1] - point[0], point[0]});
        }
        return max;
    }
    public static void main(String args[]) {
        System.out.println("points = [[1,3],[2,0],[5,10],[6,-10]], k = 1 \nOutput:"+MaxValueOfEquation.findMaxValueOfEquationWithPQ(new int[][]{{1,3},{2,0},{5,10},{6,-10}}, 1));

        System.out.println("points = [[1,3],[2,0],[5,10],[6,-10]], k = 1 \nOutput:"+MaxValueOfEquation.findMaxValueOfEquationWithQueue(new int[][]{{1,3},{2,0},{5,10},{6,-10}}, 1));

        System.out.println("points = [[-19,9],[-15,-19],[-5,-8]], k = 10 \nOutput:"+MaxValueOfEquation.findMaxValueOfEquationWithQueue(new int[][]{{-19,9},{-15,-19},{-5,-8}}, 10));
    }
}