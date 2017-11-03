package j_2_BinarySearch.Rotated;

import org.junit.Test;

import java.util.Arrays;


//  Rotate String
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
        System.out.println(Arrays.toString(A));                 //  [1, 2, 3, 4, 5]
	    offset = offset % A.length;
	    reverse(A, 0, A.length - offset - 1);
        System.out.println(Arrays.toString(A));                 //  [4, 3, 2, 1, 5]
        reverse(A, A.length - offset, A.length - 1);
        System.out.println(Arrays.toString(A));                 //  [4, 3, 2, 1, 5]
	    reverse(A, 0, A.length - 1);
        System.out.println(Arrays.toString(A));                 //  [5, 1, 2, 3, 4]
	    return A;

	    //Arrays.toString(charArray)
	}
	
	private void reverse(char[] A, int start, int end) {
	    for (int i = start, j = end; i < j; i++, j--) {
	        char temp = A[i];
	        A[i] = A[j];
	        A[j] = temp;
	    }
	}

	@Test
    public void test(){
	    String str = "12345";
	    char[] c = str.toCharArray();
//	    rotateString(c, 0);
	    rotateString(c, 1);
//	    rotateString(c, 2);
    }
    /*
            [1, 2, 3, 4, 5]
            [4, 3, 2, 1, 5]
            [4, 3, 2, 1, 5]
            [5, 1, 2, 3, 4]
     */
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

/*
给定一个字符串和一个偏移量，根据偏移量旋转字符串(从左向右旋转)

您在真实的面试中是否遇到过这个题？ Yes
样例
对于字符串 "abcdefg".

offset=0 => "abcdefg"
offset=1 => "gabcdef"
offset=2 => "fgabcde"
offset=3 => "efgabcd"
挑战
在数组上原地旋转，使用O(1)的额外空间
 */