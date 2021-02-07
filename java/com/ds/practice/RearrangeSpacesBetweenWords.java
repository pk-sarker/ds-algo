package com.ds.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a string text of words that are placed among some number of spaces.
 * Each word consists of one or more lowercase English letters and are separated by
 * at least one space. It's guaranteed that text contains at least one word.
 *
 * Rearrange the spaces so that there is an equal number of spaces between every pair
 * of adjacent words and that number is maximized. If you cannot redistribute all the
 * spaces equally, place the extra spaces at the end, meaning the returned string
 * should be the same length as text.
 *
 * Return the string after rearranging the spaces.
 *
 * Input: text = "  this   is  a sentence "
 * Output: "this   is   a   sentence"
 * Explanation: There are a total of 9 spaces and 4 words.
 * We can evenly divide the 9 spaces between the words: 9 / (4-1) = 3 spaces.
 *
 * Input: text = " practice   makes   perfect"
 * Output: "practice   makes   perfect "
 * Explanation: There are a total of 7 spaces and 3 words. 7 / (3-1) = 3 spaces plus 1 extra space.
 * We place this extra space at the end of the string.
 *
 * Input: text = "hello   world"
 * Output: "hello   world"
 *
 * Input: text = "  walks  udp package   into  bar a"
 * Output: "walks  udp  package  into  bar  a "
 *
 * Input: text = "a"
 * Output: "a"
 */
public class RearrangeSpacesBetweenWords {

    public static String reorderSpaces(String text) {

        int n = text.length(), totalSpace = 0, wordCunt = 0;
        List<String> words = new ArrayList<>();
        int startIdx = -1, endIdx = -1;
        for(int i=0; i<n; i++){
            if (text.charAt(i) == ' ') {
                totalSpace++;
            }
            if (i==0 && text.charAt(i) != ' ' || (i > 0 && text.charAt(i-1) == ' ' && text.charAt(i) != ' ')) {
                startIdx = i;
            }
            if (i==n-1 && text.charAt(i) != ' '  || (i < n-1 && text.charAt(i) != ' ' && text.charAt(i+1) == ' ')) {
                endIdx = i;
                words.add(text.substring(startIdx, endIdx+1));
                startIdx =  -1;
                endIdx = -1;
            }
        }
        int space = words.size()-1 == 0 ? 0:totalSpace/(words.size()-1);
        int remainingSpace = totalSpace - space * (words.size()-1);
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<words.size(); i++) {
            sb.append(words.get(i));
            if ( i < words.size() - 1) {
                for(int j=0; j<space; j++) {
                    sb.append(' ');
                }
            } else {
                if (remainingSpace > 0) {
                    while(remainingSpace>0) {
                        sb.append(' ');
                        remainingSpace--;
                    }
                }
            }
        }

        System.out.println("Space: " + totalSpace + " Words:  " + words.size() + " >  " + words);
        return sb.toString();
    }

    public static void main(String args[]) {
        System.out.println(RearrangeSpacesBetweenWords.reorderSpaces("  this   is  a sentence "));
    }
}
