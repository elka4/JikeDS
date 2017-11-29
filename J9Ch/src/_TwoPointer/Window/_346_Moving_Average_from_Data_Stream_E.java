package _TwoPointer.Window;
import org.junit.Test;
import java.util.*;

//  346. Moving Average from Data Stream
//  https://leetcode.com/problems/moving-average-from-data-stream/description/
//
//
public class _346_Moving_Average_from_Data_Stream_E {
//-------------------------------------------------------------------------
/*Java O(1) time solution.
    The idea is to keep the sum so far and update the sum just by replacing the oldest number with the new entry.*/

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


//-------------------------------------------------------------------------
    class Solution2{
    /*
        Java easy to understand solution
        Essentially, we just need to keep track of the sum of the current window as we go. This prevents an O(n) traversal over the Queue as we add new numbers to get the new average. If we need to evict then we just subtract that number off of our sum and divide by the size.
    */

        public class MovingAverage {
            private double previousSum = 0.0;
            private int maxSize;
            private Queue<Integer> currentWindow;

            public MovingAverage(int size) {
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
    }


//-------------------------------------------------------------------------
    //JAVA O(1) using Deque

    public class MovingAverage3 {

        Deque<Integer> dq;
        int size;
        int sum;
        public MovingAverage3(int size) {
            dq = new LinkedList<>();
            this.size = size;
            this.sum = 0;
        }

        public double next(int val) {
            if (dq.size() < size) {
                sum += val;
                dq.addLast(val);
                return (double) (sum / dq.size());
            } else {
                int temp = dq.pollFirst();
                sum -= temp;
                dq.addLast(val);
                sum += val;
                return (double) (sum / size);
            }
        }

    }
//-------------------------------------------------------------------------




}
/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
Seen this question in a real interview before?   Yes  No
Companies
Google
Related Topics
Design Queue
 */