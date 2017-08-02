package _6_Math.power;

/*
LeetCode – Power of Two (Java)

Given an integer, write a function to determine if it is a power of two.

Analysis

If a number is power of 2, it's binary form should be 10...0. So if we right shift a bit of the number and then left shift a bit, the value should be the same when the number >= 10(i.e.,2).


 */
public class Power_of_Two {
    //Java Solution 1

    public boolean isPowerOfTwo(int n) {
        if(n<=0)
            return false;

        while(n>2){
            int t = n>>1;
            int c = t<<1;

            if(n-c != 0)
                return false;

            n = n>>1;
        }

        return true;
    }
    //Java Solution 2

    //If a number is power of 2, then its highly bit is 1 and there is only one 1. Therefore, n & (n-1) is 0.

    public boolean isPowerOfTwo2(int n) {
        return n>0 && (n&n-1)==0;
    }
    //Java Solution 3

    public boolean isPowerOfTwo3(int n) {
        return n>0 && n==Math.pow(2, Math.round(Math.log(n)/Math.log(2)));
        //In this solution, the Math.round() method rounds up the number.


    }

}
