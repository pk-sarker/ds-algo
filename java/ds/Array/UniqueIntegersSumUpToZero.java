package ds.Array;

import java.util.Arrays;

/**
 * Given an integer n, return any array
 * containing n unique integers such that they add up to 0
 */
public class UniqueIntegersSumUpToZero {

    public static int[] sumZero(int n) {

        int[] uniqueInts = new int[n];
        int j = 0;
        for(int i=1; i< n/2+1; i++) {
            uniqueInts[j++] = -i;
            uniqueInts[j++] = i;
        }
        if (n%2 == 1) {
            uniqueInts[j] = 0;
        }

        return uniqueInts;
    }

    public static void main(String args[]){
        System.out.println("Input: n=5, \nOutput: " + Arrays.toString(UniqueIntegersSumUpToZero.sumZero(5)));
        System.out.println("Input: n=6, \nOutput: " + Arrays.toString(UniqueIntegersSumUpToZero.sumZero(6)));
    }
}
