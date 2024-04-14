package pattern.SubArray;

/***
 * Given a stream of integers and a window size, calculate the moving average of
 * all integers in the sliding window.
 *
 * Implement the MovingAverage class:
 *
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 *
 * Input
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * Output
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * Explanation
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // return 1.0 = 1 / 1
 * movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 *
 *
 */
public class MovingAverageOfDataStream {
    int size;
    int head = 0, count = 0;
    int sum = 0;
    int[] window;

    public MovingAverageOfDataStream(int size) {
        this.size = size;
        this.window = new int[size];
    }

    public double next(int val) {
        ++count;
        int tail = (head + 1) % this.size;
        sum = sum + val - this.window[tail];
        head = (head+1) % this.size;
        this.window[head] = val;

        return sum*1.0 / Math.min(this.size, count);
    }
    public static void main(String args[]) {
        MovingAverageOfDataStream obj = new MovingAverageOfDataStream(3);
        System.out.println("next(1): " + obj.next(1));
        System.out.println("next(10): " + obj.next(10));
        System.out.println("next(3): " + obj.next(3));
        System.out.println("next(5): " + obj.next(5));
    }
}
