package com.dsalgo.practice;

import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest
 * transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * - Return an empty list if there is no such transformation sequence.
 * - All words have the same length.
 * - All words contain only lowercase alphabetic characters.
 * - You may assume no duplicates in the word list.
 * - You may assume beginWord and endWord are non-empty and are not the same.
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 *
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadder2 {
    public List<List<String>> ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Build edges of the graph
        wordList.add(beginWord);
        Map<String, List<String>> patterns = new HashMap<>();
        int wlen = beginWord.length();
        for(String word: wordList) {
            for(int i=0; i<wlen; i++) {
                String newword = word.substring(0, i) + "*" + word.substring(i+1, wlen);
                patterns.computeIfAbsent(newword, k -> new ArrayList<>()).add(word);
            }
        }

        // creating  adjacency  list
        Map<String, Set<String>> edges = new HashMap<>();
        for(String pattern: patterns.keySet()) {
            for(String node1 : patterns.get(pattern))  {
                edges.put(node1, edges.computeIfAbsent(node1, k -> new HashSet<>()));
                System.out.println("Pattern: " + pattern + " node1: " + node1);
                for(String node2: patterns.get(pattern))  {
                    if (!node1.equals(node2)) {
                        edges.get(node1).add(node2);
                        System.out.println("\t Node2: " + node2);
                    }
                }
            }
        }

        // Now BFS Search
        List<List<String>> result = new ArrayList<>();
        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new LinkedList<>(List.of(beginWord)));

        boolean found = false;
        while (!queue.isEmpty() && !found) {
            int k = queue.size();
            Set<String> newVisited = new HashSet<>();
            while (k > 0) {
                LinkedList<String> p = (LinkedList<String>) queue.poll();
                String cur = p.getLast(); // its a linked list get the last node
                if (cur.equals(endWord)) {
                    found = true;
                    result.add(p);
                } else if (!visited.contains(cur)) {
                    newVisited.add(cur);
                    for (String w : edges.getOrDefault(cur,Set.of())) {
                        // if the adjacent node, w is not current node and w is not visited so far
                        if (!w.equals(cur) && !visited.contains(w))
                            // add w after last node (p)
                            queue.add(new LinkedList<>(p){{add(w);}});
                    }
                }
                k--;
            }
            visited.addAll(newVisited);
        }
        return result;

    }
    public static void main(String args[]) {
        WordLadder2 ob = new WordLadder2();
        List<String> wlist  = new ArrayList<String>(){{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
            add("cog");
        }};
        System.out.println("\nInput: [\"hot\",\"dot\",\"dog\",\"lot\",\"log\",\"cog\"], start: \"hit\", end: \"cog\"\nOutput: " + ob.ladderLength("hit", "cog", wlist));

    }
}
