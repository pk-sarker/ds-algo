package algo.design;

public class Trie {

    class TrieNode {
        TrieNode[] childrens;
        boolean endOfWord;
        int childSize = 26;

        public TrieNode() {
            childrens = new TrieNode[childSize];
        }

        public boolean containsChild(char key) {
            return childrens[key - 'a'] != null;
        }
        public TrieNode get(char key) {
            return childrens[key - 'a'];
        }
        public void add(char key, TrieNode node) {
            childrens[key-'a'] = node;
        }

        public void setEndOrWord() {
            this.endOfWord = true;
        }
        public boolean isEndOrWord() {
            return this.endOfWord;
        }
    }

    TrieNode rootNode;
    public Trie() {
        this.rootNode = new TrieNode();
    }

    public void insert(String word){
        TrieNode current = this.rootNode;
        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if (!current.containsChild(c)) {
                current.add(c, new TrieNode());
            }
            current = current.get(c);
        }
        current.setEndOrWord();
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        if (node != null && node.isEndOrWord()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    public TrieNode searchPrefix(String word) {
        TrieNode current = rootNode;
        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if (current.containsChild(c)) {
                current = current.get(c);
            } else {
                return null;
            }
        }
        return current;
    }

    public static void main(String args[]) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println("Insert: apple");
        System.out.println("Search: apple , found ? " + trie.search("apple"));
    }
}
