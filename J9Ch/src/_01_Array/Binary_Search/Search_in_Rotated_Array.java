package _01_Array.Binary_Search;

/*
LeetCode – Search in Rotated Sorted Array (Java)

Suppose a sorted array is rotated at some pivot unknown to you beforehand. (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1. You may assume no duplicate exists in the array.
 */


public class Search_in_Rotated_Array {
    //Java Solution 1- Recusive

    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length-1, target);
    }

    public int binarySearch(int[] nums, int left, int right, int target){
        if(left>right)
            return -1;

        int mid = left + (right-left)/2;

        if(target == nums[mid])
            return mid;

        if(nums[left] <= nums[mid]){
            if(nums[left]<=target && target<nums[mid]){
                return binarySearch(nums,left, mid-1, target);
            }else{
                return  binarySearch(nums, mid+1, right, target);
            }
        }else {
            if(nums[mid]<target&& target<=nums[right]){
                return  binarySearch(nums,mid+1, right, target);
            }else{
                return  binarySearch(nums, left, mid-1, target);
            }
        }
    }



    //Java Solution 2 - Iterative

    public int search2(int[] nums, int target) {
        int left = 0;
        int right= nums.length-1;

        while(left<=right){
            int mid = left + (right-left)/2;
            if(target==nums[mid])
                return mid;

            if(nums[left]<=nums[mid]){
                if(nums[left]<=target&& target<nums[mid]){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }else{
                if(nums[mid]<target&& target<=nums[right]){
                    left=mid+1;
                }else{
                    right=mid-1;
                }
            }
        }

        return -1;
    }

}
