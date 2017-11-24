package _08_Bit;
import java.util.*;
import org.junit.Test;

//  137. Single Number II
//  https://leetcode.com/problems/single-number-ii/description/
//  http://www.lintcode.com/zh-cn/problem/single-number-ii/
public class _137_Bit_Single_Number_II_M {
//    Challenge me , thx
    public int singleNumber1(int[] A) {
        int ones = 0, twos = 0;
        for(int i = 0; i < A.length; i++){
            ones = (ones ^ A[i]) & ~twos;
            twos = (twos ^ A[i]) & ~ones;
        }
        return ones;
    }

    //https://leetcode.com/problems/single-number-ii/discuss/
//------------------------------------------------------------------------------////
/*    An General Way to Handle All this sort of questions.
this kind of question the key idea is design a counter that record state. the problem can be every one occurs K times except one occurs M times. for this question, K =3 ,M = 1(or 2) .
    so to represent 3 state, we need two bit. let say it is a and b, and c is the incoming bit.
    then we can design a table to implement the state move.

    current   incoming  next
    a b            c    a b
0 0            0    0 0
        0 1            0    0 1
        1 0            0    1 0
        0 0            1    0 1
        0 1            1    1 0
        1 0            1    0 0
    like circuit design, we can find out what the next state will be with the incoming bit.( we only need find the ones)
    then we have for a to be 1, we have

    current   incoming  next
    a b            c    a b
    1 0            0    1 0
            0 1            1    1 0
    and this is can be represented by

            a=a&~b&~c + ~a&b&c
    and b can do the same we , and we find that

    b= ~a&b&~c+~a&~b&c
    and this is the final formula of a and b and just one of the result set, because for different state move table definition, we can generate different formulas, and this one is may not the most optimised. as you may see other's answer that have a much simple formula, and that formula also corresponding to specific state move table. (if you like ,you can reverse their formula to a state move table, just using the same way but reversely)

            for this questions we need to find the except one
    as the question don't say if the one appears one time or two time ,
    so for ab both

01 10 => 1
        00 => 0
    we should return a|b;
this is the key idea , we can design any based counter and find the occurs any times except one .
    here is my code. with comment.*/

    public class Solution2 {

        public int singleNumber(int[] nums) {
            //we need to implement a tree-time counter(base 3) that if a bit appears three time ,it will be zero.
            //#curent  income  ouput
            //# ab      c/c       ab/ab
            //# 00      1/0       01/00
            //# 01      1/0       10/01
            //# 10      1/0       00/10
            // a=~abc+a~b~c;
            // b=~a~bc+~ab~c;
            int a=0;
            int b=0;
            for(int c:nums){
                int ta=(~a&b&c)|(a&~b&~c);
                b=(~a&~b&c)|(~a&b&~c);
                a=ta;
            }
            //we need find the number that is 01,10 => 1, 00 => 0.
            return a|b;

        }
    }
//this is a general solution . and it comes from the Circuit Design on course digital logic.

//------------------------------------------------------------------------------////
/*Java O(n) easy to understand solution, easily extended to any times of occurance
    The usual bit manipulation code is bit hard to get and replicate. I like to think about the number in 32 bits and just count how many 1s are there in each bit, and sum %= 3 will clear it once it reaches 3. After running for all the numbers for each bit, if we have a 1, then that 1 belongs to the single number, we can simply move it back to its spot by doing ans |= sum << i;

    This has complexity of O(32n), which is essentially O(n) and very easy to think and implement. Plus, you get a general solution for any times of occurrence. Say all the numbers have 5 times, just do sum %= 5.*/

    public int singleNumber3(int[] nums) {
        int ans = 0;
        for(int i = 0; i < 32; i++) {
            int sum = 0;
            for(int j = 0; j < nums.length; j++) {
                if(((nums[j] >> i) & 1) == 1) {
                    sum++;
                    sum %= 3;
                }
            }
            if(sum != 0) {
                ans |= sum << i;
            }
        }
        return ans;
    }

//------------------------------------------------------------------------------////
    // 9Ch
    public int singleNumberII(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int result=0;
        int[] bits=new int[32];
        for (int i = 0; i < 32; i++) {
            for(int j = 0; j < A.length; j++) {
                bits[i] += A[j] >> i & 1;
                bits[i] %= 3;
            }

            result |= (bits[i] << i);
        }
        return result;
    }

//------------------------------------------------------------------------------////
}
/*
Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?


 */

/*
给出3*n + 1 个的数字，除其中一个数字之外其他每个数字均出现三次，找到这个数字。

样例
给出 [1,1,2,3,3,3,2,2,4,1] ，返回 4

 */