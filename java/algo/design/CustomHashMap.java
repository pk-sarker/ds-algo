package algo.design;

import java.util.ArrayList;
import java.util.List;

public class CustomHashMap {
    private int key_space;
    private List<Bucket> hash_table;

    /** Initialize your data structure here. */
    public CustomHashMap() {
        this.key_space = 2069;
        this.hash_table = new ArrayList<Bucket>();
        for (int i = 0; i < this.key_space; ++i) {
            this.hash_table.add(new Bucket());
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash_key = key % this.key_space;
        this.hash_table.get(hash_key).update(key, value);
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping
     * for the key
     */
    public int get(int key) {
        int hash_key = key % this.key_space;
        return this.hash_table.get(hash_key).get(key);
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash_key = key % this.key_space;
        this.hash_table.get(hash_key).remove(key);
    }

    public static void main(String args[]) {

        CustomHashMap obj = new CustomHashMap();

        obj.put(1,2);
        obj.put(2,4);
        obj.put(3,9);
        obj.put(100,30);
        System.out.println("get(1): " + obj.get(1));
        System.out.println("get(2): " + obj.get(2));
        System.out.println("get(3): " + obj.get(3));
        System.out.println("get(100): " + obj.get(100));
    }
}
