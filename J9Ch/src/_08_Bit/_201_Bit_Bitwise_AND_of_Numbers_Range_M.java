package _08_Bit;
import java.util.*;
import org.junit.Test;

//  201. Bitwise AND of Numbers Range

//  https://leetcode.com/problems/bitwise-and-of-numbers-range/description/
//
public class _201_Bit_Bitwise_AND_of_Numbers_Range_M {
/*    Bit operation solution(JAVA)
    The idea is very simple:

    last bit of (odd number & even number) is 0.
    when m != n, There is at least an odd number and an even number, so the last bit position result is 0.
    Move m and n rigth a position.
    Keep doing step 1,2,3 until m equal to n, use a factor to record the iteration time.*/

    public class Solution1 {
        public int rangeBitwiseAnd(int m, int n) {
            if(m == 0){
                return 0;
            }
            int moveFactor = 1;
            while(m != n){
                m >>= 1;
                n >>= 1;
                moveFactor <<= 1;
            }
            return m * moveFactor;
        }
    }

//    2 line Solution with detailed explanation
    //  https://leetcode.com/problems/bitwise-and-of-numbers-range/discuss/
    public int rangeBitwiseAnd2(int m, int n) {
        while(m<n) n = n & (n-1);
        return n;
    }


/////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////
    //jiuzhang
    public int rangeBitwiseAnd(int m, int n) {
        if (n == m) {
            return n;
        }
        if (n - m == 1) {
            return n & m;
        }
        return rangeBitwiseAnd(m / 2, n / 2) << 1;
    }

/////////////////////////////////////////////////////////////////////////
}
/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.
 */

/*

 */