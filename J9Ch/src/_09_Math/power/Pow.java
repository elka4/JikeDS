package _06_Math.power;

/*
LeetCode – Pow(x, n)

Problem:

Implement pow(x, n).

This is a great example to illustrate how to solve a problem during a technical interview. The first and second solution exceeds time limit; the third and fourth are accepted.
 */
public class Pow {
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
}
