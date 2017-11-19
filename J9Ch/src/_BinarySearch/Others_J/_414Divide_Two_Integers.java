package _BinarySearch.Others_J;

//  http://lintcode.com/zh-cn/problem/divide-two-integers/

// leetcode 29. Divide Two Integers
//  https://leetcode.com/problems/divide-two-integers/description/
public class _414Divide_Two_Integers {
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

    //Accepted Java solution with comments.
    class Solution2{
        public int divide(int dividend, int divisor) {
            long result = divideLong(dividend, divisor);
            return result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)result;
        }

        // It's easy to handle edge cases when
        // operate with long numbers rather than int
        public long divideLong(long dividend, long divisor) {

            // Remember the sign
            boolean negative = dividend < 0 != divisor < 0;

            // Make dividend and divisor unsign
            if (dividend < 0) dividend = -dividend;
            if (divisor < 0) divisor = -divisor;

            // Return if nothing to divide
            if (dividend < divisor) return 0;

            // Sum divisor 2, 4, 8, 16, 32 .... times
            long sum = divisor;
            long divide = 1;
            while ((sum+sum) <= dividend) {
                sum += sum;
                divide += divide;
            }

            // Make a recursive call for (devided-sum) and add it to the result
            return negative ? -(divide + divideLong((dividend-sum), divisor)) :
                    (divide + divideLong((dividend-sum), divisor));
        }
    }
    /*
    Nice solution, it's really easy to understand!
I modified your codes a little bit though, to avoid checking whether it is negative in the recursive calls.
     */
    class Solution3{
        public int divide(int dividend, int divisor) {
            if (dividend == 0 || divisor == 0) return 0;
            long d1 = dividend, d2 = divisor;
            long result = divideLong(Math.abs(d1), Math.abs(d2));
            result = d1 * d2 < 0 ? -result : result;
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return Integer.MAX_VALUE;
            return (int) result;
        }

        private long divideLong(long dividend, long divisor) {
            if (dividend < divisor) return 0;
            long sum = divisor, divideTimes = 1;
            while (sum + sum <= dividend) {
                sum += sum;
                divideTimes += divideTimes;
            }
            return divideTimes + divideLong(dividend - sum, divisor);
        }
    }


    //No Use of Long Java Solution
    /*
    Integer.MIN_VALUE as dividend is really troublesome. Thus, I turn everything to negative value and keep finding closest 1,2,4,8... multiples and recursive on rest. The only case that will cause overflow is Integer.MIN_VALUE / -1, so I list it alone as an edge case.
     */
    public class Solution4 {
        public int divide(int dividend, int divisor) {
            if(dividend==Integer.MIN_VALUE && divisor==-1) return Integer.MAX_VALUE;
            if(dividend > 0 && divisor > 0) return divideHelper(-dividend, -divisor);
            else if(dividend > 0) return -divideHelper(-dividend,divisor);
            else if(divisor > 0) return -divideHelper(dividend,-divisor);
            else return divideHelper(dividend, divisor);
        }

        private int divideHelper(int dividend, int divisor){
            // base case
            if(divisor < dividend) return 0;
            // get highest digit of divisor
            int cur = 0, res = 0;
            while((divisor << cur) >= dividend && divisor << cur < 0 && cur < 31) cur++;
            res = dividend - (divisor << cur-1);
            if(res > divisor) return 1 << cur-1;
            return (1 << cur-1)+divide(res, divisor);
        }
    }

    class Solution5{
        //        Using the logic to convert numbers to negative and bitwise operation,
        //        Iterative solution:
        public int divide(int dividend, int divisor) {
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }

            if (dividend > 0 && divisor > 0) {
                return divideHelper(-dividend, -divisor);
            } else if (dividend > 0) {
                return -divideHelper(-dividend, divisor);
            }
            else if(divisor > 0) {
                return -divideHelper(dividend, -divisor);
            }
            else {
                return divideHelper(dividend, divisor);
            }
        }


        private int divideHelper(int dividend, int divisor) {
            int result = 0;
            int currentDivisor = divisor;
            while(true) {
                if(dividend > divisor) {
                    break;
                }
                int temp = 1;
                while(dividend <= currentDivisor << 1 && currentDivisor << 1 < 0) {
                    temp = temp << 1;
                    currentDivisor = currentDivisor << 1;
                }
                dividend -= currentDivisor;
                result += temp;
                currentDivisor = divisor;
            }
            return result;
        }
    }

    //Very detailed step-by-step explanation (Java solution)
    //  https://discuss.leetcode.com/topic/45980/very-detailed-step-by-step-explanation-java-solution/4
    class Solution6{
        public int divide(int dividend, int divisor) {
            boolean isNegative =
                    (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0) ?
                    true : false;
            long absDividend = Math.abs((long) dividend);
            long absDivisor = Math.abs((long) divisor);
            long result = 0;
            while(absDividend >= absDivisor){
                long tmp = absDivisor, count = 1;
                while(tmp <= absDividend){
                    tmp <<= 1;
                    count <<= 1;
                }
                result += count >> 1;
                absDividend -= tmp >> 1;
            }
            return  isNegative ? (int) ~result + 1 : result > Integer.MAX_VALUE ?
                    Integer.MAX_VALUE : (int) result;
        }
    }


//-------------------------------------------------------------------------///
    //jiuzhang
    /**
     * @param dividend the dividend
     * @param divisor the divisor
     * @return the result
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return dividend >= 0? Integer.MAX_VALUE : Integer.MIN_VALUE;
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
/*
将两个整数相除，要求不使用乘法、除法和 mod 运算符。

如果溢出，返回 2147483647 。

样例
给定被除数 = 100 ，除数 = 9，返回 11。
 */

/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
 */