package ds.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MedianStream {

    public static int[] findMedian(int[] arr) {
        // high -> low, peak is low
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // low -> high, peak is high
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);

        int[] medians = new int[arr.length];

        // add a n in (l - h),
        // get h - push to (h-l)
        // if sz l-h > sz h-l
        //   get l from l-h, and push to h-l
        for(int i=0; i < arr.length; i++) {
            maxHeap.offer(arr[i]);
            minHeap.offer(maxHeap.poll());
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            // [1] => h - low
            // [1,2] = p1+p2/2
            // [1,2,3] [] h
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

        System.out.println(">> " + Arrays.toString(MedianStream.findMedian(new int[]{1,2,3,4,5})));
    }

}
