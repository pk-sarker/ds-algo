package algo.DP;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 *
 * Solution:
 * You may come to i-th step from i-1-th or i-2-th step.
 * So from each step we will make to steps, go to i+1-th step or i+2-th step
 */
public class ClimbingStairs {

    // Brute-Force
    // Time complexity: O(2^n) , from each step we are making two moves, and we do that for n steps
    // Space complexity: O(n)
    // climb(i,n) = climb(i+1,n) + climb(i+2,n)
    public int climbStairsBF(int n) {
        return helper(0, n);
    }
    private int helper(int i, int n){
        if (i>n) {
            return 0;
        }
        if (i==n) {
            return 1;
        }
        return helper(i+1, n) + helper(i+2, n);
    }

    // There will be overlapped in the helper() method
    // like start from step 0
    //                             helper(0,n)
    //                            /            \
    //                  helper(1,n)  +         helper(2,n)
    //                  /       \               /        \
    //          helper(2,n) + helper(3,n)   helper(3,n) + helper(4,n)
    //
    // We can save the result and reuse
    //  Recursion with Memoization
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int climbStairsMemo(int n) {
        int[] memo = new int[n+1];
        return helper(0, n, memo);
    }

    private int helper(int i, int n, int[] memo) {
        if (i > n) return 0;

        if (i==n) return 1;

        if (memo[i]>0) return memo[i];

        memo[i] = helper(i+1, n, memo) + helper(i+2, n, memo);
        return memo[i];
    }

    // We can solve this problem using dynamic programming
    // if we know the number of ways to reach i-1-th and i-2-th step
    // then number of ways to reach i-th step will be sum of ways to
    // reach i-1 th step and i-2 th step.
    // dp[i] = dp[i-1] + dp[i-2]

    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=2;
        for(int step=2; step<n; step++) { // step is considered 0 indexed
            dp[step] = dp[step-1] + dp[step-2];
        }
        return dp[n-1];
    }

    public static void main(String args[]) {
        ClimbingStairs obj = new ClimbingStairs();
        System.out.println("n=1, Ways: "+obj.climbStairsBF(1));
        System.out.println("n=2, Ways: "+obj.climbStairsBF(2));
        System.out.println("n=5, Ways: "+obj.climbStairsBF(5));

        System.out.println("n=5, Ways: "+obj.climbStairsMemo(5));
        System.out.println("n=5, Ways: "+obj.climbStairs(5));
    }
}
