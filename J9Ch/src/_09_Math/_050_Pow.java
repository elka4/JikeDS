package _09_Math;


//  50. Pow(x, n)
//  https://leetcode.com/problems/powx-n/description/
//  http://lintcode.com/zh-cn/problem/powx-n/
//  Math, Binary Search
//  Sqrt(x), Super Pow
//  9:9
public class _050_Pow {
//-----------------------------------------------------------------------
    public double myPow(double x, int n){
        if(n==0)
            return 1;

        if(n<0){
            return 1/myPow(x, -n);
        }

        double v = myPow(x, n/2);

        if(n%2==0){
            return v*v;
        }else{
            return v*v*x;
        }
    }
//-----------------------------------------------------------------------
    //Short and easy to understand solution
    public class Solution2 {
        public double pow(double x, int n) {
            if(n == 0)
                return 1;
            if(n<0){
                n = -n;
                x = 1/x;
            }
            return (n%2 == 0) ? pow(x*x, n/2) : x*pow(x*x, n/2);
        }
    }
//-----------------------------------------------------------------------

//    5 different choices when talk with interviewers
//    After reading some good sharing solutions, I'd like to show them together. You can see different ideas in the code.

    //nest myPow
    //AC
    class Solution3{
        double myPow(double x, int n) {
            if(n<0) return 1/x * myPow(1/x, -(n+1));
            if(n==0) return 1;
            if(n==2) return x*x;
            if(n%2==0) return myPow( myPow(x, n/2), 2);
            else return x*myPow( myPow(x, n/2), 2);
        }
    }

    //double myPow
    //AC
    class Solution4{
        double myPow(double x, int n) {
            if(n==0) return 1;
            double t = myPow(x,n/2);
            if(n%2 != 0) return n<0 ? 1/x*t*t : x*t*t;
            else return t*t;
        }
    }

    //double x
    //NOT
    class Solution5{
        double myPow(double x, int n) {
            if(n==0) return 1;
            if(n<0){
                n = -n;
                x = 1/x;
            }
            return n%2==0 ? myPow(x*x, n/2) : x*myPow(x*x, n/2);
        }
    }
    /*
    Input:
2.00000
-2147483648
Output:
∞
Expected:
0.00000
     */

    //iterative one
    //NOT
    class Solution6{
        double myPow(double x, int n) {
            if(n==0) return 1;
            if(n<0) {
                n = -n;
                x = 1/x;
            }
            double ans = 1;
            while(n>0){
                if((n&1) != 0) ans *= x;
                x *= x;
                n >>= 1;
            }
            return ans;
        }
    }
/*
Input:
2.00000
-2147483648
Output:
1.00000
Expected:
0.00000
 */
    //bit operation

//-----------------------------------------------------------------------

//    My first idea (after implementing the obligatory recursive solution) was to eat set bits from the power one-by-one, like this:
    class Solution7{
        public double myPow(double x, int n) {
            int m = n < 0 ? -n - 1 : n; // overflow protection
            double p = 1.0, q = x;
            for (int p2 = 1; m > 0; ) {
                int low = m & -m;
                while (p2 < low) {
                    q *= q;
                    p2 *= 2;
                }
                p *= q;
                m ^= low;
            }
            return n < 0 ? 1.0 / p / x : p;
        }
    }

//    This works in 1 ms, and the -n - 1 thing safely protects us from various Integer.MIN_VALUE troubles. After seeing other solutions, I decided to try to get rid of the inner while loop like this:
    class Solution8{
        public double myPow(double x, int n) {
            int m = n < 0 ? -n - 1 : n; // overflow protection
            double p = 1.0;
            for (double q = x; m > 0; m = m >>> 1) {
                if ((m & 1) != 0) {
                    p *= q;
                }
                q *= q;
            }
            return n < 0 ? (1.0 / p) / x : p;//最后看n的正负，如果为负，返回其倒数
        }
    }

//    This is more concise, and still runs for 1 ms. Funny thing, if I replace m >>> 1 with more clear m / 2, run time changes to 2 ms. And I thought the compiler should optimize it to essentially the same thing. Or maybe these run times are within the margin of error.
//-----------------------------------------------------------------------
    //9
    /*http://www.cnblogs.com/grandyang/p/4383775.html
    这道题还有迭代的解法，我们让i初始化为n，然后看i是否是2的倍数，是的话x乘以自己，否则res乘以x，i每次循环缩小一半，直到为0停止循环。最后看n的正负，如果为负，返回其倒数，参见代码如下：
     */
    //if it is "10001011",
    // then x^n = x^(1+2+8+128) = x^1 * x^2 * x^8 * x^128.关键是这个，
    class Solution9{
        public double myPow(double x, int n) {
            double res = 1.0;
            for (int i = n; i != 0; i /= 2) {//i每次循环缩小一半,直到为0停止循环
                if (i % 2 != 0) res *= x;//i是否是2的倍数，否则res乘以x
                x *= x;                 //是的话x乘以自己
            }
            return n < 0 ? 1 / res : res;//最后看n的正负，如果为负，返回其倒数
        }
    }
    /*  http://blog.csdn.net/u013270326/article/details/78524588
        http://www.voidcn.com/article/p-ediwpbki-zd.html
    3)除了上述方法，这里还提到了一种十分巧妙并且快速的方法，原文描述如下：
Consider the binary representation of n. For example, if it is "10001011", then x^n = x^(1+2+8+128) = x^1 * x^2 * x^8 * x^128. Thus, we don't want to loop n times to calculate x^n. To speed up, we loop through each bit, if the i-th bit is 1, then we add x^(1 << i) to the result.

Since (1 << i) is a power of 2, x^(1<<(i+1)) = square(x^(1<<i)).

The loop executes for a maximum of log(n) times.
该方法通过扫描n的二进制表示形式里不同位置上的1，来计算x的幂次

     */
    //这个不能过
    double my_pow(double x, int n) {
        if(n==0)
            return 1.0;
        if(n<0)
            return 1.0 / my_pow(x,-n);
        double ans = 1.0 ;
        for(; n>0; x *= x, n>>=1) {
            if((n&1)>0)
                ans *= x;
        }
        return ans;
    }
//-----------------------------------------------------------------------
}
/*
Implement pow(x, n).
-----------------------------------------------------------------------
Example 1:

Input: 2.00000, 10
Output: 1024.00000
-----------------------------------------------------------------------
Example 2:

Input: 2.10000, 3
Output: 9.26100
-----------------------------------------------------------------------

Problem:

Implement pow(x, n).

This is a great example to illustrate how to solve a problem during a technical interview. The first and second solution exceeds time limit; the third and fourth are accepted.
 */


/*
428. x的n次幂

 描述
 笔记
 数据
 评测
实现 pow(x,n)

 注意事项

不用担心精度，当答案和标准输出差绝对值小于1e-3时都算正确

您在真实的面试中是否遇到过这个题？ Yes
样例
Pow(2.1, 3) = 9.261
Pow(0, 1) = 0
Pow(1, 0) = 1
挑战
O(logn) time

标签
二分法 领英 分治法 数学 脸书
相关题目
容易 x的平方根 25 %
中等 快速幂
 */