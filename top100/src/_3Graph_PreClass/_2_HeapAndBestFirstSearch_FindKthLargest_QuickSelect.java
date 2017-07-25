package _3Graph_PreClass;

import org.junit.Test;

import java.util.List;

// 如果用quick sort的话。O(k + nlogn) : sort is nlogn
//这里用的是quick select O (n), space: recursion stack
public class _2_HeapAndBestFirstSearch_FindKthLargest_QuickSelect {


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


        //Only one recursion call for next level
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

    private void swap2(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
    @Test
    public void test04(){
        int[] nums = {2,5,3,5,9,1,8,7,4,4,4,8,9};
        long start = System.currentTimeMillis();

        for (int j = 0; j < 10000000; j++) {
            for (int i = 0; i < nums.length; i++){
                swap2(nums, i, nums.length - i -1);
            }
        }


        long end = System.currentTimeMillis();
        long time = end - start;

        System.out.println(  ": " + time + "ms");


         start = System.currentTimeMillis();

        for (int j = 0; j < 10000000; j++) {
            for (int i = 0; i < nums.length; i++){
                swap(nums, i, nums.length - i -1);
            }
        }


         end = System.currentTimeMillis();
         time = end - start;

        System.out.println(  ": " + time + "ms");

    }

    @Test
    public void test05(){
        int[] nums = {2,5,3,5,9,1,8,7,4,4,4,8,9};
        long start = System.currentTimeMillis();

        for (int j = 0; j < 10000000; j++) {
            for (int i = 0; i < nums.length; i++){
                swap(nums, i, nums.length - i -1);
            }
        }


        long end = System.currentTimeMillis();
        long time = end - start;

        System.out.println(  ": " + time + "ms");


        start = System.currentTimeMillis();

        for (int j = 0; j < 10000000; j++) {
            for (int i = 0; i < nums.length; i++){
                swap2(nums, i, nums.length - i -1);
            }
        }


        end = System.currentTimeMillis();
        time = end - start;

        System.out.println(  ": " + time + "ms");

    }

}
