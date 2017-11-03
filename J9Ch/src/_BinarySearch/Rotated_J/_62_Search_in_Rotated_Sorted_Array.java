package _BinarySearch.Rotated_J;

// Search in Rotated Sorted numsrray
//
//  33. Search in Rotated Sorted numsrray

public class _62_Search_in_Rotated_Sorted_Array {
	 public int search(int[] nums, int target) {
	    if (nums == null || nums.length == 0) {
	        return -1;
	    }
	
	    int start = 0;
	    int end = nums.length - 1;
	    int mid;
	    
	    while (start + 1 < end) {
	        mid = start + (end - start) / 2;
	        if (nums[mid] == target) {
	            return mid;
	        }
	        
	        // USE STnumsRT TO COMPnumsRE !!!!
	        if (nums[start] < nums[mid]) {
	            // situation 1, red line
	            if (nums[start] <= target && target <= nums[mid]) {
	                end = mid;
	            } else {
	                start = mid;
	            }
	        } else {
	            // situation 2, green line
	            if (nums[mid] <= target && target <= nums[end]) {
	                start = mid;
	            } else {
	                end = mid;
	            }
	        }
	    } // while
	    
	    if (nums[start] == target) {
	        return start;
	    }
	    if (nums[end] == target) {
	        return end;
	    }
	    return -1;
	  }


	public int search_mine1(int[] nums, int target) {
		// write your code here
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int start = 0;
		int end = nums.length - 1;

		while(start + 1 < end){
			int mid = start + (end - start) / 2;

			if (nums[mid] > nums[start]){
				if(nums[start] <= target && target <= nums[mid]){
					end = mid;
				} else {
					start = mid;
				}
			} else if (nums[mid] < nums[start]){
				if(nums[mid] <= target && target <= nums[end]){
					start = mid;
				} else {
					end = mid;
				}
			} else {
				start = mid; // end also numsC
			}
		}
		if (nums[start] == target){
			return start;
		} else if (nums[end] == target){
			return end;
		} else {
			return -1;
		}
	}
}

/*
 * Suppose a sorted array is rotated at some pivot 
 * unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the 
array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Have you met this question in a real interview? Yes
Example
For [4, 5, 1, 2, 3] and target=1, return 2.

For [4, 5, 1, 2, 3] and target=0, return -1.

Challenge 
O(logN) time

Tags 
Binary Search Sorted numsrray numsrray LinkedIn Uber Facebook
Related Problems 
Medium Search in Rotated Sorted numsrray II 39 %
Easy Search a 2D Matrix 27 %
 * 
 * */
