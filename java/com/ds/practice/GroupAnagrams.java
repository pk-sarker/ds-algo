package com.ds.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Input: strs = ["a"]
 * Output: [["a"]]
 */
public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0)  {
            return new ArrayList();
        }
        HashMap<String, List> res = new HashMap<>();
        for (int i=0; i<strs.length; i++) {
            char[] charAr = strs[i].toCharArray();
            Arrays.sort(charAr); // O(n log n)
            String key = String.valueOf(charAr);
            if (!res.containsKey(key)) {
                res.put(key, new ArrayList());
            }
            res.get(key).add(strs[i]);
        }
        return new ArrayList(res.values());
    }

    public static void main(String args[]) {
        System.out.println("\nInput: [\"eat\",\"tea\",\"tan\",\"ate\",\"nat\",\"bat\"] \nOutput: " + GroupAnagrams.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }
}
