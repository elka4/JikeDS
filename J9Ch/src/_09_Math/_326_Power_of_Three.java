package _09_Math;

//  326. Power of Three
//  https://leetcode.com/problems/power-of-three/description/
//
//
//  8：3, 8
//不管進階的話跟LeetCode 231. Power of Two解法是一模一樣的。 因此只要不斷的把這個數除3，如果發現餘數不是0，就可以判斷這個數不是3的次方數
//
public class _326_Power_of_Three {
//-----------------------------------------------------------------------
    //https://leetcode.com/problems/power-of-three/solution/
    public boolean isPowerOfThree(int n) {
        if(n==0) return false;
        return n == Math.pow(3, Math.round(Math.log(n)/Math.log(3)));
    }
//-----------------------------------------------------------------------
    //1
    //Approach #1 Loop Iteration [Accepted]
    public class Solution1 {
        public boolean isPowerOfThree(int n) {
            if (n < 1) {
                return false;
            }
            while (n % 3 == 0) {
                n /= 3;
            }
            return n == 1;
        }
    }

//-----------------------------------------------------------------------
    //2
    //Approach #2 Base Conversion [Accepted]
    public class Solution2 {
        public boolean isPowerOfThree(int n) {

            return Integer.toString(n, 3).matches("^10*$");
        }
    }

//-----------------------------------------------------------------------
    //3
    //Approach #3 Mathematics [Accepted]
    public class Solution3 {
        public boolean isPowerOfThree(int n) {

            return (Math.log10(n) / Math.log10(3)) % 1 == 0;
        }
    }
//-----------------------------------------------------------------------
    //4
    class Solution33 {
        public boolean isPowerOfThree(int n) {
            return n > 0 && Math.pow(3, Math.round(Math.log(n) / Math.log(3))) == n;
        }
    }

    class Solution {
        public boolean isPowerOfThree(int n) {
            return (Math.log10(n) / Math.log10(3)) % 1 == 0;

        }
    }
//-----------------------------------------------------------------------
    //5
    //Approach #4 Integer Limitations [Accepted]
    /*
    题目中的Follow up让我们不用循环，那么有一个投机取巧的方法，由于输入是int，正数范围是0-231，在此范围中允许的最大的3的次方数为319=1162261467，那么我们只要看这个数能否被n整除即可，参见代码如下：
     */
    public class Solution4 {
        public boolean isPowerOfThree(int n) {
            return n > 0 && 1162261467 % n == 0;
        }
    }
//        return (n > 0 && int(log10(n) / log10(3)) - log10(n) / log10(3) == 0);

//-----------------------------------------------------------------------
    //6
    //Java Solution 1 - Iteration
    public boolean isPowerOfThree5(int n) {
        if(n==1) return true;

        boolean result = false;

        while(n>0){
            int m = n%3;
            if(m==0){
                n=n/3;
                if(n==1)
                    return true;
            }else{
                return false;
            }
        }

        return result;
    }

//-----------------------------------------------------------------------
    //7
    //Java Solution 2 - Recursion
    public boolean isPowerOfThree2(int n) {
        if(n==0)
            return false;

        if(n==1)
            return true;

        if(n>1)
            return n%3==0 && isPowerOfThree2(n/3);
        else
            return false;
    }
//-----------------------------------------------------------------------
    //8
    //Java Solution 3 - Math
    /*http://www.cnblogs.com/grandyang/p/5138212.html
    最后还有一种巧妙的方法，利用对数的换底公式来做，高中学过的换底公式为logab = logcb / logca，那么如果n是3的倍数，则log3n一定是整数，我们利用换底公式可以写为log3n = log10n / log103，注意这里一定要用10为底数，不能用自然数或者2为底数，否则当n=243时会出错，原因请看这个帖子。现在问题就变成了判断log10n / log103是否为整数，在c++中判断数字a是否为整数，我们可以用 a - int(a) == 0 来判断，参见代码如下：
     */
    public boolean isPowerOfThree3(int n) {
        if(n==0) return false;

        return n == Math.pow(3, Math.round(Math.log(n)/Math.log(3)));
    }

//-----------------------------------------------------------------------
}
/*
LeetCode – Power of Three (Java)

Given an integer, write a function to determine if it is a power of three.
 */

//判断给定整数是否是3的某次方。

