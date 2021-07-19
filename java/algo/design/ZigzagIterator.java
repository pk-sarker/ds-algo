package algo.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given two vectors of integers v1 and v2, implement an iterator to return their elements alternately.
 *
 * Implement the ZigzagIterator class:
 *
 * ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two vectors v1 and v2.
 * boolean hasNext() returns true if the iterator still has elements, and false otherwise.
 * int next() returns the current element of the iterator and moves the iterator to the next element.
 *
 * Input: v1 = [1,2], v2 = [3,4,5,6]
 * Output: [1,3,2,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements
 * returned by next should be: [1,3,2,4,5,6].
 *
 * Input: v1 = [1], v2 = []
 * Output: [1]
 *
 * Input: v1 = [], v2 = [1]
 * Output: [1]
 *
 * Solution: We can use two pointer approach, and maintain a boolean flag to alternatively read data from the lists/vectors.
 * But if there are more than 2 vectors, let's say there are K vectors and they are in different sizes. In this approach
 * there will be some unnecessary computation happen when we read all the numbers from a or some list.
 *
 * The idea is to use a Queue of pointers of each vector.
 */
public class ZigzagIterator {

    private List<List<Integer>> data = new ArrayList<>();
    private LinkedList<int[]> queue = new LinkedList<>();

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.data.add(v1);
        this.data.add(v2);
        int index = 0;

        for(List<Integer> vec: this.data) {
            if (vec.size()>0) {
                queue.offer(new int[]{index, 0});
            }
            index++;
        }
    }

    public int next() {
        if (this.queue.isEmpty()) {
            return -1;
        }
        int[] pointer = this.queue.removeFirst();
        int vecIndex = pointer[0];
        int elemIndex = pointer[1];
        int nextElementIndex = elemIndex+1;
        if (this.data.get(vecIndex).size() > nextElementIndex) {
            this.queue.offer(new int[]{vecIndex, nextElementIndex}); // if there are more items in the list
        }
        return this.data.get(vecIndex).get(elemIndex);
    }

    public boolean hasNext() {
        return this.queue.size()>0;
    }

    public static void main(String args[]) {
        ZigzagIterator obj = new ZigzagIterator(Arrays.asList(new Integer[]{1,3,5,7}), Arrays.asList(new Integer[]{2,4,6}));
        while(obj.hasNext()) {
            System.out.print(obj.next()+ ", ");
        }
    }
}
