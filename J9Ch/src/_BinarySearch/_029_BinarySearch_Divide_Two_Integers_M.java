package _BinarySearch;
import java.util.*;
import org.junit.Test;
public class _029_BinarySearch_Divide_Two_Integers_M {
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
////////////////////////////////////////////////////////////////////////////////
    //jiuzhang
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

}
/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
 */