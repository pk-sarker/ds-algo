package com.dsalgo.practice;

/**
 * A string can be abbreviated by replacing any number of non-adjacent substrings with their lengths. For example, a string such as "substitution" could be abbreviated as (but not limited to):
 *
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 * Note that "s55n" ("s ubsti tutio n") is not a valid abbreviation of "substitution" because the replaced substrings are adjacent.
 *
 * Given a string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
 *
 * Input: word = "internationalization", abbr = "i12iz4n"
 * Output: true
 *
 * Input: word = "apple", abbr = "a2e"
 * Output: false
 *
 * Input: word = "hi", abbr = "2i"
 * Output: false
 *
 * Input: word = "hi", abbr = "02"
 * Output: false
 */
public class ValidWordAbbreviation {

    public static boolean validWordAbbreviation(String word, String abbr) {
        int p1 = 0, p2=0;
        int m=abbr.length(), n=word.length();
        if (m>n) {
            return false;
        }
        int num = 0;

        while(p1 < n && p2 < m) {

            if (Character.isDigit(abbr.charAt(p2)) && abbr.charAt(p2) != '0') {
                num = 0;
                while (p2 < m && Character.isDigit(abbr.charAt(p2))) {
                    num = num * 10 + (abbr.charAt(p2)-'0');
                    p2++;
                }
                p1 += num;
            } else if (word.charAt(p1) == abbr.charAt(p2)) {
                p1++;
                p2++;
            } else {
                return false;
            }
        }


        return p1 == n && p2 == m;
    }
    public static void main(String args[]) {
        System.out.println("Input: word=hi abbr=2 \n" + ValidWordAbbreviation.validWordAbbreviation("hi", "2"));
        System.out.println("Input: word=hi abbr=02 \n" + ValidWordAbbreviation.validWordAbbreviation("hi", "02"));
        System.out.println("Input: word=a abbr=01 \n" + ValidWordAbbreviation.validWordAbbreviation("a", "01"));
        System.out.println("Input: word=internationalization abbr=i12iz4n \n" + ValidWordAbbreviation.validWordAbbreviation("internationalization", "i12iz4n"));
    }
}
