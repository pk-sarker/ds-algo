package com.ds.practice;

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
 *
 * Follow up:
 * - Could you do get and put in O(1) time complexity?
 */
public class LeastRecentlyUsedCache {
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
    int capacity;
    int nodeCount = 0;
    DLinkedList head;
    DLinkedList tail;
    Map<Integer, DLinkedList> hashMap = new HashMap<>();
    public LeastRecentlyUsedCache(int capacity) {
        this.capacity = capacity;
        head = new DLinkedList();
        tail = new DLinkedList();
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        DLinkedList node = hashMap.get(key);
        if (node!=null) {
            this.removeNode(node);
            this.addNodeToTop(node);
            return node.value;
        }
        return -1;
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
    public void put(int key, int value) {
        DLinkedList node = hashMap.get(key);
        if (node==null) {
            node = new DLinkedList(key, value);
            addNodeToTop(node);
            this.nodeCount++;
            hashMap.put(key, node);

            if (this.nodeCount > this.capacity) {
                popNode();
                this.nodeCount--;
            }
        } else {
            node.value = value;
            removeNode(node);
            addNodeToTop(node);
            hashMap.put(key, node);
        }
    }

    public static void main(String args[]) {
        LeastRecentlyUsedCache ob = new LeastRecentlyUsedCache(2);
        System.out.println("put(1,1)");
        ob.put(1, 1);
        System.out.println("put(2,2)");
        ob.put(2, 2);
        System.out.println("get(1) " + ob.get(1));
        ob.put(3, 3);
        System.out.println("put(3,3)");
        System.out.println("get(2) " + ob.get(2));
        System.out.println("get(1) " + ob.get(1));
        ob.put(4, 4);
        System.out.println("put(4,4)");
        System.out.println("get(2) " + ob.get(2));
        System.out.println("get(3) " + ob.get(3));
        ob.put(3, -3);
        System.out.println("put(3,-3)");
        System.out.println("get(3) " + ob.get(3));
        System.out.println("get(4) " + ob.get(4));
    }
}
