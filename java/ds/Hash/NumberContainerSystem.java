package ds.Hash;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Design a number container system that can do the following:
 *
 *  - Insert or Replace a number at the given index in the system.
 *  - Return the smallest index for the given number in the system.
 *
 * Implement the NumberContainers class:
 *
 *  - NumberContainers() Initializes the number container system.
 *  - void change(int index, int number) Fills the container at index with the number. If there is already a number at that index, replace it.
 *  - int find(int number) Returns the smallest index for the given number, or -1 if there is no index that is filled by number in the system.
 */
public class NumberContainerSystem {
    Map<Integer, Integer> indexMap;
    Map<Integer, TreeSet<Integer>> numberMap;

    public NumberContainerSystem() {
        this.indexMap = new HashMap<>();
        this.numberMap = new HashMap<>();
    }

    public void change(int index, int number) {
        int prevNumber = -1;
        if (indexMap.containsKey(index)) {
            prevNumber = indexMap.get(index);
            numberMap.get(prevNumber).remove(index);
        }
        indexMap.put(index, number);


        // add index to current number
        if (!numberMap.containsKey(number)) {
            numberMap.put(number, new TreeSet<>());
        }
        numberMap.get(number).add(index);
    }

    public int find(int number) {
        if (numberMap.containsKey(number)) {
            TreeSet<Integer> indices = numberMap.get(number);
            if (indices.isEmpty()) {
                return -1;
            }
            return indices.first();
        }
        return -1;
    }

    public static void main(String args[]) {
        NumberContainerSystem obj = new NumberContainerSystem();
        System.out.println("find (7) => "+obj.find(7));
        obj.change(7, 7);
        System.out.println("change(7,7)");
        obj.change(5, 5);
        System.out.println("change(5,5)");
        obj.change(2, 7);
        System.out.println("change(2,7)");
        obj.find(7);
        System.out.println("find (7) -> "+obj.find(7));
        obj.find(5);
        System.out.println("find (5) -> "+obj.find(5));
        obj.change(0, 7);
        System.out.println("change(0,7)");
        obj.find(7);
        System.out.println("find (7) -> "+obj.find(7));
        obj.change(3, 5);
        System.out.println("change(3,5)");
        obj.change(4, 5);
        System.out.println("change(4,5)");
        obj.find(5);
        System.out.println("find (5) -> "+obj.find(5));
    }
}
