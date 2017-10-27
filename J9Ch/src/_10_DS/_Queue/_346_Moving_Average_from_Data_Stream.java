package _10_DS._Queue;
import java.util.*;

//346. Moving Average from Data Stream

public class _346_Moving_Average_from_Data_Stream {

//    Java O(1) time solution.
//    The idea is to keep the sum so far and update the sum just by replacing the oldest number with the new entry.
    public class MovingAverage {
        private int [] window;
        private int n, insert;
        private long sum;

        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            window = new int[size];
            insert = 0;
            sum = 0;
        }

        public double next(int val) {
            if (n < window.length)  n++;
            sum -= window[insert];
            sum += val;
            window[insert] = val;
            insert = (insert + 1) % window.length;

            return (double)sum / n;
        }
    }


/*    Java easy to understand solution
    Essentially, we just need to keep track of the sum of the current window as we go. This prevents an O(n) traversal over the Queue as we add new numbers to get the new average. If we need to evict then we just subtract that number off of our sum and divide by the size.*/

    public class MovingAverage2 {
        private double previousSum = 0.0;
        private int maxSize;
        private Queue<Integer> currentWindow;

        public MovingAverage2(int size) {
            currentWindow = new LinkedList<Integer>();
            maxSize = size;
        }

        public double next(int val) {
            if (currentWindow.size() == maxSize)
            {
                previousSum -= currentWindow.remove();
            }

            previousSum += val;
            currentWindow.add(val);
            return previousSum / currentWindow.size();
        }
    }

    public  class MovingAverage3 {
        private Deque <Integer>  queue;
        private int sum;
        private int size;
        /** Initialize your data structure here. */

        public MovingAverage3(int size) {
            this.queue = new LinkedList <Integer> ();
            this.sum = 0;
            this.size = size;
        }

        public double next(int val) {
            if (queue.size() == size) {
                int last = queue.pollFirst();
                sum -= last;
            }
            queue.offerLast(val);
            sum += val;
            return sum * 1.0 / queue.size();
        }
    }
}
/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

 */