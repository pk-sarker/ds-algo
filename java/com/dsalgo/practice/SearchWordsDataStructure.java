package com.dsalgo.practice;

import com.dsalgo.practice.common.TrieNode;

public class SearchWordsDataStructure {

    TrieNode trie;

    public SearchWordsDataStructure() {
        trie = new TrieNode();
    }

    /**
     * Complexity Analysis
     *
     * Time complexity: O(M), where M is the key length.
     * At each step, we either examine or create a node in the trie.
     * That takes only MM operations.
     *
     * Space complexity: O(M). In the worst-case newly inserted
     * key doesn't share a prefix with the keys already inserted in the trie.
     * We have to add MM new nodes, which takes O(M) space.
     * @param word
     */
    public void addWord(String word) {
        TrieNode node = trie;

        for(char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.word = true;
    }


    public boolean search(String word) {
        return search(word, trie);
    }

    /**
     * Time complexity: O(M) for the "well-defined" words without dots, where M is the key length,
     * and N is a number of keys, and O(N⋅26^M) for the "undefined" words.
     *
     * Space complexity: O(1) for the search of "well-defined" words without dots,
     * and up to O(M) for the "undefined" words, to keep the recursion stack.
     *
     * @param word
     * @param node
     * @return
     */
    private boolean search(String word, TrieNode node) {
        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                // char is not in child
                if (c=='.') {
                    for(char ch : node.children.keySet()) {
                        TrieNode child = node.children.get(ch);
                        if (search(word.substring(i+1), child)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return node.word;
    }

    public static void main(String args[]) {
        SearchWordsDataStructure obj = new SearchWordsDataStructure();
        obj.addWord("bad");
        obj.addWord("dad");
        obj.addWord("mad");
        obj.addWord("pad");
        obj.addWord("sad");
        obj.addWord("dog");
        obj.addWord("cat");
        obj.addWord("mat");
        System.out.println("Mad: " + obj.search("mad"));
        System.out.println(".ad: " + obj.search(".ad"));
        System.out.println(".at: " + obj.search(".at"));
        System.out.println(".am: " + obj.search(".am"));
    }
}
