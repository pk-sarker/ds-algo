package com.dsalgo.practice.common;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    public Map<Character, TrieNode> children;
    public boolean word = false;
    public TrieNode(){
        this.children = new HashMap<>();
    }
}
