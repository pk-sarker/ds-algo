package pattern.SlidingWindow;

import java.util.*;

/**
 * Given a list of words, return n words before and after a particular word (including the word) as efficiently as possible.
 *
 * e.g the word is "leetcode" , n is 3.
 *
 * apple , bat , cat , fish , leetcode, snip , snap ,hat ,dog
 *
 * you should output: bat cat fish leetcode snip snap hat
 *
 * apple , bat , cat , fish , leetcode, leetcode, snip , snap ,hat ,dog
 *
 * you should output: bat cat fish leetcode leetcode snip snap AND cat fish leetcode leetcode snip snap hat
 *
 * Solution:
 */
public class NWordsAfterAndBefore {

    public List<List<String>> solution(List<String> words, String targetWord, int n) {
        List<List<String>> result = new ArrayList<>();
        int nWords = words.size();

        LinkedList<String> wordsOnLeft = new LinkedList<>();
        LinkedList<String> wordsOnRight = new LinkedList<>();
        // Adding first n word in the left word set
        for(int i=0; i<n; i++) {
            wordsOnLeft.add(words.get(i));
        }
        for(int i=n+1; i<n*2+1; i++) {
            wordsOnRight.add(words.get(i));
        }
        for (int i=n; i<nWords; i++) {
            wordsOnLeft.add(words.get(i));
            if (words.get(i) == targetWord) {
                List<String> res = new ArrayList<>();
                // get n-words before i
                res.addAll(wordsOnLeft);
                // get n-words after i
                res.addAll(wordsOnRight);
                result.add(res);
            }
            // to maintain a list of n-words in the left
            // we have to remove the head(oldest) of the queue
            wordsOnLeft.remove();

            // Add n-th word on the right of current word
            // Check if n-th word on the right of current word is out of bound
            if (i+n+1<nWords){
                wordsOnRight.add(words.get(i+n+1));
            }
            // Maintain at-most n-words on the right,
            // don't remove if there are less than n words in the right list
            if (!wordsOnRight.isEmpty() && wordsOnRight.size()>n) {
                wordsOnRight.removeFirst();
            }
        }
        return result;
    }

    public static void main(String args[]) {
        NWordsAfterAndBefore obj = new NWordsAfterAndBefore();
        System.out.println("\nInput: [\"apple\", \"bat\", \"cat\", \"fish\", \"leetcode\", \"snip\", \"snap\", \"hat\", \"dog\"], word: leetcode, n=2");
        List<String> words = Arrays.asList( new String[] {"apple", "bat", "cat", "fish", "leetcode", "snip", "snap", "hat", "dog"});
        List<List<String>> result = obj.solution(words, "leetcode", 3);
        System.out.println(Arrays.toString(result.toArray()));

        System.out.println("\nInput: [\"apple\", \"bat\", \"cat\", \"fish\", \"leetcode\", \"snip\", \"snap\", \"leetcode\",\"hat\", \"dog\"], word: leetcode, n=2");
        List<String> words2 = Arrays.asList( new String[] {"apple", "bat", "cat", "fish", "leetcode", "snip", "snap", "leetcode","hat", "dog"});
        List<List<String>> result2 = obj.solution(words2, "leetcode", 2);
        System.out.println(Arrays.toString(result2.toArray()));

        System.out.println("\nInput: [\"apple\", \"bat\", \"cat\", \"fish\", \"leetcode\", \"snip\", \"leetcode\",\"hat\", \"dog\", \"snap\"], word: leetcode, n=2");
        List<String> words3 = Arrays.asList( new String[] {"apple", "bat", "cat", "fish", "leetcode", "snip", "leetcode","hat", "dog", "snap"});
        List<List<String>> result3 = obj.solution(words3, "leetcode", 2);
        System.out.println(Arrays.toString(result3.toArray()));

        System.out.println("\nInput: [\"apple\", \"bat\", \"cat\", \"fish\", \"leetcode\", \"leetcode\",\"ship\",\"snap\",\"hat\", \"dog\"], word: leetcode, n=3");
        List<String> words4 = Arrays.asList( new String[] {"apple", "bat", "cat", "fish", "leetcode", "leetcode","ship","snap","hat", "dog"});
        List<List<String>> result4 = obj.solution(words4, "leetcode", 3);
        System.out.println(Arrays.toString(result4.toArray()));
    }
}
