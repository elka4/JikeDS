package ch9J.chapter7_Array;
import java.util.*;
/**
 * Created by tianhuizhu on 6/20/17.
 */
public class Median_in_Data_Stream {
    // LintCode Solution
    public class Solution {
        /**
         * @param nums: A list of integers.
         * @return: the median of numbers
         */
        public int[] medianII(int[] nums) {
            // write your code here
            if(nums == null)
                return null;
            int[] res = new int[nums.length];

            PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
                @Override
                public int compare(Integer x, Integer y) {
                    return y - x;
                }
            });

            res[0] = nums[0];
            maxHeap.add(nums[0]);

            for(int i = 1; i < nums.length; i++) {
                int x = maxHeap.peek();
                if(nums[i] <= x) {
                    maxHeap.add(nums[i]);
                } else {
                    minHeap.add(nums[i]);
                }
                if(maxHeap.size() > minHeap.size()+1 ) {
                    minHeap.add(maxHeap.poll());
                } else if(maxHeap.size() < minHeap.size()) {
                    maxHeap.add(minHeap.poll());
                }
                res[i] = maxHeap.peek();
            }
            return res;
        }
    }

    // 写法一 leetcode解
    class MedianFinder {
        public PriorityQueue<Integer> minheap, maxheap;
        public MedianFinder() {
            maxheap = new PriorityQueue<Integer>(Collections.reverseOrder());
            minheap = new PriorityQueue<Integer>();
        }

        // Adds a number into the data structure.
        public void addNum(int num) {
            maxheap.add(num);
            minheap.add(maxheap.poll());
            if (maxheap.size() < minheap.size()) {
                maxheap.add(minheap.poll());
            }
        }

        // Returns the median of current data stream
        public double findMedian() {
            if (maxheap.size() == minheap.size()) {
                return (maxheap.peek() + minheap.peek()) * 0.5;
            } else {
                return maxheap.peek();
            }
        }
    }


}
