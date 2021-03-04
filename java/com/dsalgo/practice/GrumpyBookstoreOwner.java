package com.ds.practice;

/**
 * Today, the bookstore owner has a store open for customers.length minutes.  Every minute,
 * some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.
 *
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute,
 * grumpy[i] = 1, otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of
 * that minute are not satisfied, otherwise they are satisfied.
 *
 * The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight,
 * but can only use it once.
 *
 * Return the maximum number of customers that can be satisfied throughout the day.
 *
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 16
 *
 * Input: customers = [1,0,1,10,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 21
 */
public class GrumpyBookstoreOwner {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int satisfiedCustomer  = 0, satisfiedCustomerOnGrumpy =0;
        for(int i = 0; i< grumpy.length; i++) {
            if (grumpy[i]==0) {
                satisfiedCustomer += customers[i];
            }
        }
        int start =0, end = 0;
        int satisfiedCustomerWhenGrumpy = 0, maxSatisfied = Integer.MIN_VALUE;

        while (end < grumpy.length) {
            if(grumpy[end] == 1) {
                satisfiedCustomerWhenGrumpy += customers[end];
            }
            if (end - start + 1 < X) {
                end++;
                continue;
            } else if(end - start + 1 == X) {
                maxSatisfied = Math.max(maxSatisfied, satisfiedCustomerWhenGrumpy);
                if(grumpy[start] == 1) {
                    satisfiedCustomerWhenGrumpy -= customers[start];
                }

                start++;
                end++;
            }
        }
        return satisfiedCustomer + maxSatisfied;
    }

    public static void main(String args[]) {
        GrumpyBookstoreOwner ob  = new GrumpyBookstoreOwner();
        System.out.println("\nInput: Customers=[1,0,1,2,1,1,7,5] Grumpy=[0,1,0,1,0,1,0,1], X = 3\nOutput: " + ob.maxSatisfied(new int[]{1,0,1,2,1,1,7,5}, new int[]{0,1,0,1,0,1,0,1}, 3));
        System.out.println("\nInput: Customers=[1,0,1,10,1,1,7,5] Grumpy=[0,1,0,1,0,1,0,1], X = 3\nOutput: " + ob.maxSatisfied(new int[]{1,0,1,10,1,1,7,5}, new int[]{0,1,0,1,0,1,0,1}, 3));
    }
}
