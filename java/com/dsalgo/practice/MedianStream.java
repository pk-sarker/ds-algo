package com.dsalgo.practice;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MedianStream {

    public static int[] findMedian(int[] arr) {
        // Write your code here
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);

        int[] medians = new int[arr.length];

        for(int i=0; i < arr.length; i++) {
            maxHeap.offer(arr[i]);
            minHeap.offer(maxHeap.poll());
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }

            if (minHeap.size() == maxHeap.size()) {
                medians[i] = (minHeap.peek() + maxHeap.peek())/2;
            } else {
                medians[i] = maxHeap.peek();
            }
        }
        return medians;
    }

    public static void main(String args[]) {
        System.out.println(">> " + Arrays.toString(MedianStream.findMedian(new int[]{5, 15, 1, 3})));
    }

}
