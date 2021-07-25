package pattern.Palindrome;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 *
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 *
 * Input: s = "a"
 * Output: [["a"]]
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        dfs(0, result, new ArrayList<String>(), s);
        return result;
    }

    /**
     *       aab
     *       "a" - is valid
     *          -> yes: add to current list
     *              -> recurse, check the substring starting from next char, position 1 | dfs(end+1,result...)
     *              -> if start position reached to the end then add the current list to result
     *              -> back track and remove items from current list
     *
     *          -> no: continue
     *
     *      1. start=0, end=3, "a" is valid - current list = ["a"], recurs 1
     *      2. start=1, end=3, "a" is valid - current list = ["a", "a"], recurs 2
     *      3. start=2, end=3, "b" is valid - current list = ["a", "a", "b"], recurs 3
     *      4. add current list ["a","a","b"] to result
     *      5. return to recurs 3, and remove current list, end of string
     *      6. return to recurs 2, and remove current list, current index|end =2,iterate to next
     *      7. start=1, end=3 "ab" is valid - current list = ["a","a"]
     *      8
     *
     */
    void dfs(int start, List<List<String>> result, List<String> currentList, String s) {
        if (start >= s.length()) result.add(new ArrayList<String>(currentList));
        System.out.println("DFS > Start: " + start + " "+(start < s.length() ? s.charAt(start) : "") +" currentList: " + currentList.toString());
        for (int end = start; end < s.length(); end++) {
            System.out.println("\t > End: " + end + " Start: " + start);
            if (isPalindrome(s, start, end)) {
                // add current substring in the currentList
                currentList.add(s.substring(start, end + 1));
                dfs(end + 1, result, currentList, s);
                System.out.println("\t back < End: " + end + " currentList: " + currentList.toString());
                // backtrack and remove the current substring from currentList
                currentList.remove(currentList.size() - 1);

            }
        }
    }

    boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) return false;
        }
        return true;
    }

    // ==================== with DP ==================
    public List<List<String>> partitionDP(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        List<List<String>> result = new ArrayList<>();
        dfsWithDP(result, s, 0, new ArrayList<>(), dp);
        return result;
    }

    void dfsWithDP(List<List<String>> result, String s, int start, List<String> currentList, boolean[][] dp) {
        if (start >= s.length()) result.add(new ArrayList<>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
                dp[start][end] = true;
                currentList.add(s.substring(start, end + 1));
                dfsWithDP(result, s, end + 1, currentList, dp);
                currentList.remove(currentList.size() - 1);
            }
        }
    }
    public static void main(String args[]) {
        PalindromePartitioning obj = new PalindromePartitioning();
        System.out.println(obj.partition("aab").toString());
    }
}
