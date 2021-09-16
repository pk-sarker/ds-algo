package ds.Array;

/**
 * Solution:
 * Lets think about the values,
 *  - the smallest value could be 0, if all the number are same
 *  - the largest value could be n, and this is the upper bound with n numbers
 *      - if the first and last number is not the same then max distance will be n
 *
 * So the idea is we try to find largest distance n, if can't find one then we will try the next
 * largest distance, n-1.
 *
 * First we check if first and last number is the same or not, if not then return the max distance n
 * Otherwise we look from left and right.
 *
 * When we look from left we fix start as the first element and try to find a number from right end which
 * is not same as the start. When we find one then calculate the distance.
 *
 * Do the same when looking from right, fix the end position to the last number as right end and
 * try to find a start from left which is not same as the end. On finding one such number calculate the
 * distance.
 *
 * Now we take the max of both distances and return.
 *
 * Time Complexity: O(n) // 2 pass
 * Space Complexity: O(1)
 *
 */
public class MaximumDistanceBetweenUniqueNumbers {

    static int maxDistance(int arr[]) {
        int n = arr.length;

        if (arr[0] != arr[n-1]) {
            return n-1;
        }
        // fix a start
        int end = n-1;
        while(end > 0) {
            if (arr[end] != arr[0]) {
                break;
            }
            end--;
        }
        int distFromLeft = (end == 0 ? -1: end);

        int start = 0;
        while(start < n-1) {
            if (arr[start] != arr[n-1]) {
                break;
            }
            start++;
        }
        int distFromRight = start == n-1 ?  -1: (n - 1 - start);
        return Math.max(distFromLeft, distFromRight);
    }
    public static void main(String args[]) {
        System.out.println("Input: [4, 3, 2, 1, 2, 1]\nMax Distance: "+MaximumDistanceBetweenUniqueNumbers.maxDistance(new int[]{4, 3, 2, 1, 2, 1}));
        System.out.println("Input: [4, 3, 2, 1, 4, 4]\nMax Distance: "+MaximumDistanceBetweenUniqueNumbers.maxDistance(new int[]{4, 3, 2, 1, 4, 4}));
        System.out.println("Input: [4, 4, 4, 1, 4, 4]\nMax Distance: "+MaximumDistanceBetweenUniqueNumbers.maxDistance(new int[]{4, 4, 4, 1, 4, 4}));
        System.out.println("Input: [3, 3, 1, 3, 3]\nMax Distance: "+MaximumDistanceBetweenUniqueNumbers.maxDistance(new int[]{3, 3, 1, 3, 3}));
    }
}
