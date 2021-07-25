package pattern.Palindrome;

import java.util.HashMap;

/**
 * Given a string s, return true if a permutation of the string could form a palindrome.
 *
 * Input: s = "code"
 * Output: false
 *
 * Input: s = "aab"
 * Output: true
 *
 * Input: s = "carerac"
 * Output: true
 */
public class PalindromePermutation {
    // Two pass with hash map
    public boolean canPermutePalindrome(String s) {
        if (s == "" || s == null) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i=0;i<s.length();i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0)+1);
        }
        int count = 0;
        for(char key: map.keySet()) {

            count += map.get(key) % 2;
        }

        return count <= 1 ? true: false;
    }

    // two pass: using array instead of hashmpa
    //  For this, we make use of an array map with length 128.
    //  Each index of this map corresponds to one of the 128 ASCII characters possible.
    public boolean canPermutePalindrome2(String s) {
        if (s == "" || s == null) {
            return false;
        }
        int[] map = new int[128];

        for(int i=0;i<s.length();i++) {
            map[s.charAt(i)]++;
        }
        int count = 0;
        for(int i=0; i<128; i++) {

            count += map[i] % 2;
        }

        return count <= 1 ? true: false;
    }

    // single pass
    public boolean canPermutePalindrome3(String s) {
        int[] map = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0) {
                count--;
            } else {
                count++;
            }
        }
        return count <= 1;
    }

    public static void main(String args[]) {
        PalindromePermutation obj = new PalindromePermutation();

        System.out.println("\nInput: aab \nOutput: " + obj.canPermutePalindrome("aab"));
        System.out.println("\nInput: aabc \nOutput: " + obj.canPermutePalindrome("aabc"));
        System.out.println("\nInput: aabcb \nOutput: " + obj.canPermutePalindrome("aabcb"));

    }
}
