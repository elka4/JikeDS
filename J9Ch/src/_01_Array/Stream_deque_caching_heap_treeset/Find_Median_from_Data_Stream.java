package _01_Array.Stream_deque_caching_heap_treeset;

import java.util.Collections;
import java.util.PriorityQueue;
/*
LeetCode – Find Median from Data Stream (Java)

Median is the middle value in an ordered integer list. If the size of the
list is even, there is no middle value. So the median is the mean of the
two middle value.

Analysis

First of all, it seems that the best time complexity we can get for this
problem is O(log(n)) of add() and O(1) of getMedian(). This data structure
seems highly likely to be a tree.

We can use heap to solve this problem. In Java, the PriorityQueue class is
a priority heap. We can use two heaps to store the lower half and the higher
half of the data stream. The size of the two heaps differs at most 1.


 */
public class Find_Median_from_Data_Stream {

    class MedianFinder {
        PriorityQueue<Integer> maxHeap;//lower half
        PriorityQueue<Integer> minHeap;//higher half

        public MedianFinder(){
            maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
            minHeap = new PriorityQueue<Integer>();
        }

        // Adds a number into the data structure.
        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());

            if(maxHeap.size() < minHeap.size()){
                maxHeap.offer(minHeap.poll());
            }
        }

        // Returns the median of current data stream
        public double findMedian() {
            if(maxHeap.size()==minHeap.size()){
                return (double)(maxHeap.peek()+(minHeap.peek()))/2;
            }else{
                return maxHeap.peek();
            }
        }
    }



//------------------------------------------------------------------------------///////////






//------------------------------------------------------------------------------///////////






//------------------------------------------------------------------------------///////////






//------------------------------------------------------------------------------///////////






//------------------------------------------------------------------------------///////////






//------------------------------------------------------------------------------///////////






}
