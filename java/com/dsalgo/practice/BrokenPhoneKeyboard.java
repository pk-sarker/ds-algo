package com.dsalgo.practice;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Broken keyboard on a phone. Only some keys are functional,
 * Given list of words find which words can be typed on this phone.
 *
 * borken keys = [1, 8, 4], words=["a", "aa", "abc", "adkop", "zsol", "t",  "u", "quv",  "tiv", "cvh"]
 */
public class BrokenPhoneKeyboard {
    Map<Integer, String> keyMap = new HashMap<>(){{
        put(2, "abc");
        put(3, "def");
        put(4,"ghi");
        put(5,"jkl");
        put(6,"mno");
        put(7,"pqrs");
        put(8,"tuv");
        put(9,"wxyz");
    }};
    Set<Character> availableChar = new HashSet<>();
    public boolean[] canType(int[] brokenKeys, String[] words) {
        Set<Integer> brokenKeySet = Arrays.stream(brokenKeys).boxed().collect(Collectors.toSet());
        for(Integer i=2; i<=9; i++) {
            if (!brokenKeySet.contains(i)) {
                String chars = keyMap.get(i);
                for(int j=0; j<chars.length(); j++) {
                    availableChar.add(chars.charAt(j));
                }
            }
        }
        boolean[] res = new boolean[words.length];
        for(int i=0; i<words.length; i++) {
            res[i] = true;
            for(int j=0;j<words[i].length(); j++) {
                if (!availableChar.contains(words[i].charAt(j))) {
                    res[i] = false;
                    break;
                }
            }
        }
        return res;
    }
    public static void main(String  args[]) {
        BrokenPhoneKeyboard ob = new BrokenPhoneKeyboard();
        boolean[] res = ob.canType(new int[]{1,8,4}, new String[]{"a", "aa", "abc", "adkop", "zsol", "t",  "u", "quv",  "tiv", "cvh"});
        System.out.println("\nInput: brokenKey: [1,8,4], words=[\"a\", \"aa\", \"abc\", \"adkop\", \"zsol\", \"t\",  \"u\", \"quv\",  \"tiv\", \"cvh\"] \nOutput: " + Arrays.toString(res));
    }
}
