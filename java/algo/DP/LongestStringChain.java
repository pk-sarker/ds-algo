package algo.DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of words where each word consists of lowercase English letters.
 *
 * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without
 * changing the order of the other characters to make it equal to wordB.
 *
 * For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
 * A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor
 * of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
 *
 * Return the length of the longest possible word chain with words chosen from the given list of words.
 *
 * Input: words = ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
 *
 * Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * Output: 5
 * Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
 *
 * Input: words = ["abcd","dbqca"]
 * Output: 1
 * Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
 * ["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 */
public class LongestStringChain {

    public static int longestStrChain(String[] words) {
        // sorting the array in ascending order of words length, smaller words at the beginning
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        // <word, max chain length>
        Map<String, Integer> memo = new HashMap<>();
        int longestChain = 1;
        for(int i=0;i<words.length;i++) {
            int currentChainLen = 1;

            for(int j=0;j<words[i].length();j++) {
                StringBuilder word = new StringBuilder(words[i]);
                word.deleteCharAt(j); // remove char at j
                // memo.getOrDefault(word.toString(), 0) => previous chain length
                currentChainLen = Math.max(currentChainLen, memo.getOrDefault(word.toString(), 0)+1);
            }
            memo.put(words[i], currentChainLen); // save
            longestChain = Math.max(longestChain, currentChainLen);
        }

        return longestChain;
    }

    public static void main(String args[]) {
        String[] words = new String[]{"bca","a","b","bda","ba", "bdca"};
        System.out.println(Arrays.toString(words));
        System.out.println(LongestStringChain.longestStrChain(words));


        String[] words2 = new String[]{"xbc","pcxbcf","xb","cxbc","pcxbc"};
        System.out.println(Arrays.toString(words2));
        System.out.println(LongestStringChain.longestStrChain(words2));

        String[] words3 = new String[]{"abcd","dbqca"};
        System.out.println(Arrays.toString(words3));
        System.out.println(LongestStringChain.longestStrChain(words3));

    }
}
