package _BinarySearch._BinarySearch;
import java.util.*;
import org.junit.Test;
import java.math.BigInteger;

public class _483_BinarySearch_Smallest_Good_Base_H {

//Java solution with hand-writing explain
public String smallestGoodBase(String nn) {
    long n = Long.parseLong(nn);
    long res = 0;
    for(int k = 60; k >= 2; k--){
        long s = 2, e = n;
        while(s < e){
            long m = s + (e - s) / 2;

            BigInteger left = BigInteger.valueOf(m);
            left = left.pow(k).subtract(BigInteger.ONE);
            BigInteger right = BigInteger.valueOf(n).multiply(BigInteger.valueOf(m).subtract(BigInteger.ONE));
            int cmr = left.compareTo(right);
            if(cmr == 0){
                res =  m;
                break;
            } else if(cmr < 0){
                s = m + 1;
            } else {
                e = m;
            }
        }

        if(res != 0) break;
    }

    return "" + res;
}


/*    Java/C# binary search solutions with detailed explanation
    The java solution is submitted by lixx2100 to contest.

    n is equal to x^(k-1) + x^(k-2) + ... + x + 1, where k is from 2 to, say, 66
            for each k from 2 to 66, we get "x", and the minimum of all "x"s is the answer
    To get "x", we use binary search approach with left = 2, and right = Long.MAX_VALUE
    to compare whether n is equal to x^(k-1) + x^(k-2) + ... + x + 1, we don't need to calculate x^(k-1) + x^(k-2) + ... + x + 1 from x.
            if n = x^(k-1) + x^(k-2) + ... + x + 1, then n * (x - 1) = x^k -1.
    in the source code, cb is x^k -1 and wb is n * (x - 1).*/

    public class Solution2 {

        public String smallestGoodBase(String n) {
            BigInteger N = new BigInteger(n);
            long base = Long.MAX_VALUE;

            for (int k = 2; k < 66; k++) {

                long l = 2, r = Long.MAX_VALUE - 5;
                while (l <= r) {
                    long mid = l + (r - l) / 2;

                    BigInteger cb = BigInteger.valueOf(mid).pow(k).subtract(BigInteger.ONE);
                    BigInteger wb = N.multiply(BigInteger.valueOf(mid).subtract(BigInteger.ONE));

                    int cmp = cb.compareTo(wb);
                    if (cmp == 0) {
                        base = Math.min(base, mid);
                        break;
                    } else if (cmp < 0) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
            }

            return "" + base;
        }
    }


    //Java Binary Search solution, 9 ms
    public class Solution3 {
        public String smallestGoodBase(String n) {
            long num = 0;
            for (char c : n.toCharArray()) num = num * 10 + c - '0';

            long x = 1;
            for (int p = 64; p >= 1; p--) {
                if ((x << p) < num) {
                    long k = helper(num, p);
                    if (k != -1) return String.valueOf(k);
                }
            }
            return String.valueOf(num - 1);
        }

        private long helper(long num, int p) {
            long l = 1, r = (long)(Math.pow(num, 1.0/p) + 1);
            while (l < r) {
                long mid = l + (r - l) / 2;
                long sum = 0, cur = 1;
                for (int i = 0; i <= p; i++) {
                    sum += cur;
                    cur *= mid;
                }
                if (sum == num) return mid;
                else if (sum > num) r = mid;
                else l = mid + 1;
            }
            return -1;
        }
    }
}
/*
For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.

Now given a string representing n, you should return the smallest good base of n in string format.

Example 1:
Input: "13"
Output: "3"
Explanation: 13 base 3 is 111.
Example 2:
Input: "4681"
Output: "8"
Explanation: 4681 base 8 is 11111.
Example 3:
Input: "1000000000000000000"
Output: "999999999999999999"
Explanation: 1000000000000000000 base 999999999999999999 is 11.
Note:
The range of n is [3, 10^18].
The string representing n is always valid and will not have leading zeros.

 */