package _TwoPointer.Duplicate;
import org.junit.Test;

//  283. Move Zeroes
//  https://leetcode.com/problems/move-zeroes/description/
//  http://www.lintcode.com/zh-cn/problem/move-zeroes/
//  6:
public class _283_TwoPointer_Move_Zeroes_E {
//------------------------------------------------------------------------------
    //1
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
//------------------------------------------------------------------------------
    //2
    //In fact in approach 2, we can fill 0 proactively anytime after move non-zero.
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

//------------------------------------------------------------------------------
    //3
    //The optimal should be like this(from idea 3, but not swap)
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
//------------------------------------------------------------------------------
    //4
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
//------------------------------------------------------------------------------
    //5
    //1ms Java solution
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

//------------------------------------------------------------------------------
    private void print(int[] nums){
        for (int i:nums
                ) {
            System.out.print(i + " ");

        }
        System.out.println();
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    private void printArr(int[] nums){
//        System.out.print("nums[]: ");
        for (int i:nums
                ) {
            System.out.print(i + " ");
        }
//        System.out.print(" | ");
//        System.out.println();
    }


//------------------------------------------------------------------------------
    //6
    // 9Ch
    /**
     * @param nums an integer array
     * @return nothing, do this in-place
     */
    public void moveZeroes(int[] nums) {
        int left = 0, right = 0;

        while (right < nums.length) {
            printArr(nums);
            System.out.print("| " + "left: " + left + " | " + "right: " + right);

            if (nums[right] != 0) {
                System.out.print(" | nums[right] " + nums[right]);
                swap(nums, left, right);
                left++;
            }
            right++;
            System.out.println();
        }
    }


    @Test
    public void test(){
        int[] nums = {0, 1, 0, 3, 12};
//        print(nums);
        moveZeroes(nums);
//        print(nums);
    }
/*
        0 1 0 3 12 | left: 0 | right: 0
        0 1 0 3 12 | left: 0 | right: 1 | nums[right] 1
        1 0 0 3 12 | left: 1 | right: 2
        1 0 0 3 12 | left: 1 | right: 3 | nums[right] 3
        1 3 0 0 12 | left: 2 | right: 4 | nums[right] 12
 */

//------------------------------------------------------------------------------
}
/*
------------------------------------------------------------------------------
给一个数组 nums 写一个函数将 0 移动到数组的最后面，非零元素保持原数组的顺序

 注意事项

1.必须在原数组上操作
2.最小化操作数

样例
给出 nums = [0, 1, 0, 3, 12], 调用函数之后, nums = [1, 3, 12, 0, 0].
------------------------------------------------------------------------------
 */


/*
------------------------------------------------------------------------------
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
------------------------------------------------------------------------------
 */