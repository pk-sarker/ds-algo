package com.ds.practice;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different
 * day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 * Reference: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BuyAndSellStockLE {

    public static int maxProfit(int[] prices) {
        if (prices == null) {
            return 0;
        }
        if (prices.length == 0) {
            return 0;
        }

        int maxProfit = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i=0;i<prices.length;i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > maxProfit) {
                    maxProfit = prices[i] - min;
            }
        }
        return maxProfit == Integer.MIN_VALUE ? 0:maxProfit;
    }

    /**
     * Return the indices of days to buy and sell to have maximum profit
     * @param prices
     * @return
     */
    public static int[] daysForMaxProfit(int[] prices) {
        if (prices == null) {
            return new int[]{};
        }
        if (prices.length == 0) {
            return new int[]{};
        }

        int maxProfit = Integer.MIN_VALUE;
        int maxIdx = 0, minIdx = 0, tempMin = 0;

        for(int i=0;i<prices.length;i++) {
            if (prices[i] < prices[minIdx]) {
                tempMin = i;
            } else if (prices[i] - prices[tempMin] > maxProfit) {
                maxProfit = prices[i] - prices[minIdx];
                maxIdx = i;
                minIdx = tempMin;
            } else if (prices[i] - prices[minIdx] > maxProfit) {
                maxProfit = prices[i] - prices[minIdx];
                maxIdx = i;
            }
        }
        if (minIdx == maxIdx) return new int[]{};

        return new int[]{minIdx, maxIdx};
    }

    public static void main(String args[]) {
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long startTime = System.currentTimeMillis();


        System.out.println("\nInput: [7,2,9,1,6,7,2,3,9,0,3] \nOutput: " + BuyAndSellStockLE.maxProfit(new int[]{7,2,9,1,6,7,2,3,9,0,3}));

        System.out.println("\nInput: [7,2,9,1,6,7,2,3,9,0,3] \nOutput: " + Arrays.toString(BuyAndSellStockLE.daysForMaxProfit(new int[]{7,2,9,1,6,7,2,3,9,0,3})));

        System.out.println("\nInput: [1,2,3,4,5,6] \nOutput: " + Arrays.toString(BuyAndSellStockLE.daysForMaxProfit(new int[]{1,2,3,4,5,6})));

        System.out.println("\nInput: [6,5,4,3,2,1] \nOutput: " + Arrays.toString(BuyAndSellStockLE.daysForMaxProfit(new int[]{6,5,4,3,2,1})));

        System.out.println("\nInput: [1,3,5,2,0,1,4] \nOutput: " + Arrays.toString(BuyAndSellStockLE.daysForMaxProfit(new int[]{1,3,5,2,0,1,4})));

        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long stopTime = System.currentTimeMillis();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        System.out.println("\nMemory Used: " +  new DecimalFormat("#.####").format(((double) actualMemUsed / (1024d * 1024d))) + " MB");
        long elapsedTime = stopTime - startTime;
        System.out.println("\nTime: " + elapsedTime);
    }
}
