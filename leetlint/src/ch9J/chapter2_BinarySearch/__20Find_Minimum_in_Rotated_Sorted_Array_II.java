package ch9J.chapter2_BinarySearch;

//find Minimum in Rotated Sorted Array II

//version 2: use *fake* binary-search
public class __20Find_Minimum_in_Rotated_Sorted_Array_II {
	 /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == nums[end]) {
                // if mid equals to end, that means it's fine to remove end
                // the smallest element won't be removed
                end--;
            } else if (nums[mid] < nums[end]) {
                end = mid;
                // of course you can merge == & <
            } else {
                start = mid;
                // or start = mid + 1
            }
        }
        
        if (nums[start] <= nums[end]) {
            return nums[start];
        }
        return nums[end];
    }
}

/*
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

 Notice

The array may contain duplicates.

Have you met this question in a real interview? Yes
Example
Given [4,4,5,6,7,0,1,2] return 0.

Tags 
Binary Search Divide and Conquer
Related Problems 
Medium Find Minimum in Rotated Sorted Array 35 %
Medium Search in Rotated Sorted Array II 40 %*/
