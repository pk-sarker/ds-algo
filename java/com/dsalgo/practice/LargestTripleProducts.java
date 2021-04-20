package com.dsalgo.practice;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LargestTripleProducts {
    public static int[] findMaxProduct(int[] arr) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(3, (a, b) -> b-a);
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
