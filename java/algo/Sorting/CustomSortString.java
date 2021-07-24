package algo.Sorting;

/**
 * You are given two strings order and s. All the words of order are unique and were sorted in
 * some custom order previously.
 *
 * Permute the characters of s so that they match the order that order was sorted.
 * More specifically, if a character x occurs before a character y in order, then x should
 * occur before y in the permuted string.
 *
 * Return any permutation of s that satisfies this property.
 *
 * Input: order = "cba", s = "abcd"
 * Output: "cbad"
 * Explanation:
 * "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
 * Since "d" does not appear in order, it can be at any position in the returned string.
 * "dcba", "cdba", "cbda" are also valid outputs.
 *
 * Input: order = "cbafg", s = "abcd"
 * Output: "cbad"
 */
public class CustomSortString {

    public static String customSortString(String S, String T) {
        char[] res = new char[T.length()];

        int[] count = new int[26];

        for(int i=0;i<T.length();i++) {
            count[T.charAt(i)-'a']++;
        }
        // abc - bdefcaba
        // a -> 2 a in T, so res='aa'
        // b -> 2 b, res = 'aabb'
        // c -> 1 c res = 'aabbc'

        // fill the remaining letters, discard the letters that are in S
        // and use the count
        int j=0;
        for(int i=0;i<S.length();i++) {
            char c = S.charAt(i);
            while (count[c-'a'] > 0) {
                res[j++] = c;
                count[c-'a']--;
            }
        }
        //
        for(char c='a'; c<='z';c++) {
            while(count[c-'a']-- > 0){ // the characters which are not in order but in string, we can add them at the end
                res[j++] = c;
            }
        }
        return new String(res);
    }

    public static void main(String args[]) {

        System.out.println(CustomSortString.customSortString("cba", "xabcadc"));
    }
}
