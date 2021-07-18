package algo.design;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 *  - MinStack() initializes the stack object.
 *  - void push(val) pushes the element val onto the stack.
 *  - void pop() removes the element on the top of the stack.
 *  - int top() gets the top element of the stack.
 *  - int getMin() retrieves the minimum element in the stack.
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 */
public class MinStack {
    private Stack<int[]> stack;
    public MinStack() {
        this.stack = new Stack<>();
    }

    public void push(int x) {
        if (this.stack.isEmpty()) {
            this.stack.push(new int[]{x, x});
        } else {
            int lastMin = this.stack.peek()[1];
            this.stack.push(new int[]{x, Math.min(lastMin, x)});
        }
    }

    public void pop() {
        this.stack.pop();
    }

    public int top() {
        if (this.stack.isEmpty()) {
            return -1;
        }
        return this.stack.peek()[0];
    }

    public int getMin() {
        if (this.stack.isEmpty()) {
            return -1;
        }
        return this.stack.peek()[1];
    }

    public static void main(String args[]) {
        MinStack smtack = new MinStack();

        smtack.push(-2);
        System.out.println("push(-2)");
        smtack.push(0);
        System.out.println("push(0)");
        smtack.push(-3);
        System.out.println("push(-3)");
        System.out.println("getMin(): " + smtack.getMin());
        smtack.pop();
        System.out.println("top(): " + smtack.top());
        System.out.println("getMin(): " + smtack.getMin());
        smtack.push(4);
        System.out.println("push(4)");
        smtack.push(-4);
        System.out.println("push(-4)");
        System.out.println("getMin(): " + smtack.getMin());
    }
}
