package ds.Trie;

import Common.TrieNode;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a
 * dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word
 * that has the prefix prefix, and false otherwise.
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 */
public class ImplementTrie {


    private TrieNode root;

    /** Initialize your data structure here. */
    public ImplementTrie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     * Time complexity: O(m), where m is the key length.
     * Space complexity: O(m)
     * */
    public void insert(String word) {
        TrieNode current = root;
        for( int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if (!current.containsKey(c)) {
                current.put(c, new TrieNode());
            }
            current = current.get(c);
        }
        current.setEnd();
    }

    /**
     * Search a prefix or whole key in trie and
     * Returns the node where search ends
     * a->b->c
     *
     * node -> a
     * node -> b
     * node -> c
     *
     * */
    public TrieNode searchPrefix(String word) {
        TrieNode node = root;

        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if (node.containsKey(c)) {
                node = node.get(c);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        if (node != null && node.isEnd()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns if there is any word in the trie
     *
     * that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    public static void main(String args[]) {
        ImplementTrie obj = new ImplementTrie();
        obj.insert("apple");
        System.out.println("insert(\"apple\")");
        System.out.println("search(\"apple\") " + obj.search("apple"));
        System.out.println("search(\"app\") " + obj.search("app"));
        System.out.println("startsWith(\"app\") " + obj.startsWith("app"));
        obj.insert("app");
        System.out.println("insert(\"app\")");
        System.out.println("search(\"app\") " + obj.search("app"));

    }
}
