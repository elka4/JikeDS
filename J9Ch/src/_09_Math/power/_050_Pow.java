package _09_Math.power;


//  50. Pow(x, n)
//  https://leetcode.com/problems/powx-n/description/
//
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