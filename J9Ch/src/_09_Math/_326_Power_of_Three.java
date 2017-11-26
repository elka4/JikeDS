package _09_Math;

//  326. Power of Three
//  https://leetcode.com/problems/power-of-three/description/
//
public class _326_Power_of_Three {
//-----------------------------------------------------------------------
    //https://leetcode.com/problems/power-of-three/solution/
//-----------------------------------------------------------------------
    //Approach #1 Loop Iteration [Accepted]
    public class Solution1 {
        public boolean isPowerOfThree(int n) {
            if (n < 1) {
                return false;
            }

            while (n % 3 == 0) {
                n /= 3;
            }

            return n == 1;
        }
    }

//-----------------------------------------------------------------------
    //Approach #2 Base Conversion [Accepted]
    public class Solution2 {
        public boolean isPowerOfThree(int n) {
            return Integer.toString(n, 3).matches("^10*$");
        }
    }

//-----------------------------------------------------------------------
    //Approach #3 Mathematics [Accepted]
    public class Solution3 {
        public boolean isPowerOfThree(int n) {
            return (Math.log10(n) / Math.log10(3)) % 1 == 0;
        }
    }

//-----------------------------------------------------------------------
    //Approach #4 Integer Limitations [Accepted]
    public class Solution4 {
        public boolean isPowerOfThree(int n) {
            return n > 0 && 1162261467 % n == 0;
        }
    }

//-----------------------------------------------------------------------
    //5
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

//-----------------------------------------------------------------------
    //6
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
//-----------------------------------------------------------------------
    //7
    //Java Solution 3 - Math
    public boolean isPowerOfThree3(int n) {
        if(n==0) return false;

        return n == Math.pow(3, Math.round(Math.log(n)/Math.log(3)));
    }

//-----------------------------------------------------------------------
}
/*
LeetCode – Power of Three (Java)

Given an integer, write a function to determine if it is a power of three.
 */