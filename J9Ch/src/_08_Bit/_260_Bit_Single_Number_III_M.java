package _08_Bit;
import java.util.*;
import org.junit.Test;

//  260. Single Number III

//  https://leetcode.com/problems/single-number-iii/description/
//  http://www.lintcode.com/zh-cn/problem/single-number-iii/
public class _260_Bit_Single_Number_III_M {

//Accepted C++/Java O(n)-time O(1)-space Easy Solution with Detail Explanations
/*
Once again, we need to use XOR to solve this problem. But this time, we need to do it in two passes:

In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find. Note that since the two numbers are distinct, so there must be a set bit (that is, the bit with value '1') in the XOR result. Find
out an arbitrary set bit (for example, the rightmost set bit).

In the second pass, we divide all numbers into two groups, one with the aforementioned bit set, another with the aforementinoed bit unset. Two different numbers we need to find must fall into thte two distrinct groups. XOR numbers in each group, we can find a number in either group.

Complexity:

Time: O (n)

Space: O (1)

A Corner Case:

When diff == numeric_limits<int>::min(), -diff is also numeric_limits<int>::min(). Therefore, the value of diff after executing diff &= -diff is still numeric_limits<int>::min(). The answer is still correct.

 */
public class Solution1 {
    public int[] singleNumber(int[] nums) {
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;

        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums)
        {
            if ((num & diff) == 0) // the bit is not set
            {
                rets[0] ^= num;
            }
            else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }
}

//Bit manipulation beats 99.62%
//Find the rightmost set bit, divide numbers into two groups.
//    Each group will end up being one unique number.

    public int[] singleNumber2(int[] nums) {
        int result[] = new int[2];
        int xor = nums[0];
        for (int i=1; i<nums.length; i++)
        {
            xor ^= nums[i];
        }

        int bit = xor & ~(xor-1);
        int num1 = 0;
        int num2 = 0;

        for (int num : nums)
        {
            if ((num & bit) > 0)
            {
                num1 ^= num;
            }
            else
            {
                num2 ^= num;
            }
        }

        result[0] = num1;
        result[1] = num2;
        return result;
    }


//-------------------------------------------------------------------------///////////
//  My Java solution adapted from the commonest solution here
/*I read @zhiqing_xiao 's post to get an idea about the solution. His solution is really smart and elegant, but it took me a while to get understand how "diff &= -diff" works. I changed it a little bit to make it better understood, but it is totally based on his solution.

    Instead of using the right-most "1" of diff, I used the left-most "1" to divide groups. This should also do the trick.*/

    public class Solution3 {
        public int[] singleNumber(int[] nums) {
            int diff = 0;
            for(int num: nums){
                diff^=num;
            }
            diff = Integer.highestOneBit(diff);

            int[] result = new int[2];
            Arrays.fill(result,0);
            for(int num: nums){
                if((diff & num) == 0){
                    result[0] ^= num;
                }
                else{
                    result[1] ^= num;
                }
            }
            return result;
        }
    }

//-------------------------------------------------------------------------///////////


//-------------------------------------------------------------------------///////////
    //jiuzhang
    /**
     * @param A : An integer array
     * @return : Two integers
     */
    public List<Integer> singleNumberIII(int[] A) {
        int xor = 0;
        for (int i = 0; i < A.length; i++) {
            xor ^= A[i];
        }

        int lastBit = xor - (xor & (xor - 1));
        int group0 = 0, group1 = 0;
        for (int i = 0; i < A.length; i++) {
            if ((lastBit & A[i]) == 0) {
                group0 ^= A[i];
            } else {
                group1 ^= A[i];
            }
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(group0);
        result.add(group1);
        return result;
    }

//-------------------------------------------------------------------------///////////
}
/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?

 */

/*
给出2*n + 2个的数字，除其中两个数字之外其他每个数字均出现两次，找到这两个数字。

样例
给出 [1,2,2,3,4,4,5,3]，返回 1和5
 */