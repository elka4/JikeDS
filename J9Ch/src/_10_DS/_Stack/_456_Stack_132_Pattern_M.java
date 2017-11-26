package _10_DS._Stack;
import java.util.*;
import org.junit.Test;
public class _456_Stack_132_Pattern_M {

    //  https://leetcode.com/problems/132-pattern/solution/



//--------------------------------------------------------------------------------
    // 9Ch
    public class Solution {
        /**
         * @param nums a list of n integers
         * @return true if there is a 132 pattern or false
         */
        public boolean find132pattern(int[] nums) {
            // Write your code here
            Stack<Integer> stack = new Stack<Integer>();
            for (int i = nums.length - 1, two = Integer.MIN_VALUE; i >= 0; i--) {
                if (nums[i] < two) {
                    return true;
                } else {
                    while (!stack.empty() && nums[i] > stack.peek())
                        two = stack.pop();
                }
                stack.push(nums[i]);
            }
            return false;
        }
    }
}

/*
Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */

/*
给你一个 n 个整数的序列 a1,a2,...,an，一个 132 模式是对于一个子串 ai,aj,ak，满足 i < j < k 和 ai < ak < aj。设计一个算法来检查输入的这 n 个整数的序列中是否存在132模式。
n 会小于 20,000。

您在真实的面试中是否遇到过这个题？ Yes
样例
给你序列 nums = [1,2,3,4]
返回 False//没有132模式在这个序列中。
给你序列 nums = [3,1,4,2]
返回 True//存在132模式：[1,4,2]。
 */