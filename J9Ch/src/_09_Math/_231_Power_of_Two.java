package _09_Math;


//  231. Power of Two
//  https://leetcode.com/problems/power-of-two/description/
//  Math, Bit Manipulation
//  3:2  If a number is power of 2, then its highly bit is 1 and there is only one 1. Therefore, n & (n-1) is 0.
//  2的次方数实在太有特点，最高位为1，其他位均为0
public class _231_Power_of_Two {
//-----------------------------------------------------------------------
    public boolean isPowerOfTwo(int n) {
        if(n==0) return false;
        return n == Math.pow(2, Math.round(Math.log(n)/Math.log(2)));
    }

    class Solution {
        public boolean isPowerOfTwo(int n) {
            return (Math.log10(n) / Math.log10(2)) % 1 == 0;

        }
    }
//-----------------------------------------------------------------------
    //1
    //Java Solution 1
    public boolean isPowerOfTwo1(int n) {
        if(n<=0)
            return false;

        while(n>2){
            int t = n>>1;
            int c = t<<1;

            if(n-c != 0)
                return false;

            n = n>>1;
        }

        return true;
    }

//-----------------------------------------------------------------------
    //2
    //Java Solution 2
    //If a number is power of 2, then its highly bit is 1 and there is only one 1.
    // Therefore, n & (n-1) is 0.

    public boolean isPowerOfTwo2(int n) {

        return n>0 && (n&n-1)==0;
    }
//-----------------------------------------------------------------------
    //3
    //Java Solution 3
    public boolean isPowerOfTwo3(int n) {
        return n>0 && n==Math.pow(2, Math.round(Math.log(n)/Math.log(2)));
        //In this solution, the Math.round() method rounds up the number.
    }
//-----------------------------------------------------------------------
    /*
    4 different ways to solve -- Iterative / Recursive / Bit operation / Math
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
//-----------------------------------------------------------------------
}
/*
LeetCode – Power of Two (Java)
Given an integer, write a function to determine if it is a power of two.
Analysis
If a number is power of 2, it's binary form should be 10...0. So if we right shift a bit of the number and then left shift a bit, the value should be the same when the number >= 10(i.e.,2).
*/