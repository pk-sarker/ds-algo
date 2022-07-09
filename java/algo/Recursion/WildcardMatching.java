package algo.Recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching
 * with support for '?' and '*' where:
 * - '?' Matches any single character.
 * - '*' Matches any sequence of characters (including the empty sequence).
 *
 * The matching should cover the entire input string (not partial).
 *
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Input: s = "aa", p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 *
 * Input: s = "cb", p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 */
public class WildcardMatching {

    Map<String, Boolean> dp = new HashMap<String, Boolean>();

    public boolean isMatch(String s, String p) {
        dp.clear();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<p.length(); i++) {
            if (sb.length() == 0 || p.charAt(i) != '*') {
                sb.append(p.charAt(i));
            } else if (i>0 && p.charAt(i-1) != '*') {
                sb.append(p.charAt(i));
            }
        }
        p = sb.toString();
        return helper(s, p);
    }

    public boolean helper(String s, String p) {
        String sp = s+"_"+p;
        if (dp.containsKey(sp)) {
            return dp.get(sp);
        }

        if (s.equalsIgnoreCase(p) || p == "*") {
            dp.put(sp, true);
        } else if (s.isEmpty() || p.isEmpty()) {
            dp.put(sp, false);
        } else if ((s.charAt(0) == p.charAt(0)) || p.charAt(0) == '?') {
            dp.put(sp, helper(s.substring(1), p.substring(1)));
        } else if (p.charAt(0) == '*') {
            dp.put(sp, helper(s,p.substring(1)) || helper(s.substring(1), p));
        } else {
            dp.put(sp, false);
        }

        return dp.get(sp);
    }
    public static void main(String args[]) {
        WildcardMatching obj = new WildcardMatching();
        System.out.println("Input: s=aa, p = a \nOutput: Match ? " + obj.isMatch("aa", "a"));
        System.out.println("Input: s=aa, p = a? \nOutput: Match ? " + obj.isMatch("aa", "a?"));

        System.out.println("Input: s=xyzca, p = ?y*a \nOutput: Match ? " + obj.isMatch("xyzca", "?y*a"));
        System.out.println("Input: s=xyzca, p = ?y*a* \nOutput: Match ? " + obj.isMatch("xyzca", "?y*a*"));
    }
}
