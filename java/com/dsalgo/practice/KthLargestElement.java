package com.dsalgo.practice;

import java.util.PriorityQueue;

/**
 * Given an unsorted array of numbers, find the ‘K’ largest numbers in it.
 *
 * Input: [3, 1, 5, 12, 2, 11], K = 3
 * Output: [5, 12, 11]
 *
 * Input: [5, 12, 11, -1, 12], K = 3
 * Output: [12, 11, 12]
 *
 * We can use max-heap to find k-th largest element, time complexity will be O(log n)
 *
 * We can use quick select which will take O(n) on avarage and O(n^2) in worse case.
 *
 */
public class KthLargestElement {

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums,0, nums.length-1, nums.length-k);
    }

    public int quickSelect(int[] nums, int low, int high, int kThSmallest) {
        // if there is only one element in the list
        if (low == high) {
            return nums[low];
        }

        int pivot = partition(nums, low, high);

        if (kThSmallest == pivot) {
            return nums[pivot];
        } else if (kThSmallest < pivot) {
            return quickSelect(nums, low, pivot-1, kThSmallest);
        } else {
            return quickSelect(nums, pivot+1, high, kThSmallest);
        }
    }

    public int partition(int[] nums, int low,  int high)  {
        int pivotFinalPos = low;
        if (low < high) {
            int pivot = high;
            for(int i=low; i<high; i++) {
                if (nums[i] < nums[pivot]) {
                    swap(nums, i, pivotFinalPos);
                    pivotFinalPos++;
                }
            }
            swap(nums, pivotFinalPos, pivot);
        }
        return pivotFinalPos;
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public int findKthLargestUsingHeap(int[] nums, int k){
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> a-b);
        for(int i=0; i<nums.length;i++) {
            maxHeap.offer(nums[i]);
            if (maxHeap.size() >  k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    public static void main(String args[]) {
        KthLargestElement ob  = new KthLargestElement();
        System.out.println("\nInput: [1,4,9,6,5,8,2,7] k=1 \nOutput: " + ob.findKthLargest(new int[]{1,4,9,6,5,8,2,7}, 1));
        System.out.println("\nInput: [1,4,9,6,5,8,2,7] k=2 \nOutput: " + ob.findKthLargest(new int[]{1,4,9,6,5,8,2,7}, 2));
        System.out.println("\nInput: [1,4,9,6,5,8,2,7] k=3 \nOutput: " + ob.findKthLargest(new int[]{1,4,9,6,5,8,2,7}, 3));
        System.out.println("\n---- ---- ---- ---- ----\n");
        System.out.println("\nInput: [1,4,9,6,5,8,2,7] k=1 \nOutput: " + ob.findKthLargestUsingHeap(new int[]{1,4,9,6,5,8,2,7}, 1));
        System.out.println("\nInput: [1,4,9,6,5,8,2,7] k=2 \nOutput: " + ob.findKthLargestUsingHeap(new int[]{1,4,9,6,5,8,2,7}, 2));
    }
}
