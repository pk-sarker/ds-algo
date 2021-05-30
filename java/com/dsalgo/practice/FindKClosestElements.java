package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {

    /**
     * Binary Search + Sliding Window
     * Problem that involves a sorted array, we can consider binary search.
     * First find the closest number to x in arr.
     * Logically, the second closest number to x must be directly beside the first number,
     * either to the left or right. Then, the third closest number to x must be either
     * to the left of the first number or to the right of the second number. This pattern continues, and
     * is true because the input is sorted.
     *
     * Using two pointers, we can maintain a sliding window that will expand to
     * contain the k closest elements to x.
     *
     * Algorithm:
     * 1. base case: arr.length == k, return arr.
     * 2. Use binary search to find the index of the closest element to x in arr.
     * 3. Initailize two pointers left and right, with left set equal to this index,
     *    and right equal to this index plus one.
     * 4. While the window's size is less than k, check which number is closer to
     *    x: arr[left] or arr[right]. Whichever pointer has the closer number,
     *    move that pointer towards the edge to include that element in our output.
     * 5. Return the elements inside arr contained within the window defined between
     *    left and right.
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>();

        // Base case
        if (arr.length == k) {
            for (int i = 0; i < k; i++) {
                result.add(arr[i]);
            }

            return result;
        }

        // Binary search to find the closest element
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] > x) {
                right = mid - 1;
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else {
                break;
            }
        }

        // Initialize our sliding window's bounds
        left = mid - 1;
        right = left + 1;

        // While the window size is less than k
        while (right - left - 1 < k) {
            // Be careful to not go out of bounds
            if (left == -1) {
                right += 1;
                continue;
            }

            // Expand the window towards the side with the closer number
            // Be careful to not go out of bounds with the pointers
            if (right == arr.length || Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                left -= 1;
            } else {
                right += 1;
            }
        }

        // Build and return the window
        for (int i = left + 1; i < right; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    /**
     * Time complexity: O(log(N−k)+k).
     *
     * Although finding the bounds only takes O(log(N−k)) time from the binary search,
     * it still costs us O(k) to build the final output.
     *
     * Space complexity: O(1).
     *
     *  If there needs to be k elements, then the left bound's upper limit is arr.length - k
     *  because if it were any further to the right, you would run out of elements to include in the final answer.
     *
     *  Binary search is typically used to find if an element exists or where an element belongs in a sorted array.
     *  The beauty of algorithms lies in how abstract they are - with some clever thinking, we can apply
     *  binary search in a unique way to move our left and right pointers closer and closer to the
     *  left bound of our answer.
     *
     *  Let's consider two indices at each binary search operation, the usual mid, and some index mid + k.
     *  The relationship between these indices is significant because only one of them could possibly be in a final answer.
     *  For example, if mid = 2, and k = 3, then arr[2] and arr[5] could not possibly both be in the answer,
     *  since that would require taking 4 elements [arr[2], arr[3], arr[4], arr[5]].
     *
     * This leads us to the question: how do we move our pointers left and right? If the element
     * at arr[mid] is closer to x than arr[mid + k], then that means arr[mid + k], as well as
     * every element to the right of it can never be in the answer. This means we should move
     * our right pointer to avoid considering them. The logic is the same vice-versa
     * - if arr[mid + k] is closer to x, then move the left pointer.
     */
    public List<Integer> findClosestElementsOpt(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        int n = arr.length;
        // if x is lower then the first element
        // then get first k elements
        if (x<=arr[0]) {
            for(int i=0;i<k;i++) {
                result.add(arr[i]);
            }
            return result;
        }

        // if x is greater than the last element
        // then get the last k elements
        if (x>= arr[n-1]) {
            for(int i=n-k;i<n;i++) {
                result.add(arr[i]);
            }
            return result;
        }

        // if x is in the middle then use binary search
        // to find the position and explore both direction

        int l=0,r=n-k;
        while(l<r) {
            int mid = l + (r-l)/2;
            if (x-arr[mid] > arr[mid+k]-x) {
                l = mid+1;
            } else {
                r = mid;
            }
        }
        int i=l;
        while(i<l+k) {
            result.add(arr[i++]);
        }
        return result;
    }

    public static void main(String args[]) {
        FindKClosestElements obj = new FindKClosestElements();
        System.out.println(obj.findClosestElementsOpt(new int[]{0,1,2,4,5,6}, 4, -1).toString());
        System.out.println(obj.findClosestElementsOpt(new int[]{0,1,2,4,5,6}, 4, 7).toString());
        System.out.println(obj.findClosestElementsOpt(new int[]{0,1,2,4,5,6}, 4, 3).toString());
    }
}
