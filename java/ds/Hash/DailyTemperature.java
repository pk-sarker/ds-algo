package ds.Hash;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an array of integers temperatures represents the daily temperatures, return an array answer
 * such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 *
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 *
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 *
 */
public class DailyTemperature {
    public static int[] dailyTemperaturesBF(int[] T) {
        int[] res = new int[T.length];
        for(int i=0; i<T.length; i++) {
            for(int j=0; j<=i; j++) {
                if (T[j] < T[i] && res[j] == 0) {
                    res[j] = i-j;
                }
            }
        }

        return res;
    }

    public static int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        int[] next = new int[101];

        Arrays.fill(next, Integer.MAX_VALUE);

        for(int i=T.length-1; i>=0; i--) {
            int warmer_index = Integer.MAX_VALUE;
            for(int t = T[i]+1; t<=100; t++) {
                if (next[t] < warmer_index) {
                    warmer_index = next[t];
                }
            }
            if (warmer_index < Integer.MAX_VALUE) {
                ans[i] = warmer_index - i;
            }
            next[T[i]] = i;
        }

        return ans;
    }

    public static int[] dailyTemperaturesWithStack(int[] T) {
        int[] ans = new int[T.length];
        // store indices of the temperature with increasing order
        Stack<Integer> stack = new Stack<>();

        for(int i=T.length-1; i>=0; i--) {

            while(!stack.isEmpty() && T[i] > T[stack.peek()]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty()  ?  0: stack.peek()-i;

            stack.push(i);
        }

        return ans;
    }
    public static void main(String args[]) {
        System.out.println("\n-- Bruteforce --\nInput: [73,74,75,71,69,72,76,73], \nOutput: " + Arrays.toString(DailyTemperature.dailyTemperaturesBF(new int[]{73,74,75,71,69,72,76,73})));
        System.out.println("\n-- Using Next day temperature --Input: [73,74,75,71,69,72,76,73], \nOutput: " + Arrays.toString(DailyTemperature.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
        System.out.println("\n-- Using  Stack --\nInput: [73,74,75,71,69,72,76,73], \nOutput: " + Arrays.toString(DailyTemperature.dailyTemperaturesWithStack(new int[]{73,74,75,71,69,72,76,73})));
    }
}
