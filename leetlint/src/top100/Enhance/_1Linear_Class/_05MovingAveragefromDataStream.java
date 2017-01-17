package top100.Enhance._1Linear_Class;

import java.util.Deque;
import java.util.LinkedList;

public class _05MovingAveragefromDataStream {

public  class MovingAverage {
    private Deque <Integer>  queue;
    private int sum;
    private int size;
    /** Initialize your data structure here. */

    public MovingAverage(int size) {
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
