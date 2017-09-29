package HF.HF_extra;

public class _03O1_CheckPowerof2 {
    /*
         * @param n: An integer
         * @return: True or false
         */
    public boolean checkPowerOf2(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n-1)) == 0;
    }

///////////////////////////////////////////////////////////////

}
/*
Using O(1) time to check whether an integer n is a power of 2.

Example
For n=4, return true;

For n=5, return false;


 */