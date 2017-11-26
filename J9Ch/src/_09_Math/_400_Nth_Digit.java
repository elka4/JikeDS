package _09_Math;


//  400. Nth Digit
//  https://leetcode.com/problems/nth-digit/description/
//
public class _400_Nth_Digit {
//-----------------------------------------------------------------------
    //1
/*    Java solution
    Straight forward way to solve the problem in 3 steps:

    1find the length of the number where the nth digit is from
    2find the actual number where the nth digit is from
    3find the nth digit and return*/

    class Solution1{
        public int findNthDigit(int n) {
            int len = 1;
            long count = 9;
            int start = 1;

            while (n > len * count) {
                n -= len * count;
                len += 1;
                count *= 10;
                start *= 10;
            }

            start += (n - 1) / len;
            String s = Integer.toString(start);
            return Character.getNumericValue(s.charAt((n - 1) % len));
        }
    }
//-----------------------------------------------------------------------

    public int findNthDigit(int m) {
        long n=m; // convert int to long
        long start=1,  len=1,  count=9;

        while(n>len*count){
            n=n-len*count;
            len++;
            count=count*10;
            start=start*10;
        }

        // identify the number
        start = start + (n-1)/len;

        // identify the digit
        return String.valueOf(start).charAt((int)((n-1)%len))-'0';
    }
//-----------------------------------------------------------------------


//-----------------------------------------------------------------------

}
/*
LeetCode – Nth Digit (Java)

Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.

 */