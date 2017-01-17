package ch9J.chapter2_BinarySearch;

public class _09Sqrtx {
	  /**
	 * @param x: An integer
	 * @return: The sqrt of x
	 */
	public int sqrt(int x) {
	    // find the last number which square of it <= x
	    long start = 1, end = x;
	    while (start + 1 < end) {
	        long mid = start + (end - start) / 2;
	        if (mid * mid <= x) {
	            start = mid;
	        } else {
	            end = mid;
	        }
	    }
	    
	    if (end * end <= x) {
	        return (int) end;
	    }
	    return (int) start;
	}
}

/*
 * Implement int sqrt(int x).

Compute and return the square root of x.

Have you met this question in a real interview? Yes
Example
sqrt(3) = 1

sqrt(4) = 2

sqrt(5) = 2

sqrt(10) = 3

Challenge 
O(log(x))

Tags 
Binary Search Mathematics Facebook
Related Problems 
Medium Pow(x, n) 38 %
Easy First Position of Target 32 %
 * */