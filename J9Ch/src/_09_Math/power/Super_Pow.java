package _09_Math.power;

/*
LeetCode – Super Pow (Java)

Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

Example1:

a = 2
b = [3]

Result: 8


 */
public class Super_Pow {
    public int superPow(int a, int[] b) {
        int result=1;

        for(int i=0; i<b.length; i++){
            result = helper(result, 10)*helper(a, b[i])%1337; // result^10
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
}
