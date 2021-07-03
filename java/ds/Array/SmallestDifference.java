package ds.Array;

import java.util.Arrays;

public class SmallestDifference {

    public static int[] smallestDifference(int[] arr1, int[] arr2) {
        Arrays.sort(arr1); // O(n log n)
        Arrays.sort(arr2); // O(n log n)
        int smallestDiff = Integer.MAX_VALUE;
        int currentDiff = Integer.MAX_VALUE;

        int idxAr1 = 0, idxAr2 = 0;
        int[] smallestPair = new int[2];
        while(idxAr1 < arr1.length && idxAr2 < arr2.length) {
            int firstNum = arr1[idxAr1];
            int secondNum = arr2[idxAr2];
            if (firstNum == secondNum) {
                return new int[]{ firstNum, secondNum };
            } else if (firstNum > secondNum) {
                currentDiff = firstNum - secondNum;
                idxAr2++;
            } else {
                currentDiff = secondNum - firstNum;
                idxAr1++;
            }

            if (currentDiff < smallestDiff) {
                smallestDiff = currentDiff;
                smallestPair = new int[]{firstNum, secondNum};
            }
        }

        return smallestPair;
    }
    public static void main(String args[]) {
        System.out.println(Arrays.toString(SmallestDifference.smallestDifference(new int[]{-1, 5, 10, 20, 28, 3}, new int[]{26, 134, 135, 15, 17})));
    }
}
