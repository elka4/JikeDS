package _09_Math;


//  172. Factorial Trailing Zeroes
//  https://leetcode.com/problems/factorial-trailing-zeroes/description/
//  Number of Digit One
//
public class _172_Factorial_Trailing_Zeroes {
//-----------------------------------------------------------------------
    /*
    My one-line solutions in 3 languages
This question is pretty straightforward.

Because all trailing 0 is from factors 5 * 2.

But sometimes one number may have several 5 factors, for example, 25 have two 5 factors, 125 have three 5 factors. In the n! operation, factors 2 is always ample. So we just count how many 5 factors in all number from 1 to n.
     */
    class Solution {
        public int trailingZeroes(int n) {
            return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
        }
    }
//-----------------------------------------------------------------------

/*    O(log_5(N)) solution, java
    O(log_5(N)) (base 5!) is faster than any polynomial. You need no more than 14 iterations to get the result. You just need to count how many times 5 appears in n! during multiplication in different forms: 5, 25, 125, 625, ... . when any 5 is multiplied by 2 (we have many of them) then we get 0 at the end. That's it.*/

    public class Solution2 {
        public int trailingZeroes(int n) {
            int r = 0;
            while (n > 0) {
                n /= 5;
                r += n;
            }
            return r;
        }
    }

//-----------------------------------------------------------------------

    public int trailingZeroes(int n) {
        if (n < 0)
            return -1;

        int count = 0;
        for (long i = 5; n / i >= 1; i *= 5) {
            count += n / i;
        }

        return count;
    }

//-----------------------------------------------------------------------
}
/*
Given an integer n, return the number of trailing zeroes in n!.

Note: Your solution should be in logarithmic time complexity.

Similar Questions
Number of Digit One
 */