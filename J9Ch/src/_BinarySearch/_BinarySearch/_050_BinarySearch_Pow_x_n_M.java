package _BinarySearch._BinarySearch;
import java.util.*;
import org.junit.Test;
public class _050_BinarySearch_Pow_x_n_M {

//5 different choices when talk with interviewers
//    After reading some good sharing solutions, I'd like to show them together. You can see different ideas in the code.

//    nest myPow
    class Solution1{
        double myPow(double x, int n) {
            if(n<0) return 1/x * myPow(1/x, -(n+1));
            if(n==0) return 1;
            if(n==2) return x*x;
            if(n%2==0) return myPow( myPow(x, n/2), 2);
            else return x*myPow( myPow(x, n/2), 2);
        }
    }

//    double myPow
    class Solution2 {
        double myPow(double x, int n) {
            if(n==0) return 1;
            double t = myPow(x,n/2);
            if(n%2 == 0) return n<0 ? 1/x*t*t : x*t*t;
            else return t*t;
        }
    }

//    double x
    class Solution3{
        double myPow(double x, int n) {
            if(n==0) return 1;
            if(n<0){
                n = -n;
                x = 1/x;
            }
            return n%2==0 ? myPow(x*x, n/2) : x*myPow(x*x, n/2);
        }
    }
//    iterative one
    class Solution4{
        double myPow(double x, int n) {
            if(n==0) return 1;
            if(n<0) {
                n = -n;
                x = 1/x;
            }
            double ans = 1;
            while(n>0){
                if(n % 1  == 1) ans *= x;
                x *= x;
                n >>= 1;
            }
            return ans;
        }
    }
//    bit operation

//-------------------------------------------------------------------------/////

    public class Solution6 {
        public double pow(double x, int m) {
            double temp = x;
            if (m == 0)
                return 1;
            temp = pow(x, m / 2);
            if (m % 2 == 0)
                return temp * temp;
            else {
                if (m > 0)
                    return x * temp * temp;
                else
                    return (temp * temp) / x;
            }

        }

    }
}
