package _08_Bit;
import java.util.*;
import org.junit.Test;

//  476. Number Complement

//  https://leetcode.com/problems/number-complement/description/
//
public class _476_Bit_Number_Complement_E {

//  https://discuss.leetcode.com/topic/74642/java-1-line-bit-manipulation-solution

//    I post solution first and then give out explanation. Please think why does it work before read my explanation.

    public class Solution1 {
        public int findComplement(int num) {
            return ~num & ((Integer.highestOneBit(num) << 1) - 1);
        }
    }
/*    According to the problem, the result is

    The flipped version of the original input but
    Only flip N bits within the range from LEFTMOST bit of 1 to RIGHTMOST.
    For example input = 5 (the binary representation is 101), the LEFTMOST bit of 1 is the third one from RIGHTMOST (100, N = 3). Then we need to flip 3 bits from RIGHTMOST and the answer is 010
    To achieve above algorithm, we need to do 3 steps:

    Create a bit mask which has N bits of 1 from RIGHTMOST. In above example, the mask is 111. And we can use the decent Java built-in function Integer.highestOneBit to get the LEFTMOST bit of 1, left shift one, and then minus one. Please remember this wonderful trick to create bit masks with N ones at RIGHTMOST, you will be able to use it later.
    Negate the whole input number.
    Bit AND numbers in step 1 and 2.
    Three line solution if you think one line solution is too confusing:*/

    public class Solution2 {
        public int findComplement(int num) {
            int mask = (Integer.highestOneBit(num) << 1) - 1;
            num = ~num;
            return num & mask;
        }
    }
/*    UPDATE
    As several people pointed out, we don't need to left shift 1. That's true because the highest 1 bit will always become 0 in the Complement result. So we don't need to take care of that bit.*/

    public class Solution3 {
        public int findComplement(int num) {
            return ~num & (Integer.highestOneBit(num) - 1);
        }
    }
//-------------------------------------------------------------------------///////////
    /*Java, very simple code and self-evident, explanation

33
    D dongll
    Reputation:  44
            for example:
            100110, its complement is 011001, the sum is 111111. So we only need get the min number large or equal to num, then do substraction
*/
    public int findComplement4(int num)
    {
        int i = 0;
        int j = 0;

        while (i < num)
        {
            i += Math.pow(2, j);
            j++;
        }

        return i - num;
    }


//    Same idea, but using bit manipulation instead of Math.pow().

    public class Solution5 {
        public int findComplement(int num) {
            int n = 0;
            while (n < num) {
                n = (n << 1) | 1;
            }
            return n - num;
        }
    }

/*    Java one line solution without using AND (&) or XOR (^)

            7
    Y yuxiangmusic
    Reputation:  955
    To find complement of num = 5 which is 101 in binary.
    First ~num gives ...11111010 but we only care about the rightmost 3 bits.
    Then to erase the 1s before 010 we can add 1000*/

    public int findComplement6(int num) {
        return ~num + (Integer.highestOneBit(num) << 1);
    }
//-------------------------------------------------------------------------///////////

//    Share my Java solution with explanation

    public int findComplement7(int num) {
        // find highest one bit
        int id = 31, mask = 1<<id;
        while ((num & mask)==0) mask = 1<<--id;

        // make mask
        mask = (mask<<1) - 1;

        return (~num) & mask;
    }
//-------------------------------------------------------------------------///////////
//  Java 1 line bit manipulation solution
    public class Solution8 {
        public int findComplement(int num) {
            return ~num & ((Integer.highestOneBit(num) << 1) - 1);
        }
    }
/*    I post solution first and then give out explanation. Please think why does it work before read my explanation.


    According to the problem, the result is

    The flipped version of the original input but
    Only flip N bits within the range from LEFTMOST bit of 1 to RIGHTMOST.
    For example input = 5 (the binary representation is 101), the LEFTMOST bit of 1 is the third one from RIGHTMOST (100, N = 3). Then we need to flip 3 bits from RIGHTMOST and the answer is 010
    To achieve above algorithm, we need to do 3 steps:

    Create a bit mask which has N bits of 1 from RIGHTMOST. In above example, the mask is 111. And we can use the decent Java built-in function Integer.highestOneBit to get the LEFTMOST bit of 1, left shift one, and then minus one. Please remember this wonderful trick to create bit masks with N ones at RIGHTMOST, you will be able to use it later.
    Negate the whole input number.
    Bit AND numbers in step 1 and 2.
    Three line solution if you think one line solution is too confusing:*/

    public class Solution9 {
        public int findComplement(int num) {
            int mask = (Integer.highestOneBit(num) << 1) - 1;
            num = ~num;
            return num & mask;
        }
    }
/*    UPDATE
    As several people pointed out, we don't need to left shift 1. That's true because the highest 1 bit will always become 0 in the Complement result. So we don't need to take care of that bit.*/

    public class Solution10 {
        public int findComplement(int num) {
            return ~num & (Integer.highestOneBit(num) - 1);
        }
    }

//-------------------------------------------------------------------------///////////
}
/*
Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.
Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.

 */

/*

 */