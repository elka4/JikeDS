package _08_Bit;
import java.util.*;
import org.junit.Test;

//  231. Power of Two

//  Given an integer, write a function to determine if it is a power of two.


//
public class _231_Bit_Power_of_Two_E {

//  One line java solution using bitCount
//This is kind of cheating, but the idea is that a power of two in binary form has and only has one "1".

    public class Solution1 {
        public boolean isPowerOfTwo(int n) {
            return n>0 && Integer.bitCount(n) == 1;
        }
    }
    public boolean isPowerOfTwo2(int n) {

        if(n<=0)
            return false;
        int log_base_2= (int)(Math.log(n)/Math.log(2));
        return (((n & (1<< log_base_2)) ==n)?true:false);
    }

//    Same idea here. Count the number of ones (should be 1) by right shifting n by 32 times (be sure to kick out 0x80000000).

    public boolean isPowerOfTwo3(int n) {
        if (n == 0x80000000) return false;
        int ones = 0;
        for (int i = 1; i <= 32; ++i) {
            ones += n & 1;
            n = n >> 1;
        }
        return ones == 1;
    }

    public boolean isPowerOfTwo4(int n) {
        return n > 0 && Integer.highestOneBit(n) == Integer.lowestOneBit(n) ? true : false;
    }
//------------------------------------------------------------------------------
//4 different ways to solve -- Iterative / Recursive / Bit operation / Math

/*
This question is not an difficult one, and there are many ways to solve it.

Method 1: Iterative

check if n can be divided by 2. If yes, divide n by 2 and check it repeatedly.

if(n==0) return false;
while(n%2==0) n/=2;
return (n==1);
Time complexity = O(log n)

Method 2: Recursive

return n>0 && (n==1 || (n%2==0 && isPowerOfTwo(n/2)));
Time complexity = O(log n)

Method 3: Bit operation

If n is the power of two:

n = 2 ^ 0 = 1 = 0b0000...00000001, and (n - 1) = 0 = 0b0000...0000.
n = 2 ^ 1 = 2 = 0b0000...00000010, and (n - 1) = 1 = 0b0000...0001.
n = 2 ^ 2 = 4 = 0b0000...00000100, and (n - 1) = 3 = 0b0000...0011.
n = 2 ^ 3 = 8 = 0b0000...00001000, and (n - 1) = 7 = 0b0000...0111.
we have n & (n-1) == 0b0000...0000 == 0

Otherwise, n & (n-1) != 0.

For example, n =14 = 0b0000...1110, and (n - 1) = 13 = 0b0000...1101.

return n>0 && ((n & (n-1)) == 0);
Time complexity = O(1)

Method 4: Math derivation

Because the range of an integer = -2147483648 (-2^31) ~ 2147483647 (2^31-1), the max possible power of two = 2^30 = 1073741824.

(1) If n is the power of two, let n = 2^k, where k is an integer.

We have 2^30 = (2^k) * 2^(30-k), which means (2^30 % 2^k) == 0.

(2) If n is not the power of two, let n = j*(2^k), where k is an integer and j is an odd number.

We have (2^30 % j*(2^k)) == (2^(30-k) % j) != 0.

return n>0 && (1073741824 % n == 0);
Time complexity = O(1)
 */

//------------------------------------------------------------------------------
//One line Java solution
    public boolean isPowerOfTwo(int n) {
        return ((n & (n-1))==0 && n>0);
    }

//------------------------------------------------------------------------------


//------------------------------------------------------------------------------
}
/*

 */

/*

 */