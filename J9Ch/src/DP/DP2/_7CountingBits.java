package DP.DP2;

//• 序列+位操作型动态规划

/*
f[i] = f[i>>1] + (i mod 2)
f[i] : i的二进制表示中有多少个1
f[i>>1]: i的二进制表示中去掉最后一位，剩下的1的个数
(i mod 2): i的二进制表示中最后一位

如果求有多少个0，就用（i + 1） % 2

-----------------------------------------------------------------------------------------------
 */

//• 序列+位操作型动态规划
//  338. Counting Bits
//  https://leetcode.com/problems/counting-bits/description/
//  7:
public class _7CountingBits {
//-------------------------------------------------------------------------------
    //1
    // 9Ch DP
    //序列，每个元素就是0 ≤ i ≤ num中一个数字， 这位置是不能变的呀，所以时序列型
    public int[] countBits(int num) {
        int[] f = new int[num + 1];
        f[0] = 0;

        for (int i = 1; i <= num; ++i) {
            f[i] = f[i >> 1] + (i % 2);
        }
        return f;
    }

//-------------------------------------------------------------------------------
    //2
    //Three-Line Java Solution
    public int[] countBits1(int num) {
        int[] f = new int[num + 1];
        for (int i=1; i<=num; i++)
            f[i] = f[i >> 1] + (i & 1);
        return f;
    }


//-------------------------------------------------------------------------------
    //3
    //DP
    public int[] countBits2(int num) {
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

//-------------------------------------------------------------------------------
    //4
    //Approach #1 Pop Count [Accepted]
    public class Solution {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int i = 0; i <= num; ++i)
                ans[i] = popcount(i);
            return ans;
        }
        private int popcount(int x) {
            int count;
            for (count = 0; x != 0; ++count)
                x &= x - 1; //zeroing out the least significant nonzero bit
            return count;
        }
    }

//-------------------------------------------------------------------------------
    //5
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

//-------------------------------------------------------------------------------
    //6
    //Approach #3 DP + Least Significant Bit [Accepted]
    public class Solution3 {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int i = 1; i <= num; ++i)
                ans[i] = ans[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
            return ans;
        }
    }
//-------------------------------------------------------------------------------
    //7
    //Approach #4 DP + Last Set Bit [Accepted]
    public class Solution4 {
        public int[] countBits(int num) {
            int[] ans = new int[num + 1];
            for (int i = 1; i <= num; ++i)
                ans[i] = ans[i & (i - 1)] + 1;
            return ans;
        }
    }

//-------------------------------------------------------------------------------
}
/*
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */