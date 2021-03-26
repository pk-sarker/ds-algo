package com.dsalgo.practice;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given a list of the scores of different students, items, where items[i] = [IDi, scorei]
 * represents one score from a student with IDi, calculate each student's top five average.
 *
 * Return the answer as an array of pairs result, where result[j] = [IDj, topFiveAveragej]
 * represents the student with IDj and their top five average. Sort result by IDj in increasing
 * order.
 *
 * A student's top five average is calculated by taking the sum of their top five scores and
 * dividing it by 5 using integer division.
 *
 * Input: items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * Output: [[1,87],[2,88]]
 *
 * Explanation:
 * The student with ID = 1 got scores 91, 92, 60, 65, 87, and 100.
 * Their top five average is (100 + 92 + 91 + 87 + 65) / 5 = 87.
 * The student with ID = 2 got scores 93, 97, 77, 100, and 76. Their top five average is
 * (100 + 97 + 93 + 77 + 76) / 5 = 88.6, but with integer division their average converts to 88.
 */
public class HighFive {

    public static int[][] highFive(int[][] items) {
        Arrays.sort(items, (a, b) -> b[1] - a[1]);
        HashMap<Integer, int[]> topFiveAvg = new HashMap<>();

        for(int[] item : items) {
            if (!topFiveAvg.containsKey(item[0])) {
                topFiveAvg.put(item[0], new int[]{item[1], 1});
            } else {
                int[] counts = topFiveAvg.get(item[0]);
                if (counts[1] < 5) {
                    counts[0] += item[1];
                    counts[1]++;
                    topFiveAvg.put(item[0], counts);
                }
            }
        }
        int[][] res = new int[topFiveAvg.size()][2];
        int i = 0;
        for(int id : topFiveAvg.keySet()) {
            res[i] = new int[]{id, topFiveAvg.get(id)[0]/topFiveAvg.get(id)[1]};
            i++;
        }
        return res;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]] \nOutput: " + Arrays.deepToString(HighFive.highFive(
                new int[][]{{1,91},{1,92},{2,93},{2,97},{1,60},{2,77},{1,65},{1,87},{1,100},{2,100},{2,76}})
        ));

        System.out.println("\nInput: [[1,100],[3,50], [7,100],[1,100],[7,100],[3,88], [1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[3,70],[3,90],[3,94]] \nOutput: " + Arrays.deepToString(HighFive.highFive(
                new int[][]{{1,100},{3,50}, {7,100},{1,100},{7,100},{3,88}, {1,100},{7,100},{1,100},{7,100},{1,100},{7,100},{3,70},{3,90},{3,94}})
        ));
    }
}
