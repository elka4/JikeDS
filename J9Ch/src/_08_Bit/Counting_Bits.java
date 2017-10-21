package _5_Bit;

/*
LeetCode – Counting Bits (Java)

Given a non negative integer number num. For every numbers i
in the range 0 ≤ i ≤ num calculate the number of 1's in their
binary representation and return them as an array.

Example:

For num = 5 you should return [0,1,1,2,1,2].

 */

//返回0～m全部数字的二进制表示中1的数目

public class Counting_Bits {

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
//////////////////////////////////////?

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
}
