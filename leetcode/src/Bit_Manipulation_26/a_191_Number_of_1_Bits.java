package Bit_Manipulation_26;
import java.util.*;

public class a_191_Number_of_1_Bits {
//Approach #1 (Loop and Flip) [Accepted]

    /*
    The solution is straight-forward. We check each of the 3232 bits of the number.
     If the bit is 11, we add one to the number of 11-bits.

We can check the i^{th}i
​th
​​  bit of a number using a bit mask. We start with a mask m=1m=1,
 because the binary representation of 11 is,

0000\ 0000\ 0000\ 0000\ 0000\ 0000\ 0000\ 0001 0000 0000 0000 0000 0000 0000 0000 0001

Clearly, a logical AND between any number and the mask 11 gives us the least
significant bit of this number. To check the next bit, we shift the mask to the left by one.

0000\ 0000\ 0000\ 0000\ 0000\ 0000\ 0000\ 0010 0000 0000 0000 0000 0000 0000 0000 0010

And so on.
     */
public int hammingWeight(int n) {
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
//    The run time depends on the number of bits in nn. Because nn in this piece of code is
// a 32-bit integer, the time complexity is O(1)O(1).
//
//    The space complexity is O(1)O(1), since no additional space is allocated.

/////////////////////////////////////////////////////
//Approach #2 (Bit Manipulation Trick) [Accepted]

//https://leetcode.com/articles/number-1-bits/

    // n &= (n - 1) 去掉n里的最后一个1
    public int hammingWeight2(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

//    The run time depends on the number of 11-bits in nn. In the worst case,
// all bits in nn are 11-bits. In case of a 32-bit integer, the run time is O(1)O(1).
//
//    The space complexity is O(1)O(1), since no additional space is allocated.
/////////////////////////////////////////////////////


/////////////////////////////////////////////////////


/////////////////////////////////////////////////////


/////////////////////////////////////////////////////


/////////////////////////////////////////////////////


/////////////////////////////////////////////////////



}

/*
Write a function that takes an unsigned integer and returns the number
of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation
00000000000000000000000000001011, so the function should return 3.
 */