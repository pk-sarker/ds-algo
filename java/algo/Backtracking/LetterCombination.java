package algo.Backtracking;

import java.util.*;

/**
 * Given an array of letters and integer n, return a list of all the words that can be
 * formed (of length = n) using the letters. Discard words that contain the banned words.
 * Banned words can have any length up to n.
 *
 * Sample input:
 * letters = ['a', 'b', 'c']
 * bannedWords = ['aa', 'bc']
 * n = 3
 *
 * The expected output is a list of all the words that do not contain the bannedWord substrings
 * e.g. 'aaa', 'aab' and 'aac' would not be in the output list because they contain 'aa', but 'aba' is fine.
 *
 */
public class LetterCombination {
    private List<String> getWords(char[] letters, int n, String[] banned) {
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        List<String> result = new LinkedList<>();
        permute("", letters, n, set, result);
        return result;
    }

    private void permute(String s, char[] letters, int n, Set<String> banned, List<String> result) {
        System.out.println("> " + s);
        for (String word : banned) {
            // not continuing permutation as any string
            // formed after this will contain the banned word
            if (s.contains(word)) return;
        }

        // to not add the empty string
        // that is initially sent to this function
        if (!s.isEmpty()) {
            result.add(s);
        }

        // the following logic permutes characters to form different words
        if (n == 0) return;

        //                           ""
        //               /           |                  \
        // permute(""+a, n-1)   permute(""+ b, n-1)  permute(""+ c, n-1)
        for (char c : letters) {
            permute(s + c, letters, n - 1, banned, result);
        }
    }

    public static void main(String args[]){
        LetterCombination obj = new LetterCombination();

        System.out.println(obj.getWords(new char[]{'a','b', 'c'}, 3, new String[]{"aa", "bc"}).toString());
    }
}
