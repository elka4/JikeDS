package DP_Classified._2Sequence;

import org.junit.Test;

//Counting Bits
public class _7CountingBits {
    //leetcode
    //Three-Line Java Solution
    public int[] countBits(int num) {
        int[] f = new int[num + 1];
        for (int i=1; i<=num; i++) {
            f[i] = f[i >> 1] + (i & 1);
        }
        return f;
    }

    @Test
    public void test01(){
        int[] result = countBits(5);
        for (int i:result) {
            System.out.println(i);
        }

    }

//---------------------------------/
    //DP
    //https://leetcode.com/problems/counting-bits/discuss/
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

    //leetcode
    //Approach #1 Pop Count [Accepted]
    public int[] countBits3(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; ++i)
            ans[i] = popcount3(i);
        return ans;
    }
    private int popcount3(int x) {
        int count;
        for (count = 0; x != 0; ++count)
            x &= x - 1; //zeroing out the least significant nonzero bit
        return count;
    }



//-------------------------------------------------------------------------------

    //Approach #2 DP + Most Significant Bit [Accepted]
    public int[] countBits4(int num) {
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


//-------------------------------------------------------------------------------

    //Approach #3 DP + Least Significant Bit [Accepted]
    public int[] countBits5(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            ans[i] = ans[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
        return ans;
    }



//-------------------------------------------------------------------------------

    //Approach #4 DP + Last Set Bit [Accepted]
    public int[] countBits6(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            ans[i] = ans[i & (i - 1)] + 1;
        return ans;
    }



//-------------------------------------------------------------------------------

}
/*
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = c you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */