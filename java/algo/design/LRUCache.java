package algo.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * - LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * - int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * - void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 */
public class LRUCache {

    class DLinkedList {
        public DLinkedList next;
        public DLinkedList prev;
        public int key;
        public int value;
        public DLinkedList() {
            next = null;
            prev = null;
        }

        public DLinkedList(int key, int value) {
            this.key = key;
            this.value = value;
        }
        public DLinkedList(DLinkedList next, DLinkedList prev) {
            this.next =  next;
            this.prev = prev;
        }
    }

    int capacity = 0;
    int nodeCount = 0;
    DLinkedList head;
    DLinkedList tail;


    // we use hash map to store node value to node, this is to remove creating
    // duplicate nodes.
    Map<Integer, DLinkedList> hashMap = new HashMap<>();

    /**
     * As there will be frequent insert and delete it will be good to use
     * linked list data structure to store the items. Doubly linked list will be
     * good in this case. We need to keep track of previous to delete an element.
     *
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new DLinkedList();
        this.tail = new DLinkedList();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * Get suppose to be constant time, O(1)
     * @param key
     * @return
     */
    public int get(int key) {
        DLinkedList node = hashMap.get(key);
        if (node!=null) {
            this.removeNode(node);
            this.addNodeToTop(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        DLinkedList node = null;
        if (this.hashMap.containsKey(key)) {
            node = this.hashMap.get(key);

            node.value = value;
            removeNode(node);
            addNodeToTop(node);
            hashMap.put(key, node);
        } else {
            node = new DLinkedList(key, value);
            addNodeToTop(node);
            this.nodeCount++;
            hashMap.put(key, node);

            if (this.nodeCount > this.capacity) {
                popNode();
                this.nodeCount--;
            }
        }

    }

    public void addNodeToTop(DLinkedList node) {
        DLinkedList next = this.head.next;
        node.prev = this.head;
        node.next = this.head.next;
        next.prev = node;
        this.head.next = node;
    }
    public void popNode() {
        DLinkedList lastNode = this.tail.prev;
        DLinkedList newLastNode = lastNode.prev;
        hashMap.remove(lastNode.key);
        lastNode = null;
        newLastNode.next = this.tail;
        this.tail.prev = newLastNode;
    }
    public void removeNode(DLinkedList node) {
        DLinkedList prev = node.prev;
        DLinkedList next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public static void main(String args[]) {
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(1, 1); // cache is {1=1}
        System.out.println("put(1,1)");
        lruCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println("put(1,2)");
        //lruCache.get(1);    // return 1
        System.out.println("get(1) = 1 == " + lruCache.get(1));
        lruCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println("pt(3,3)");
        lruCache.get(2);    // returns -1 (not found)
        System.out.println("get(2) = -1 == " + lruCache.get(2));
        lruCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println("put(4,4)");
        lruCache.get(1);    // return -1 (not found)
        System.out.println("get(1) = -1 == " + lruCache.get(1));
        lruCache.get(3);    // return 3
        System.out.println("get(3) = 3 == " + lruCache.get(3));
        lruCache.get(4);    // return 4
        System.out.println("get(4) = 4 == " + lruCache.get(4));

    }
}
