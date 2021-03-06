package _09_Math;

//  372. Super Pow
//  https://leetcode.com/problems/super-pow/
//  Math
//  Pow()
//
public class _372_Super_Pow {
//-----------------------------------------------------------------------

    public int superPow(int a, int[] b) {
        int result=1;

        for(int i=0; i<b.length; i++){
            // result^10
            result = helper(result, 10)*helper(a, b[i])%1337;
        }

        return result;
    }

    public int helper(int x, int n){
        if(n==0)
            return 1;
        if(n==1)
            return x%1337;

        return helper(x%1337, n/2)*helper(x%1337,n-n/2)%1337;
    }

//-----------------------------------------------------------------------
}
/*
LeetCode – Super Pow (Java)

Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.
-----------------------------------------------------------------------
Example1:

a = 2
b = [3]

Result: 8
-----------------------------------------------------------------------
Example2:

a = 2
b = [1,0]

Result: 1024
-----------------------------------------------------------------------
 */