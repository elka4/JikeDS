package com.lintcode._2BinarySearch;

/**
 * Created by tzh on 1/16/17.
 */
public class bt_SearchInRotatedArray {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > nums[start]) {

            } else if (nums[mid] < nums[start]) {

            } else {
                start++;
            }
        }
    return -1;
    }
}
