package algo.Searching.BinarySearch;

/**
 * Given a bitonic sequence A of N distinct elements, write a program to find a given element B in the bitonic sequence in O(logN) time.
 *
 * NOTE:
 * A Bitonic Sequence is a sequence of numbers which is first strictly increasing then after a point
 * strictly decreasing.
 *          /\
 *        /   \
 *      /      \
 *    /         \
 *  /
 *
 */

public class SearchInBitonicArray {
    int[] ar;
    int target;
    int result = -1;

    public int bitonicSearch(int[] A, int B) {
        this.ar = A;
        this.target = B;
        this.result = -1;
        bitonicSearchHelper(0, A.length-1);
        return this.result;
    }

    private void bitonicSearchHelper(int left, int right) {
        if (left == right) {
            if (this.ar[left] == this.target) {
                this.result = left;
            } else {
                return;
            }
        }
        int mid = left + (right-left)/2;

        if (this.ar[mid] == this.target) {  // target found
            this.result = mid;
            return;
        }

        if (this.ar[mid] > this.ar[mid-1]) { // max is on the right, increasing
            if (this.target > this.ar[mid]) {
                // search is on the right, mid+1 to right
                bitonicSearchHelper(mid+1, right);
            } else {
                // search in left, left to mid-1
                binarySearch(left, mid, true);
                // search in right  mid+1 to right
                binarySearch(mid+1, right, false);
            }
        } else { // max is on the left, decreasing 
            if (this.target > this.ar[mid]) {
                // search in left, left to mid
                bitonicSearchHelper(left, mid);
            } else {
                // search in left, left to mid
                binarySearch(left, mid, true);
                // search in right, mid to right
                binarySearch(mid+1, right, false);
            }
        }
    }

    private void binarySearch(int left, int right, boolean isIncreasing) {
        if (left == right) {
            if (this.ar[left] == this.target) {
                this.result = left;
            } else {
                return;
            }
        }
        int mid = left + (right-left)/2;

        if (this.ar[mid] == this.target) {
            this.result = mid;
            return;
        }

        if (this.target > this.ar[mid]) {
            if (isIncreasing) {
                binarySearch(mid+1, right, isIncreasing);
            } else {
                binarySearch(left, mid, isIncreasing);
            }
        } else {
            if (isIncreasing) {
                binarySearch(left, mid, isIncreasing);
            } else {
                binarySearch(mid+1, right, isIncreasing);
            }
        }
    }

    public static void main(String args[]) {
        SearchInBitonicArray obj = new SearchInBitonicArray();
        System.out.println("\nInput: A=[1, 3, 5, 9, 10, 20, 17, 11, 8, 2] B=20\nOutput: " + obj.bitonicSearch(new int[]{3, 9, 10, 20, 17, 5, 1}, 20));
        System.out.println("\nInput: A=[1, 3, 5, 9, 10, 20, 17, 11, 8, 4, 2] B=5\nOutput: " + obj.bitonicSearch(new int[]{1, 3, 5, 9, 10, 20, 17, 11, 8, 4, 2}, 5));
        System.out.println("\nInput: A=[1, 3, 5, 9, 10, 20, 17, 11, 8, 4, 2] B=4\nOutput: " + obj.bitonicSearch(new int[]{1, 3, 5, 9, 10, 20, 17, 11, 8, 4, 2}, 2));
        System.out.println("\nInput: A=[1, 3, 5, 9, 10, 20, 17, 11, 8, 2] B=5\nOutput: " + obj.bitonicSearch(new int[]{1, 3, 5, 9, 10, 20, 17, 11, 8, 2}, 5));
        System.out.println("\nInput: A=[1, 3, 5, 9, 10, 20, 17, 11, 8, 4, 2] B=4\nOutput: " + obj.bitonicSearch(new int[]{1, 3, 5, 9, 10, 20, 17, 11, 8, 4, 2}, 4));
        System.out.println("\nInput: A=[3, 9, 10, 20, 17, 5, 1] B=3\nOutput: " + obj.bitonicSearch(new int[]{3, 9, 10, 20, 17, 5, 1}, 3));
        System.out.println("\nInput: A=[3, 9, 10, 20, 17, 5, 1] B=1\nOutput: " + obj.bitonicSearch(new int[]{3, 9, 10, 20, 17, 5, 1}, 1));
        System.out.println("\nInput: A=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11] B=12\nOutput: " + obj.bitonicSearch(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11}, 5));
    }

}
