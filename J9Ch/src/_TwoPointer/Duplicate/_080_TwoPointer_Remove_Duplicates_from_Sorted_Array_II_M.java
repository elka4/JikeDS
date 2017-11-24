package _TwoPointer.Duplicate;


//  80. Remove Duplicates from Sorted Array II
//  https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
//  http://www.lintcode.com/zh-cn/problem/remove-duplicates-from-sorted-array-ii/
//  Array, Two Pointers
//  4:
public class _080_TwoPointer_Remove_Duplicates_from_Sorted_Array_II_M {
//------------------------------------------------------------------------------
    //1
//Same simple solution written in several languages. Just go through the numbers and include those in the result that haven't been included twice already.
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }

//------------------------------------------------------------------------------
    //2
//Share my O(N) time and O(1) solution when duplicates are allowed at most K times
/*I think both Remove Duplicates from Sorted Array I and II could be solved in a consistent and more general way by allowing the duplicates to appear k times (k = 1 for problem I and k = 2 for problem II). Here is my way: we need a count variable to keep how many times the duplicated element appears, if we encounter a different element, just set counter to 1, if we encounter a duplicated one, we need to check this count, if it is already k, then we need to skip it, otherwise, we can keep this element. The following is the implementation and can pass both OJ:
 */

    //https://www.sigmainfy.com/blog/leetcode-remove-duplicates-from-sorted-array-i-and-ii.html


    int removeDuplicates2(int A[], int n, int k) {
        if (n <= k) return n;
        int i = 1, j = 1;
        int cnt = 1;

        while (j < n) {
            if (A[j] != A[j-1]) {
                cnt = 1;
                A[i++] = A[j];
            }
            else {
                if (cnt < k) {
                    A[i++] = A[j];
                    cnt++;
                }
            }
            ++j;
        }
        return i;
    }
//------------------------------------------------------------------------------
    //3
//O(N) Time and O(1) Java Solution When Allowed at Most K times of Duplicates
//Share my general solution for "Remove Duplicates Problem".
//
//    If anyone could think of a better solution please let me know.

    public int removeDuplicates3(int[] nums) {
        //define at most k times of duplicate numbers
        final int k = 2;

        //check if it is an empty array
        if(nums.length == 0) return 0;

        //start pointer of new array
        int m = 1;

        // count the time of duplicate numbers occurence
        int count = 1;

        for(int i = 1; i < nums.length; ++i) {
            if(nums[i] == nums[i - 1]) {
                if(count < k) {
                    nums[m++] = nums[i];
                }
                count++;
            } else {
                count = 1;
                nums[m++] = nums[i];
            }
        }
        return m;
    }


//------------------------------------------------------------------------------
    //4
    // 9Ch
    public class Jiuzhang {
        /**
         * @param nums: a array of integers
         * @return : return an integer
         */
        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int index = 0, count = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[index]) {
                    if (count < 2) {
                        nums[++index] = nums[i];
                        count ++;
                    }
                } else {
                    nums[++index] = nums[i];
                    count = 1;
                }
            }
            return index + 1;
        }
    }
//------------------------------------------------------------------------------
}
/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 */

/*
跟进“删除重复数字”：

如果可以允许出现两次重复将如何处理？
 */