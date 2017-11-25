package _08_Bit.HF_bit;

import org.junit.Test;

//
//
//
public class _05FlipBits {
//------------------------------------------------------------------
    //1
    public int bitSwapRequired(int a, int b) {
        int count = 0;
         //c包含a和b不同的1; >>> 是为了处理负数。
        for (int c = a ^ b; c != 0; c = c >>> 1) {
            count += c & 1;
        }
        return count;
    }

    @Test
    public void test(){
        System.out.println(Integer.toBinaryString(Integer.valueOf("11111", 2)));
        System.out.println(Integer.toBinaryString(Integer.valueOf("01110", 2)));
        System.out.println(bitSwapRequired(Integer.valueOf("11111", 2),Integer.valueOf("01110", 2)));
        System.out.println("------------------------------------------------");
    }

    @Test
    public void test2(){
        System.out.println(Integer.toBinaryString(0));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(bitSwapRequired(0, -1));
        System.out.println("------------------------------------------------");
    }

//------------------------------------------------------------------

    //这个用count 1那题，计算a ^ b有多少个1，就是a和b有多少个不同。
    public int countOnes(int num) {
        int count = 0;
        while (num != 0) {
            num = num & (num - 1);
            count++;
        }
        return count;
    }

    public int bitSwapRequired2(int a, int b) {
        // write your code here
        return countOnes(a ^ b);
    }
//------------------------------------------------------------------
}
/*------------------------------------------------------------------
Determine the number of bits required to flip if you want to convert integer n to integer m.

Both n and m are 32-bit integers.

Example
Given n = 31 (11111), m = 14 (01110), return 2.
 */


/*/*------------------------------------------------------------------
181. 将整数A转换为B

如果要将整数A转换为B，需要改变多少个bit位？

 注意事项

Both n and m are 32-bit integers.

您在真实的面试中是否遇到过这个题？ Yes
样例
如把31转换为14，需要改变2个bit位。

(31)10=(11111)2

(14)10=(01110)2
 */