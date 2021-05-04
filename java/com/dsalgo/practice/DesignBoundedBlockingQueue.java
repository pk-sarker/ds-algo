package com.dsalgo.practice;

import java.util.Deque;
import java.util.LinkedList;

public class DesignBoundedBlockingQueue {

    static class BoundedBlockingQueue {
        private int capacity = 0;
        private Deque<Integer> queue = new LinkedList<>();
        private Object lock;

        public BoundedBlockingQueue(int capacity) {
            this.capacity = capacity;
            this.lock = new Object();
        }

        public void enqueue(int element) throws InterruptedException {
            synchronized(this.lock){
                while (this.queue.size() == this.capacity) {
                    this.lock.wait();
                }
                this.queue.add(element);
                this.lock.notify();
            }
        }

        public int dequeue() throws InterruptedException {
            int element = 0;
            synchronized(this.lock) {
                while(this.queue.isEmpty()) {
                    this.lock.wait();
                }
                element = this.queue.poll();
                this.lock.notify();
            }
            return element;
        }

        public int size() {
            return this.queue.size();
        }
    }

    public static void main(String args[]) {
        BoundedBlockingQueue obj = new BoundedBlockingQueue(2);
        try {
            System.out.println("Enqueue 1");
            obj.enqueue(1);
            System.out.println("Dequeue: " + obj.dequeue());
            System.out.println("Enqueue 5");
            obj.enqueue(5);
            System.out.println("Enqueue 7");
            obj.enqueue(7);
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(9000);
                        System.out.println("Dequeue: " + obj.dequeue());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            System.out.println("Enqueue 6");
            obj.enqueue(6);

            System.out.println("Dequeue: " + obj.dequeue());
            System.out.println("Dequeue: " + obj.dequeue());
            System.out.println("Enqueue 9");
            obj.enqueue(9);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
