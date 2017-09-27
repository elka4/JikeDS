package Classification.Bit_Manipulation_26;
import org.junit.Test;

import java.util.*;

public class b_338_Counting_Bits {
//https://leetcode.com/problems/counting-bits/tabs/solutionZhu

    //Approach #1 Pop Count [Accepted]
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 0; i <= num; ++i)
            ans[i] = popcount(i);
        return ans;
    }
    private int popcount(int x) {
        int count;
        for (count = 0; x != 0; ++count){
            System.out.println("x: " + Integer.toBinaryString(x));
            System.out.println("x - 1: " + Integer.toBinaryString(x - 1));
            //zeroing out the least significant nonzero bit
            x &= x - 1;
            System.out.println("x &= x - 1: " + Integer.toBinaryString(x));

        }
        System.out.println("------------------------");

        return count;
    }
    @Test
    public void test00(){
        int a = 3;
        System.out.println(popcount(a));
    }

    @Test
    public void test01(){
        int a = 3;
        System.out.println(Integer.toBinaryString(a));
        System.out.println("=======================");
        /*
        1: 0 1
        2: 0 1 1
        3: 0 1 1 2

         */
        for (int i:countBits(a)) {

            System.out.println(i);
        }

    }


////////////////////////////////////////////////////////////////////////

//Approach #2 DP + Most Significant Bit [Accepted]
    public int[] countBits2(int num) {
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

////////////////////////////////////////////////////////////////////////


//Approach #3 DP + Least Significant Bit [Accepted]
    public int[] countBits3(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            // x / 2 is x >> 1 and x % 2 is x & 1
            ans[i] = ans[i >> 1] + (i & 1);
        return ans;
    }


////////////////////////////////////////////////////////////////////////


//Approach #4 DP + Last Set Bit [Accepted]
    public int[] countBits4(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; ++i)
            ans[i] = ans[i & (i - 1)] + 1;
        return ans;
    }


////////////////////////////////////////////////////////////////////////


}

/*
Given a non negative integer number num. For every numbers i in the range
 0 ≤ i ≤ num calculate the number of 1's in their binary representation
 and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)).
 But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like
__builtin_popcount in c++ or in any other language.

 */