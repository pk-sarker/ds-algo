package com.ds.practice;

/**
 * Given two strings s and t. Count the number of occurence of t in s as follows.
 * Length of t is 3, increment count when t == s[i]s[i+2]s[i+4]
 *
 * Example:
 * s = aabbcc, t = abc
 * Output: 2
 * s = aabbccdd, t=abcd
 * Output: 2
 */
public class CountAStringInAnother {
    public int count(String s, String t) {
        int n = s.length(), m = t.length();
        int end = n - (m*2-2);
        if (end < 0) {
            return -1;
        }
        int res = 0;
        for(int i = 0; i<end; i++) {
            char[] ssub = new char[m];
            int k = 0, idx = 0;
            for(int j=0; j<m; j++) {
                ssub[k++] = s.charAt(idx+j*2);
            }
            if (t.equals(new String(ssub))) {
                res++;
            }
        }

        return res;
    }
    public static void main(String args[]) {
        CountAStringInAnother ob = new CountAStringInAnother();
        System.out.println(ob.count("aabbcc", "abc"));
        System.out.println(ob.count("aabbccdd", "abcd"));
        System.out.println(ob.count("aabbccd", "abcd"));
    }
}
