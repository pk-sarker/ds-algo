package com.dsalgo.practice;

import java.util.Stack;

public class BasicCalculator2 {

    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();
        Stack<Integer> stack = new Stack<>();
        char operator = '+';
        int currentNumber = 0;
        for(int i=0;i<n;i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c-'0');
                if (i < n-1) {
                    continue;
                }
            }
            // current char is not a number, it s operation
            //
            if (Character.isWhitespace(c)) {
                if (i < n-1) {
                    continue;
                }
            }
            if (operator=='+') {
                stack.push(currentNumber);
            }
            if (operator=='-') {
                stack.push(-currentNumber);
            }
            if (operator=='*') {
                stack.push(stack.pop() * currentNumber);
            }
            if (operator=='/') {
                stack.push(stack.pop() / currentNumber);
            }

            operator = c;
            currentNumber = 0;
        }

        int result = 0;
        while(!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    // optimize and faster
    // keep track of last and current number
    // update the result when + or - is found
    // for * and / just keep updating the last number
    public int calculate2(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();
        char operator = '+';
        int currentNumber = 0;
        int lastNumber = 0;
        int result = 0;
        for(int i=0;i<n;i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c-'0');
                if (i < n-1) {
                    continue;
                }
            }
            // current char is not a number, it s operation
            //
            if (Character.isWhitespace(c)) {
                if (i < n-1) {
                    continue;
                }
            }
            if (operator=='+' || operator=='-') {
                result += lastNumber;
                lastNumber = (operator == '+') ? currentNumber: -currentNumber;
            } else if (operator=='*') {
                lastNumber = lastNumber * currentNumber;
            } else if (operator=='/') {
                lastNumber = lastNumber / currentNumber;
            }

            operator = c;
            currentNumber = 0;
        }


        return result+lastNumber;
    }

    public static void main(String args[]) {
        BasicCalculator2 obj = new BasicCalculator2();
        System.out.println("Input: 2+12*5*2-100/5+3\nOutput: Expected 105 | Actual " + obj.calculate("2+12*5*2-100/5+3"));
        System.out.println("2: Input: 2+12*5*2-100/5+3\nOutput: Expected 105 | Actual " + obj.calculate2("2+12*5*2-100/5+3"));
    }
}
