package _5_Bit_Manipulation;

/*
LeetCode – Sum of Two Integers (Java)

Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.


Example:
Given a = 1 and b = 2, return 3.


 */
public class Sum_of_Two_Integers {

    /*
    Java Solution

Given two numbers a and b, a&b returns the number formed by '1' bits on a and b. When it is left shifted by 1 bit, it is the carry.

For example, given a=101 and b=111 (in binary), the a&b=101. a&b << 1 = 1010.

a^b is the number formed by different bits of a and b. a&b=10.
     */

    public int getSum(int a, int b) {

        while(b!=0){
            int c = a&b;
            a=a^b;
            b=c<<1;
        }

        return a;
    }
}
