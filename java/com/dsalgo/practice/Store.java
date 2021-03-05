package com.dsalgo.practice;

import java.util.LinkedList;
import java.util.List;

public class Store {
    private List<KeyValuePair<Integer, Integer>> hash_table;

    public Store() {
        this.hash_table = new LinkedList<KeyValuePair<Integer, Integer>>();
    }

    public int get(Integer key) {
        for(KeyValuePair<Integer, Integer> keyValue : this.hash_table) {
            if (keyValue.key == key) {
                return keyValue.value;
            }
        }
        return -1;
    }

    public void update(Integer key, Integer value) {
        boolean found = false;
        for(KeyValuePair<Integer, Integer> keyValue : this.hash_table) {
            if (keyValue.key == key) {
                keyValue.value = value;
                found = true;
                break;
            }
        }
        if (!found) {
            this.hash_table.add(new KeyValuePair<>(key, value));
        }
    }

    public void remove(Integer key) {
        for(KeyValuePair<Integer, Integer> keyValue : this.hash_table) {
            if (keyValue.key == key) {
                this.hash_table.remove(keyValue);
                break;
            }
        }
    }
}
