package com.dsalgo.practice;

public class GuessMaster {
    private String secretWord;

    public GuessMaster(String secretWord) {
        this.secretWord = secretWord;
    }
    public int guess(String word) {
        int count = 0, len = secretWord.length();
        for(int i=0;i<len;i++) {
            if (secretWord.charAt(i) == word.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
