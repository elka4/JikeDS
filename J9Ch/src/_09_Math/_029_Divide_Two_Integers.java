package _09_Math;


//  29. Divide Two Integers
//  https://leetcode.com/problems/divide-two-integers/description/
//  http://www.lintcode.com/zh-cn/problem/divide-two-integers/
//  4:4
public class _029_Divide_Two_Integers {
//-----------------------------------------------------------------------
    //1
    //Clean Java solution with some comment.
    class Solution1{
        public int divide(int dividend, int divisor) {
            //Reduce the problem to positive long integer to make it easier.
            //Use long to avoid integer overflow cases.
            int sign = 1;
            if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
                sign = -1;
            long ldividend = Math.abs((long) dividend);
            long ldivisor = Math.abs((long) divisor);

            //Take care the edge cases.
            if (ldivisor == 0) return Integer.MAX_VALUE;
            if ((ldividend == 0) || (ldividend < ldivisor))	return 0;

            long lans = ldivide(ldividend, ldivisor);

            int ans;
            if (lans > Integer.MAX_VALUE){ //Handle overflow.
                ans = (sign == 1)? Integer.MAX_VALUE : Integer.MIN_VALUE;
            } else {
                ans = (int) (sign * lans);
            }
            return ans;
        }

        private long ldivide(long ldividend, long ldivisor) {
            // Recursion exit condition
            if (ldividend < ldivisor) return 0;

            //  Find the largest multiple so that (divisor * multiple <= dividend),
            //  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
            //  Think this as a binary search.
            long sum = ldivisor;
            long multiple = 1;
            while ((sum+sum) <= ldividend) {
                sum += sum;
                multiple += multiple;
            }
            //Look for additional value for the multiple from the reminder (dividend - sum) recursively.
            return multiple + ldivide(ldividend - sum, ldivisor);
        }
    }
//-----------------------------------------------------------------------
    //2
    class Solution2{
        public int divide(int dividend, int divisor) {
            //handle special cases
            if(divisor==0) return Integer.MAX_VALUE;
            if(divisor==-1 && dividend == Integer.MIN_VALUE)
                return Integer.MAX_VALUE;

            //get positive values
            long pDividend = Math.abs((long)dividend);
            long pDivisor = Math.abs((long)divisor);

            int result = 0;
            while(pDividend>=pDivisor){
                //calculate number of left shifts
                int numShift = 0;
                while(pDividend>=(pDivisor<<numShift)){
                    numShift++;
                }

                //dividend minus the largest shifted divisor
                result += 1<<(numShift-1);
                pDividend -= (pDivisor<<(numShift-1));
            }

            if((dividend>0 && divisor>0) || (dividend<0 && divisor<0)){
                return result;
            }else{
                return -result;
            }
        }
    }

//-----------------------------------------------------------------------
    //3
    // 9Ch
    public class Solution3 {
        /**
         * @param dividend the dividend
         * @param divisor the divisor
         * @return the result
         */
        public int divide(int dividend, int divisor) {
            if (divisor == 0) {
                return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            if (dividend == 0) {
                return 0;
            }

            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }

            boolean isNegative = (dividend < 0 && divisor > 0) ||
                    (dividend > 0 && divisor < 0);

            long a = Math.abs((long)dividend);
            long b = Math.abs((long)divisor);
            int result = 0;
            while(a >= b){
                int shift = 0;
                while(a >= (b << shift)){
                    shift++;
                }
                a -= b << (shift - 1);
                result += 1 << (shift - 1);
            }
            return isNegative? -result: result;
        }
    }
//-----------------------------------------------------------------------
    //4
    /*  http://blog.csdn.net/linhuanmars/article/details/20024907
    原题链接: http://oj.leetcode.com/problems/divide-two-integers/
这道题属于数值处理的题目，对于整数处理的问题，在Reverse Integer中我有提到过，比较重要的注意点在于符号和处理越界的问题。对于这道题目，因为不能用乘除法和取余运算，我们只能使用位运算和加减法。比较直接的方法是用被除数一直减去除数，直到为0。这种方法的迭代次数是结果的大小，即比如结果为n，算法复杂度是O(n)。
那么有没有办法优化呢？ 这个我们就得使用位运算。我们知道任何一个整数可以表示成以2的幂为底的一组基的线性组合，即
num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。基于以上这个公式以及左移一位相当于乘以2，我们先让除数左移直到大于被除数之前得到一个最大的基。然后接下来我们每次尝试减去这个基，如果可以则结果增加加2^k,然后基继续右移迭代，直到基为0为止。因为这个方法的迭代次数是按2的幂直到超过结果，所以时间复杂度为O(logn)。代码如下：
     */

    class Solution4{
        public int divide(int dividend, int divisor) {
            if(divisor == 0) {
                return Integer.MAX_VALUE;
            }
            boolean isNeg = (dividend^divisor)>>>31 == 1;
            int res = 0;

            if(dividend == Integer.MIN_VALUE) {
                dividend += Math.abs(divisor);
                if(divisor == -1) {
                    return Integer.MAX_VALUE;
                }
                res++;
            }
            if(divisor == Integer.MIN_VALUE) {
                return res;
            }
            dividend = Math.abs(dividend);
            divisor = Math.abs(divisor);
            int digit = 0;
            //我们先让除数左移直到大于被除数之前得到一个最大的基
            while(divisor <= (dividend>>1)) {
                divisor <<= 1;//divisor = divisor << 1;
                digit++;
            }

            while(digit>=0) {//直到基为0为止
                if(dividend >= divisor){
                    res += 1<<digit;//如果可以则结果增加加2^k
                    dividend -= divisor;//每次尝试减去这个基
                }
                divisor >>= 1;  //divisor = divisor >> 1;
                digit--;
            }
            return isNeg?-res:res;
        }
    }

    /*
    这种数值处理的题目在面试中还是比较常见的，类似的题目有Sqrt(x)，Pow(x, n)等。上述方法在其他整数处理的题目中也可以用到，大家尽量熟悉实现这些问题。
     */
//-----------------------------------------------------------------------
}
/*
LeetCode – Divide Two Integers (Java)

Divide two integers without using multiplication, division and mod operator. If it is overflow, return MAX_INT.

Analysis

This problem can be solved based on the fact that any number can be converted to the format of the following:

num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n
The time complexity is O(logn).
-----------------------------------------------------------------------
 */


/*
414. 两个整数相除

将两个整数相除，要求不使用乘法、除法和 mod 运算符。

如果溢出，返回 2147483647 。

样例
给定被除数 = 100 ，除数 = 9，返回 11。

标签
二分法
相关题目
容易 加一 31 %
容易 二进制求和
-----------------------------------------------------------------------
 */