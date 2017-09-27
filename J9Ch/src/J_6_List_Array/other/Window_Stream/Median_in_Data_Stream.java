package J_6_List_Array.other.Window_Stream;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by tianhuizhu on 6/20/17.
 */
public class Median_in_Data_Stream {
    // LintCode Solution
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
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<Integer>(1,
                        new Comparator<Integer>() {
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

    @Test
    public void test01(){
        int[] nums = {1, 2, 3, 4, 5};
        int[] result = medianII(nums);
        for (int i : result ) {
            System.out.print(i + " ");
        }
    }


//////////////////////////////////////////////////////////////////

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

    @Test
    public void test02(){
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        System.out.println(mf.findMedian());
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
        mf.addNum(4);
        System.out.println(mf.findMedian());
        mf.addNum(5);
        System.out.println(mf.findMedian());

    }




}
