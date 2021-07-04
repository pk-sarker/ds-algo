package ds.Stack;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * - push(x) -- Push element x onto stack.
 * - pop() -- Removes the element on top of the stack.
 * - top() -- Get the top element.
 * - getMin() -- Retrieve the minimum element in the stack.
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
        stack = new Stack<int[]>();
    }

    public void push(int x) {
        int min = x;
        if (stack.isEmpty()) {
            stack.push(new int[]{x, x});
        } else {
            stack.push(new int[]{x, Math.min(x, stack.peek()[1])});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
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
