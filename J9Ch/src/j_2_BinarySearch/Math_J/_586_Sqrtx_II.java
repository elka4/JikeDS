package j_2_BinarySearch.Math_J;

//
public class _586_Sqrtx_II {

    //Solution1 : 二分法
    public class _09Sqrtx_II_1 {
        /**
         * @param x a double
         * @return the square root of x
         */
        public double sqrt(double x) {
            // Write your code here
            double left = 0.0;
            double right = x;
            double eps = 1e-12;

            if(right < 1.0) {
                right = 1.0;
            }

            while(right - left > eps) {
                // 二分浮点数 和二分整数不同
                // 一般都有一个精度的要求 譬如这题就是要求小数点后八位
                // 也就是只要我们二分的结果达到了这个精度的要求就可以
                // 所以 需要让 right 和 left 小于一个我们事先设定好的精度值 eps
                // 一般eps的设定1e-8,因为这题的要求是到1e-8,所以我把精度调到了1e-12
                // 最后 选择 left 或 right 作为一个结果即可
                double mid = (right + left) / 2;
                if(mid * mid < x) {
                    left = mid;
                }
                else {
                    right = mid;
                }
            }

            return left;
        }
    }

    //Solution2 : 牛顿法
    public class _09Sqrtx_II_2 {
        /**
         * @param x a double
         * @return the square root of x
         */
        public double sqrt(double x) {
            // Write your code here
            double res = 1.0;
            double eps = 1e-12;

            while(Math.abs(res * res - x) > eps) {
                res = (res + x / res) / 2;
            }

            return res;
        }
    }
}
/*
Implement double sqrt(double x) and x >= 0.

Compute and return the square root of x.
 */