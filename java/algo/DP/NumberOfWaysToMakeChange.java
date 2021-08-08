package algo.DP;

/**
 *
 Given an array of distinct positive integers representing coin denominations and a
 single non-negative integer n, representing a target amount of
 money, write a function that returns the number of ways to make change for
 that target amount using the given coin denominations.

 Note that an unlimited amount of coins is at your disposal.


 Example:
 n=6, denominations = [1,5]
 Output: 2 // 5 1's, 1 5 and 1 1
 */
public class NumberOfWaysToMakeChange {

    /**
     * Time Complexity: O(d*n), d is the number of denominations
     * Space Complexity: O(n)
     * @param n
     * @param denominations
     * @return
     */
    public static int numberOfWaysToMakeChange(int n, int[] denominations) {

        // index of ways represent amount
        // ways[3] = 2, means we have 2 ways to change $3
        int[] ways = new int[n+1]; // starting from 0
        ways[0] = 1; //

        // ways[amount] = ways[amount] + ways[amount-denomination]
        for(int denom : denominations) {
            for(int amount = 1; amount<n+1; amount++) {
                if (denom <= amount) {
                    ways[amount] = ways[amount] + ways[amount-denom];
                }
            }
        }
        return ways[n];
    }

    public static void main(String args[]) {
        System.out.println(NumberOfWaysToMakeChange.numberOfWaysToMakeChange(
                6, new int[]{1, 5}
        ));
    }
}
