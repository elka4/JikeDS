package j_2_BinarySearch.Rotated;

import org.junit.Test;

// Find Minimum in Rotated Sorted Array


// leetcode  153. Find Minimum in Rotated Sorted Array
public class _159Find_Minimum_in_Rotated_Sorted_Array {
	 /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;
        int target = nums[nums.length - 1];
        
        // find the first element <= target
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        System.out.println("findMin: " + nums[start] +" ; " + nums[end]);
        if (nums[start] <= target) {
            return nums[start];
        } else {
            return nums[end];
        }
    }

    //set the far right: nums[end] as target, the target will not be changed
    //move end pointer towards min until exiting while loop

    /*if nums[mid] < target, then end = mid;: means as long as mid fall into the right part,
     even target > mid (which is not relevant at all), move the end towards left to mid;
    */

    /* so the purpose of  < + > in the while loop is simply to determine to move start or end
    * to mid;
    * */

    public int findMin2(int[] nums) {
        // write your code here
        int start = 0;
        int end = nums.length - 1;
        int target = nums[end];
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                end = mid;
            } else if (nums[mid] > target) {
                start = mid;
            } else {//nums[mid] < target
                end = mid; //start NOT AC.
            }
        }
        System.out.println("findMin2: " + nums[start] +" ; " + nums[end]);
        return Math.min(nums[start], nums[end]);
    }


    //do not use target, but use nums[end] to compare with mid
    //end is updated during the process, which is actually not necessary
    public int findMin_mine(int[] nums) {
        // write your code here
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]){
                start = mid;
            } else if (nums[mid] < nums[end]){
                end = mid;
            } else {//nums[mid] == nums[end]
                start = mid; //end = mid also AC
            }
        }
        System.out.println("findMin_mine: " + nums[start] +" ; " + nums[end]);
        return nums[start] < nums[end] ? nums[start] : nums[end];
    }

    @Test
    public void test01(){
        int[] input = {5,6,7,9,1,2,3,4};
        System.out.println(findMin(input));

        int[] input2 = {1,2,3,4,5,6,7,9};
        System.out.println(findMin(input2));
    }

    @Test
    public void test02(){
        int[] input = {5,6,7,9,1,2,3,4};
        System.out.println(findMin2(input));

        int[] input2 = {1,2,3,4,5,6,7,9};
        System.out.println(findMin2(input2));
    }

    @Test
    public void test03(){
        int[] input = {5,6,7,9,1,2,3,4};
        System.out.println(findMin_mine(input));

        int[] input2 = {1,2,3,4,5,6,7,9};
        System.out.println(findMin_mine(input2));
    }
}

/*
 * Suppose a sorted array is rotated at some pivot
 *  unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

 Notice

You may assume no duplicate exists in the array.

Have you met this question in a real interview? Yes
Example
Given [4, 5, 6, 7, 0, 1, 2] return 0

Tags 
Binary Search
Related Problems 
Medium Find Minimum in Rotated Sorted Array II 35 %
Medium Search in Rotated Sorted Array II 39 %
 * 
 * */
