package algo.design;

import java.util.Stack;

public class DesignQueueUsingStack {

    static class MyQueue {
        Stack<Integer> s1;
        Stack<Integer> s2;
        private int front;
        /** Initialize your data structure here. */
        public MyQueue() {
            this.s1 = new Stack<>();
            this.s2 = new Stack<>();
        }

        /** Push element x to the back of queue.
         * Time Complexity: O(n)
         * */
        public void push(int x) {
            if (s1.empty()) {
                front = x;
            }

            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }

            s2.push(x);
            while (!s2.isEmpty()) {
                s1.push(s2.pop());
            }
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            int data = s1.pop();
            if (!s1.empty()){
                front = s1.peek();
            }
            return data;
        }

        /** Get the front element. */
        public int peek() {
            return this.front;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return this.s1.isEmpty();
        }
    }

    public static void main(String args[]) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        System.out.println("POP: " + obj.pop());
        System.out.println("PEEK: " + obj.peek());
        System.out.println("is empty: " + obj.empty());

    }
}
