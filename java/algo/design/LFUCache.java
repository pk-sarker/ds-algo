package algo.design;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    class Node {
        int key, value, count;
        Node next, previous;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
            this.count = 1;
        }
    }

    class DoublyLinkedList {
        Node head, tail;
        int size;

        public DoublyLinkedList() {
            head = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
            tail = new Node(Integer.MIN_VALUE,Integer.MIN_VALUE);
            head.next = tail;
            tail.previous = head;
        }

        public void add(Node node) {
            head.next.previous = node;
            node.next = head.next;
            head.next = node;
            node.previous = head;
            size++;
        }

        public void remove(Node node) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            size--;
        }

        public Node removeLast() {
            if (size > 0) {
                Node node = tail.previous;
                remove(node);
                return node;
            } else {
                return null;
            }
        }
    }

    int capacity, size, min;
    Map<Integer, Node> nodeMap;
    Map<Integer, DoublyLinkedList> countMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new HashMap<>();
        this.countMap = new HashMap<>();
        this.min = -1;
    }


    public void put(int key, int value) {
        if (capacity == 0)
            return;
        Node node;
        if (!nodeMap.containsKey(key)) {
            node = new Node(key, value);
            nodeMap.put(key, node);
            if (size == capacity) { // cache is full, evict
                DoublyLinkedList lastList = countMap.get(min);
                Node lastNode = lastList.removeLast();
                nodeMap.remove(lastNode.key);
                size--;
            }
            size++;
            min = 1;
            DoublyLinkedList newList = countMap.getOrDefault(node.count, new DoublyLinkedList());
            newList.add(node);
            countMap.put(node.count, newList);
        } else {
            node = nodeMap.get(key);
            node.value = value;
            update(node);
        }
    }

    public int get(int key) {
        Node node;
        if (nodeMap.containsKey(key)) {
            node = nodeMap.get(key);
            update(node);
        } else {
            return -1;
        }
        return node.value;
    }

    private void update(Node node) {
        DoublyLinkedList dlist = countMap.get(node.count);
        dlist.remove(node);
        // if node is the lowest count node,
        // then update min value if there is no other node with same min value
        if (node.count == min && dlist.size == 0) {
            min++;
        }
        node.count++;
        DoublyLinkedList newDlist = countMap.getOrDefault(node.count, new DoublyLinkedList());
        newDlist.add(node);
        countMap.put(node.count, newDlist);
    }

    public static void main(String args[]) {
        LFUCache obj = new LFUCache(2);
        obj.put(1,1);
        System.out.println("put(1,1)");
        obj.put(2,2);
        System.out.println("put(2,2)");
        System.out.println("get(1): "+obj.get(1));
        obj.put(3,3);
        System.out.println("put(3,3)");
        System.out.println("get(2): "+obj.get(2));
        System.out.println("get(3): "+obj.get(3));
        obj.put(4,4);
        System.out.println("put(4,4)");
        System.out.println("get(1): "+obj.get(1));
        System.out.println("get(3): "+obj.get(3));
        System.out.println("get(4): "+obj.get(4));
    }

}
