package _08_Bit;
import java.util.*;
import org.junit.Test;

//  191. Number of 1 Bits
//  https://leetcode.com/problems/number-of-1-bits/description/
//  5:
//
public class _191_Bit_Number_of_1_Bits_E {
//------------------------------------------------------------------------------
    //1
    //  https://leetcode.com/articles/number-1-bits/
    //  Approach #1 (Loop and Flip) [Accepted]

    public int hammingWeight1(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }
//------------------------------------------------------------------------------
    //2
    //  Approach #2 (Bit Manipulation Trick) [Accepted]
    public int hammingWeight2(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

//------------------------------------------------------------------------------
    //3
    // 9Ch
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        if (n == 0) {
            return 0;
        }

        int count = 1;
        while ((n & (n - 1)) != 0) {
            n &= n-1;
            count++;
        }
        return count;
    }

//------------------------------------------------------------------------------
    //4
    public int hammingWeight01(int n) {
        int count = 0;
        for(int i=1; i<33; i++){
            if(getBit(n, i) == true){
                count++;
            }
        }
        return count;
    }

    public boolean getBit(int n, int i){
        return (n & (1 << i)) != 0;
    }
//------------------------------------------------------------------------------
    //5
//Approach #1 (Loop and Flip) [Accepted]

/*
Algorithm

The solution is straight-forward. We check each of the 3232 bits of the number. If the bit is 11, we add one to the number of 11-bits.

We can check the i^{th}i
​th
​​  bit of a number using a bit mask. We start with a mask m=1m=1, because the binary representation of 11 is,

0000\ 0000\ 0000\ 0000\ 0000\ 0000\ 0000\ 0001 0000 0000 0000 0000 0000 0000 0000 0001

Clearly, a logical AND between any number and the mask 11 gives us the least significant bit of this number. To check the next bit, we shift the mask to the left by one.

0000\ 0000\ 0000\ 0000\ 0000\ 0000\ 0000\ 0010 0000 0000 0000 0000 0000 0000 0000 0010

And so on.


 */

    //要学会mask这个方法
    public int hammingWeight02(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }
//------------------------------------------------------------------------------
}
/*
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 */


/* 这个方法没有下面leetcode官方给的好
LeetCode – Number of 1 Bits (Java)

Problem

Write a function that takes an unsigned integer and
returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 */

/*
计算n的二进制表示中的1的个数
 */