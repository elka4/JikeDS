package _3Graph_PreClass;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by tianhuizhu on 6/25/17.
 */

//minheap, capacity k,碰到比栈顶更大的就弹栈并加入这个更大的元素
//最后栈顶元素就是第k大的
//O((n-k)logk + k ). heapify k, push/poll logk.
//space O(k)
public class _2_FindKthLargest_Heap {
    public int findKthLargest(int[] nums, int k) {
        // if curVal <= heap.peek, skip

        if (nums == null || nums.length < k){
            return Integer.MIN_VALUE;
        }

        //Method: Maintain a min heap with size z
        PriorityQueue<Integer>  minHeap =
                new PriorityQueue<Integer>(k);

        //1.add first k elem
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        //2.add rest elems
        for (int i = k; i < nums.length; i++) {
            // if curVal > heap.peek, poll and add curVal
            if(nums[i] > minHeap.peek()){
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.poll();

    }

    //MyComparator needs to override compare function to give the
    //heap a rule of sorting
    // this is the default comparator
    class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2){
            if(o1.equals(o2)){
                return 0;
            } else {
                return o1 - o2;
            }
        }
    }

    @Test
    public void test01(){
        int[] nums = {2,5,3,5,9,1,8,7,4};
        System.out.println(findKthLargest(nums, 2));
        //8
    }

    @Test
    public void test02(){
        int[] nums = {2,5,3,5,9,1,8,7,4};
        System.out.println(findKthLargest(nums, 4));
        //5
    }

    @Test
    public void test03(){
        int[] nums = {2,5,3,5,9,1,8,7,4,4,4,8,9};
        System.out.println(findKthLargest(nums, 4));
        //8
    }

    @Test
    public void test04(){
        PriorityQueue<Integer>  minHeap =
                new PriorityQueue<Integer>(4);

        minHeap.offer(5);
        minHeap.offer(2);
        minHeap.offer(3);
        minHeap.offer(4);
        minHeap.offer(1);
        System.out.println(minHeap);
        //8
    }


}
