package algo.design;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Design an iterator that supports the peek operation on a list in addition to the hasNext and the next operations.
 *
 * Implement the PeekingIterator class:
 *
 *  - PeekingIterator(int[] nums) Initializes the object with the given integer array nums.
 *  - int next() Returns the next element in the array and moves the pointer to the next element.
 *  -  bool hasNext() Returns true if there are still elements in the array.
 *  - int peek() Returns the next element in the array without moving the pointer.
 *
 *  Input
 * ["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
 * [[[4, 1, 5]], [], [], [], [], []]
 * Output
 * [null, 4, 1, 1, 5, false]
 *
 * Saving next item in next pointer/variable as both next() and peek()
 * should return the next item. The difference is that peek() won't move
 * the pointer.
 */
public class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> itr;
    private Integer next;

    public PeekingIterator(Iterator<Integer> iterator){
        this.itr = iterator;

        if (this.itr.hasNext()) {
            this.next = this.itr.next();
        }
    }
    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public Integer next() {
        if (this.next == null) {
            throw new NoSuchElementException();
        }
        Integer nextItem = this.next;
        this.next = null;
        if (this.itr.hasNext()) {
            this.next = this.itr.next();
        }

        return nextItem;
    }

    public int peek() {
        return this.next;
    }

    public static void main(String args[]) {
        List<Integer> data = Arrays.asList(new Integer[]{1,2,3,4,5,6});
        Iterator<Integer> iterator = data.listIterator();
        PeekingIterator obj = new PeekingIterator(iterator);
        System.out.println("Input: [1,2,3,4,5,6]");
        System.out.println( "peek : "+ obj.peek());
        System.out.println( "next : "+ obj.next());
        System.out.println( "next : "+ obj.next());
        System.out.println( "peek : "+ obj.peek());
        System.out.println( "next : "+ obj.next());
        System.out.println( "hasNext : "+ obj.hasNext());
    }


}
