package ds.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being
 * repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for
 * those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 */
public class DecodeString {
    public String decodeString(String s) {
        if (s==null || s.length()==0) {
            return "";
        }
        int n = s.length();
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            if (c!=']') {
                stack.push(c);
            } else {
                List<Character> decodeStr = new ArrayList<>();
                while(stack.peek()!='[') {
                    decodeStr.add(stack.pop());
                }
                stack.pop();

                int base = 1, k = 0;
                while(!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + base * (stack.pop()-'0');
                    base = base * 10;
                }
                while (k!=0) {
                    for(int j=decodeStr.size()-1; j>=0; j--) {
                        stack.push(decodeStr.get(j));
                    }
                    k--;
                }
            }
        }

        char[] res = new char[stack.size()];
        for(int i = stack.size()-1; i>=0; i--) {
            res[i] = stack.pop();
        }

        return new String(res);
    }

    public static void main(String args[]) {

    }
}
