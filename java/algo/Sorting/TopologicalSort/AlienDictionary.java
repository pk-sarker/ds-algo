package algo.Sorting.TopologicalSort;

/**
 * In an alien language, surprisingly they also use english lowercase letters,
 * but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet,
 * return true if and only if the given words are sorted lexicographicaly in this alien language.
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
 *
 */
public class AlienDictionary {

    public boolean isAlienSorted(String[] words, String order) {
        int[] dict = new int[26];

        // order of the letters
        for(int i=0; i<order.length(); i++) {
            dict[order.charAt(i) - 'a'] = i;
        }

        for(int i=0; i<words.length-1; i++) {

            // compare w1 and w2
            if (!compareTwoWord(dict, words[i], words[i+1])) {
                return false;
            }
        }
        return true;
    }

    public boolean compareTwoWord(int[] dict,  String w1, String w2) {
        int n = Math.min(w1.length(), w2.length());
        int matchCount = 0;
        for(int  i=0; i<n; i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                if (dict[w1.charAt(i)-'a'] > dict[w2.charAt(i)-'a']) {
                    return false;
                }
                break;
            } else {
                matchCount++;
            }
        }
        if ((matchCount == w1.length() || matchCount == w2.length()) &&  w1.length() > w2.length()) {
            return false;
        }
        return true;
    }
    public static void main(String args[]) {
        AlienDictionary obj = new AlienDictionary();
        System.out.println("\nInput: [\"hello\", \"leetcode\"] Order: hlabcdefgijkmnopqrstuvwxyz  \nOutput: " + obj.isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));

        System.out.println("\nInput: [\"apple\", \"app\"] Order: hlabcdefgijkmnopqrstuvwxyz  \nOutput: " + obj.isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));

        System.out.println("\nInput: [\"apple\", \"app\"] Order: hlabcdefgijkmnopqrstuvwxyz  \nOutput: " + obj.isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
    }
}
