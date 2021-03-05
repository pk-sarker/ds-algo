package com.dsalgo.practice;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given an array and operations
 * There are 3 operations
 * a) 2 then you should return min element in array
 * b) (0,x) add x to elements of an array
 * c? (1,x) add x to end of an array
 */
public class OperationAddMin {
    List<Integer> nums = new ArrayList<>();
    List<Integer> minList = new ArrayList<>();
    PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a-b);
    Integer addToAll = 0;
    public OperationAddMin(int[] num){
        nums = IntStream.of(num).boxed().collect(Collectors.toList());
        for(int i=0; i<num.length;i++) {
            minHeap.offer(num[i]);
        }
    }
    public void operation(int arg1, int arg2) {
        if (arg1 == 0) {
            this.addToAll  += arg2;
        } else if(arg1 == 1) {
            nums.add(arg2-addToAll);
            minHeap.offer(arg2-addToAll);
        }
    }
    public int operation(int arg1) {
        return minHeap.peek() + addToAll;
    }
    public static void main(String  args[]) {
        OperationAddMin  ob = new OperationAddMin(new int[]{3,6,2,9,5});
        System.out.println("\nInput: [3,6,2,9,5]");
        System.out.println("Min: " + ob.operation(2));

        ob.operation(1, 1);
        System.out.println("op(1, 1)");
        System.out.println("Min: " + ob.operation(2));
        ob.operation(0, 2);
        System.out.println("op(0, 2)");
        System.out.println("Min: " + ob.operation(2));
        ob.operation(1, 0);
        System.out.println("op(1, 0)");
        System.out.println("Min: " + ob.operation(2));
    }
}
