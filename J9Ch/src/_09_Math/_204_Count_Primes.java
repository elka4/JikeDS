package _09_Math;


//  204. Count Primes
//  https://leetcode.com/problems/count-primes/description/
//  Hash Table Math
//  Ugly Number
//  Ugly Number II
//  Perfect Squares
public class _204_Count_Primes {
//-----------------------------------------------------------------------

    //My simple Java solution
    public class Solution1 {
        public int countPrimes(int n) {
            boolean[] notPrime = new boolean[n];
            int count = 0;
            for (int i = 2; i < n; i++) {
                if (notPrime[i] == false) {
                    count++;
                    for (int j = 2; i*j < n; j++) {
                        notPrime[i*j] = true;
                    }
                }
            }

            return count;
        }
    }
//-----------------------------------------------------------------------
    //12 ms Java solution modified from the hint method, beats 99.95%
    public class Solution2 {
        public int countPrimes(int n) {
            if (n < 3)
                return 0;

            boolean[] f = new boolean[n];
            //Arrays.fill(f, true); boolean[] are initialed as false by default
            int count = n / 2;
            for (int i = 3; i * i < n; i += 2) {
                if (f[i])
                    continue;

                for (int j = i * i; j < n; j += 2 * i) {
                    if (!f[j]) {
                        --count;
                        f[j] = true;
                    }
                }
            }
            return count;
        }
    }


//-----------------------------------------------------------------------
}
/*
Count the number of prime numbers less than a non-negative number, n.


 */