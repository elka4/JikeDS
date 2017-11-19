package _01_Array.Sort_Colors;

public class _23Sort_Colors {
	public void sortColors(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        //have only 0 on its left
        int pl = 0;

        //have only 2 on its right
        int pr = a.length - 1;

        //have both 0 and 1 on its left
        int i = 0;

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

//-------------------------------------------------------------------------//////
    /*
    https://leetcode.com/problems/sort-colors/discuss/
     */
    //Four different solutions

    // two pass O(m+n) space
    void sortColors2(int A[], int n) {
        int num0 = 0, num1 = 0, num2 = 0;

        for(int i = 0; i < n; i++) {
            if (A[i] == 0) ++num0;
            else if (A[i] == 1) ++num1;
            else if (A[i] == 2) ++num2;
        }

        for(int i = 0; i < num0; ++i) A[i] = 0;
        for(int i = 0; i < num1; ++i) A[num0+i] = 1;
        for(int i = 0; i < num2; ++i) A[num0+num1+i] = 2;
    }

    // one pass in place solution
    void sortColors3(int A[], int n) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < n; ++i) {
            if (A[i] == 0)
            {
                A[++n2] = 2; A[++n1] = 1; A[++n0] = 0;
            }
            else if (A[i] == 1)
            {
                A[++n2] = 2; A[++n1] = 1;
            }
            else if (A[i] == 2)
            {
                A[++n2] = 2;
            }
        }
    }

    // one pass in place solution
    void sortColors4(int A[], int n) {
        int j = 0, k = n - 1;
        for (int i = 0; i <= k; ++i){
            if (A[i] == 0 && i != j)
                swap(A[i--], A[j++]);
            else if (A[i] == 2 && i != k)
                swap(A[i--], A[k--]);
        }
    }

    // one pass in place solution
    void sortColors5(int A[], int n) {
        int j = 0, k = n-1;
        for (int i=0; i <= k; i++) {
            if (A[i] == 0)
                swap(A[i], A[j++]);
            else if (A[i] == 2)
                swap(A[i--], A[k--]);
        }
    }

    void swap(int i, int j){
	    int temp = i;
	    i = j;
	    j = temp;
    }



//-------------------------------------------------------------------------//////

    //Java solution, both 2-pass and 1-pass

    public void sortColors6(int[] nums) {
        // 1-pass
        int p1 = 0, p2 = nums.length - 1, index = 0;
        while (index <= p2) {
            if (nums[index] == 0) {
                nums[index] = nums[p1];
                nums[p1] = 0;
                p1++;
            }
            if (nums[index] == 2) {
                nums[index] = nums[p2];
                nums[p2] = 2;
                p2--;
                index--;
            }
            index++;
        }
    }

    public void sortColors7(int[] nums) {
        // 2-pass
        int count0 = 0, count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {count0++;}
            if (nums[i] == 1) {count1++;}
            if (nums[i] == 2) {count2++;}
        }
        for(int i = 0; i < nums.length; i++) {
            if (i < count0) {nums[i] = 0;}
            else if (i < count0 + count1) {nums[i] = 1;}
            else {nums[i] = 2;}
        }
    }

//-------------------------------------------------------------------------//////
    //Share one pass java solution

    /*
    The concept is simple. Maintain two pointer, pointer "one" indicates the begging of all ones and pointer "two" indicates the begging of all twos. When we meet 1, we move 1 to the end of 1 sequence which is begging of two sequence then move begging of 2 forward 1. Doing the same to the 2.


     */

    public void sortColors8(int[] A) {
        int one = 0;
        int two = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                A[i] = A[two];
                A[two] = A[one];
                A[one] = 0;
                one++;
                two++;
            } else if (A[i] == 1) {
                A[i] = A[two];
                A[two] = 1;
                two++;
            }
        }
    }


//-------------------------------------------------------------------------//////

//mine, similar in spirit, note that this solution is essentially the partition() method in quicksort, applied on both left and right side at the same time


    public void sortColors9(int[] A) {

        int red=0;int blue=A.length-1;
        for(int i=0;i<=blue && i < A.length;) {
            if (A[i] == 0 && i > red ) {
                swap(A,i,red++);
            } else if (A[i] == 2 && i < blue)
                swap(A,i,blue--);
            else
                i++;
        }
    }
//-------------------------------------------------------------------------//////


//-------------------------------------------------------------------------//////


//-------------------------------------------------------------------------//////



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