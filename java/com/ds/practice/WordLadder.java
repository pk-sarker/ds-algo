package com.ds.practice;

// import javafx.util.Pair;

import java.util.*;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary
 * wordList is a sequence of words such that:
 * - The first word in the sequence is beginWord.
 * - The last word in the sequence is endWord.
 * - Only one letter is different between each adjacent pair of words in the sequence.
 * - Every word in the sequence is in wordList.
 *
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words
 * in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog" with 5 words.
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no possible transformation.
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int l = beginWord.length();

        // map the states for each word in the wordList
        Map<String, List<String>> stateMap = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();
        for(int i=0; i<wordList.size(); i++) {
            String word = wordList.get(i);
            for(int j=0; j<l; j++) {
                String state = word.substring(0,j) + "*" + word.substring(j+1,l);
                List<String> list = stateMap.computeIfAbsent(state, k -> new ArrayList<>());
                list.add(word);
                stateMap.put(state, list);
            }
        }
        Queue<Map<String, Integer>> queue = new LinkedList<Map<String, Integer>>();
        Map<String, Integer> map =  new HashMap<>();
        map.put(beginWord, 1);

        queue.add(map);
        visited.put(beginWord, true);
        while(!queue.isEmpty()) {
            Map<String, Integer> wordMap = queue.remove();
            String key = (String) wordMap.keySet().toArray()[0];
            Integer level = wordMap.get(key);

            // intermidiate states
            for(int i=0; i<l;i++) {
                String state = key.substring(0,i) + "*" + key.substring(i+1,l);
                List<String> listOfAdjacentWords = stateMap.computeIfAbsent(state, k -> new ArrayList<String>());
                for (String adjacentWord : listOfAdjacentWords) {
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    if (!visited.containsKey(adjacentWord)) {
                        Map<String, Integer> map1 =  new HashMap<>();
                        map1.put(adjacentWord, level+1);
                        queue.add(map1);
                        visited.put(adjacentWord, true);
                    }
                }

            }

        }
        return 0;
    }

    public static void main(String args[]) {
        WordLadder ob = new WordLadder();
        List<String> wlist  = new ArrayList<String>(){{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
            add("cog");
        }};
       System.out.println("\nInput: [\"hot\",\"dot\",\"dog\",\"lot\",\"log\",\"cog\"], start: \"hit\", end: \"cog\"\nOutput: " + ob.ladderLength("hit", "cog", wlist));
        wlist.clear();
        wlist = Arrays.asList("hot","dot","dog","lot","log");
       System.out.println("\nInput: [\"hot\",\"dot\",\"dog\",\"lot\",\"log\"], start: \"hit\", end: \"cog\"\nOutput: "+ ob.ladderLength("hit", "cog", wlist));
    }
}
