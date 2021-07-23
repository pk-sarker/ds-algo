package algo.design;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack
 * should support all the functions of a normal stack (push, top, pop, and empty).
 *
 * Implement the MyStack class:
 *
 *  - void push(int x) Pushes element x to the top of the stack.
 *  - int pop() Removes the element on the top of the stack and returns it.
 *  - int top() Returns the element on the top of the stack.
 *  - boolean empty() Returns true if the stack is empty, false otherwise.
 *
 * Notes:
 * You must use only standard operations of a queue, which means that only push to back, peek/pop from front,
 * size and is empty operations are valid.
 * Depending on your language, the queue may not be supported natively. You may simulate a queue using a list
 * or deque (double-ended queue) as long as you use only a queue's standard operations.
 */
public class StackUsingQueue {
    public static class MyStack {
        /** Initialize your data structure here. */
        private Queue<Integer> q1;
        private Queue<Integer> q2;

        public MyStack() {
            this.q1 = new ArrayDeque<>();
            this.q2 = new ArrayDeque<>();
        }

        /** Push element x onto stack.
         * O(1)
         * */
        public void push(int x) {
            this.q1.add(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            while(q1.size()>1) {
                q2.offer(q1.poll());
            }
            int data = q1.poll();
            while(!q2.isEmpty()) {
                q1.offer(q2.poll());
            }
            return data;
        }

        /** Get the top element. */
        public int top() {
            int top = Integer.MIN_VALUE;
            while(!q1.isEmpty()) {
                top = q1.poll();
                q2.offer(top);
            }
            while(!q2.isEmpty()) {
                q1.offer(q2.poll());
            }
            return top;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return this.q1.isEmpty();
        }

        public void print() {
            System.out.println(this.q1.toString());
        }
    }

    public static void main(String args[]) {
        MyStack obj = new MyStack();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        obj.print();
        System.out.println("Top: " + obj.top());
        System.out.println("Pop: " + obj.pop());
        System.out.println("Top: " + obj.top());
        System.out.println("Is Empty: " + obj.empty());
    }
}
