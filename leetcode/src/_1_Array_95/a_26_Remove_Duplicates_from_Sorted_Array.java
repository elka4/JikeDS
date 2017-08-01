package _1_Array_95;

import java.util.Arrays;

/**
 * Created by tianhuizhu on 6/21/17.
 */

//26. Remove Duplicates from Sorted Array

/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 */


public class a_26_Remove_Duplicates_from_Sorted_Array {

    //Approach #1 (Two Pointers) [Accepted]
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

//////////////////////////////////???
    //Solution 1
    // Manipulate original array
    public static int removeDuplicatesNaive(int[] A) {
        if (A.length < 2)
            return A.length;

        int j = 0;
        int i = 1;

        while (i < A.length) {
            if (A[i] == A[j]) {
                i++;
            } else {
                j++;
                A[j] = A[i];
                i++;
            }
        }

        return j + 1;
    }



//////////////////////////////////???

    //Solution 2
    // Create an array with all unique elements
    public static int[] removeDuplicates3(int[] A) {
        if (A.length < 2)
            return A;

        int j = 0;
        int i = 1;

        while (i < A.length) {
            if (A[i] == A[j]) {
                i++;
            } else {
                j++;
                A[j] = A[i];
                i++;
            }
        }

        int[] B = Arrays.copyOf(A, j + 1);

        return B;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 3, 3 };
        arr = removeDuplicates3(arr);
        System.out.println(arr.length);
    }


//////////////////////////////////???

    //Solution 3

    //If we only want to count the number of unique elements, the following method is good enough.



    // Count the number of unique elements
    public static int countUnique4(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] == A[i + 1]) {
                count++;
            }
        }
        return (A.length - count);
    }


//////////////////////////////////???



//////////////////////////////////???



}
