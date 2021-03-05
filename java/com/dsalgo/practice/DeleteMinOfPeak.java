package com.dsalgo.practice;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given an array of integers. Delete min of peak elements one by one in an array
 * Peak element defined as it should be greater than it’s neighbors.
 * 2,7,8,5,1,6,3,9,4
 * 6,8,7,5,9,2,4,3,1
 * current peak: 8, 6, 9, min: 6
 * delete 6
 * current peaks: 8, 9
 * check neighbor of deleted peak, if 1 or 3 is peak after deletion of 6 - no
 *
 * delete 8
 * check 7 & 5 if they are peak
 * current peaks: 7, 9
 *
 * delete 7
 * current peaks: 5, 9
 *
 * delete 5
 * current peaks: 9
 *
 * delete 9  (2, 1, 3, 4)
 * current peaks: 9
 *
 *
 *
 */
public class DeleteMinOfPeak {

    public static int[] deleteMinPeak1(int[] nums) {
        int  n =  nums.length;
        List<Integer> nList = IntStream.of(nums).boxed().collect(Collectors.toList());
        int[] res = new int[n];
        int count = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        HashMap<Integer, Integer> map =  new HashMap<>();
        Set<Integer> seen = new HashSet<>();
        for(int i=0; i<n; i++)  {

            // find lowest peak
            if (nList.size() == 1) {
                res[count++] = nList.get(0);
                break;
            }
            for(int j=0; j < nList.size();  j++)  {
                if (map.containsKey(nList.get(j))) {
                    map.put(nList.get(j), j);
                    continue;
                }

                if (j==0 && j+1 < nList.size() && nList.get(j) > nList.get(j+1)) {
                    minHeap.offer(nList.get(j));
                    //seen.add(nList.get(j));
                    map.put(nList.get(j), j);
                }
                if (j== nList.size() - 1 && j-1 >=0 && nList.get(j) > nList.get(j-1)) {
                    minHeap.offer(nList.get(j));
                    //seen.add(nList.get(j));
                    map.put(nList.get(j), j);
                }
                if (j-1 >= 0 && j+1 < nList.size() && nList.get(j)  > nList.get(j-1) && nList.get(j) > nList.get(j+1)) {
                    minHeap.offer(nList.get(j));
                    //seen.add(nList.get(j));
                    map.put(nList.get(j), j);
                }
            }
            Integer peak = minHeap.poll();
            if (peak != null) {
                nList.remove(map.get(peak).intValue());
                res[count++] = peak.intValue();
            }

        }
        return res;
    }
    public static int[] deleteMinPeak(int[] nums) {
        int n = nums.length;
        if (n==1) {
            return new int[]{nums[0]};
        }
        if (n==2) {
            int[] res  = new int[2];
            if (nums[0]> nums[1]) {
                res[0] = nums[0];
                res[1] = nums[1];
            } else {
                res[0] = nums[1];
                res[1] = nums[0];
            }
            return res;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
        Map<Integer, Integer> indexMap = new HashMap<>();
        for(Integer i=0; i<n;  i++)  {
            indexMap.put(nums[i], i);
            if (i==0 && nums[i] > nums[i+1]) {
                minHeap.offer(nums[i]);
            } else if (i==n-1 && nums[n-1]  > nums[n-2]) {
                minHeap.offer(nums[i]);
            } else if  (i-1 >= 0 && i+1 < n && nums[i-1] < nums[i] && nums[i] > nums[i+1]){
                minHeap.offer(nums[i]);
            }
        }

        int[] res = new int[n];
        int count = 0;

        Set<Integer> seen = new HashSet<>();
        int processed = 0;
        while (processed < n) {
            if (minHeap.isEmpty()) {
                // check border elements
                System.out.println("-- minHeap empty --, " + Arrays.toString(res));

                for(int i=0; i<n; i++) {
                    if (!seen.contains(i))  {
                        if (processed == n-1) {
                            res[count] = nums[i];
                            processed++;
                            System.out.println("-- Done -- , " + Arrays.toString(res));
                            break;
                        }
                        int rn = findRightNeighbor(nums, seen, i);
                        if (rn > -1 && rn < n && nums[i] > nums[rn]) {
                            minHeap.add(nums[i]);
                        }
                        break;
                    }
                }
                for(int i=n-1; i>=0; i--) {
                    if (!seen.contains(i))  {
                        int ln = findLeftNeighbor(nums, seen, i);
                        if (ln > -1 && ln < n && nums[i] > nums[ln]) {
                            minHeap.add(nums[i]);
                        }
                        break;
                    }
                }
            } else {
                int  peak = minHeap.poll();
                res[count++] = peak;
                processed++;
                // update index of peak neighbors
                Integer index = indexMap.get(peak);
                seen.add(index);

                if (index == 0  && index+2 < n && nums[index+1] > nums[index+2]) {
                    minHeap.add(nums[index+1]);
                } else if (index == n-1 && index-3 >= 0 && nums[index-2] > nums[index-3]){
                    minHeap.add(nums[index-2]);
                } else {
                    int[] idx = findLeftRightNeighbor(nums, seen, index);
                    if (idx[0] != -1 && idx[1] != -1) {
                        if (nums[idx[0]] > nums[idx[1]])  {
                            int ln = findLeftNeighbor(nums, seen, idx[0]);
                            if (ln > -1 && nums[ln] < nums[idx[0]]) {
                                minHeap.add(nums[idx[0]]);
                            }
                        } else {
                            int rn = findRightNeighbor(nums, seen, idx[1]);
                            if (rn > -1 && rn < n && nums[rn] < nums[idx[1]]) {
                                minHeap.add(nums[idx[1]]);
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    public static int[] findLeftRightNeighbor(int[] nums, Set<Integer> seen, int idx) {
        int  ln = findLeftNeighbor(nums, seen,  idx), rn = findRightNeighbor(nums, seen, idx);
        return new int[]{ln, rn};
    }

    public static int findLeftNeighbor(int[] nums, Set<Integer> seen, int idx) {
        int  ln = -1;
        if (idx-1 < 0) {
            return ln;
        }
        for(int i=idx-1; i>=0; i--) {
            if (!seen.contains(i)) {
                ln = i;
                break;
            }
        }
        return ln;
    }

    public static int findRightNeighbor(int[] nums, Set<Integer> seen, int idx) {
        int  rn = -1;
        if (idx+1 >= nums.length) {
            return rn;
        }
        for(int i=idx+1; i<nums.length; i++) {
            if (!seen.contains(i)) {
                rn = i;
                break;
            }
        }
        return rn;
    }

    // Function to return the list of
    // minimum peak elements
    static void minPeaks(ArrayList<Integer> list)
    {

        // Length of original list
        int n = list.size();

        // Initialize resultant list
        ArrayList<Integer> result
                = new ArrayList<>();

        // Traverse each element of list
        for (int i = 0; i < n; i++) {

            int min = Integer.MAX_VALUE;
            int index = -1;

            // Length of original list after
            // removing the peak element
            int size = list.size();

            // Traverse new list after removal
            // of previous min peak element
            for (int j = 0; j < size; j++) {

                // Update min and index,
                // if first element of
                // list > next element
                if (j == 0 && j + 1 < size) {

                    if (list.get(j) > list.get(j + 1)
                            && min > list.get(j)) {
                        min = list.get(j);
                        index = j;
                    }
                }

                else if (j == size - 1
                        && j - 1 >= 0) {

                    // Update min and index,
                    // if last elemnt of
                    // list > previous one
                    if (list.get(j)
                            > list.get(j - 1)
                            && min
                            > list.get(j)) {
                        min = list.get(j);
                        index = j;
                    }
                }

                // Update min and index, if
                // list has single element
                else if (size == 1) {

                    min = list.get(j);
                    index = j;
                }

                // Update min and index,
                // if current element >
                // adjacent elements
                else if (list.get(j)
                        > list.get(j - 1)
                        && list.get(j)
                        > list.get(j + 1)
                        && min
                        > list.get(j)) {

                    min = list.get(j);
                    index = j;
                }
            }

            // Remove current min peak
            // element from list
            list.remove(index);

            // Insert min peak into
            // resultant list
            result.add(min);
        }

        // Print resultant list
        System.out.println(result);
    }

    public static void main(String args[]) {
//        System.out.println("\nInput: [2,7,8,5,1,6,3,9,4] \nOutput: " + Arrays.toString(DeleteMinOfPeak.deleteMinPeak(new int[]{2,7,8,5,1,6,3,9,4})));
//        System.out.println("\nInput: [2,7,8,5,1,6,3,9,4] \nOutput: " + Arrays.toString(DeleteMinOfPeak.deleteMinPeak(new int[]{2,7,8,5,1,6,3,9,4})));
        System.out.println("\nInput: [2,7,8,5,1,6,3,9,4] \nOutput: " + Arrays.toString(DeleteMinOfPeak.deleteMinPeak1(new int[]{2,7,8,5,1,6,3,9,4})));

//        System.out.println("\nInput: [2,7,8,5,1,6,3,9,4] \nOutput: ");
//        ArrayList<Integer> arr = new ArrayList<>(
//                Arrays.asList(2,7,8,5,1,6,3,9,4));
//        DeleteMinOfPeak.minPeaks(arr);

    }
}
