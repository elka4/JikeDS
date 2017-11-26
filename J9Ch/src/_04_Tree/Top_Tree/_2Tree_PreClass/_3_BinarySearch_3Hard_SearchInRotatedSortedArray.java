package _04_Tree.Top_Tree._2Tree_PreClass;

/**
 * Created by tzh on 1/27/17.
 */

public class _3_BinarySearch_3Hard_SearchInRotatedSortedArray {

    public int search(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] == target){
                return mid;
            }
            if (nums[mid] > nums[start]){
                if(target >= nums[start] && target < nums[mid]){
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[mid] < nums[start]){
                if(target > nums[mid] && target <= nums[end]){
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                start++; //Duplicate 就是mid和start相同
            }
        }
        if(nums[start] == target){
            return start;
        }
        if(nums[end] == target){
            return end;
        }
        return -1;
    }

//-------------------------------------------------------------------------------

}
