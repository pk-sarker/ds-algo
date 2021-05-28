package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string num that contains only digits and an integer target, return
 * all possibilities to add the binary operators '+', '-', or '*' between the
 * digits of num so that the resultant expression evaluates to the target value.
 *
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 *
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 *
 * Input: num = "00", target = 0
 * Output: ["0*0","0+0","0-0"]
 *
 * Solution:
 *
 * 1. procedure recurrse(digits, index, expression):
 * 2.     if we have reached the end of the string:
 * 3.         if the expression evaluates to the target:
 * 4.             Valid Expression found!
 * 5.     else:
 * 6.         try out operator 'NO OP' and recurrse
 * 7.         try out operator * and recurrse
 * 8.         try out operator + and recurrse
 * 9.         try out operator - and recurrse
 *
 * "123", 6
 * > 1
 * > > 1 + 2 = 3
 *      > 3 + 3 = 6
 *      > 3 - 3 = 0
 *      > 3 - 2 + (2*3) = 7
 *   > 1 - 2 = -1
 *      > -1 + 3 = 2
 *      > -1 - 2 = -3
 *      > -1 + 2 + (2*3) = 7
 *   > 1 * 2 = 2
 *      > 2 + 3 = 5
 *      > 2 - 3 = -1
 *      > 2 - 2 + (2*3) = 6
 *
 * addOp(0, )
 */
public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        if(num.length() == 0) return new ArrayList<>();

        List<String> result = new ArrayList<>();
        addOp(0, 0, 0, 0, num, target, new StringBuilder(), result);
        return result;
    }

    /**
     *
     * @param idx position in the number
     * @param prev Previous operand
     * @param curr current operand
     * @param value previous value
     * @param num Number
     * @param target
     * @param sb
     * @param res
     */
    public void addOp(int idx, long prev, long curr, long value, String num, long target, StringBuilder sb, List<String> res) {
        // When reached at the end of the number
        if(idx == num.length()) {
            if(curr == 0 && value == target) { // add the string pattern if previous value = target.
                res.add(sb.toString());
            }
            return;
        }

        // Extending the current operand by one digit
        curr = curr * 10 + (num.charAt(idx)-'0');
        if (curr > 0) {
            addOp(idx+1, prev, curr, value, num, target, sb, res);
        }

        int strIdx = sb.length();
        if(strIdx!=0) sb.append('+');
        sb.append(curr);
        addOp(idx+1, curr, 0, value+curr, num, target, sb, res);
        sb.delete(strIdx, sb.length()); // remove previous operation, 1+2 => 1

        if(strIdx == 0) return;

        sb.append('-');
        sb.append(curr);
        addOp(idx+1, -curr, 0, value-curr, num, target, sb, res);
        sb.delete(strIdx, sb.length());

        sb.append('*');
        sb.append(curr);
        addOp(idx+1, curr*prev, 0, value - prev + (curr*prev), num, target, sb, res);
        sb.delete(strIdx, sb.length());
    }

    public static void main(String args[]) {
        ExpressionAddOperators obj = new ExpressionAddOperators();
        System.out.println("Num=\"123\", target=6 -> "+obj.addOperators("123", 6).toString());
        System.out.println("Num=\"105\", target=5 -> "+obj.addOperators("105", 5).toString());
        System.out.println("Num=\"1116\", target=5 -> "+obj.addOperators("1116", 5).toString());
        System.out.println("Num=\"1611\", target=5 -> "+obj.addOperators("1611", 5).toString());
        System.out.println("Num=\"2532\", target=255 -> "+obj.addOperators("2532", 255).toString());
    }
}
