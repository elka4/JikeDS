package ch9J.chapter2_BinarySearch;

//First Position of Target

//version 1: with jiuzhang template
public class _02First_Position_of_Target {
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
        //把区间变小
        //只缩小区间，不找目标
        //每次通过O(1)时间的比较 -> n => n / 2
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
        //区间已经缩小到只有2个数
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}

//version 2: without jiuzhang template
class First_Position_of_Target2 {
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