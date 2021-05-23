package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s that contains parentheses and letters,
 * remove the minimum number of invalid parentheses to make the input string valid.
 *
 * Return all the possible results. You may return the answer in any order.
 */
public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        remove(s, 0, 0, result, new char[]{'(',')'});
        return result;
    }

    public void remove(String s,int last_i, int last_j, List<String> result, char[] par) {
        System.out.println("==== remove ==== \n s="+s+" last_i: " + last_i+" last_j: "+last_j+", res: " + result.toString()+" par: "+ (new String(par)));
        int stack = 0;
        for(int i= last_i; i<s.length();i++) {
            if (s.charAt(i) == par[0]) {
                stack++;
            } else if (s.charAt(i)==par[1]) {
                stack--;
            }
            System.out.println("\t> stack: " + stack);
            if (stack>=0) {
                continue;
            }
            // if there are more closing parentheses than opening
            for(int j=last_j; j<=i; j++) {
                if (s.charAt(j) == par[1] && (j == last_j|| s.charAt(j-1) != par[1])) {
                    System.out.println("> Invalid : remove { " + s.substring(0, j) + s.substring(j+1, s.length()) + ","+i+","+j+" }");
                    remove(s.substring(0, j) + s.substring(j+1, s.length()), i, j, result, par);
                }
            }
            return;
        }


        String sRev = new StringBuilder(s).reverse().toString();
        if (par[0]=='(') {
            // remove all invalid ')'
            System.out.println("<--- Remove: " + s +" Rev: "+ sRev);
            remove(sRev, 0,0,result, new char[]{')', '('});
        } else {
            System.out.println("-> Add to Result: " + sRev);
            result.add(sRev);
        }
    }

    public static void main(String args[]) {
        RemoveInvalidParentheses obj = new RemoveInvalidParentheses();

        System.out.println("Input: ()())()\nOutput: " +obj.removeInvalidParentheses("()())()").toString());
    }
}
