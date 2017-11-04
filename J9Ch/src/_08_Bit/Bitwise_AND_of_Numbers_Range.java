package _08_Bit;

import org.junit.Test;

/*
LeetCode – Bitwise AND of Numbers Range (Java)

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise
 AND of all numbers in this range, inclusive.
 For example, given the range [5, 7], you should return 4.

 Java Solution

The key to solve this problem is bitwise AND consecutive numbers.
You can use the following example to walk through the code.

    8  4  2  1
---------------
5 | 0  1  0  1
6 | 0  1  1  0
7 | 0  1  1  1
 */

/*
m~n这个区间的数字做&操作

与相邻的数字左&操作之后，就会变小


 */
public class Bitwise_AND_of_Numbers_Range {
    public int rangeBitwiseAnd(int m, int n) {
        //退出条件就是n == m
        while (n > m) {
            n = n & n - 1;
        }
        //不遗忘m。但此时n == m，所以其实可以直接返回n。
        return m & n;
    }

    @Test
    public void test01(){
        System.out.println(rangeBitwiseAnd(5,7));
    }




    /*
    last bit of (odd number & even number) is 0.
    when m != n, There is at least an odd number and an even number,
    so the last bit position result is 0.
    Move m and n rigth a position.
     */
    public int rangeBitwiseAnd2(int m, int n) {
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

//////////////////////////////////////////////

    public int rangeBitwiseAnd3(int m, int n) {
        while(m<n) n = n & (n-1);
        return n;
    }

}
