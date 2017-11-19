package _TwoPointer.TwoPointer_J;
import java.util.*;
import org.junit.Test;

/** 461 Kth Smallest Numbers in Unsorted Array
 * Created by tianhuizhu on 6/28/17.
 */
public class _461_Kth_Smallest_Numbers_in_Unsorted_Array {

    //    Sort Array
    public class Solution1 {
        public int findKthLargest(int[] nums, int k) {
            final int N = nums.length;
            Arrays.sort(nums);
            return nums[N - k];
        }
    }

    //    Max Heap
    class Solution2 {
        /*
         * @param k : description of k
         * @param nums : array of nums
         * @return: description of return
         */
        public int kthLargestElement(int k, int[] nums) {
            if (nums == null || nums.length == 0 || k == 0) {
                return -1;
            }
            PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            for (int i = 0; i < nums.length; i++) {
                heap.offer(nums[i]);
            }
            for (int j = 0; j < k - 1; j++) {
                heap.poll();
            }
            return heap.peek();
        }
    }


    //    Quick Select (Partition with two pointers)
    class Solution3 {
        /*
         * @param k : description of k
         * @param nums : array of nums
         * @return: description of return
         */
        public int kthLargestElement(int k, int[] nums) {
            if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
                return 0;
            }

            return select(nums, 0, nums.length - 1, nums.length - k);

        }

        public int select(int[] nums, int left, int right, int k) {
            if (left == right) {
                return nums[left];
            }

            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == k) {
                return nums[pivotIndex];
            } else if (pivotIndex < k) {
                return select(nums, pivotIndex + 1, right, k);
            }  else {
                return select(nums, left, pivotIndex - 1, k);
            }
        }

        public int partition(int[] nums, int left, int right) {

            // Init pivot, better to be random
            int pivot = nums[left];

            // Begin partition
            while (left < right) {
                while (left < right && nums[right] >= pivot) { // skip nums[i] that equals pivot
                    right--;
                }
                nums[left] = nums[right];
                while (left < right && nums[left] <= pivot) { // skip nums[i] that equals pivot
                    left++;
                }
                nums[right] = nums[left];
            }

            // Recover pivot to array
            nums[left] = pivot;
            return left;
        }
    }

    @Test
    public void test(){
        System.out.println("kth Largest Element: Quick Select");
        int[] A = {21, 3, 34, 5, 13, 8, 2, 55, 1, 19};
        Solution3 search = new Solution3();
        int expResult[] = {1, 2, 3, 5, 8, 13, 19, 21, 34, 55};
        int k = expResult.length;
        int err = 0;
        for (int exp : expResult) {
            if (exp != search.kthLargestElement(k--, A)) {
                System.out.println("Test failed: " + k);
                err++;
            }
        }
        System.out.println("Test finished");
    }
//-------------------------------------------------------------------------////////////

    //  https://discuss.leetcode.com/topic/14597/solution-explained

//-------------------------------------------------------------------------////////////

/*    Quick Select (with random pivot)

    Source: wikipedia: QuickSelect
    Animation:
    geekviewpoint: quickselect
    csanimated: Quicksort*/

    class Solution4 {
        /*
         * @param k : description of k
         * @param nums : array of nums
         * @return: description of return
         */
        public int kthLargestElement(int k, int[] nums) {
            if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
                return 0;
            }

            return select(nums, 0, nums.length - 1, nums.length - k);

        }

        public int select(int[] nums, int left, int right, int k) {
            if (left == right) {
                return nums[left];
            }

            int pivotIndex = partition(nums, left, right);
            if (pivotIndex == k) {
                return nums[pivotIndex];
            } else if (pivotIndex < k) {
                return select(nums, pivotIndex + 1, right, k);
            } else {
                return select(nums, left, pivotIndex - 1, k);
            }
        }

        public void swap(int[] nums, int x, int y) {
            int tmp = nums[x];
            nums[x] = nums[y];
            nums[y] = tmp;
        }

        public int partition(int[] nums, int left, int right) {

            Random rand = new Random();
            int pivotIndex = rand.nextInt((right - left) + 1) + left;
            // Init pivot
            int pivotValue = nums[pivotIndex];

            swap(nums, pivotIndex, right);

            // First index that nums[firstIndex] > pivotValue
            int firstIndex = left;

            for (int i = left; i <= right - 1; i++) {
                if (nums[i] < pivotValue) {
                    swap(nums, firstIndex, i);
                    firstIndex++;
                }
            }

            // Recover pivot to array
            swap(nums, right, firstIndex);
            return firstIndex;
        }
    }
//-------------------------------------------------------------------------////////////
//-------------------------------------------------------------------------////////////
    /*
     * @param k an integer
     * @param nums an integer array
     * @return kth smallest element
     */
    public int kthSmallest(int[] nums, int k) {
        // write your code here
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    public int quickSelect(int[] A, int start, int end , int k) {

        if (start == end)
            return A[start];

        int left = start, right = end;
        int pivot = A[(start + end) / 2];

        while (left <= right) {
            while (left <= right && A[left] < pivot) {
                left++;
            }

            while (left <= right && A[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;

                left++;
                right--;
            }
        }

        if (right >= k && start <= right)
            return quickSelect(A, start, right, k);
        else if (left <= k && left <= end)
            return quickSelect(A, left, end, k);
        else
            return A[k];
    }

    @Test
    public void test01(){
        int arr[] = {7, 10, 4, 3, 20, 15};
        int target = 3;
        System.out.println(kthSmallest(arr, target));
    }
    @Test
    public void test02(){
        int arr[] = {7, 10, 4, 3, 20, 15};
        int target = 4;
        System.out.println(kthSmallest(arr, target));
    }

//-------------------------------------------------------------------------//

}
/*
Given an array and a number k where k is smaller than size of array, we need to find the k’th smallest element in the given array. It is given that ll array elements are distinct.

Examples:

Input: arr[] = {7, 10, 4, 3, 20, 15}
       k = 3
Output: 7

Input: arr[] = {7, 10, 4, 3, 20, 15}
       k = 4
Output: 10
 */