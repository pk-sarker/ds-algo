package ds.Heap;

import java.util.PriorityQueue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value
 * and the median is the mean of the two middle values.
 *
 * - For example, for arr = [2,3,4], the median is 3.
 * - For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 *
 * Implement the MedianFinder class:
 *
 *  - MedianFinder() initializes the MedianFinder object.
 *  - void addNum(int num) adds the integer num from the data stream to the data structure.
 *  - double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be
 *      accepted.
 *
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 */
public class MedianFromDataStream {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    public MedianFromDataStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a,b) -> b-a);
    }

    public void addNum(int num) {
        this.maxHeap.offer(num);
        this.minHeap.offer(this.maxHeap.poll());
        if (this.minHeap.size() > this.maxHeap.size()) {
            this.maxHeap.offer(this.minHeap.poll());
        }
    }

    public double findMedian() {
        if (this.minHeap.size()==this.maxHeap.size()) {
            return (double) (this.minHeap.peek() + this.maxHeap.peek()) / 2;
        } else {
            return (double) this.maxHeap.peek();
        }
    }
    public static void main(String args[]) {
        MedianFromDataStream obj = new MedianFromDataStream();
        System.out.println("addNum(1)");
        obj.addNum(1);
        System.out.println("addNum(2)");
        obj.addNum(2);
        System.out.println("findMedian: " + obj.findMedian());
        System.out.println("addNum(3)");
        obj.addNum(3);
        System.out.println("findMedian: " + obj.findMedian());
        System.out.println("addNum(4)");
        obj.addNum(4);
        System.out.println("findMedian: " + obj.findMedian());
    }
}
