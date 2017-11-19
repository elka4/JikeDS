package _10_DS._Heap;
import java.util.*;
import org.junit.Test;
public class _215_Heap_Kth_Largest_Element_in_an_Array_M {

/*
Solution explained
This problem is well known and quite often can be found in various text books.

You can take a couple of approaches to actually solve it:

O(N lg N) running time + O(1) memory
The simplest approach is to sort the entire input array and then access the element by it's index (which is O(1)) operation:
 */
public int findKthLargest1(int[] nums, int k) {
    final int N = nums.length;
    Arrays.sort(nums);
    return nums[N - k];
}

/*
O(N lg K) running time + O(K) memory
Other possibility is to use a min oriented priority queue that will store the K-th largest values. The algorithm iterates over the whole input and maintains the size of priority queue.
 */
public int findKthLargest2(int[] nums, int k) {

    final PriorityQueue<Integer> pq = new PriorityQueue<>();
    for(int val : nums) {
        pq.offer(val);

        if(pq.size() > k) {
            pq.poll();
        }
    }
    return pq.peek();
}

/*
O(N) best case / O(N^2) worst case running time + O(1) memory
The smart approach for this problem is to use the selection algorithm (based on the partion method - the same one as used in quicksort).
 */
public int findKthLargest3(int[] nums, int k) {

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

/*    O(N) guaranteed running time + O(1) space

    So how can we improve the above solution and make it O(N) guaranteed? The answer is quite simple, we can randomize the input, so that even when the worst case input would be provided the algorithm wouldn't be affected. So all what it is needed to be done is to shuffle the input.*/

    public int findKthLargest4(int[] nums, int k) {

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


//-------------------------------------------------------------------------////////////////////
//AC Clean QuickSelect Java solution avg. O(n) time
public class Solution {

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
//-------------------------------------------------------------------------////////////////////
/*
Concise JAVA solution based on Quick Select
The basic idea is to use Quick Select algorithm to partition the array with pivot:

Put numbers < pivot to pivot's left
Put numbers > pivot to pivot's right
Then

if indexOfPivot == k, return A[k]
else if indexOfPivot < k, keep checking left part to pivot
else if indexOfPivot > k, keep checking right part to pivot
Time complexity = O(n)

Discard half each time: n+(n/2)+(n/4)..1 = n + (n-1) = O(2n-1) = O(n), because n/2+n/4+n/8+..1=n-1.

Quick Select Solution Code:


 */
class Solution3{
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return Integer.MAX_VALUE;
        return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
    }

    public int findKthLargest(int[] nums, int start, int end, int k) {// quick select: kth smallest
        if (start > end) return Integer.MAX_VALUE;

        int pivot = nums[end];// Take A[end] as the pivot,
        int left = start;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) // Put numbers < pivot to pivot's left
                swap(nums, left++, i);
        }
        swap(nums, left, end);// Finally, swap A[end] with A[left]

        if (left == k)// Found kth smallest number
            return nums[left];
        else if (left < k)// Check right part
            return findKthLargest(nums, left + 1, end, k);
        else // Check left part
            return findKthLargest(nums, start, left - 1, k);
    }

    void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
//-------------------------------------------------------------------------////////////////////


}
/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.


 */