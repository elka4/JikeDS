package _08_Bit;
import java.util.*;
import org.junit.Test;


//  338. Counting Bits
//  https://leetcode.com/problems/counting-bits/description/
//
//  8:
public class _338_Bit_Counting_Bits_M {
//------------------------------------------------------------------------------
    //  https://leetcode.com/problems/counting-bits/solution/
//------------------------------------------------------------------------------
    //1
    //Approach #1 Pop Count [Accepted]
    public class Solution1 {
        public int[] countBits(int num) {
            int [ ] ans = new int [ num + 1 ];
            for (int i = 0; i <= num; ++i)
                ans[i] = popcount(i);
            return ans;
        }
        private int popcount(int x) { int count;
            for (count = 0; x != 0; ++count)
                x &= x - 1; //zeroing out the least significant nonzero bit
             return count;
        }
    }
//------------------------------------------------------------------------------
    //2
    //Approach #2 DP + Most Significant Bit [Accepted]
    public class Solution2 {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            int i = 0, b = 1;
            // [0, b) is calculated
            while (b <= num) {
                // generate [b, 2b) or [b, num) from [0, b)
                while(i < b && i + b <= num){
                    ans[i + b] = ans[i] + 1;
                    ++i;
                }
                i = 0;   // reset i
                b <<= 1; // b = 2b
            }
            return ans;
        }
    }
//------------------------------------------------------------------------------
    //3
    //Approach #3 DP + Least Significant Bit [Accepted]
    public class Solution3 {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int i = 1; i <= num; ++i)
                ans[i] = ans[i >> 1] + (i & 1);
            return ans;
        }
    }
    // x / 2 is x » 1 and x % 2 is x & 1

    //Approach #4 DP + Last Set Bit [Accepted]
    public class Solution4 {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int i = 1; i <= num; ++i)
                ans[i] = ans[i & (i - 1)] + 1;
            return ans;
        }
    }

//------------------------------------------------------------------------------
    //4
    //Three-Line Java Solution
    //An easy recurrence for this problem is f[i] = f[i / 2] + i % 2.

    public int[] countBits5(int num) {
        int[] f = new int[num + 1];
        for (int i=1; i<=num; i++) f[i] = f[i >> 1] + (i & 1);
        return f;
    }

//------------------------------------------------------------------------------
    //5
/*Simple Java O(n) solution using two pointers
    This uses the hint from the description about using ranges. Basically, the numbers in one range are equal to 1 plus all of the numbers in the ranges before it. If you write out the binary numbers, you can see that numbers 8-15 have the same pattern as 0-7 but with a 1 at the front.

    My logic was to copy the previous values (starting at 0) until a power of 2 was hit (new range), at which point we just reset the t pointer back to 0 to begin the new range.*/

    public int[] countBits6(int num) {
        int[] ret = new int[num+1];
        ret[0] = 0;
        int pow = 1;
        for(int i = 1, t = 0; i <= num; i++, t++) {
            if(i == pow) {
                pow *= 2;
                t = 0;
            }
            ret[i] = ret[t] + 1;
        }
        return ret;
    }

//------------------------------------------------------------------------------
    //6
/*    How we handle this question on interview [Thinking process + DP solution]
    Question:
    Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

    Thinking:

    We do not need check the input parameter, because the question has already mentioned that the number is non negative.

    How we do this? The first idea come up with is find the pattern or rules for the result. Therefore, we can get following pattern

    Index : 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15

    num : 0 1 1 2 1 2 2 3 1 2 2 3 2 3 3 4

    Do you find the pattern?

    Obviously, this is overlap sub problem, and we can come up the DP solution. For now, we need find the function to implement DP.

            dp[0] = 0;

    dp[1] = dp[0] + 1;

    dp[2] = dp[0] + 1;

    dp[3] = dp[1] +1;

    dp[4] = dp[0] + 1;

    dp[5] = dp[1] + 1;

    dp[6] = dp[2] + 1;

    dp[7] = dp[3] + 1;

    dp[8] = dp[0] + 1;
...

    This is the function we get, now we need find the other pattern for the function to get the general function. After we analyze the above function, we can get
    dp[0] = 0;

    dp[1] = dp[1-1] + 1;

    dp[2] = dp[2-2] + 1;

    dp[3] = dp[3-2] +1;

    dp[4] = dp[4-4] + 1;

    dp[5] = dp[5-4] + 1;

    dp[6] = dp[6-4] + 1;

    dp[7] = dp[7-4] + 1;

    dp[8] = dp[8-8] + 1;
..

    Obviously, we can find the pattern for above example, so now we get the general function

    dp[index] = dp[index - offset] + 1;

    Coding:*/

    public int[] countBits7(int num) {
        int result[] = new int[num + 1];
        int offset = 1;
        for (int index = 1; index < num + 1; ++index){
            if (offset * 2 == index){
                offset *= 2;
            }
            result[index] = result[index - offset] + 1;
        }
        return result;
    }
//------------------------------------------------------------------------------
    //7
        /*1. Naive Solution

    We can simply count bits for each number like the following:*/

    public int[] countBits(int num) {
        int[] result = new int[num+1];

        for(int i=0; i<=num; i++){
            result[i] = countEach(i);
        }

        return result;
    }

    public int countEach(int num) {
        int result = 0;

        while (num != 0) {
            if (num % 2 == 1) {
                result++;
            }
            num = num / 2;
        }

        return result;
    }
//------------------------------------------------------------------------------
    //8
    /*
    2. Improved Solution

    For number 2(10), 4(100), 8(1000), 16(10000), ..., the number
    of 1's is 1. Any other number can be converted to be 2^m + x.

    For example, 9=8+1, 10=8+2. The number of 1's for any other
    number is 1 + # of 1's in x.
     */

    //有点DP的感觉啊

    public int[] countBits2(int num) {
        int[] result = new int[num+1];

        int p = 1; //p tracks the index for number x
        int pow = 1;
        for(int i=1; i<=num; i++){
            if(i==pow){
                result[i] = 1;
                pow <<= 1;
                p = 1;
            }else{
                result[i] = result[p]+1;
                p++;
            }

        }

        return result;
    }

//------------------------------------------------------------------------------
}
/*
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
Credits:
 */

/*

 */