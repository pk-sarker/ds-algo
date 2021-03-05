package com.dsalgo.practice;

import java.util.HashMap;
import java.util.Stack;

/**
 * Given a string s containing just the characters `(`, `)`, `{`, `}`, `[` and `]`, determine if the input string is valid.
 *
 * An input string is valid if:
 * - Open brackets must be closed by the same type of brackets.
 * - Open brackets must be closed in the correct order.
 *
 * Input: s = "()"
 * Output: true
 *
 * Input: s = "()[]{}"
 * Output: true
 *
 * Input: s = "(]"
 * Output: false
 *
 * Input: s = "([)]"
 * Output: false
 *
 * Input: s = "{[]}"
 * Output: true
 *
 * Input: s = "[{()}({{}})[]]"
 * Output: true
 *
 * Input: s = "[{()}({{}})[]"
 * Output: false
 *
 */
public class ValidateParentheses {

    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        int n = s.length();
        if (n == 0) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put(')', '(');
        bracketMap.put('}', '{');
        bracketMap.put(']', '[');

        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            if (bracketMap.containsKey(c)) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != bracketMap.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    public static void main(String args[]) {
        System.out.println("\nInput: \"\" \nOutput: " + ValidateParentheses.isValid(""));
        System.out.println("\nInput: \"()\" \nOutput: " + ValidateParentheses.isValid("()"));
        System.out.println("\nInput: \"{}\" \nOutput: " + ValidateParentheses.isValid("{}"));
        System.out.println("\nInput: \"[]\" \nOutput: " + ValidateParentheses.isValid("[]"));
        System.out.println("\nInput: \"()[]{}\" \nOutput: " + ValidateParentheses.isValid("()[]{}"));

        System.out.println("\nInput: \"(]\" \nOutput: " + ValidateParentheses.isValid("(]"));
        System.out.println("\nInput: \"([)]\" \nOutput: " + ValidateParentheses.isValid("([)]"));
        System.out.println("\nInput: \"{[]}\" \nOutput: " + ValidateParentheses.isValid("{[]}"));

        System.out.println("\nInput: \"[{()}({{}})[]]\" \nOutput: " + ValidateParentheses.isValid("[{()}({{}})[]]"));
        System.out.println("\nInput: \"[{()}({{}})[]\" \nOutput: " + ValidateParentheses.isValid("[{()}({{}})[]"));
    }
}
