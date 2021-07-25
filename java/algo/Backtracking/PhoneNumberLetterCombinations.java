package algo.Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations
 * that the number could represent. Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that
 * 1 does not map to any letters.
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * Input: digits = ""
 * Output: []
 */
public class PhoneNumberLetterCombinations {
    HashMap<String, String> digitLetterMap = new HashMap<>(){{
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

        int n = digits.length();
        List<String> result = new ArrayList<>();
        if (n==0) {
            return result;
        }
        combine("", digits);
        return allCombinations;
    }

    public void combine(String prefix, String remainingDigits) {

        if (remainingDigits.length() == 0) {
            allCombinations.add(prefix);
            return;
        }

        String digit = remainingDigits.substring(0,1);
        String newRem = remainingDigits.substring(1);
        String letters = digitLetterMap.get(digit);

        for(int i=0;i<letters.length();i++) {
            combine(prefix+""+letters.charAt(i),newRem);
        }

    }

    public static void main(String args[]) {
        PhoneNumberLetterCombinations obj = new PhoneNumberLetterCombinations();
        System.out.println(obj.letterCombinations("23").toString());
    }
}
