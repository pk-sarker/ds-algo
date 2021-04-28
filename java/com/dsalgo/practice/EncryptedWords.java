package com.dsalgo.practice;

public class EncryptedWords {

    public String findEncryptedWord(String s) {
        // Write your code here
        return helper(s);
    }

    public String helper(String s) {
        int n = s.length();
        if (n <= 1) {
            return s;
        }
        int mid = n/2;
        if (n%2 == 0) {
            mid = (n/2) - 1;
        }
        System.out.println(">> n: " + n + " mid: " + mid);

        return s.charAt(mid) + "" + helper(s.substring(0, mid)) + "" + helper(s.substring(mid+1, n));
    }

    public static void main(String args[]) {
        EncryptedWords obj = new EncryptedWords();
        //System.out.println(obj.findEncryptedWord("abc"));


        //System.out.println(obj.findEncryptedWord("abcd"));
        //System.out.println(obj.findEncryptedWord("abcxcba"));
        System.out.println(obj.findEncryptedWord("facebook"));
    }
}
