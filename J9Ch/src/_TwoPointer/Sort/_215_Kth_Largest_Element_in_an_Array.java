package _TwoPointer.Sort;
import java.util.*;

//  215. Kth Largest Element in an Array
//  https://leetcode.com/problems/kth-largest-element-in-an-array/description/
//  4:
//  Divide and Conquer, Heap
//  Wiggle Sort II
//  Top K Frequent Elements
//  Third Maximum Number
public class _215_Kth_Largest_Element_in_an_Array {
//-------------------------------------------------------------------------
    //1
//  https://discuss.leetcode.com/topic/14597/solution-explained
/*This problem is well known and quite often can be found in various text books.

    You can take a couple of approaches to actually solve it:

    O(N lg N) running time + O(1) memory
    The simplest approach is to sort the entire input array and then access the element by it's index (which is O(1)) operation:

    public int findKthLargest(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
    }
    O(N lg K) running time + O(K) memory
    Other possibility is to use a min oriented priority queue that will store the K-th largest values. The algorithm iterates over the whole input and maintains the size of priority queue.*/

    public int findKthLargest1(int[] nums, int k) {

        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val : nums) {
            pq.offer(val);

            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }
//-------------------------------------------------------------------------
    //2
/*    O(N) best case / O(N^2) worst case running time + O(1) memory
    The smart approach for this problem is to use the selection algorithm (based on the partion method - the same one as used in quicksort).*/

    public int findKthLargest2(int[] nums, int k) {

        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private int partition(int[] a, int lo, int hi) {

        int i = lo;
        int j = hi + 1;
        while(true) {
            while(i < hi && less(a[++i], a[lo]));
            while(j > lo && less(a[lo], a[--j]));
            if(i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void exch(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }
//-------------------------------------------------------------------------
    //3
   /* O(N) guaranteed running time + O(1) space

    So how can we improve the above solution and make it O(N) guaranteed? The answer is quite simple, we can randomize the input, so that even when the worst case input would be provided the algorithm wouldn't be affected. So all what it is needed to be done is to shuffle the input.*/

    public int findKthLargest3(int[] nums, int k) {

        shuffle(nums);
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private void shuffle(int a[]) {

        final Random random = new Random();
        for(int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            exch(a, ind, r);
        }
    }
//    There is also worth mentioning the Blum-Floyd-Pratt-Rivest-Tarjan algorithm that has a guaranteed O(N) running time.



//-------------------------------------------------------------------------
    //4
//AC Clean QuickSelect Java solution avg. O(n) time
//    https://en.wikipedia.org/wiki/Quickselect

    public class Solution4 {

        public int findKthLargest(int[] a, int k) {
            int n = a.length;
            int p = quickSelect(a, 0, n - 1, n - k + 1);
            return a[p];
        }

        // return the index of the kth smallest number
        int quickSelect(int[] a, int lo, int hi, int k) {
            // use quick sort's idea
            // put nums that are <= pivot to the left
            // put nums that are  > pivot to the right
            int i = lo, j = hi, pivot = a[hi];
            while (i < j) {
                if (a[i++] > pivot) swap(a, --i, --j);
            }
            swap(a, i, hi);

            // count the nums that are <= pivot from lo
            int m = i - lo + 1;

            // pivot is the one!
            if (m == k)     return i;
                // pivot is too big, so it must be on the left
            else if (m > k) return quickSelect(a, lo, i - 1, k);
                // pivot is too small, so it must be on the right
            else            return quickSelect(a, i + 1, hi, k - m);
        }

        void swap(int[] a, int i, int j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

//-------------------------------------------------------------------------
}
/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
