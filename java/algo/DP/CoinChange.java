package algo.DP;

import java.util.Arrays;

/**
 * You are given an integer array coins representing coins of different denominations and
 * an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount
 * of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 * Input: coins = [1], amount = 1
 * Output: 1
 *
 * Input: coins = [1], amount = 2
 * Output: 2
 */
public class CoinChange {


    /**
     * Dynamic programming: Top down up approach
     * F(S) = F(S-C) + 1
     *
     * Time complexity : O(S*n)
     * Space complexity : O(S)
     */
    public int coinChange(int[] coins, int amount) {
        if (amount<1) {
            return 0;
        }

        return coinChangeHelper(coins, amount, new int[amount]);
    }

    public int coinChangeHelper(int[] coins, int remaining, int[] counts) {
        if (remaining < 0) {
            return -1;
        }
        if (remaining == 0) {
            return 0;
        }
        if (counts[remaining-1] != 0) {
            return counts[remaining-1];
        }

        int  min = Integer.MAX_VALUE;
        for (int coin: coins) {
            int result = coinChangeHelper(coins, remaining-coin, counts);
            if (result >= 0 && result < min) {
                min = result+1;
            }
        }
        // save minimum  count value
        counts[remaining-1] = (min == Integer.MAX_VALUE) ? -1: min;

        return counts[remaining-1];
    }

    /**
     * Dynamic programming: Bottom up approach
     *
     * */
    public int coinChangeItr(int[] coins, int amount) {
        if (amount<1) {
            return 0;
        }
        int max = amount + 1;
        int[] numOfCoins = new int[amount + 1];
        Arrays.fill(numOfCoins, max);
        numOfCoins[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    numOfCoins[i] = Math.min(numOfCoins[i], numOfCoins[i - coins[j]] + 1);
                }
            }
        }
        return numOfCoins[amount] > amount ? -1 : numOfCoins[amount];
    }
    public static void main(String args[]) {
        CoinChange obj = new CoinChange();

        System.out.println(obj.coinChange(new int[]{1,2,5}, 11));
        System.out.println(obj.coinChangeItr(new int[]{1,2,5}, 11));
    }
}
