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

        int maxCount = Integer.MIN_VALUE, lastMaxTimestamp = -1;
        int tempCount = 0, curTimestamp = -1;

        for(int i=0; i<data.length; i++) {
            if (curTimestamp == -1) {
                curTimestamp = data[i][0];

                if (data[i][2] == 1) {
                    tempCount += data[i][1];
                } else {
                    tempCount -= data[i][1];
                }

                continue;
            }

            if (curTimestamp == data[i][0]) {
                if (data[i][2] == 1) {
                    tempCount += data[i][1];
                } else {
                    tempCount -= data[i][1];
                }
            } else {
                if (tempCount > maxCount) {
                    maxCount = tempCount;
                    lastMaxTimestamp = curTimestamp;
                }

                curTimestamp = -1;
                tempCount = 0;
            }
        }

        if (curTimestamp > -1 && tempCount > maxCount) {
            lastMaxTimestamp = curTimestamp;
        }

        return lastMaxTimestamp;
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
    }
}
