package ds.Hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * You are given a string s and an array of strings words of the same length. Return all starting indices of
 * substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any
 * intervening characters.
 *
 * You can return the answer in any order.
 *
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 *
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * Output: [6,9,12]
 *
 */
public class SubstringWithConcatenationOfWords {
    public List<Integer> findSubstring(String s, String[] words) {
        int n=s.length(), wn = words.length;
        if (n==0||wn==0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        int wl = words[0].length();
        HashMap<String, Integer> wordMap = new HashMap<>();
        for(int i=0; i<wn; i++) {
            wordMap.put(words[i], wordMap.getOrDefault(words[i], 0)+1);
        }

        for(int i = 0; i <= n - wn * wl; i++) {
            HashMap<String, Integer> seenWords = new HashMap<>();

            for(int j=0; j<wn; j++) {
                int wordStartIndex = i + j * wl;
                String word = s.substring(wordStartIndex, wordStartIndex + wl);
                seenWords.put(word, seenWords.getOrDefault(word, 0)+1);
                if (!wordMap.containsKey(word)) {
                    break;
                }


                if (seenWords.get(word) > wordMap.getOrDefault(word, 0)) {
                    break;
                }

                if (j+1 == wn) {
                    result.add(i);
                }
            }
        }

        return result;
    }
}
