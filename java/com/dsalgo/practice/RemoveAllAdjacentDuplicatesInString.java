package com.dsalgo.practice;

import java.util.Stack;

/**
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent
 * and equal letters from s and removing them, causing the left and the right side of the deleted
 * substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It is guaranteed that
 * the answer is unique.
 */
public class RemoveAllAdjacentDuplicatesInString {

    public String removeDuplicates(String s, int k) {
        if (s == null || s.isEmpty() || s.length() < k) {
            return s;
        }
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        int[] counts = new int[n];

        for(int i=0;i<sb.length();++i) {
            if (i==0 || sb.charAt(i) != sb.charAt(i-1)) {
                counts[i] = 1;
            } else {
                counts[i] = counts[i-1]+1;
                if (counts[i]==k) {
                    // delete k chars
                    sb.delete(i-k+1, i+1);
                    i = i-k;
                }
            }
        }
        return sb.toString();
    }

    // We will use stack to store the counts
    // If stack is empty or character at i-th and i-1-th position are node same then we push 1 to stack
    // otherwise we update the top of the stack, increment the top item.
    public String removeDuplicatesUsingStack(String s, int k) {
        if (s == null || s.isEmpty() || s.length() < k) {
            return s;
        }
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> count = new Stack<>();

        for(int i=0;i<sb.length();++i) {
            if (i==0 || sb.charAt(i) != sb.charAt(i-1)) {
                count.push(1);
            } else {
                int incrementedCount = count.pop() +1;
                if (incrementedCount==k) {
                    // delete k chars
                    sb.delete(i-k+1, i+1);
                    i = i-k;
                } else {
                    count.push(incrementedCount);
                }
            }
        }
        return sb.toString();
    }


    // Instead of removing characters from the string we can use pointers
    // to maintain the sequence.
    // The idea is we will use one pointer that will point to the lowest position in the string
    // before which there are not k consecutive duplicate characters. And another pointer that
    // will be exploring to the right
    public String removeDuplicatesOptimized(String s, int k) {
        if (s == null || s.isEmpty() || s.length() < k) {
            return s;
        }
        int n = s.length();
        char[] chAr = s.toCharArray();
        Stack<Integer> count = new Stack<>();
        int slowPtr = 0;
        for(int fastPtr=0;fastPtr<n;fastPtr++,slowPtr++) {
            chAr[slowPtr] = chAr[fastPtr];
            if (slowPtr==0 || chAr[slowPtr] != chAr[slowPtr-1]) {
                count.push(1);
            } else {
                int incrementedCount = count.pop() +1;
                if (incrementedCount==k) {
                    // delete k chars
                    slowPtr = slowPtr-k;
                } else {
                    count.push(incrementedCount);
                }
            }
        }
        return new String(chAr, 0, slowPtr);
    }
    public static void main(String args[]) {
        RemoveAllAdjacentDuplicatesInString obj = new RemoveAllAdjacentDuplicatesInString();

        System.out.println(" \"deeedbbcccbdaa\" k=3 -> " + obj.removeDuplicates("deeedbbcccbdaa", 3));
        System.out.println(" \"deeedbbcccbdaa\" k=3 -> " + obj.removeDuplicatesUsingStack("deeedbbcccbdaa", 3));
        System.out.println(" \"deeedbbcccbdaa\" k=3 -> " + obj.removeDuplicatesOptimized("deeedbbcccbdaa", 3));

        System.out.println(" \"aac\" k=3 -> " + obj.removeDuplicates("aac", 3));
        System.out.println(" \"aac\" k=3 -> " + obj.removeDuplicatesUsingStack("aac", 3));
        System.out.println(" \"aac\" k=3 -> " + obj.removeDuplicatesOptimized("aac", 3));

        System.out.println(" \"aaa\" k=3 -> " + obj.removeDuplicates("aaa", 3));
        System.out.println(" \"aaa\" k=3 -> " + obj.removeDuplicatesUsingStack("aaa", 3));
        System.out.println(" \"aaa\" k=3 -> " + obj.removeDuplicatesOptimized("aaa", 3));
    }
}
