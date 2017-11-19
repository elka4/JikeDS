package _08_Bit;
import java.util.*;
import org.junit.Test;

//  191. Number of 1 Bits
//  https://leetcode.com/problems/number-of-1-bits/description/
//
public class _191_Bit_Number_of_1_Bits_E {
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


    //  Approach #2 (Bit Manipulation Trick) [Accepted]
    public int hammingWeight2(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
//-------------------------------------------------------------------------///////////


//-------------------------------------------------------------------------///////////


//-------------------------------------------------------------------------///////////
    //jiuzhang
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

//-------------------------------------------------------------------------///////////

}
/*
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.


 */

/*

 */