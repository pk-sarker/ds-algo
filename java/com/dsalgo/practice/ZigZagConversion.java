package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * PAYPALISHIRING
 *
 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13
 * P A Y P A L I S H I R   I  N  G
 *
 * row=3
 * PAY P ALI S HIR I NG
 * Line 1: 0,4,8,12 PAHN = first like
 * Line 2: 1,3,5,7,9,11,13 APLSIIG
 * Line 3: 2,6,10 YIR = last line
 *
 * 3 - 2 = 1 cross line 1,
 *
 *
 * row=4
 * PAYP AL ISHI RI NG
 * 4-2 = 2 cross line 1,2,
 * row=5
 * PAYPA LIS HIRIN G
 *
 * row=6
 * PAYPAL ISHI RING
 *
 */
public class ZigZagConversion {

    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> lines = new ArrayList<>();
        boolean dirTopDown = false;
        for(int i=0; i<Math.min(numRows, s.length()); i++) {
            lines.add(new StringBuilder());
        }
        int line = 0;
        for(char c: s.toCharArray()) {
            lines.get(line).append(c);

            if (line ==0 || line == numRows-1) {
                dirTopDown = !dirTopDown;
            }

            if (dirTopDown) {
                line += 1;
            } else {
                line -= 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (StringBuilder row : lines) {
            sb.append(row);
        }

        return sb.toString();
    }
    public static void main(String args[]) {
        System.out.println("Input: PAYPALISHIRING, row=3\nOutput: " + ZigZagConversion.convert("PAYPALISHIRING",3));

        System.out.println("Input: PAYPALISHIRING, row=4\nOutput: " + ZigZagConversion.convert("PAYPALISHIRING",4));

        System.out.println("Input: PAYPALISHIRING, row=5\nOutput: " + ZigZagConversion.convert("PAYPALISHIRING",5));
    }
}
