package algo.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Design Add and Search Words Data Structure
 * Design a data structure that supports adding new words and finding if a
 * string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that
 * matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 */
public class SearchDataStructure {

    class TrieNode {
        public Map<Character, TrieNode> children;
        public boolean isWord = false;

        public TrieNode(){
            this.children = new HashMap<>();
        }
    }
    TrieNode trieRoot;
    public SearchDataStructure() {
        this.trieRoot = new TrieNode();
    }
    public void addWord(String word) {
        TrieNode node = this.trieRoot;
        for(int i=0;i<word.length(); i++) {
            char c = word.charAt(i);
            if (node.children.isEmpty() || !node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        return search(word, this.trieRoot);
    }

    public boolean search(String word, TrieNode node) {
        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                // char is not in child
                if (c=='.') {
                    // try to search the remaining letters
                    // in the child nodes, we don't know which child we should
                    // travers so we try to travese  all
                    for(char ch : node.children.keySet()) {
                        TrieNode child = node.children.get(ch);
                        if (search(word.substring(i+1), child)) {  // i-th char is '.', the remaining substring is word.substring(i+1)
                            return true;
                        }
                    }
                }
                return false;
            }
        }
        return node.isWord;
    }

}
