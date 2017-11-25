package _BinarySearch.Math;

// leetcode 29. Divide Two Integers
//
//
public class _029_BinarySearch_Divide_Two_Integers_M {
//------------------------------------------------------------------------------
    //1
    //Clean Java solution with some comment.
    class Solution {
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
//------------------------------------------------------------------------------
    //2
    //  JAVA----------------Easy Version To Understand!!!!!!!!!!!
    class Solution2{
        public int divide(int dividend, int divisor) {

            if(dividend==0)
                return 0;
            int signal;
            if((dividend<0&&divisor>0)||(dividend>0&&divisor<0))
                signal=-1;
            else
                signal=1;
            //Math.abs(最小负数) 结果还是其本身. 在进行该运算前，要将其转化为long类型。
            long absDividend=Math.abs((long)dividend);
            long absDivisor=Math.abs((long)divisor);//
            long result=0;
            while(absDividend>=absDivisor){
                long tmp=absDivisor,count=1;;
                while(tmp<=absDividend){
                    tmp=tmp<<1;//这里可能溢出！！超出int表示的范围
                    count=count<<1;//这里可能溢出！！超出int表示的范围
                }
                tmp=tmp>>1;
                count=count>>1;
                result+=count;
                absDividend-=tmp;
            }
            if(signal==-1)
                return (int)(signal*result);
            else{
                if(result>Integer.MAX_VALUE)//溢出
                    return Integer.MAX_VALUE;
                else
                    return (int)result;
            }
        }
    }
//-----------------------------------------------------------------------------//
    //3
    // 9Ch
    public class Jiuzhang {
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
//------------------------------------------------------------------------------
}
/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
 */