package com.dsalgo.practice;

import java.util.*;

/**
 * We can shift a string by shifting each of its letters to its successive letter.
 *
 * For example, "abc" can be shifted to be "bcd".
 * We can keep shifting the string to form a sequence.
 *
 * For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
 * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.
 *
 * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
 * Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
 *
 */
public class GroupShiftedStrings {

    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> groupMap = new HashMap<>();

        for(int i=0;i<strings.length;i++) {

            StringBuilder key = new StringBuilder();

            if (strings[i].length() > 1) {
                for(int j=0;j<strings[i].length();j++) {
                    if (j==0) {
                        key.append('1');
                        continue;
                    }
                    int diff = (strings[i].charAt(j)-'a') - ( strings[i].charAt(j-1)-'a');
                    if (diff < 0) { // da -> diff will be negative
                        diff += 26;
                    }
                    key.append('-'+diff);
                }
            }
            List<String> list = groupMap.getOrDefault(key.toString(), new ArrayList<String>());
            list.add(strings[i]);
            groupMap.put(key.toString(), list);
        }

        for(String key:groupMap.keySet()) {
            res.add(groupMap.get(key));
        }

        return res;
    }
    public static void main(String args[]) {
        GroupShiftedStrings obj = new GroupShiftedStrings();
        System.out.println(obj.groupStrings(new String[]{"abc","bcd","acef","xyz","az","ba","a","z","al"}).toString());
    }
}
