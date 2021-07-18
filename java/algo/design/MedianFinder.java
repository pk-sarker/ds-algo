package algo.design;

import java.util.PriorityQueue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even,
 * there is no middle value and the median is the mean of the two middle values.
 *
 * - For example, for arr = [2,3,4], the median is 3.
 * - For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 *
 * Implement the MedianFinder class:
 *
 * - MedianFinder() initializes the MedianFinder object.
 * - void addNum(int num) adds the integer num from the data stream to the data structure.
 * - double findMedian() returns the median of all elements so far. Answers within 10-5 of the
 *      actual answer will be accepted.
 *
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 */
public class MedianFinder {
    PriorityQueue<Integer> minHeap; // min-heap to store the larger half of the input numbers
    PriorityQueue<Integer> maxHeap; // max-heap to store the smaller half of the input numbers
    // need to keep two heaps balanced,
    // minHeap may have at most one more element than maxHeap
    private boolean even = true;

    /**
     * <--- Max Heap---><--- Min Heap --->
     *                ↓  ↓
     * ------------------------------------
     * |       3  5  7 | 9  10  12        |
     * ------------------------------------
     * ADD 2
     * <--- Max Heap---><--- Min Heap --->
     *                ↓  ↓
     * ------------------------------------
     * |    2  3  5  7 | 9  10  12        |  => Size(max heap) != Size(min heap), get the largest from max heap
     * ------------------------------------
     * <--- Max Heap---><--- Min Heap --->
     *                ↓  ↓
     * -----------------------------------
     * |       2  3  5 | 7  9  10  12     |
     * -----------------------------------
     *
     */
    public MedianFinder() {
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

    // With flag for odd and even number of elements
    public void addNum2(int num) {
        this.maxHeap.offer(num);
        this.minHeap.offer(this.maxHeap.poll());

        if (!this.even) {
            this.maxHeap.offer(this.minHeap.poll());
        }
        this.even = !this.even;
    }

    public double findMedian2() {
        if (this.even) {
            return (double) (this.minHeap.peek() + this.maxHeap.peek()) / 2;
        } else {
            return (double) this.maxHeap.peek();
        }
    }

    public static void main(String args[]) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(2);
        obj.addNum(1);
        System.out.println("[2,1] median = " + obj.findMedian());
        obj.addNum(3);
        System.out.println("[2,1,3] median = " + obj.findMedian());
        obj.addNum(6);
        System.out.println("[2,1,3,6] median = " + obj.findMedian());
        obj.addNum(7);
        System.out.println("[2,1,3,6,7] median = " + obj.findMedian());
    }
}
