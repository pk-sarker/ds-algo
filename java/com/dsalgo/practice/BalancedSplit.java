package com.dsalgo.practice;

/**
 * Balanced Split
 * Given an array of integers (which may include repeated integers), determine if there's a way to split the array into two subsequences A and B such that the sum of the integers in both arrays is the same, and all of the integers in A are strictly smaller than all of the integers in B.
 * Note: Strictly smaller denotes that every integer in A must be less than, and not equal to, every integer in B.
 *
 */
public class BalancedSplit {


    public void swap(int ar[], int i, int j) {
        int t = ar[i];
        ar[i] = ar[j];
        ar[j] = t;
    }

    int[] quickPartition(int[] arr, int low, int high) {
        int pivotIndex = high;
        int pivot = arr[pivotIndex];
        //System.out.println("\nPartition\nPivot: " + pivotIndex + " (" + pivot+") ar="+ Arrays.toString(arr));

        int insertPosLeft = low;
        int insertPosRight = high;

        for (int scan = low; scan <= insertPosRight; ) {
            if (arr[scan] < pivot) { // add lower number to lowest index and increase the low pointer
                swap(arr, insertPosLeft, scan);
                insertPosLeft++;
                scan++;
            } else if (arr[scan] > pivot) { // if the number is bigger that the pivot then move the number to right
                if (insertPosRight == pivotIndex) {
                    swap(arr, insertPosRight, scan);
                    insertPosRight--;
                    scan++;
                } else {
                    swap(arr, insertPosRight, scan);
                    insertPosRight--;
                }
            } else {
                scan++;
            }
        }

        arr[insertPosLeft] = pivot;
        return new int[] {insertPosLeft, insertPosRight};
    }

    boolean balancedSplitExists(int[] arr) {
        long total = 0;
        for (int i=0; i<arr.length;i++) {
            total += arr[i];
        }
        if (total == 0) return true;
        if (total %2==1) return false;

        long totalLeft = 0;
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            int p[] = quickPartition(arr, i, j);
            int pL = p[0];  // leftmost pivot index.
            int pR = p[1];  // rightmost pivot index.
            long left = 0;

            // Count values less than or equal to pivot.
            for (int x = i; x <= pR; x++) {
                left += arr[x];
            }

            // If said values are not enough, add them to the left side and then
            // partition the right side next.
            if ((totalLeft + left) * 2 <= total) {
                totalLeft += left;
                i = pR + 1;
            } else {
                // If said values would be too high, leave the pivot values on the
                // right and partition the left side next time.
                j = pL - 1;
            }

            if (totalLeft * 2 == total) return true;
        }

        return false;
    }
    public static void main(String args[]) {
        BalancedSplit obj = new BalancedSplit();
        System.out.println(obj.balancedSplitExists(new int[]{1, 5, 7, 1}));
        System.out.println(obj.balancedSplitExists(new int[]{12, 7, 6, 7, 6}));
        System.out.println(obj.balancedSplitExists(new int[]{3, 6, 3, 4, 4}));
    }
}
