package com.dsalgo.practice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueRemovals {

    public static int[] findPositions(int[] arr, int x) {
        // Write your code here
        int[] result = new int[x];
        Queue<int[]> queue = new LinkedList();
        for(int i=0;i<arr.length;i++){
            queue.offer(new int[]{arr[i],i+1});
        }
        int i=0;
        while(i<x && !queue.isEmpty()){
            int size = x;
            // compare with value first then with index
            // Used PriorityQueue to find the max in constant time
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b)->b[0]==a[0]?a[1]-b[1]:b[0]-a[0]);
            Queue<int[]> temp = new LinkedList();
            while(size>0 && !queue.isEmpty()){
                int[] item = queue.poll();
                pq.offer(item);
                temp.offer(item); // This  is needed to maintain original order of elements in the queue.
                size--;
            }
            int[] max = pq.poll();
            result[i++]=max[1];
            while(!temp.isEmpty()){
                int[] item = temp.poll();
                // if the current number matched max number or index matches
                if(item[0]==max[0] && item[1]==max[1]){
                    continue;
                }
                if(item[0]!=0){
                    item[0]--;
                }
                queue.offer(item);
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int[] res = QueueRemovals.findPositions(new int[]{1, 2, 2, 3, 4, 5}, 5);
        System.out.println(Arrays.toString(res));
    }
}
