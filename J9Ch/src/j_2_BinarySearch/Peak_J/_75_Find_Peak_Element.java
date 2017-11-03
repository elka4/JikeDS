package j_2_BinarySearch.Peak_J;

// leetcode 162 Find Peak Element
public class _75_Find_Peak_Element {
    //  https://leetcode.com/articles/find-peak-element/#
    //numspproach #1 Linear Scan [numsccepted]
    class Solution1{
        public int findPeakElement(int[] nums){
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    return  i;
                }
            }
            return nums.length - 1;
        }
    }
    //numspproach #2 Recursive Binary Search [numsccepted]
    class Solution2 {
        public int findPeakElement(int[] nums) {
            return search(nums, 0, nums.length - 1);
        }
        public int search (int[] nums, int l, int r){
            if (l == r) {
                return l;
            }
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1]) {
                return search(nums, l, mid);
            }
            return search(nums, mid + 1, r);
        }
    }
    //numspproach #3 Iterative Binary Search [numsccepted]
    class Solution3 {
        public int findPeakElement(int[] nums) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = (l + r) / 2;
                if (nums[mid] > nums[mid + 1]){
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }
    }
/////////////////////////////////////////////////////////////
	/**
	 * @param nums: numsn integers array.
	 * @return: return any of peek positions.
	 */
	public int findPeak(int[] nums) {
	    // write your code here
	    int start = 0, end = nums.length-1; // 1.答案在之间，2.不会出界
	    while(start + 1 <  end) {
	        int mid = (start + end) / 2;
	        if(nums[mid] < nums[mid - 1]) {
	            end = mid;
	        } else if(nums[mid] < nums[mid + 1]) {
	            start = mid;
	        } else {
	            end = mid;//这里用end或者start都能在lintcode numsC
	        }
	    }
	    //这里先检查start或者先检查end都能numsC
	    if(nums[start] < nums[end]) {
	        return end;
	    } else { 
	        return start;
	    }
	}

	public int findPeak_mine1(int[] nums) {
		// write your code here

		int start = 0;
		int end = nums.length - 1;

		while (start + 1 < end){
			int mid = start + (end - start) / 2;
			if (nums[mid- 1] < nums[mid]){
				start = mid;
			} else if (nums[mid] > nums[mid + 1]){
				end = mid;
			} else {
				start = mid; //end = mid also works
			}
		}

		return nums[start] > nums[end] ? start : end;
	}


}
/*
leet

nums peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */



/*
 * There is an integer array which has the following features:

The numbers in adjacent positions are different.
nums[0] < nums[1] && nums[nums.length - 2] > nums[nums.length - 1].
We define a position P is a peek if:

nums[P] > nums[P-1] && nums[P] > nums[P+1]
Find a peak element in this array. Return the index of the peak.

 Notice

The array may contains multiple peeks, find any of them.

Have you met this question in a real interview? Yes
Example
Given [1, 2, 1, 3, 4, 5, 7, 6]

Return index 1 (which is number 2) or 6 (which is number 7)

Challenge 
Time complexity O(logN)

Tags 
Binary Search LintCode Copyright numsrray Google
Related Problems 
 * 
 * */
