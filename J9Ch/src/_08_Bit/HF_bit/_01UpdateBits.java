package _08_Bit.HF_bit;
import org.junit.Test;

//  http://lintcode.com/zh-cn/problem/update-bits/
//  Cracking the Coding Interview
//
public class _01UpdateBits {
//----------------------------------------------------------------------------

    /**
     *@param n, m: Two integer
     *@param i, j: Two bit positions
     *return: An integer
     */
    public int updateBits(int n, int m, int i, int j) {
        int max = ~0; /* All 1’s */

        // 1’s through position j, then 0’s
        if (j == 31){
            j = max;
        }
        else {//j个1， 1111111
            j = (1 << (j + 1)) - 1;
            System.out.printf("%8s%32s\n", "j: ", Integer.toBinaryString(j));
        }
        //11111111111111111111111110000000
        int left = max - j;
        System.out.printf("%8s%32s\n", "left: ", Integer.toBinaryString(left));

        //11
        // 1’s after position i
        int right = ((1 << i) - 1);
        System.out.printf("%8s%32s\n", "right: ", Integer.toBinaryString(right));

        // 1’s, with 0s between i and j
        //11111111111111111111111110000011
        int mask = left | right;
        System.out.printf("%8s%32s\n", "mask: ", Integer.toBinaryString(mask));

        // Clear i through j, then put m in there
        //10000000000
        //n & mask 把中间清零，i到j
        System.out.printf("%8s%32s\n", "n & mask: ", Integer.toBinaryString(n & mask));
        //1010100
        //左移i位
        System.out.printf("%8s%32s\n", "m << i: ", Integer.toBinaryString(m << i));

        return (n & mask) | (m << i);
    }

    @Test
    public void test1_1(){
        System.out.printf("%8s%32s\n", "n: ", Integer.toBinaryString(Integer.valueOf("10000000000", 2)));
        System.out.printf("%8s%32s\n", "m: ", Integer.toBinaryString(Integer.valueOf("10101", 2)));
        System.out.printf("%8s%32s\n", "result: ", Integer.toBinaryString(updateBits(
                Integer.valueOf("10000000000", 2),
                Integer.valueOf("10101", 2),
                2,6

        )));
    }
    @Test
    public void test1_2(){
        System.out.println(Integer.toBinaryString(Integer.valueOf("10000000000", 2)));
    }

    @Test
    public void test1_3(){
        System.out.printf("%8s%32s\n", "n: ", Integer.toBinaryString(Integer.valueOf("1111111111111", 2)));
        System.out.printf("%8s%32s\n", "m: ", Integer.toBinaryString(Integer.valueOf("00000", 2)));
        System.out.printf("%8s%32s\n", "result: ", Integer.toBinaryString(updateBits(
                Integer.valueOf("1111111111111", 2),
                Integer.valueOf("00000", 2),
                2,6

        )));
    }

//----------------------------------------------------------------------------

    public int updateBits2(int n, int m, int i, int j) {
        return ((~((((-1) << (31 - j)) >>> (31 - j + i)) << i)) & n) | (m << i);
    }
//----------------------------------------------------------------------------

}
/*--------------------------------------------------------------------
Given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to set all bits between i and j in N equal to M (e g , M becomes a substring of N located at i and starting at j)

 Notice

In the function, the numbers N and M will given in decimal, you should also return a decimal number.

Clarification
You can assume that the bits j through i have enough space to fit all of M. That is, if M=10011， you can assume that there are at least 5 bits between j and i. You would not, for example, have j=3 and i=2, because M could not fully fit between bit 3 and bit 2.

Example
Given N=(10000000000)2, M=(10101)2, i=2, j=6

return N=(10001010100)2
 */

/*--------------------------------------------------------------------
给出两个32位的整数N和M，以及两个二进制位的位置i和j。写一个方法来使得N中的第i到j位等于M（M会是N中从第i为开始到第j位的子串）
 */

/*--------------------------------------------------------------------
给出两个32位的整数N和M，以及两个二进制位的位置i和j。写一个方法来使得N中的第i到j位等于M（M会是N中从第i为开始到第j位的子串）

 注意事项

In the function, the numbers N and M will given in decimal, you should also return a decimal number.

您在真实的面试中是否遇到过这个题？ Yes
说明
You can assume that the bits j through i have enough space to fit all of M. That is, if M=10011， you can assume that there are at least 5 bits between j and i. You would not, for example, have j=3 and i=2, because M could not fully fit between bit 3 and bit 2.

样例
给出N = (10000000000)2，M = (10101)2， i = 2, j = 6

返回 N = (10001010100)2

挑战
最少的操作次数是多少？


困难 下一个稀疏数
困难 二进制表示

 */