package J_2_BinarySearch;

public class _16Rotate_String {
	  /*
	 * param A: A string
	 * param offset: Rotate string with offset.
	 * return: Rotated string.
	 */
	public char[] rotateString(char[] A, int offset) {
	    if (A == null || A.length == 0) {
	        return A;
	    }
	    
	    offset = offset % A.length;
	    reverse(A, 0, A.length - offset - 1);
	    reverse(A, A.length - offset, A.length - 1);
	    reverse(A, 0, A.length - 1);
	    return A;
	}
	
	private void reverse(char[] A, int start, int end) {
	    for (int i = start, j = end; i < j; i++, j--) {
	        char temp = A[i];
	        A[i] = A[j];
	        A[j] = temp;
	    }
	}
}

/*
 * Given a string and an offset, rotate string by offset. 
 * (rotate from left to right)

Have you met this question in a real interview? Yes
Example
Given "abcdefg".

offset=0 => "abcdefg"
offset=1 => "gabcdef"
offset=2 => "fgabcde"
offset=3 => "efgabcd"
Challenge 
Rotate in-place with O(1) extra memory.

Tags 
String
Related Problems 
Medium Rotate List 25 %
Medium Rotate Image 34 %
Easy Recover Rotated Sorted Array 26 %
 * 
 * */
