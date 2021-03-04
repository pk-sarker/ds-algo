package com.ds.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters
 * and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces
 * on a line do not divide evenly between words, the empty slots on the left will be assigned more
 * spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * Note:
 * - A word is defined as a character sequence consisting of non-space characters only.
 * - Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * - The input array words contains at least one word.
 *
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified becase it contains only one word.
 */
public class TextJustification {

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int lineLen = 0;
        int start = 0;
        int n = words.length;
        for(int i=0; i<n; i++) {
            if (lineLen + words[i].length() + (i-start) > maxWidth) {
                int totalSpace = maxWidth - lineLen;
                int spaceWordSep = (i-start-1) == 0 ?  0 : totalSpace/(i-start-1);
                int extraSpace = totalSpace - spaceWordSep * (i-start-1);

                StringBuilder sb = new StringBuilder();
                for(int j=start;j<i; j++) {
                    sb.append(words[j]);
                    // don't add space for last word
                    for(int k=0; k<spaceWordSep && j + 1 != i; k++) {
                        sb.append(" ");
                    }
                    if (extraSpace > 0) {
                        sb.append(" ");
                        extraSpace--;
                    }

                    while (spaceWordSep == 0 && extraSpace > 0) {
                        sb.append(' ');
                        extraSpace--;
                    }
                }
                System.out.println(""+sb.toString()+ " : " + sb.length());
                result.add(sb.toString());
                start = i;
                lineLen = 0;
            }
            lineLen += words[i].length();
        }
        if (lineLen > 0) {
            StringBuilder sb = new StringBuilder();
            for(int j=start;j<n; j++) {
                sb.append(words[j]);
                if (j+1 != n) {
                    sb.append(" ");
                }
            }
            while(sb.length() < maxWidth) {
                sb.append(" ");
            }
            System.out.println(""+sb.toString()+ " : " + sb.length());
            result.add(sb.toString());
        }
        return result;
    }

    public static void main(String args[]) {
        System.out.println("\nInput [\"This\", \"is\", \"an\", \"example\", \"of\", \"text\", \"justification.\"] maxLen = 16\nOutput: " + TextJustification.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));


        System.out.println("\nInput [\"What\",\"must\",\"be\",\"acknowledgment\",\"shall\",\"be\"] maxLen = 16\nOutput: " + TextJustification.fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"}, 16));
    }
}
