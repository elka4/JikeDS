package _TwoPointer.Duplicate;

//  26. Remove Duplicates from Sorted Array
//  https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/

//http://www.lintcode.com/en/problem/remove-duplicates-from-sorted-array/
public class _026_TwoPointer_Remove_Duplicates_from_Sorted_Array_E {
    //https://leetcode.com/articles/remove-duplicates-sorted-array/

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

/////////////////////////////////////////////////////////////////////
    //jiuzhang
public class Jiuzhang {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[size]) {
                nums[++size] = nums[i];
            }
        }
        return size + 1;
    }
}

}
/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.


 */

/*
给定一个排序数组，在原数组中删除重复出现的数字，使得每个元素只出现一次，并且返回新的数组的长度。

不要使用额外的数组空间，必须在原地没有额外空间的条件下完成。
 */