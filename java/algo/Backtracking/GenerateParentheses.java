package algo.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * Input: n = 1
 * Output: ["()"]
 */
public class GenerateParentheses {

    public List<String> generate(int n) {
        List<String> res = new ArrayList<>();

        backtrack(new StringBuilder(), 0, 0, n, res);
        return res;
    }

    public void backtrack(StringBuilder sb, int open, int close, int max, List<String> res) {
        if (sb.length() == max * 2) {
            res.add(sb.toString());
            return;
        }

        // if open count is less than max open allowed
        if (open < max) {
            sb.append("(");
            backtrack(sb, open + 1, close, max, res);
            sb.deleteCharAt(sb.length() - 1);
        }

        // if close count is less than max close allowed
        if (close < open) {
            sb.append(")");
            backtrack(sb, open, close + 1, max, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String args[]) {
        GenerateParentheses obj = new GenerateParentheses();
        System.out.println(obj.generate(3));
    }
}

