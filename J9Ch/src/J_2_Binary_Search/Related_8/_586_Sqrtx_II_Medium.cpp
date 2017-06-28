/**
586
Sqrt(x) II
*/


class Solution {
public:
    /**
     * @param x a double
     * @return the square root of x
     */
    double sqrt(double x) {
        // Write your code here
        double left = 0.0;
        double right = x;
        double eps = 1e-12;

        if(right < 1.0) {
            right = 1.0;
        }

        while(right - left > eps) {
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
};