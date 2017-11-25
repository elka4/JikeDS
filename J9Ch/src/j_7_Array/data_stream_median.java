package j_7_Array;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * Created by tianhuizhu on 6/20/17.
 */
public class data_stream_median {
    /**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    private PriorityQueue<Integer> maxHeap, minHeap;
    private int numOfElements = 0;

    public int[] medianII(int[] nums) {
        // write your code here
        Comparator<Integer> revCmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer left, Integer right) {

                return right.compareTo(left);
            }
        };

        int cnt = nums.length;
        maxHeap = new PriorityQueue<Integer>(cnt, revCmp);
        minHeap = new PriorityQueue<Integer>(cnt);

        int[] ans = new int[cnt];
        for (int i = 0; i < cnt; ++i) {
            addNumber(nums[i]);
            ans[i] = getMedian();
        }
        return ans;
    }

    void addNumber(int value) {
        maxHeap.add(value);
        if (numOfElements%2 == 0) {
            if (minHeap.isEmpty()) {
                numOfElements++;
                return;
            }
            else if (maxHeap.peek() > minHeap.peek()) {
                Integer maxHeapRoot = maxHeap.poll();
                Integer minHeapRoot = minHeap.poll();
                maxHeap.add(minHeapRoot);
                minHeap.add(maxHeapRoot);
            }
        }
        else {
            minHeap.add(maxHeap.poll());
        }
        numOfElements++;
    }
    int getMedian() {
        return maxHeap.peek();
    }

    @Test
    public void test01(){
        int[] nums = {1, 2, 3, 4, 5};
        int[] result = medianII(nums);
        for (int i : result ) {
            System.out.print(i + " ");
        }
    }

//------------------------------------------------------------------------------

    public int[] medianII2(int[] nums) {
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
    public void test02(){
        int[] nums = {1, 2, 3, 4, 5};
        int[] result = medianII2(nums);
        for (int i : result ) {
            System.out.print(i + " ");
        }
    }

    /*
    Numbers keep coming, return the median of numbers at every time
     a new number added.

    Clarification
    What's the definition of Median?
    - Median is the number that in the middle of a sorted array.
    If there are n numbers in a sorted array A, the median is A[(n - 1) / 2].
     For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.

    Example
    For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].

    For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].

    For numbers coming list: [2, 20, 100], return [2, 2, 20].
     */

}
