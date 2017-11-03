package j_2_BinarySearch.Others_J;

//Classic Binary Search

//version 1: with jiuzhang template
public class _457Classical_Binary_Search {
	 /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public int findPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        //A   start < end
        //B   start  + 1 < end
        //C   start <= end
        //D   start + 1 <= end
        //
        int start = 0, end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;//经典二分法，既缩小区间，又找到目标
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
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


    //version 2: without jiuzhang template
    /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public int findPosition2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
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
Find any position of a target number in a sorted array. 
Return -1 if target does not exist.

Have you met this question in a real interview? Yes
Example: Given [1, 2, 2, 4, 5, 5].

For target = 2, return 1 or 2.

For target = 5, return 4 or 5.

For target = 6, return -1.

Challenge: O(logn) time

Tags: Binary Search

Related Problems: 
Easy Closest Number in Sorted Array 38 %
Easy Last Position of Target 40 %
Easy First Position of Target 32 %
*/
