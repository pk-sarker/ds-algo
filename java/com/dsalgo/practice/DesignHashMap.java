package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;

public class DesignHashMap {
    private final int KEY_SPACE = 100000;
    private List<Store> hashMap;
    /** Initialize your data structure here. */
    public DesignHashMap() {
        hashMap = new ArrayList<Store>();
        for(int i=0; i<KEY_SPACE; i++) {
            this.hashMap.add(new Store());
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hashKey = key % KEY_SPACE;
        this.hashMap.get(hashKey).update(key, value);
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hashKey = key % KEY_SPACE;
        return this.hashMap.get(hashKey).get(key);
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hashKey = key % KEY_SPACE;
        this.hashMap.get(hashKey).remove(key);
    }


    public static void main(String args[]) {
        DesignHashMap hashMap = new DesignHashMap();
        hashMap.put(1, 1);
        System.out.println("put(1,1)");
        hashMap.put(2, 2);
        System.out.println("put(2,2)");
        System.out.println("get(2): " + hashMap.get(2));
        System.out.println("get(3): " + hashMap.get(3));
        hashMap.put(3, 4);
        System.out.println("put(3,4)");
        hashMap.put(2, -2);
        System.out.println("put(2,-2)");
        System.out.println("get(2): " + hashMap.get(2));
        hashMap.remove(2);
        System.out.println("remove(2)");
        System.out.println("get(2): "+hashMap.get(2));
        System.out.println("get(3): "+hashMap.get(3));



    }
}
