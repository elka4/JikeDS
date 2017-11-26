package _09_Math;

//  9. Palindrome Number
//  https://leetcode.com/problems/palindrome-number/description/
//  http://lintcode.com/en/problem/palindrome-number/
//  3:  1 完全按照String的操作来, 3 照搬_007_Reverse_Integer的method
public class _009_Palindrome_Number {
//-----------------------------------------------------------------------
    //1
    //https://leetcode.com/problems/palindrome-number/solution/
    //Approach #1 Revert half of the number [Accepted]
/*public class Solution {
    public bool IsPalindrome(int x) {
        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return x == revertedNumber || x == revertedNumber/10;
    }
}
*/
    class Solution1{
        public boolean isPalindrome(int x) {
            for (int i = 0,j = String.valueOf(x).length() - 1 ;
                 i <= String.valueOf(x).length()/2; i++ , j--){
                if (String.valueOf(x).charAt(i)!=String.valueOf(x).charAt(j)){
                    return false;
                }
            }
            return true;
        }
    }

//-----------------------------------------------------------------------
    //2
    public boolean isPalindrome(int x) {
        //negative numbers are not palindrome
        if (x < 0)
            return false;

        // initialize how many zeros
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }

        while (x != 0) {
            int left = x / div;
            int right = x % 10;

            if (left != right)
                return false;

            x = (x % div) / 10;
            div /= 100;
        }

        return true;
    }
//-----------------------------------------------------------------------
    //3
    //  9Ch
    public class Solution3 {
        /**
         * @param x a positive number
         * @return true if it's a palindrome or false
         */
        public boolean isPalindrome(int x) {
            if(x < 0) {
                return false;
            }
            return x == reverse(x);
        }

        public int reverse(int x) {
            int rst = 0;
            while(x != 0) {
                rst = rst * 10 + x % 10;
                x = x / 10;
            }
            return rst;
        }
    }
//-----------------------------------------------------------------------
}
/*-------------------------------------------------------------------------------------
LeetCode – Palindrome Number (Java)

Determine whether an integer is a palindrome. Do this without extra space.

Thoughts

Problems related with numbers are frequently solved by / and %. No need of extra space is required. This problem is similar with the Reverse Integer problem.

Note: no extra space here means do not convert the integer to string, since string will be a copy of the integer and take extra space. The space take by div, left, and right can be ignored.
 */

/*-------------------------------------------------------------------------------------
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output:  321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only hold integers within the 32-bit signed integer range. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
-------------------------------------------------------------------------------------
 */



/*
-------------------------------------------------------------------------------------
491. 回文数

判断一个正整数是不是回文数。

回文数的定义是，将这个数反转之后，得到的数仍然是同一个数。

 注意事项

给的数一定保证是32位正整数，但是反转之后的数就未必了。

您在真实的面试中是否遇到过这个题？ Yes
样例
11, 121, 1, 12321 这些是回文数。

23, 32, 1232 这些不是回文数。

标签
字符串处理 整数
相关题目
中等 回文链表 29 %
容易 有效回文串
-------------------------------------------------------------------------------------
 */