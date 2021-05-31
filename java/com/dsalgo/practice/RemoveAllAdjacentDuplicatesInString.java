package com.dsalgo.practice;

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

    public String removeDuplicatesOptimized(String s, int k) {
        return s;
    }
    public static void main(String args[]) {
        RemoveAllAdjacentDuplicatesInString obj = new RemoveAllAdjacentDuplicatesInString();

        System.out.println(" \"deeedbbcccbdaa\" -> " + obj.removeDuplicates("deeedbbcccbdaa", 3));
        System.out.println(" \"aa\" -> " + obj.removeDuplicates("aa", 3));
    }
}
