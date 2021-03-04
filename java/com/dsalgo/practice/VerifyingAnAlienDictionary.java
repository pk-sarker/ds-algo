package com.ds.practice;

import java.util.Arrays;

/**
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if
 * the given words are sorted lexicographicaly in this alien language.
 *
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 *
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 *
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to
 * lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 *
 */
public class VerifyingAnAlienDictionary {

    public static boolean isAlienSorted(String[] words, String order) {
        if (order == null || words.length == 0) {
            return true;
        }

        int n = words.length;
        int[] dict = new int[26];

        for(int i =0;i<order.length(); i++) {
            dict[order.charAt(i)-'a'] = i;
        }
        for(int i=0;i<n-1;i++) {
            if (!isOrderedLexicographicaly(dict, words[i], words[i+1])) {
                return false;
            }
        }

        return true;
    }

    public static boolean isOrderedLexicographicaly(int[] dict, String w1, String w2) {
        int matchCount = 0;
        for(int i=0; i< Math.min(w1.length(), w2.length()); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                if (dict[w1.charAt(i)-'a'] > dict[w2.charAt(i)-'a']) {
                    return false;
                }
                break;
            } else {
                matchCount++;
            }
        }

        if ((matchCount == w1.length() || matchCount == w2.length()) && w1.length() > w2.length()) {
            return false;
        }

        return true;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: [\"hello\", \"leetcode\"], order= \"hlabcdefgijkmnopqrstuvwxyz\"\nOutput: " + VerifyingAnAlienDictionary.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        System.out.println("\nInput: [\"word\",\"world\",\"row\"], order= \"worldabcefghijkmnpqstuvxyz\"\nOutput: " + VerifyingAnAlienDictionary.isAlienSorted(new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz"));
        System.out.println("\nInput: [\"apple\",\"app\"], order= \"worldabcefghijkmnpqstuvxyz\"\nOutput: " + VerifyingAnAlienDictionary.isAlienSorted(new String[]{"apple","app"}, "abcdefghijklmnopqrstuvwxyz"));
        System.out.println("\nInput: [\"app\",\"apple\"], order= \"worldabcefghijkmnpqstuvxyz\"\nOutput: " + VerifyingAnAlienDictionary.isAlienSorted(new String[]{"app","apple"}, "abcdefghijklmnopqrstuvwxyz"));
        System.out.println("\nInput: [\"kuvp\",\"q\"], order= \"ngxlkthsjuoqcpavbfdermiywz\"\nOutput: " + VerifyingAnAlienDictionary.isAlienSorted(new String[]{"kuvp","q"}, "ngxlkthsjuoqcpavbfdermiywz"));
    }
}
