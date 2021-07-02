package ds.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a string s consisting of small English letters, find and return the first instance
 * of a non-repeating character in it. If there is no such character, return '_'.
 * Example:
 * S = "abacabad"
 * Output: c
 * There are 2 non-repeating characters in the string: 'c' and 'd'.
 * Return c since it appears in the string first.
 *
 * S = "abacabaabacaba"
 * Output: '_'
 */
public class FirstNonRepeatingCharacter {

    /**
     *
     * @param s
     * @return
     */
    public static char find(String s) {
        HashMap<Character, Integer> charCountMap = new HashMap<>();

        int n = s.length();
        for(int i=0;i<n;i++) {
            char c = s.charAt(i);
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        for(int i=0; i<n;i++) {
            char c = s.charAt(i);
            if (charCountMap.get(c) == 1) {
                return c;
            }
        }
        return '_';
    }

    /**
     * If the string is very long/large and most of them are repeating
     * then we can keep track of the list of unique characters in the string
     * which will help us to reduce the computation to find the first
     * non-repeating character in the 2nd pass.
     *
     * Example:
     * s = "abcaacabcbbdabceadecbbbcbcdacdexaybc"
     * Output = x
     * There are 36 characters, among them only 7 are unique
     * After first pass, we will have a hash table with unique character with number of occurrences.
     * In the 2nd pass we can just iterate over the unique characters instead of the whole string.
     * @param s
     * @return
     */
    public static char findLargeInputOpt(String s) {
        HashMap<Character, Integer> charCountMap = new HashMap<>();
        List<Character> charSet = new ArrayList<>();
        int n = s.length();
        for(int i=0;i<n;i++) {
            char c = s.charAt(i);
            if (!charCountMap.containsKey(c)) {
                charSet.add(c);
            }
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        for(int i=0; i<charSet.size();i++) {
            char c = charSet.get(i);
            if (charCountMap.get(c) == 1) {
                return c;
            }
        }
        return '_';
    }

    public static void main(String args[]) {
        System.out.println("Input: \"abacabad\" \nOutput: " + FirstNonRepeatingCharacter.find("abacabad"));
        System.out.println("Input: \"abacabad\" \nOutput: " +FirstNonRepeatingCharacter.find("abacabaabacaba"));

        System.out.println("Input: \"abacabad\" \nOutput: " + FirstNonRepeatingCharacter.findLargeInputOpt("abacabad"));
        System.out.println("Input: \"abacabad\" \nOutput: " +FirstNonRepeatingCharacter.findLargeInputOpt("abacabaabacaba"));

        System.out.println("Input: \"abcaacabcbbdabceadecbbbcbcdacdexaybc\" \nOutput: " + FirstNonRepeatingCharacter.findLargeInputOpt("abcaacabcbbdabceadecbbbcbcdacdexaybc"));
        System.out.println("Input: \"abcaacabcbbdabceadecbbbcbcdacdexaybc\" \nOutput: " +FirstNonRepeatingCharacter.findLargeInputOpt("abcaacabcbbdabceadecbbbcbcdacdexaybc"));
    }
}
