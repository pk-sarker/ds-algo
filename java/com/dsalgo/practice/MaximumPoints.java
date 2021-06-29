package com.dsalgo.practice;

/**
 * There are several cards arranged in a row, and each card has an associated number of points.
 * The points are given in the integer array cardPoints.
 *
 * In one step, you can take one card from the beginning or from the end of the row. You have to
 * take exactly k cards.
 *
 * Your score is the sum of the points of the cards you have taken.
 *
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 *
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the
 * rightmost card first will maximize your total score. The optimal strategy is to take the
 * three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 *
 */
public class MaximumPoints {


    public static int maxScore(int[] cardPoints, int k) {

        int[] leftSum = new int[k+1];
        int[] rightSum = new int[k+1];
        int n = cardPoints.length;
        // [1,2,3,4,5,6,1]
        for(int i=0;i<k;i++) {
            leftSum[i+1] = leftSum[i] + cardPoints[i]; // 1, 1+2=3, 3+3=6
            rightSum[i+1] = rightSum[i] + cardPoints[n-i-1]; // 1, 6+1=7, 7+5=12
        }
        // leftSum = [1,3,6], rightSum=[1,7,12]
        // pick 1 from left and k-1 from right and compare the max
        int maxScore = 0;

        //
        int currentSum = 0;
        for(int i=0;i<=k;i++) {
            currentSum = leftSum[i] + rightSum[k-i];
            maxScore = Math.max(maxScore, currentSum);
        }

        return maxScore;
    }

    // sum k points from left first then try to remove one from the sum
    // and add one from the right
    public static int maxScoreSpaceOpt(int[] cardPoints, int k) {
        int leftSum = 0, rightSum = 0;

        for(int i=0;i<k;i++) {
            leftSum += cardPoints[i];
        }
        int maxScore = leftSum;
        int np = cardPoints.length;
        for(int i=k-1; i>=0; i--) {
            leftSum -= cardPoints[i];
            rightSum += cardPoints[np - (k-i)];

            int currentSum = leftSum+rightSum;
            maxScore = Math.max(maxScore, currentSum);
        }
        return maxScore;
    }

    public static void main(String args[]) {
        System.out.println(MaximumPoints.maxScore(new int[]{1,2,3,4,5,6,1}, 3));
        System.out.println(MaximumPoints.maxScoreSpaceOpt(new int[]{1,2,3,4,5,6,1}, 3));
    }
}
