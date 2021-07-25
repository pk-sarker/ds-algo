package algo.DP;

/**
 * You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
 *
 * Train tickets are sold in three different ways:
 *
 * a 1-day pass is sold for costs[0] dollars,
 * a 7-day pass is sold for costs[1] dollars, and
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 *
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 *
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total, you spent $11 and covered all the days of your travel.
 *
 * Solution:
 * let dp(i) be the cost to travel from day days[i] to the end of the plan.
 *
 * j1 is the largest index such that days[j1] < days[i] + 1,
 * j7 is the largest index such that days[j7] < days[i] + 7,
 * and j30 is the largest index such that days[j30] < days[i] + 30, then we have:
 *
 * dp(i)=min(dp(j1)+costs[0],dp(j7)+costs[1],dp(j30)+costs[2])
 *
 */
public class MinimumCostForTickets {
    int[] days, costs;
    Integer[] memo;
    int[] duration = new int[]{1,7,30};

    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        this.memo = new Integer[days.length];
        return dp(0);
    }

    private int dp(int index) {
        if (index >= this.days.length) {
            return 0;
        }
        if (this.memo[index] != null) {
            return this.memo[index];
        }
        int minValue = Integer.MAX_VALUE;
        int j = index;
        for(int k=0;k<this.duration.length; k++) {
            while(j < this.days.length && days[j] < days[index] + this.duration[k]) {
                // index is  the start day,
                // here we are trying to find a day till which the ticket will be valid,
                // days[index] + this.duration[k] = from start day  (day at index ) to ticket duration
                // for example, if days[index] = 3, then
                //      > 3 + 1 = if one day ticket purchased
                //      > 3 + 7 = if seven day ticket purchased
                //      > 3 + 30 = if thirty day ticket purchased
                // if you are at 3rd  day and purchased 7 day ticket, then j will find
                // a day when you have to purchase the ticket again, which will be current day + 7day
                j++;
            }
            minValue = Math.min(minValue, dp(j) + this.costs[k]);
        }
        this.memo[index] = minValue;
        return minValue;
    }

    public static void main(String  args[]) {
        MinimumCostForTickets obj = new MinimumCostForTickets();

        System.out.println(obj.mincostTickets(new int[]{1,4,6,7,8,20}, new int[]{2,7,15}));
    }
}
