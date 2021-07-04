package ds.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given a stream of integers represented as arr[]. For each index i from 0 to n-1, print the multiplication of
 * largest, second largest, third largest element of the subarray arr[0…i]. If i < 2 print -1.
 *
 * arr = [1, 2, 3, 4, 5]
 * output = [-1, -1, 6, 24, 60]
 * The 3rd element of output is 3*2*1 = 6, the 4th is 4*3*2 = 24, and the 5th is 5*4*3 = 60.
 *
 * arr = [2, 1, 2, 1, 2]
 * output = [-1, -1, 4, 4, 8]
 * The 3rd element of output is 2*2*1 = 4, the 4th is 2*2*1 = 4, and the 5th is 2*2*2 = 8.
 */
public class LargestTripleProducts {

    public static int[] findMaxProduct(int[] arr) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(3, (a,b) -> b-a);
        int n = arr.length;
        int[] products = new int[n];
        products[0] = -1;
        products[1] = -1;
        heap.offer(arr[0]);
        heap.offer(arr[1]);
        for(int i=2; i<n; i++) {
            heap.offer(arr[i]);
            int[] maxNum = new int[3];
            int product = 1;
            for(int j=0;j<3;j++) {
                maxNum[j] = heap.poll();
                product *= maxNum[j];
            }
            products[i] = product;
            for(int j=2; j>=0; j--) {
                heap.offer(maxNum[j]);
            }
        }
        return products;
    }
    public static void main(String args[]) {
        System.out.println("\nInput: [1, 2, 3, 4, 5]\nOutput: " + Arrays.toString(LargestTripleProducts.findMaxProduct(new int[]{1, 2, 3, 4, 5})));
        System.out.println("\nInput: [2, 1, 2, 1, 2]\nOutput: " + Arrays.toString(LargestTripleProducts.findMaxProduct(new int[]{2, 1, 2, 1, 2})));
    }
}
