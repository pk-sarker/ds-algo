package com.dsalgo.practice;

import java.util.PriorityQueue;

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
