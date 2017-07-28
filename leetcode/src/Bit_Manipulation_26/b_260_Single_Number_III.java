package Bit_Manipulation_26;
import java.util.*;

public class b_260_Single_Number_III {

    /*
    Accepted C++/Java O(n)-time O(1)-space Easy Solution with Detail Explanations
Once again, we need to use XOR to solve this problem.
But this time, we need to do it in two passes:

In the first pass, we XOR all elements in the array,
and get the XOR of the two numbers we need to find.
Note that since the two numbers are distinct,
so there must be a set bit (that is, the bit with value '1') in the XOR result. Find
out an arbitrary set bit (for example, the rightmost set bit).

In the second pass, we divide all numbers into two groups,
one with the aforementioned bit set, another with the aforementinoed bit unset.
 Two different numbers we need to find must fall into thte two distrinct groups.
 XOR numbers in each group, we can find a number in either group.

Complexity:

Time: O (n)

Space: O (1)

A Corner Case:

When diff == numeric_limits<int>::min(), -diff is also numeric_limits<int>::min().
Therefore, the value of diff after executing diff &= -diff is still
 numeric_limits<int>::min(). The answer is still correct.
     */


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

////////////////////////////////////////////////////////////////////////////////////////
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
/*
Bit manipulation beats 99.62%
Find the rightmost set bit, divide numbers into two groups.
Each group will end up being one unique number.
 */



}

/*

Given an array of numbers nums, in which exactly two elements appear
 only once and all the other elements appear exactly twice.
 Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important.
So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity.
Could you implement it using only constant space complexity?

 */