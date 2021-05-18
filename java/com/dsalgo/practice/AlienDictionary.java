package com.dsalgo.practice;

import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> charMap = new HashMap<>();
        Map<Character, Integer> nodeInDegree = new HashMap<>();
        for(String word: words) {
            for(int i=0;i<word.length();i++) {
                nodeInDegree.put(word.charAt(i), 0);
            }
        }

        for(int i=0;i<words.length-1;i++) {
            String w1 = words[i], w2 = words[i+1];
            // abc, ab => can't decide if a is before b or b is before a
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "";
            }
            int minLen = Math.min(w1.length(), w2.length());
            for (int j=0;j<minLen;j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = new HashSet<>();
                    if (charMap.containsKey(c1)) {
                        set = charMap.get(c1); // get previous links
                    }
                    if (!set.contains(c2)) { // if c1->c2 doesn't exist then add
                        set.add(c2);
                        charMap.put(c1, set);
                        nodeInDegree.put(c2, nodeInDegree.get(c2)+1);
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<Character>();

        for(char ch: nodeInDegree.keySet()) {
            if (nodeInDegree.get(ch) == 0) {
                queue.add(ch);
            }
        }
        StringBuilder result = new StringBuilder();
        while(!queue.isEmpty()){
            char ch = queue.poll();
            result.append(ch);
            if(charMap.containsKey(ch)) {
                for(char c2: charMap.get(ch)) {
                    nodeInDegree.put(c2,nodeInDegree.get(c2)-1);
                    if(nodeInDegree.get(c2)==0) { // if no more incoming node
                        queue.add(c2);
                    }
                }
            }
        }
        if(result.length() < nodeInDegree.size()) {
            return "";
        }

        return result.toString();
    }

    public static void main(String args[]) {
        AlienDictionary obj = new AlienDictionary();
        System.out.println("Input: [\"wrt\",\"wrf\",\"er\",\"ett\",\"rftt\"]\nOutput: " + obj.alienOrder(new String[]{"wrt","wrf","er","ett","rftt"}));
        System.out.println("Input: [\"a\",\"a\"]\nOutput: " + obj.alienOrder(new String[]{"a","a"}));
        System.out.println("Input: [\"ac\",\"ab\",\"zc\",\"zb\"]\nOutput: " + obj.alienOrder(new String[]{"ac","ab","zc","zb"}));

        System.out.println("1 Input: [\"abc\",\"ab\"]\nOutput: " + obj.alienOrder(new String[]{"abc","ab"}));
    }
}
