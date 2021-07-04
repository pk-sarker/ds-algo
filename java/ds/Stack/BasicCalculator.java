package ds.Stack;

import java.util.Stack;

/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it,
 * and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical
 * expressions, such as eval().
 *
 * Input: s = "1 + 1"
 * Output: 2
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * Input: s = "+48 + -48"
 * Output: 0
 * Explanation: Numbers can have multiple digits and start with +/-.
 */
public class BasicCalculator {

    public static int calculate(String s) {
        int result = 0;
        if (s==null || s.isEmpty()) {
            return result;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int operand = 0, sign = 1;
        int n = s.length();
        char[] exp = s.toCharArray();
        for(int i=0;i<n;i++) {
            if (exp[i]==' ') {
                continue;
            }
            if (Character.isDigit(exp[i])) {
                operand = operand  * 10 + (exp[i]-'0');
                continue;
            }
            if (exp[i]== '+' || exp[i]== '-')  {
                result += sign * operand;
                operand = 0; // reset
                sign = exp[i]== '-' ? -1:1;
                continue;
            }
            if (exp[i]=='(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (exp[i]==')') {
                result += sign*operand;
                result *= stack.pop();
                result += stack.pop();
                operand = 0;
            }

        }

        return result + sign*operand;
    }

    public static void main(String args[]) {
        System.out.println("Input: \"1 + 3\"\nOutput: " + BasicCalculator.calculate(" 1 + 3"));
        System.out.println("Input: \"(1-3)\"\nOutput: " + BasicCalculator.calculate("(1-3)"));
        System.out.println("Input: \"- (1 + 3)\" \nOutput: " + BasicCalculator.calculate("- (1 + 3)"));
        System.out.println("Input: \"2 + 7 - ( 1 + 3 )\"\nOutput: " + BasicCalculator.calculate("2 + 7 - ( 1 + 3 )"));
    }
}
