package _09_Math;

//  7. Reverse Integer
//  https://leetcode.com/problems/reverse-integer/description/
//  http://www.lintcode.com/zh-cn/problem/reverse-integer/
//  _009_Palindrome_Number
//  4:3
public class _007_Reverse_Integer {
//-----------------------------------------------------------------------
    //1
    //https://www.programcreek.com/2012/12/leetcode-reverse-integer/
/*
1. Naive Method

We can convert the integer to a string/char array, reverse the order, and convert the string/char array back to an integer. However, this will require extra space for the string. It doesn't seem to be the right way, if you come with such a solution.
*/
//-----------------------------------------------------------------------
    //2
    /*
    2. Efficient Approach

    Actually, this can be done by using the following code.
    */
    public int reverse(int x) {
        //flag marks if x is negative
        boolean flag = false;
        if (x < 0) {
            x = 0 - x;
            flag = true;
        }

        int res = 0;
        int p = x;

        while (p > 0) {
            int mod = p % 10;
            p = p / 10;
            res = res * 10 + mod;
        }

        if (flag) {
            res = 0 - res;
        }

        return res;
    }
//-----------------------------------------------------------------------
    //3
    /*
    3. Succinct Solution

    This solution is from Sherry, it is succinct and it is pretty.
    */
    public int reverse2(int x) {
        int rev = 0;
        while(x != 0){
            rev = rev*10 + x%10; //x%10取得最右位
            x = x/10;            //x/10去掉最右位
        }

        return rev;
    }

//-----------------------------------------------------------------------
    //4
    public class Solution4 {
        /**
         * @param n the integer to be reversed
         * @return the reversed integer
         */
        public int reverseInteger(int n) {
            int reversed_n = 0;

            while (n != 0) {
                int temp = reversed_n * 10 + n % 10;
                n = n / 10;
                if (temp / 10 != reversed_n) {
                    reversed_n = 0;
                    break;
                }
                reversed_n = temp;
            }
            return reversed_n;
        }
    }
//-----------------------------------------------------------------------

//-----------------------------------------------------------------------
}
/*
LeetCode – Reverse Integer

LeetCode - Reverse Integer:

Reverse digits of an integer.
Example1: x = 123, return 321
Example2: x = -123, return -321
 */