package algo.design;

import java.util.HashMap;
import java.util.Map;

/**
 * You've created a new programming language, and now you've decided to add hashmap support to it.
 * to add a number to all hashmap keys, or all its values.
 * So you've decided to take matters into your own hands and implement your own hashmap
 * in your new language that has the following operations:
 *
 * - insert x y - insert an object with key x and value y.
 * - get x - return the value of an object with key x.
 * - addToKey x - add x to all keys in map.
 * - addToValue y - add y to all values in map.
 *
 * To test out your new hashmap, you have a list of queries in the form of two arrays:
 * queryTypes contains the names of the methods to be called (eg: insert, get, etc),
 * and queries contains the arguments for those methods (the x and y values).
 *
 * Your task is to implement this hashmap, apply the given queries, and to find the sum of all the
 * results for get operations.
 *
 * Example
 * For queryType = ["insert", "insert", "addToValue", "addToKey", "get"] and
 * query = [[1, 2], [2, 3], [2], [1], [3]],
 * the output should be hashMap(queryType, query) = 5.
 */

public class HashMapForNewProgrammingLang {
    private final Map<Integer, Integer> actualMap;
    private int keyOffset = 0;
    private int valueOffset = 0;

    public HashMapForNewProgrammingLang() {
        actualMap = new HashMap<>();
    }

    public int size() {
        return actualMap.size();
    }

    public boolean isEmpty() {
        return actualMap.isEmpty();
    }

    public boolean containsKey(int key) {
        var keyWithoutOffset = key - keyOffset;
        return actualMap.containsKey(keyWithoutOffset);
    }

    public boolean containsValue(int value) {
        var valueWithoutOffset = value - valueOffset;
        return actualMap.containsValue(valueWithoutOffset);
    }

    public Integer get(int key) {
        var keyWithoutOffset = key - keyOffset;
        var value = actualMap.get(keyWithoutOffset);
        if (value == null) return null;
        return value + valueOffset;
    }

    public Integer put(int key, int value) {
        var keyWithoutOffset = key - keyOffset;
        var valueWithoutOffset = value - valueOffset;
        var oldValue = actualMap.put(keyWithoutOffset, valueWithoutOffset);
        if (oldValue == null) return null;
        return oldValue + valueOffset;
    }

    public Integer remove(int key) {
        var keyWithoutOffset = key - keyOffset;
        var oldValue = actualMap.remove(keyWithoutOffset);
        if (oldValue == null) return null;
        return oldValue + valueOffset;
    }

    public void clear() {
        actualMap.clear();
        keyOffset = 0;
        valueOffset = 0;
    }

    public int getKeyOffset() {
        return keyOffset;
    }

    public void setKeyOffset(int keyOffset) {
        this.keyOffset = keyOffset;
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public void setValueOffset(int valueOffset) {
        this.valueOffset = valueOffset;
    }

    public void addToValues(int toAdd) {
        this.valueOffset += toAdd;
    }

    public void addToKeys(int toAdd) {
        this.keyOffset += toAdd;
    }
    public static void main(String args[]) {
        HashMapForNewProgrammingLang obj = new HashMapForNewProgrammingLang();
        obj.put(1,2);
        obj.put(2,3);
        obj.put(3,5);
        System.out.println("get(2) : " + obj.get(2));
        obj.addToValues(2);
        System.out.println("addToValues(2)");
        System.out.println("get(2) : " + obj.get(2));
        obj.addToKeys(1);
        System.out.println("addToKeys(1)");
        System.out.println("get(1) : " + obj.get(1));
        System.out.println("get(2) : " + obj.get(2));
        System.out.println("get(3) : " + obj.get(3));
        System.out.println("get(4) : " + obj.get(4));
    }
}
