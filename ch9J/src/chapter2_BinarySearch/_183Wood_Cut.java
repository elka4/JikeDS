package chapter2_BinarySearch;

//Wood Cut

public class _183Wood_Cut {
	/** 
	 *@param L: Given n pieces of wood with length L[i]
	 *@param k: An integer
	 *return: The maximum length of the small pieces.
	 */
	public int woodCut(int[] L, int k) {
	    int max = 0;
	    for (int i = 0; i < L.length; i++) {
	        max = Math.max(max, L[i]);
	    }
	    
// find the largest length that can cut more than k pieces of wood.
	    //Start should be 1!!!!!! NOT 0!!!!
	    int start = 1, end = max;
	    while (start + 1 < end) {
	        int mid = start + (end - start) / 2;
	        if (count(L, mid) >= k) {
	            start = mid;
	        } else {
	            end = mid;
	        }
	    }
	    
	    if (count(L, end) >= k) {
	        return end;
	    }
	    if (count(L, start) >= k) {
	        return start;
	    }
	    return 0;
	}
	
	private int count(int[] L, int length) {
	    int sum = 0;
	    for (int i = 0; i < L.length; i++) {
	        sum += L[i] / length;
	    }
	    return sum;
	}
}

/*
 * Given n pieces of wood with length L[i] (integer array). 
 * Cut them into small pieces to guarantee you could have equal or more than 
 * k pieces with the same length. What is the longest length you can get from
  the n pieces of wood? Given L & k, return the maximum length of the small pieces.

 Notice

You couldn't cut wood into float length.

Have you met this question in a real interview? Yes
Example
For L=[232, 124, 456], k=7, return 114.

Challenge 
O(n log Len), where Len is the longest length of the wood.

Tags 
Binary Search
 * */
