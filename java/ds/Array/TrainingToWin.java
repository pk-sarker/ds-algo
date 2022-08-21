package ds.Array;

/**
 * You are entering a competition, and are given two positive integers initialEnergy and initialExperience
 * denoting your initial energy and initial experience respectively.
 *
 * You are also given two 0-indexed integer arrays energy and experience, both of length n.
 *
 * You will face n opponents in order. The energy and experience of the ith opponent is denoted
 * by energy[i] and experience[i] respectively. When you face an opponent, you need to have both strictly
 * greater experience and energy to defeat them and move to the next opponent if available.
 *
 * Defeating the ith opponent increases your experience by experience[i], but decreases your energy by energy[i].
 *
 * Before starting the competition, you can train for some number of hours. After each hour of training,
 * you can either choose to increase your initial experience by one, or increase your initial energy by one.
 *
 * Return the minimum number of training hours required to defeat all n opponents.
 *
 * Example:
 * Input: initialEnergy = 5, initialExperience = 3, energy = [1,4,3,2], experience = [2,6,3,1]
 * Output: 8
 *
 * Input: initialEnergy = 2, initialExperience = 4, energy = [1], experience = [3]
 * Output: 0
 */

public class TrainingToWin {
    public static int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int minTrainingHrs = 0;
        int totalEnergy = 0;

        for(int i=0; i<energy.length;i++) {
            totalEnergy += energy[i];
        }

        if (totalEnergy - initialEnergy >=0) {
            minTrainingHrs = totalEnergy - initialEnergy +1;
        }

        for(int i=0; i<experience.length;i++) {
            if (initialExperience <= experience[i]) {
                minTrainingHrs += (experience[i] - initialExperience +1);
                initialExperience = experience[i] + 1;
            }
            initialExperience += experience[i];
        }
        return minTrainingHrs;
    }

    public static void main(String args[]) {
        System.out.println(TrainingToWin.minNumberOfHours(5, 3, new int[]{1,4,3,2}, new int[]{2,6,3,1}));
    }
}
