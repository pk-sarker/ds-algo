package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations
 * that the number could represent. Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1
 * does not map to any letters.
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Input: digits = ""
 * Output: []
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 */
public class PhoneLetterCombination {

    Map<String, String> digitLetterMap = new HashMap<>(){{
        put("2", "abc");
        put("3", "def");
        put("4","ghi");
        put("5","jkl");
        put("6","mno");
        put("7","pqrs");
        put("8","tuv");
        put("9","wxyz");
    }};
    List<String> allCombinations = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        int n = digits.length();
        if (n==0) {
            return result;
        }


        this.combine("", digits);

        return allCombinations;
    }

    public void combine(String prefix, String remainingDigits){
        // add prefix to the final list only when all the digits are processed
        // and no remaining digits
        if (remainingDigits.length() == 0) {
            allCombinations.add(prefix);
            return;
        }
        String digit = remainingDigits.substring(0, 1);
        String letters = digitLetterMap.get(digit);
        for(int j=0; j< letters.length(); j++) {
                StringBuilder sb  = new StringBuilder();
                sb.append(prefix);
                sb.append(letters.charAt(j));
                combine(sb.toString(), remainingDigits.substring(1));
        }
    }

    public static void main(String args[]) {
        PhoneLetterCombination ob = new PhoneLetterCombination();
        System.out.println(ob.letterCombinations("23"));
    }
}
