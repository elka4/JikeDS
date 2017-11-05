package _TwoPointer;
import java.util.*;
import org.junit.Test;

//  283. Move Zeroes
//  https://leetcode.com/problems/move-zeroes/description/

//  http://www.lintcode.com/zh-cn/problem/move-zeroes/
public class _283_TwoPointer_Move_Zeroes_E {
    class Solution1 {
        public void moveZeroes(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }

            for (int i = 0, left = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    continue;
                }
                nums[left] = nums[i];
                if (left != i) {
                    nums[i] = 0;
                }
                left++;
            }
        }
    }

//    In fact in approach 2, we can fill 0 proactively anytime after move non-zero.
//
    public void moveZeroes2(int[] nums) {
        int lastNonZeroFoundAt = 0;
        // If the current element is not 0, then we need to
        // append it just in front of last non 0 element we found.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                //move non-zeno close to the head
                nums[lastNonZeroFoundAt] = nums[i];
                //fill zero for current position if it is behind the last non-zer0
                if(lastNonZeroFoundAt<i){
                    nums[i] = 0;
                }

                //non-zero position move ahead
                lastNonZeroFoundAt++;
            }
        }
    }

//    The optimal should be like this(from idea 3, but not swap)
    public class Solution3 {
        public void moveZeroes(int[] nums) {
            int first = 0, second = 0;
            while (first != nums.length) {
                if (nums[first] != 0) {
                    nums[second] = nums[first];
                    if (first != second) nums[first] = 0;
                    second++;
                }
                first++;
            }
        }
    }

//    Simple O(N) Java Solution Using Insert Index
// Shift non-zero values as far forward as possible
// Fill remaining space with zeros

    public void moveZeroes4(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) nums[insertPos++] = num;
        }

        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }


//    1ms Java solution
    public class Solution5 {

        public void moveZeroes(int[] nums) {

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
    }
/////////////////////////////////////////////////////////
    //jiuzhang
public class Jiuzhang {
    /**
     * @param nums an integer array
     * @return nothing, do this in-place
     */
    //双前向型指针
    public void moveZeroes(int[] nums) {
        // Write your code here
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }
}
}
/*
移动零

给一个数组 nums 写一个函数将 0 移动到数组的最后面，非零元素保持原数组的顺序

 注意事项

1.必须在原数组上操作
2.最小化操作数

样例
给出 nums = [0, 1, 0, 3, 12], 调用函数之后, nums = [1, 3, 12, 0, 0].
 */

/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.

 */