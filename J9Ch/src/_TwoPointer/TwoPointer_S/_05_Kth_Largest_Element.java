package _TwoPointer.TwoPointer_S;
import java.util.*;

//215. Kth Largest Element in an Array
//  https://leetcode.com/problems/kth-largest-element-in-an-array/description/
//  http://www.lintcode.com/zh-cn/problem/kth-largest-element/
public class _05_Kth_Largest_Element {
    //  https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/
/*    Solution explained
    This problem is well known and quite often can be found in various text books.

    You can take a couple of approaches to actually solve it:

    O(N lg N) running time + O(1) memory
    The simplest approach is to sort the entire input array and then access the element by it's index (which is O(1)) operation:*/

    public int findKthLargest1(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
    }
/*    O(N lg K) running time + O(K) memory
    Other possibility is to use a min oriented priority queue that will store the K-th largest values. The algorithm iterates over the whole input and maintains the size of priority queue.*/

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
/*    O(N) best case / O(N^2) worst case running time + O(1) memory
    The smart approach for this problem is to use the selection algorithm (based on the partion method - the same one as used in quicksort).*/

    public int findKthLargest3(int[] nums, int k) {

        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition3(nums, lo, hi);
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

    private int partition3(int[] a, int lo, int hi) {

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
//    There is also worth mentioning the Blum-Floyd-Pratt-Rivest-Tarjan algorithm that has a guaranteed O(N) running time.
//-------------------------------------------------------------------------//////////////////
	/*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
    public int kthLargestElement01(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (k <= 0) {
            return 0;
        }
        return helper(nums, 0, nums.length - 1, nums.length - k + 1);
        
    }
    public int helper(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }
        int position = partition(nums, l, r);
        if (position + 1 == k) {
            return nums[position];
        } else if (position + 1 < k) {
            return helper(nums, position + 1, r, k);
        }  else {
            return helper(nums, l, position - 1, k);
        }
    }
    public int partition(int[] nums, int l, int r) {
        // 初始化左右指针和pivot
        int left = l, right = r;
        int pivot = nums[left];
        
        // 进行partition
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        
        // 返还pivot点到数组里面
        nums[left] = pivot;
        return left;         
    }

    //
    public int kthLargestElement02(int k, int[] nums) {

        // write your code here
        int low = 0, high = nums.length -1;
        while(low <= high){
            int pivot = nums[high];
            int index = low-1;
            for(int i = low; i < high; i++){
                if(nums[i] > nums[high]){
                    swap(nums, i, ++index);
                }
            }
            swap(nums, ++index, high);
            if(index == k - 1){
                return nums[index];
            }
            if(index < k -1){
                low = index + 1;
            }else{
                high = index - 1;
            }
        }
        return -1;
    }

    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
//-------------------------------------------------------------------------//////////////////
}
/*
第k大元素

 描述
 笔记
 数据
 评测
在数组中找到第k大的元素

 注意事项

你可以交换数组中的元素的位置

样例
给出数组 [9,3,2,4,8]，第三大的元素是 4

给出数组 [1,2,3,4,5]，第一大的元素是 5，第二大的元素是 4，第三大的元素是 3，以此类推

挑战
要求时间复杂度为O(n)，空间复杂度为O(1)

标签
排序 快速排序
 */

/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

