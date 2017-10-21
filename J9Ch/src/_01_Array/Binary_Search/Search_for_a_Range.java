package _01_Array.Binary_Search;

public class Search_for_a_Range {
/*
    Given a sorted array of integers, find the starting and ending position of a given target value. Your algorithm's runtime complexity must be in the order of O(log n). If the target is not found in the array, return [-1, -1]. For example, given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].

    Analysis

    Based on the requirement of O(log n), this is a binary search problem apparently.

    Java Solution
*/

    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return null;
        }

        int[] arr= new int[2];
        arr[0]=-1;
        arr[1]=-1;

        binarySearch(nums, 0, nums.length-1, target, arr);

        return arr;
    }

    public void binarySearch(int[] nums, int left, int right, int target, int[] arr){
        if(right<left)
            return;

        if(nums[left]==nums[right] && nums[left]==target){
            arr[0]=left;
            arr[1]=right;
            return;
        }

        int mid = left+(right-left)/2;


        if(nums[mid]<target){
            binarySearch(nums, mid+1, right, target, arr);
        }else if(nums[mid]>target){
            binarySearch(nums, left, mid-1, target, arr);
        }else{
            arr[0]=mid;
            arr[1]=mid;

            //handle duplicates - left
            int t1 = mid;
            while(t1 >left && nums[t1]==nums[t1-1]){
                t1--;
                arr[0]=t1;
            }

            //handle duplicates - right
            int t2 = mid;
            while(t2 < right&& nums[t2]==nums[t2+1]){
                t2++;
                arr[1]=t2;
            }
            return;
        }
    }
}
