package algo.DP;

/**
 * We are playing the Guessing Game. The game will work as follows:
 *
 *  - I pick a number between 1 and n.
 *  - You guess a number.
 *  - If you guess the right number, you win the game.
 *  - If you guess the wrong number, then I will tell you whether the number I picked is higher or lower, and you will continue guessing.
 *  - Every time you guess a wrong number x, you will pay x dollars. If you run out of money, you lose the game.
 *
 * Given a particular n, return the minimum amount of money you need to guarantee a win
 * regardless of what number I pick.
 *
 * Input: n = 10
 * Output: 16
 * Explanation: The winning strategy is as follows:
 * - The range is [1,10]. Guess 7.
 *     - If this is my number, your total is $0. Otherwise, you pay $7.
 *     - If my number is higher, the range is [8,10]. Guess 9.
 *         - If this is my number, your total is $7. Otherwise, you pay $9.
 *         - If my number is higher, it must be 10. Guess 10. Your total is $7 + $9 = $16.
 *         - If my number is lower, it must be 8. Guess 8. Your total is $7 + $9 = $16.
 *     - If my number is lower, the range is [1,6]. Guess 3.
 *         - If this is my number, your total is $7. Otherwise, you pay $3.
 *         - If my number is higher, the range is [4,6]. Guess 5.
 *             - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $5.
 *             - If my number is higher, it must be 6. Guess 6. Your total is $7 + $3 + $5 = $15.
 *             - If my number is lower, it must be 4. Guess 4. Your total is $7 + $3 + $5 = $15.
 *         - If my number is lower, the range is [1,2]. Guess 1.
 *             - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $1.
 *             - If my number is higher, it must be 2. Guess 2. Your total is $7 + $3 + $1 = $11.
 * The worst case in all these scenarios is that you pay $16. Hence, you only need $16 to guarantee a win.
 */
public class GuessNumberHigherOrLower {

    public int getMoneyAmount(int n) {
        int[][] money = new int[n+1][n+1]; // start from index 1
        return getMoneyInRange(money, 1, n);
    }


    private int getMoneyInRange(int[][] money, int start, int end) {
        if (start >= end) {
            return 0;
        }
        if (money[start][end] != 0) { // cost is previously calculated
            return money[start][end];
        }
        int res = Integer.MAX_VALUE;

        // selection is random, so we need to consider any number in the range

        for(int amount = start; amount <= end; amount++) {
            // current cost if we pick amount
            // cost = amount that we pick(consider the pick is not correct) +
            //          Maximum of picking a number between start to previous of current pick
            //          and picking a number between next of current pick to end
            // Is similar to binary search, we pick a number if its not the desired one then we check either
            // before and after that number. But here we need to check both direction as the cost to
            // find the number in left and right is different.
            int temp = amount + Math.max(getMoneyInRange(money, start, amount-1),getMoneyInRange(money, amount+1, end));
            // We want to minimize the cost, so we keep the previous result and select the lowest one.
            // for example, [....5,6,7,8,9,10....]
            // we pick 8, then the cost will be: temp = 8 + Max(getMoneyInRange(..5,6,7), getMoneyInRange(9,10,...))
            // we pick 9, then the cost will be: temp = 9 + Max(getMoneyInRange(..5,6,7,8), getMoneyInRange(10,...))
            res = Math.min(res, temp);
        }
        money[start][end] = res;
        return res;
    }


    public static void main(String args[]) {
        GuessNumberHigherOrLower obj = new GuessNumberHigherOrLower();
        System.out.println("n=10, minCost: $"+ obj.getMoneyAmount(10));
    }
}
