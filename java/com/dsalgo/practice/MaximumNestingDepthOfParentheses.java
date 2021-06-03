package com.dsalgo.practice;

/**
 * A string is a valid parentheses string (denoted VPS) if it meets one of the following:
 *
 * It is an empty string "", or a single character not equal to "(" or ")",
 * It can be written as AB (A concatenated with B), where A and B are VPS's, or
 * It can be written as (A), where A is a VPS.
 * We can similarly define the nesting depth depth(S) of any VPS S as follows:
 *
 * depth("") = 0
 * depth(C) = 0, where C is a string with a single character not equal to "(" or ")".
 * depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's.
 * depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
 * For example, "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.
 *
 * Given a VPS represented as string s, return the nesting depth of s.
 *
 * Solution:
 * - Only consider '(' and ')', ignore digits and signs,
 * - Only count the current open parentheses.
 * - The depth equals to the maximum open parentheses.
 */
public class MaximumNestingDepthOfParentheses {

    public static int maxDepth(String s) {
        int maxDepth = 0;
        if (s == null || s.isEmpty()) {
            return maxDepth;
        }
        int balance = 0;
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (c == '(') {
                balance++;
                maxDepth = Math.max(maxDepth, balance);
            } else if (c == ')') {
                balance--;
            }
        }
        return maxDepth;
    }
    public static void main(String args[]) {
        System.out.println( "\nInput: \"abc\" \nOutput: "+ MaximumNestingDepthOfParentheses.maxDepth("abc"));
        System.out.println( "\nInput: \"()()\" \nOutput: "+ MaximumNestingDepthOfParentheses.maxDepth("()()"));
        System.out.println( "\nInput: \"(())\" \nOutput: "+ MaximumNestingDepthOfParentheses.maxDepth("(())"));

        System.out.println( "\nInput: \"(1+(2*3)+((8)/4))+1\" \nOutput: "+ MaximumNestingDepthOfParentheses.maxDepth("(1+(2*3)+((8)/4))+1"));

        System.out.println( "\nInput: \"(1)+((2))+(((3)))\" \nOutput: "+ MaximumNestingDepthOfParentheses.maxDepth("(1)+((2))+(((3)))"));
    }
}
