package com.dsalgo.practice;

/**
 * input:  data = [ [1487799425, 14, 1],
 *                  [1487799425, 4,  0],
 *                  [1487799425, 2,  0],
 *                  [1487800378, 10, 1],
 *                  [1487801478, 18, 0],
 *                  [1487801478, 18, 1],
 *                  [1487901013, 1,  0],
 *                  [1487901211, 7,  1],
 *                  [1487901211, 7,  0] ]
 *
 * output: 1487800378 # since the increase in the number of people
 *                    # in the mall is the highest at that point
 */
public class BusiestTimeInTheMall {
    public static int findBusiestPeriod(int[][] data) {
        int n = data.length,
        count = 0,
        maxCount = 0,
        maxTimestamp = 0;

        for(int i=0; i<n; i++) {
            if (data[i][2]==1) {
                count += data[i][1];
            } else {
                count -= data[i][1];
            }

            if (i < n-1 && data[i][0] == data[i+1][0]) {
                continue;
            }
            if (count > maxCount) {
                maxCount = count;
                maxTimestamp = data[i][0];
            }
        }

        return maxTimestamp;
    }
    public static void main(String args[]) {
        System.out.println("\nOutput: " + BusiestTimeInTheMall.findBusiestPeriod(new int[][]{
                {1487799425, 14, 1},
                {1487799425, 4,  0},
                {1487799425, 2,  0},
                {1487800378, 10, 1},
                {1487801478, 18, 0},
                {1487801478, 18, 1},
                {1487901013, 1,  0},
                {1487901211, 7,  1},
                {1487901211, 7,  0}
        }));

        System.out.println("\nOutput: " + BusiestTimeInTheMall.findBusiestPeriod(new int[][]{
                {1487799425,14,1},
                {1487799425,4,1},
                {1487799425,2,1},
                {1487800378,10,1},
                {1487801478,18,1},
                {1487901013,1,1},
                {1487901211,7,1},
                {1487901211,7,1}}));
    }
}
