package _3Graph_PreClass;

import org.junit.Test;

public class _2_HeapAndBestFirstSearch_FindKthLargest {


    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length < k)
            return Integer.MIN_VALUE;
        //Change to 0 based kth smallest
        return findKthLargest(nums, 0,
                nums.length - 1, nums.length - k);
    }

    // quick select: kth smallest
    private int findKthLargest(int[] nums, int start, int end, int k) {
        if (start > end) {
            return Integer.MIN_VALUE;
        }

        int pivot = nums[end];// Take nums[end] as the pivot
        int pos = start;

        for (int i = start; i < end; i++) {
            //if curVal <= pivot, pos++, i++, swap(pos, i)
            if (nums[i] <= pivot) {
                swap(nums, pos++, i);
            }
        }
        // set pivot to the partition position
        swap(nums, pos, end);

        if (pos == k) {// Found
            return nums[pos];
        } else {
            return pos < k ? findKthLargest(nums, pos + 1, end, k)
                    : findKthLargest(nums, start, pos - 1, k);
        }
    }
    private void swap(int[] arr, int i, int j) {
            if (arr[i] == arr[j]) {
                return;
            } else {
                arr[i] ^= arr[j];
                arr[j] ^= arr[i];
                arr[i] ^= arr[j];
            }
    }

    @Test
    public void test01(){
        int[] nums = {2,5,3,5,9,1,8,7,4};
        System.out.println(findKthLargest(nums, 2));
    }

    @Test
    public void test02(){
        int[] nums = {2,5,3,5,9,1,8,7,4};
        System.out.println(findKthLargest(nums, 4));
    }

    @Test
    public void test03(){
        int[] nums = {2,5,3,5,9,1,8,7,4,4,4,8,9};
        System.out.println(findKthLargest(nums, 4));
    }

}
