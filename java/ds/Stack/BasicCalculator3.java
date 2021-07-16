package ds.Stack;

import java.util.Stack;

/**
 *Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, '+', '-', '*', '/'
 * operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the
 * range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical
 * expressions, such as eval().
 *
 *
 * Input: s = "1+1"
 * Output: 2
 *
 * Input: s = "6-4/2"
 * Output: 4
 *
 * Input: s = "2*(5+5*2)/3+(6/2+8)"
 * Output: 21
 *
 *
 * Input: s = "(2+6*3+5-(3*14/7+2)*5)+3"
 * Output: -12
 */
public class BasicCalculator3 {

    /**
     * Level 1 operation: +, -
     * Level 2 operation: *, /
     * for '+' o1 = 1, and o1 = -1 for '-'
     * for '*' o2 = 1, and o1 = -1 for '/'
     *
     * Initial value of l1 = 0, o1=1 (+)  // partial sum/result
     * Initial value of l2 = 1, o2=1 (*)
     *
     * On '(' save the current state of the op in stack
     * On ')' get the last state of the operation and compute
     *
     *
     * @param s
     * @return
     */
    public static int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int n = s.length();
        int l1 = 0, l2 = 1, op1 = 1, op2 = 1;

        Stack<Integer> stack = new Stack<>();
        char[] exp = s.toCharArray();
        for(int i=0;i<n;i++) {
            char c = exp[i];
            // if digit
            if (Character.isDigit(c)) {
                int num = c - '0';
                while(i<n-1 && Character.isDigit(exp[i+1])) {
                    num = num*10 + (exp[++i]-'0');
                }
                if (op2 == 1) {
                    l2 = l2 * num;
                } else {
                    l2 = l2 / num;
                }
            } else if(c == '(') {
                stack.push(l1);
                stack.push(op1);
                stack.push(l2);
                stack.push(op2);
                l1 = 0;
                op1 = 1;
                l2 = 1;
                op2 = 1;
            } else if(c == ')') {
                // save current calculation (a+b-c/d), num = a+b-c/d
                int num = l1 + op1 * l2;

                op2 = stack.pop();
                l2 = stack.pop();
                op1 = stack.pop();
                l1 = stack.pop();
                l2 = op2 == 1 ? l2*num:l2/num;
            } else if(c == '*' || c == '/') {
                op2 = c == '/' ? -1:1;
            } else if (c == '+' || c == '-') {
                l1 = l1 + op1 * l2;
                op1 = c == '-' ? -1:1;
                l2 = 1;  // reset
                op2 = 1;
            }
        }
        return l1 + op1*l2;
    }
    public static void main(String args[]) {
        //System.out.println("Input: \"2 + 7 - ( 1 + 3 )\"\nOutput: " + BasicCalculator3.calculate("2 + 7 - ( 1 + 3 )"));

//        System.out.println("Input: \"6-4/2\"\nOutput: " + BasicCalculator3.calculate("6-4/2"));
//        System.out.println("Input: \"2*(5+5*2)/3+(6/2+8)\"\nOutput: " + BasicCalculator3.calculate("2*(5+5*2)/3+(6/2+8)"));
//        System.out.println("Input: \"(2+6*3+5-(3*14/7+2)*5)+3\"\nOutput: " + BasicCalculator3.calculate("(2+6*3+5-(3*14/7+2)*5)+3"));
        System.out.println("Input: \"(5-(1+(5)))\"\nOutput: " + BasicCalculator3.calculate("(5-(1+(5)))"));
        //System.out.println("Input: \"(5-(1+(5)))\"\nOutput: " + BasicCalculator3.calculate2("(5-(1+(5)))"));


    }
}
