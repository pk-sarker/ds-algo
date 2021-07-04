package ds.Array;

/**
 * Given an array of size n, find the second maximum element in the array.
 * Input: [4,1,7,3,2,7,5,9]
 * Output: 7
 */
public class SecondMaximumValue {

    public static int findSecondMaximum(int[] arr) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;

        for(int i=0;i<arr.length;i++) {

            if (arr[i] > max1) {
                max2 = max1;
                max1 = arr[i];
            } else if (arr[i]> max2) {
                max2 = arr[i];
            }
        }
        return max2 == Integer.MIN_VALUE ? -1 : max2;
    }

    public static void main(String args[]) {
        System.out.println(SecondMaximumValue.findSecondMaximum(new int[]{4,1,7,3,2,7,5,9}));
    }
}
