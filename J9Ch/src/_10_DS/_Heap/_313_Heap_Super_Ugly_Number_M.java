package _10_DS._Heap;
import java.util.*;
import org.junit.Test;
public class _313_Heap_Super_Ugly_Number_M {
//108ms easy to understand java solution

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ret    = new int[n];
        ret[0] = 1;

        int[] indexes  = new int[primes.length];

        for(int i = 1; i < n; i++){
            ret[i] = Integer.MAX_VALUE;

            for(int j = 0; j < primes.length; j++){
                ret[i] = Math.min(ret[i], primes[j] * ret[indexes[j]]);
            }

            for(int j = 0; j < indexes.length; j++){
                if(ret[i] == primes[j] * ret[indexes[j]]){
                    indexes[j]++;
                }
            }
        }

        return ret[n - 1];
    }


//    The idea is from Ugly Number II.
//            https://leetcode.com/problems/ugly-number-ii/
//    Below code is for ugly number II

    public int nthUglyNumber(int n){
        int i2=0, i3=0, i5=0;
        int[] k = new int[n];
        k[0] = 1;
        for (int i=1; i<n; i++) {
            k[i] = Math.min(k[i2]*2, Math.min(k[i3]*3, k[i5]*5));
            if (k[i]%2 == 0) i2++;
            if (k[i]%3 == 0) i3++;
            if (k[i]%5 == 0) i5++;
        }

        return k[n-1];
    }
//    Similarly, for this problem, just use loop to replace above i2, i3, i5.

    public int nthSuperUglyNumber2(int n, int[] primes) {
        int len = primes.length;
        int[] index = new int[len]; //index[0]==0, ... index[len-1]==0
        int[] res = new int[n];
        res[0] = 1;
        for(int i=1; i<n; i++) {
            int min = Integer.MAX_VALUE;
            for(int j=0; j<len; j++){
                min = Math.min(res[index[j]]*primes[j], min);
            }
            res[i] = min;
            for (int j=0; j<len; j++) {
                if(res[i]%primes[j]==0) index[j]++;
            }

        }

        return res[n-1];
    }


//-----------------------------------------------------------------------------////
    // 9Ch
public class Jiuzhang {
    /**
     * @param n a positive integer
     * @param primes the given prime list
     * @return the nth super ugly number
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] times = new int[primes.length];
        int[] uglys = new int[n];
        uglys[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, primes[j] * uglys[times[j]]);
            }
            uglys[i] = min;

            for (int j = 0; j < times.length; j++) {
                if (uglys[times[j]] * primes[j] == min) {
                    times[j]++;
                }
            }
        }
        return uglys[n - 1];
    }
}

}
/*
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
(4) The nth super ugly number is guaranteed to fit in a 32-bit signed integer.


 */

/*
写一个程序来找第 n 个超级丑数。

超级丑数的定义是正整数并且所有的质数因子都在所给定的一个大小为 k 的质数集合内。

比如给你 4 个质数的集合 [2, 7, 13, 19], 那么 [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] 是前 12 个超级丑数。

 注意事项

1 永远都是超级丑数不管给的质数集合是什么。
给你的质数集合已经按照升序排列。
0 < k ≤ 100, 0 < n ≤ 10^6, 0 < primes[i] < 1000
您在真实的面试中是否遇到过这个题？ Yes
样例
给出 n = 6 和质数集合 [2, 7, 13, 19]。第 6 个超级丑数为 13，所以返回 13 作为结果。
 */