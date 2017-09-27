package j_7_Array;

public class _23Sort_Colors {
	public void sortColors(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        
        int pl = 0;//have only 0 on its left
        int pr = a.length - 1;//have only 2 on its right
        int i = 0;//have both 0 and 1 on its left
        while (i <= pr) {
            if (a[i] == 0) {
                swap(a, pl, i);
                pl++;
                i++;
            } else if(a[i] == 1) {
                i++;
            } else {
                swap(a, pr, i);
                pr--;
            }
        }
    }
    
    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

/*Given an array with n objects colored red, white or blue, 
 * sort them so that objects of the same color are adjacent,
 *  with the colors in the order red, white and blue.
 

Here, we will use the integers 0, 1, and 2 
to represent the color red, white, and blue respectively.

 Notice

You are not suppose to use the library's sort function for this problem. 
You should do it in-place (sort numbers in the original array).

Have you met this question in a real interview? Yes
Example
Given [1, 0, 1, 2], sort it in-place to [0, 1, 1, 2].

Challenge 
A rather straight forward solution is a two-pass algorithm using 
counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then
overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?

Tags 
Two Pointers Sort Array Facebook
Related Problems 
Medium Wiggle Sort 40 %
Medium Wiggle Sort II 24 %
Medium Sort Colors II 33 %
Easy Recover Rotated Sorted Array 26 %*/