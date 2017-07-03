package ch9J.chapter2_BinarySearch;

//Find Peak Element

public class _75Find_Peak_Element {
	/**
	 * @param A: An integers array.
	 * @return: return any of peek positions.
	 */
	public int findPeak(int[] A) {
	    // write your code here
	    int start = 1, end = A.length-2; // 1.答案在之间，2.不会出界 
	    while(start + 1 <  end) {
	        int mid = (start + end) / 2;
	        if(A[mid] < A[mid - 1]) {
	            end = mid;
	        } else if(A[mid] < A[mid + 1]) {
	            start = mid;
	        } else {
	            end = mid;//这里用end或者start都能在lintcode AC
	        }
	    }
	    //这里先检查start或者先检查end都能AC
	    if(A[start] < A[end]) {
	        return end;
	    } else { 
	        return start;
	    }
	}

	public int findPeak_mine1(int[] A) {
		// write your code here

		int start = 0;
		int end = A.length - 2;

		while (start + 1 < end){
			int mid = start + (end - start) / 2;
			if (A[mid- 1] < A[mid]){
				start = mid;
			} else if (A[mid] > A[mid + 1]){
				end = mid;
			} else {
				start = mid; //end = mid also works
			}		}

		return A[start] > A[end] ? start : end;
	}


}

/*
 * There is an integer array which has the following features:

The numbers in adjacent positions are different.
A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
We define a position P is a peek if:

A[P] > A[P-1] && A[P] > A[P+1]
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
Binary Search LintCode Copyright Array Google
Related Problems 
 * 
 * */
