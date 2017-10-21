package _06_Math.power;

/*
LeetCode – Power of Three (Java)

Given an integer, write a function to determine if it is a power of three.
 */
public class Power_of_Three {
    //Java Solution 1 - Iteration

    public boolean isPowerOfThree(int n) {
        if(n==1) return true;

        boolean result = false;

        while(n>0){
            int m = n%3;
            if(m==0){
                n=n/3;
                if(n==1)
                    return true;
            }else{
                return false;
            }
        }

        return result;
    }
    //Java Solution 2 - Recursion

    public boolean isPowerOfThree2(int n) {
        if(n==0)
            return false;

        if(n==1)
            return true;

        if(n>1)
            return n%3==0 && isPowerOfThree2(n/3);
        else
            return false;
    }
    //Java Solution 3 - Math

    public boolean isPowerOfThree3(int n) {
        if(n==0) return false;

        return n == Math.pow(3, Math.round(Math.log(n)/Math.log(3)));
    }
}
