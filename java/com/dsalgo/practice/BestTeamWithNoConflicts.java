package com.dsalgo.practice;

import java.util.Arrays;

/**
 * You are the manager of a basketball team. For the upcoming tournament, you want to choose the team
 * with the highest overall score. The score of the team is the sum of scores of all the players in the team.
 *
 * However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player
 * has a strictly higher score than an older player. A conflict does not occur between players of the same age.
 *
 * Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of
 * the ith player, respectively, return the highest overall score of all possible basketball teams.
 *
 *
 * Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * Output: 34
 * Explanation: You can choose all the players.
 *
 *
 * Input: scores = [4,5,6,5], ages = [2,1,2,1]
 * Output: 16
 * Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
 *
 * Input: scores = [1,2,3,5], ages = [8,9,10,1]
 * Output: 6
 * Explanation: It is best to choose the first 3 players.
 *
 */
public class BestTeamWithNoConflicts {

    public static int bestTeamScore(int[] scores, int[] ages) {
        int[][] ageScore = new int[scores.length][2];

        for(int i=0; i < scores.length; i++) {
            ageScore[i] = new int[]{ages[i], scores[i]};
        }
        Arrays.sort(ageScore, (a, b) -> b[0]-a[0]);

        int res = 0;
        int[] dp = new int[scores.length];
        for(int i=0; i< scores.length; i++) {
            int score = ageScore[i][1];
            dp[i] = score;

            for(int j=0; j<i; j++){
                if (ageScore[i][1]> ageScore[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j]+score);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: scores = [1,3,5,10,15], ages = [1,2,3,4,5] \nOutput: " + BestTeamWithNoConflicts.bestTeamScore(new int[]{1,3,5,10,15}, new int[]{1,2,3,4,5}));
    }
}
