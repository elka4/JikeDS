package _5_Bit_Manipulation;

/*
LeetCode – Bitwise AND of Numbers Range (Java)

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive. For example, given the range [5, 7], you should return 4. Java Solution

The key to solve this problem is bitwise AND consecutive numbers. You can use the following example to walk through the code.

    8  4  2  1
---------------
5 | 0  1  0  1
6 | 0  1  1  0
7 | 0  1  1  1
 */
public class Bitwise_AND_of_Numbers_Range {
    public int rangeBitwiseAnd(int m, int n) {
        while (n > m) {
            n = n & n - 1;
        }
        return m & n;
    }

}
