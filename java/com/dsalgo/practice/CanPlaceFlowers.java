package com.dsalgo.practice;

/**
 * You have a long flowerbed in which some of the plots are planted, and some are not.
 * However, flowers cannot be planted in adjacent plots.
 *
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1
 * means not empty, and an integer n, return if n new flowers
 * can be planted in the flowerbed without violating the no-adjacent-flowers rule.
 *
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: true
 *
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: false
 *
 * Input: flowerbed = [1,0,0,0,1,0,0], n = 2
 * Output: true
 *
 * Input: flowerbed = [1], n = 0
 * Output: true
 *
 * Input: flowerbed = [0], n = 1
 * Output: true
 */
public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int bedLen = flowerbed.length;
        if (n==0) {
            return true;
        }
        int prev = 0, next = 0;
        for(int i=0;i<bedLen;i++) {
            prev = i>0 ? flowerbed[i-1]: 0;
            next = i < bedLen-1 ? flowerbed[i+1]:0;
            if ((prev + flowerbed[i] + next)==0 ) {
                n--;
                flowerbed[i] = 1;
                continue;
            }

            if (n<=0) {
                break;
            }
        }
        return n == 0;
    }

    public static void main(String args[]) {
        CanPlaceFlowers obj = new CanPlaceFlowers();
        System.out.println(obj.canPlaceFlowers(new int[]{1,0,0,0,1,0,0}, 2));
    }
}
