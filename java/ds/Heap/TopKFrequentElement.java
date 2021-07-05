package ds.Heap;

import java.util.*;

public class TopKFrequentElement {


    Map<Integer, Integer> counts = new HashMap<>();
    int[] unique;
    int[] nums;
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        if (n==k) {
            return nums;
        }
        this.nums = nums;

        for(int i: nums) {
            this.counts.put(i, this.counts.getOrDefault(i, 0) + 1);
        }
        int un = counts.size();
        unique = new int[un];
        int i = 0;
        for (int num: counts.keySet()) {
            unique[i] = num;
            i++;
        }
        quickSelect(0, un-1, un-k);
        return Arrays.copyOfRange(unique, un - k, un);
    }

    public void quickSelect(int left, int right, int smPos) {
        if (left == right) {
            return;
        }

        int pivot_index = partition(left, right);
        if (pivot_index == smPos) {
            return;
        } else if (smPos < pivot_index) {
            quickSelect(left, pivot_index-1, smPos);
        } else {
            quickSelect(pivot_index+1, right, smPos);
        }

    }

    public int partition(int low, int high) {
        int pivot = this.counts.get(this.unique[high]);
        int left = low;
        while(low < high) {
            if (this.counts.get(this.unique[low]) < pivot){
                swap(low, left);
                left++;
            }
            low++;
        }
        swap(left, high);
        return left;
    }

    public void swap(int a, int b) {
        int t = this.unique[a];
        this.unique[a] = this.unique[b];
        this.unique[b] = t;
    }

    public static int[] topKFrequentPQ(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }

        // 1. build hash map : character and how often it appears
        // O(N) time
        Map<Integer, Integer> count = new HashMap();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }

        // 3. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }


    public static void main(String args[]) {
        TopKFrequentElement obj = new TopKFrequentElement();
        System.out.println(Arrays.toString(obj.topKFrequent(new int[]{1,1,1,2,2,3}, 2)));
        System.out.println(Arrays.toString(obj.topKFrequentPQ(new int[]{1,1,1,2,2,3}, 2)));
    }

}
