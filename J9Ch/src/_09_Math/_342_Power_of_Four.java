package _09_Math;

import org.junit.Test;

//  342. Power of Four
//  https://leetcode.com/problems/power-of-four/description/
//  5:5
//  2的次方数实在太有特点，最高位为1，其他位均为0
//  4最高位是1，而且后面的0都是偶数个
public class _342_Power_of_Four {
//-----------------------------------------------------------------------
    public boolean isPowerOfFour(int n) {
        if(n==0) return false;

        return n == Math.pow(4, Math.round(Math.log(n)/Math.log(4)));
    }

    class Solution {
        public boolean isPowerOfFour(int n) {
            return (Math.log10(n) / Math.log10(4)) % 1 == 0;

        }
    }
//-----------------------------------------------------------------------
    //1
    //Java Solution 1 - Naive Iteration
    public boolean isPowerOfFour1(int num) {
        while(num>0){
            if(num==1){
                return true;
            }
            if(num%4!=0){
                return false;
            }else{
                num=num/4;
            }
        }
        return false;
    }

//-----------------------------------------------------------------------
    //2
    //Java Solution 2 - Bit Manipulation
    public boolean isPowerOfFour2(int num) {
        int count0=0;
        int count1=0;

        while(num>0){
            if((num&1)==1){
                count1++;
            }else{
                count0++;
            }

            num>>=1;
        }

        return count1==1 && (count0%2==0);
    }

//-----------------------------------------------------------------------
    //3
    //Java Solution 3 - Math Equation
    //We can use the following formula to solve this problem without using recursion/iteration.
    //Power of Four
    public boolean isPowerOfFour3(int num) {
        if(num==0) return false;

        int pow = (int) (Math.log(num) / Math.log(4));
        if(num==Math.pow(4, pow)){
            return true;
        }else{
            return false;
        }
    }

//-----------------------------------------------------------------------
    //Java one-line Solution by bit operation
    public class Solution4 {
        public boolean isPowerOfFour(int num) {
            return (num >0 && Integer.bitCount(num) == 1 &&
                    ((num & 0xAAAAAAAA) == 0));
        }
    }
    @Test
    public void test4_(){
        System.out.println(Integer.toBinaryString(0xAAAAAAAA));
    }//10101010101010101010101010101010

    @Test
    public void test4_2(){
        for (int i = 1; i < 100; i++) {
            System.out.println(Math.round(Math.pow(4, i)));
            System.out.println(Integer.toBinaryString((int)
                    Math.round(Math.pow(4, i))));
            System.out.println("------------------------");
        }
    }

//-----------------------------------------------------------------------
public class Solution5 {
    public boolean isPowerOfFour(int num) {
        return  (num >= 1) && (Integer.bitCount(num) == 1) &&
                ( (num & 1431655765)== num);

    }
}
/*
Here is what I did:

return  (num >= 1) && (Integer.bitCount(num) == 1) && ( (num & 1431655765)== num);
The power of 4 is 1 (0001), 4 (0100) , 16 (1 0000), 64 (100 0000), ...
However, the binary form of these numbers only includes one 1's bit. Using bit count we can verify if it has that one bit. But that one could be anywhere so we want to make sure that it is at the correct position. To do that, we can form a bit string that represents correct positions of all power of 4 which is this number 1431655765 (1010101010101010101010101010101).
By performing bitwise & on a given number we should get the same number back if it is a power of 4, otherwise, we would get some number other than the given number.

For example,

3 & 1431655765:

0000 0000 0000 0000 0000 0000 0000 0011
&
0101 0101 0101 0101 0101 0101 0101 0101

will result into 1:
0000 0000 0000 0000 0000 0000 0000 0001

So, the original number changed to a different number. But, if it was power of four.

For example, 16 = 4^2

16 & 1431655765:

0000 0000 0000 0000 0000 0000 0001 0000
&
0101 0101 0101 0101 0101 0101 0101 0101

the result would be 16 :
0000 0000 0000 0000 0000 0000 0001 0000

So, it remains the same.

Hopefully, that makes sense!
Feel free to leave your constructive criticism in comments!
 */
//-----------------------------------------------------------------------
}
/*
LeetCode – Power of Four (Java)

Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?
 */