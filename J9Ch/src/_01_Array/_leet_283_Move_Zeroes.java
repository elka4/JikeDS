package _01_Array;

//283. Move Zeroes

//Array, two pointer

//Given an array nums, write a function to move all 0's to the end of it
// while maintaining the relative order of the non-zero elements.

//For example, given nums = [0, 1, 0, 3, 12], after calling your function,
// nums should be [1, 3, 12, 0, 0].
//
//        Note:
//        You must do this in-place without making a copy of the array.
//        Minimize the total number of operations.

import org.junit.Test;

public class _leet_283_Move_Zeroes {
/*
FB高频题，写入次数（Write times）
Analysis

展示如何一步一步去优化整个算法
The most naive approach should be extremely straightforward.
By keeping two arrays: one for non-zero numbers and one for all zeroes,
we can concatenate them at the end. Since we just need to traverse the array once,
 this will give us O(N) time complexity. Space complexity is O(N) as we
  need two additional arrays.
Apparently, time complexity can’t be improved as we need to traverse the array
 at least once. In order to use less space, we should look for modifying the array.
 
// 先演一演，展示一个swap的做法，找到第一个0，然后和后面非0的元素进行swap
// 一次 swap = 2次数组写入 + 1次 temp 写入，总写入次数为 O(3n)，总数组写入次数为 O(2n).
//
// [0, 1, 0, 3, 12]
 */

    public void moveZeroes(int[] nums) {
        int p0 = 0;
        int n = nums.length;
        // p0寻找为0的位置
        while(p0 < n && p0 != 0) p0++;
        for(int i = p0 + 1; i < nums.length; i++) {
            if(nums[i] != 0) {
                swap(nums, p0++, i);
            }
        }
    }

    public void swap (int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    @Test
    public void test01(){
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    /*
    然后 follow-up 肯定是如何减少写入次数 (aka 别用 swap)，后面的演技就是这样……
其实我一直觉得这个写法才是最直观的，暴力点用 swap 的写法反而 tricky 一点。。
这种写法中，数组每一个位置一定会被不多不少写入一次，写入次数为 O(n)
双指针法，一个track当前有效数组的长度，一个去找非0的数，然后覆盖
Quick sort的partition思想+

     */

    public void moveZeroes2(int[] nums) {
        // i维护的是有效数组的长度
        int i = 0, j = 0;
        int n = nums.length;
        //双前向指针，同array
        //j指向每一个元素，i指向非0数值位置
        //j每轮都++向前，j非0都时候，就把j赋值给i，i++
        while(j < n) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
            j++;
        }
        while(i < n) {
            nums[i++] = 0;
        }
    }

//-------------------------------------------------------------------------/////////////
//Simple O(N) Java Solution Using Insert Index

    // Shift non-zero values as far forward as possible
    // Fill remaining space with zeros

    public void moveZeroes3(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }


//-------------------------------------------------------------------------/////////////
//1ms Java solution

    public void moveZeroes4(int[] nums) {

        int j = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }
//-------------------------------------------------------------------------/////////////

//Java - Short and Swappy - 1ms

    public void moveZeroes5(int[] nums) {
        int z = -1;
        for (int i=0; i< nums.length; i++) {
            int temp = nums[i];
            if (temp != 0) {
                nums[i]=nums[++z];
                nums[z]=temp;
            }
        }
    }


//-------------------------------------------------------------------------/////////////
//Java easy and simple solutions
    public void moveZeroes6(int[] nums) {
        int movings=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0) {
                movings++;
                continue;
            }

            int temp=nums[i-movings];
            nums[i-movings]=nums[i];
            nums[i]=temp;
        }
    }


//-------------------------------------------------------------------------/////////////
//Simple in-place java solution, O(n) time complexity

    public void moveZeroes7(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            } else {
                nums[i - count] = nums[i];
                if (count != 0) {nums[i] = 0;}
            }
        }
        return;
    }

//-------------------------------------------------------------------------/////////////
//My easy java solution in 1ms with explanation

    public void moveZeroes8(int[] nums) {

        //if only 1 element is present, return
        if (nums.length == 1)
            return;

        int i = 0;

        //iterate through the array counting number of zeros
        for (int k : nums) {
            if (k == 0)
                i++;
        }

        //if no 0's exists or array only contains zeros , return
        if (i == 0 || i == nums.length)
            return;

        //index to keep track of non-zero elements
        int m = 0;

        //for loop to move all the non zero elements in order
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0)
                nums[m++] = nums[j];
        }

        //insert the 0's in remaining spaces up till the end
        for (int j = nums.length - i; j < nums.length; j++) {
            nums[j] = 0;
        }
    }

    //Nice idea. Code can be refined into the following which uses only two loops:

    public void moveZeroes9(int[] nums) {
        int count = 0;
        for(int i = 0; i < nums.length; ++i ){
            if ( nums[i] == 0 ){
                count++; //track zero count
            }else{
                nums[i-count] = nums[i]; // move non-zero element
            }
        }
        // set the trailing zeros
        for( int i = 0; i < count; ++i ){
            nums[nums.length-1-i] = 0;
        }
    }

    //neater and faster code

    public void moveZeroes10(int[] nums) {
        int m = 0;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[m++] = nums[i];
            }
        }
        for(int i = m;i < nums.length; i++){
            nums[i]=0;
        }
    }


//-------------------------------------------------------------------------/////////////

//-------------------------------------------------------------------------/////////////



//-------------------------------------------------------------------------/////////////

//-------------------------------------------------------------------------/////////////



//-------------------------------------------------------------------------/////////////



}
