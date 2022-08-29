package algo.Sorting.TopologicalSort;

import java.util.*;

/**
 * You are given a positive integer k. You are also given:
 *
 *  - a 2D integer array rowConditions of size n where rowConditions[i] = [above_i, below_i], and
 *  - a 2D integer array colConditions of size m where colConditions[i] = [left_i, right_i].
 * The two arrays contain integers from 1 to k.
 *
 * You have to build a k x k matrix that contains each of the numbers from 1 to k exactly once. The remaining cells should have the value 0.
 *
 * The matrix should also satisfy the following conditions:
 *
 *  - The number abovei should appear in a row that is strictly above the row at which the number belowi appears for all i from 0 to n - 1.
 *  - The number lefti should appear in a column that is strictly left of the column at which the number righti appears for all i from 0 to m - 1.
 *
 * Return any matrix that satisfies the conditions. If no answer exists, return an empty matrix.
 *
 * Example:
 * Input: k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
 * Output: [[3,0,0],[0,0,1],[0,2,0]]
 * Explanation: The diagram above shows a valid example of a matrix that satisfies all the conditions.
 * The row conditions are the following:
 * - Number 1 is in row 1, and number 2 is in row 2, so 1 is above 2 in the matrix.
 * - Number 3 is in row 0, and number 2 is in row 2, so 3 is above 2 in the matrix.
 * The column conditions are the following:
 * - Number 2 is in column 1, and number 1 is in column 2, so 2 is left of 1 in the matrix.
 * - Number 3 is in column 0, and number 2 is in column 1, so 3 is left of 2 in the matrix.
 * Note that there may be multiple correct answers.
 */
public class MatrixWithConditions {

    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {

        List<Integer> rowOrder = topologicalSort(rowConditions, k);
        List<Integer> colOrder = topologicalSort(colConditions, k);

        if (rowOrder.size() < k || colOrder.size() < k) return new int[0][0];
        int[][] answer = new int[k][k];

        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<k; i++) {
            map.put(colOrder.get(i), i);
        }
        for(int i=0; i<k; i++) {
            map.put(colOrder.get(i), i);
            answer[i][map.get(rowOrder.get(i))] = rowOrder.get(i);
        }
        return answer;
    }

    private List<Integer> topologicalSort(int[][] conditions, int k) {
        int[] degree = new int[k];
        List<Integer> order = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<k; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] condition: conditions) {
            graph.get(condition[0]-1).add(condition[1]-1);
            degree[condition[1]-1]++;
        }

        for(int i=0; i<k; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node+1); // was deducted above
            for(int nextNode: graph.get(node)) {
                if (--degree[nextNode] == 0) {
                    queue.add(nextNode);
                }
            }
        }
        return order;
    }

    public static void main(String args[]) {
        MatrixWithConditions obj = new MatrixWithConditions();
        int [][] matrix = obj.buildMatrix(3, new int[][]{{1,2},{3,2}}, new int[][]{{2,1},{3,2}});
        System.out.println(Arrays.deepToString(matrix));

        int [][] matrix2 = obj.buildMatrix(3, new int[][]{{1,2},{2,3}, {3,1}, {2,3}}, new int[][]{{2,1}});
        System.out.println(Arrays.deepToString(matrix2));
    }
}
