package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Guess the Word
 *
 * You are given an array of unique strings wordlist where wordlist[i] is 6 letters long,
 * and one word in this list is chosen as secret.
 *
 * You may call Master.guess(word) to guess a word. The guessed word should have type string
 * and must be from the original list with 6 lowercase letters.
 *
 * This function returns an integer type, representing the number of exact matches (value and
 * position) of your guess to the secret word. Also, if your guess is not in the given wordlist,
 * it will return -1 instead.
 *
 * For each test case, you have exactly 10 guesses to guess the word. At the end of any number
 * of calls, if you have made 10 or fewer calls to Master.guess and at least one of these guesses
 * was secret, then you pass the test case.
 *
 *
 * Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
 * Output: You guessed the secret word correctly.
 * Explanation:
 * master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 * master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 * master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 * master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 * We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 *
 */
public class GuessTheWord {

    public static int match(String x, String y) {
        int count = 0;
        int len = x.length();
        for(int i=0;i<len;i++) {
            if (x.charAt(i) == y.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Time complexity O(N)
     * Space complexity O(N)
     *
     * @param wordlist
     * @param master
     */
    public static void findSecretWord(String[] wordlist, GuessMaster master) {
        // iterate 10 times and until all 6 character matches
        int i = 0, x = 0;
        for (; i < 10 && x < 6; ++i) {
            String guess = wordlist[new Random().nextInt(wordlist.length)];
            x = master.guess(guess);

            // If current guess is not the solution then
            // the solution will be in the wordlist.
            // For sure solution will have exactly x matching character with the guess
            List<String> matchingWordlist = new ArrayList<>();
            for (String w : wordlist) {
                if (match(guess, w) == x) {
                    matchingWordlist.add(w);
                }
            }
            wordlist = matchingWordlist.toArray(new String[matchingWordlist.size()]);
        }

        System.out.println("Master Guess check: " + i);
    }

    public static void main(String args[]){
        GuessMaster guessMaster = new GuessMaster("acckzz");
        GuessTheWord.findSecretWord(new String[]{"ccbazz","bdefgh","eiowzz","abcczz","acckzz"}, guessMaster);
    }
}
