package _TwoPointer._TwoPointer;
import java.util.*;
import org.junit.Test;

//  27. Remove Element

//  https://leetcode.com/problems/remove-element/description/

//http://www.lintcode.com/en/problem/remove-element/
public class _027_TwoPointer_1_Remove_Element_E {
    //https://leetcode.com/problems/remove-element/solution/

    //Approach #1 (Two Pointers) [Accepted]
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
    //Approach #2 (Two Pointers - when elements to remove are rare) [Accepted]
    public int removeElement2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }

    //My solution for your reference.
    int removeElement(int A[], int n, int elem) {
        int begin=0;
        for(int i=0;i<n;i++) if(A[i]!=elem) A[begin++]=A[i];
        return begin;
    }
//---------------------------------///////////////////////
    // 9Ch
public class Jiuzhang{
    public int removeElement(int[] A, int elem) {
        int i = 0;
        int pointer = A.length - 1;
        while(i <= pointer){
            if(A[i] == elem){
                A[i] = A[pointer];
                pointer--;
            } else {
                i++;
            }
        }
        return pointer + 1;
    }
}

}
/*
Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.
 */


/*
给定一个数组和一个值，在原地删除与值相同的数字，返回新数组的长度。

元素的顺序可以改变，并且对新的数组不会有影响。

样例
给出一个数组 [0,4,4,0,0,2,4,4]，和值 4

返回 4 并且4个元素的新数组为[0,0,0,2]
 */