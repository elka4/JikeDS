package HF.HF5_Math_Graphic_Bit;

public class _5Pow {
    public class Solution {
        /**
         * @param x the base number
         * @param n the power number
         * @return the result
         */
        public double myPow(double x, int n) {
            // Write your code here
            if (n == 0) {
                return 1;
            }

            if (n == 1) {
                return x;
            }

            boolean isNegative = false;
            if (n < 0) {
                isNegative = true;
            }

            int k = n / 2;
            int l = n - k * 2;
            double t1 = myPow(x, Math.abs(k));
            double t2 = myPow(x, Math.abs(l));
            if (isNegative) {
                return 1 / (t1 * t1 * t2);
            } else {
                return t1 * t1 * t2;
            }
        }
    }

////////////////////////////////////////////////

    // version: 高频题班 & 位运算版
    /**
     * @param x the base number
     * @param n the power number
     * @return the result
     */
    public double myPow(double x, int n) {
        // Write your code here
        boolean isNegative = false;
        if (n < 0) {
            x = 1 / x;
            isNegative = true;
            n = -(n + 1); // Avoid overflow when n == MIN_VALUE
        }

        double ans = 1, tmp = x;

        while (n != 0) {
            if (n % 2 == 1) {
                ans *= tmp;
            }
            tmp *= tmp;
            n /= 2;
        }

        if (isNegative) {
            ans *= x;
        }
        return ans;
    }
}
/*
实现 pow(x,n)
Pow(2.1, 3) = 9.261
Pow(0, 1) = 0
Pow(1, 0) = 1

 */