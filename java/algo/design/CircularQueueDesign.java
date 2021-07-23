package algo.design;

/**
 *
 * Design your implementation of the circular queue. The circular queue is a linear data structure
 * in which the operations are performed based on FIFO (First In First Out) principle and the last
 * position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the
 * queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if
 * there is a space in front of the queue. But using the circular queue, we can use the space to
 * store new values.
 *
 * Implementation the MyCircularQueue class:
 *
 * - MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * - int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * - int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * - boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * - boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * - boolean isEmpty() Checks whether the circular queue is empty or not.
 * - boolean isFull() Checks whether the circular queue is full or not.
 *
 * You must solve the problem without using the built-in queue data structure in your programming language.
 *
 */
public class CircularQueueDesign {

    private int[] queue;
    private int headIndex;
    private int count;
    private int capacity;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public CircularQueueDesign(int k) {
        this.capacity = k;
        this.queue = new int[k];
        this.headIndex = 0;
        this.count = 0;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (this.count == this.capacity) {
            return false;
        }

        this.queue[(this.headIndex + this.count) % this.capacity] = value;
        this.count += 1;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (this.count == 0) {
            return false;
        }
        this.headIndex = (this.headIndex + 1) % this.capacity;
        this.count -= 1;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (this.count == 0) {
            return -1;
        }
        return this.queue[this.headIndex];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (this.count == 0) {
            return -1;
        }
        int tailIndex = (this.headIndex + this.count - 1) % this.capacity;
        return this.queue[tailIndex];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return (this.count == 0);
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (this.count == this.capacity);
    }

    public static void main(String args[]) {
        CircularQueueDesign queue = new CircularQueueDesign(5);
        System.out.println("Q isEmpty: " + queue.isEmpty());
        System.out.println("enQueue 3: " + queue.enQueue(3));
        System.out.println("Q isEmpty: " + queue.isEmpty());
        System.out.println("enQueue 9: " + queue.enQueue(9));
        System.out.println("enQueue 4: " + queue.enQueue(4));
        System.out.println("dequeue : " + queue.deQueue());
        System.out.println("Front : " + queue.Front());
        System.out.println("Rear : " + queue.Rear());
        System.out.println("enQueue 5: " + queue.enQueue(5));
        System.out.println("enQueue 6: " + queue.enQueue(6));
        System.out.println("enQueue 8: " + queue.enQueue(8));
        System.out.println("Q isEmpty: " + queue.isEmpty());
        System.out.println("Q isFull: " + queue.isFull());
    }
}
