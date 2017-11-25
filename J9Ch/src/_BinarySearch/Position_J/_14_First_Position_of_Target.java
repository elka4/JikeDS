package _BinarySearch.Position_J;

//  First Position of Target
//  version 1: with jiuzhang template
//
//
public class _14_First_Position_of_Target {
//------------------------------------------------------------------------------
	 /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0, end = nums.length - 1;


        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
                // or start = mid + 1
            } else {
                end = mid;
                // or end = mid - 1
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
//------------------------------------------------------------------------------

    //version 2: without jiuzhang template
    /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public int binarySearch2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (nums[start] == target) {
            return start;
        }

        return -1;
    }
//------------------------------------------------------------------------------
}



/*
 * For a given sorted array (ascending order) and a target number, 
 * find the first index of this number in O(log n) time complexity.

If the target number does not exist in the array, return -1.

Example: If the array is [1, 2, 3, 3, 4, 5, 10], for given target 3, return 2.

Challenge :
If the count of numbers is bigger than 2^32, can your code work properly?

Tags: Binary Search Array

Related Problems: 
Easy Closest Number in Sorted Array 38 %
Easy Last Position of Target 40 %
Easy Classical Binary Search 47 %
Medium Search in a Big Sorted Array 27 %
Medium Unique Binary Search Trees 33 %
Easy Sqrt(x) 24 %
Medium Search Range in Binary Search Tree 37 %
 * */

/*


给定一个排序的整数数组（升序）和一个要查找的整数target，用O(logn)的时间查找到target第一次出现的下标（从0开始），
如果target不存在于数组中，返回-1。

您在真实的面试中是否遇到过这个题？
样例

在数组 [1, 2, 3, 3, 4, 5, 10] 中二分查找3，返回2。
挑战

如果数组中的整数个数超过了2^32，你的算法是否会出错？

 */