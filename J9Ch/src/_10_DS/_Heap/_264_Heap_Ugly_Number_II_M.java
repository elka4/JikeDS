package _10_DS._Heap;
import java.util.*;
import org.junit.Test;
public class _264_Heap_Ugly_Number_II_M {

/*
O(n) Java solution
The idea of this solution is from this page:http://www.geeksforgeeks.org/ugly-numbers/

The ugly-number sequence is 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the sequence to three groups as below:

(1) 1×2, 2×2, 3×2, 4×2, 5×2, …
(2) 1×3, 2×3, 3×3, 4×3, 5×3, …
(3) 1×5, 2×5, 3×5, 4×5, 5×5, …
We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5.

Then we use similar merge method as merge sort, to get every ugly number from the three subsequence.

Every step we choose the smallest one, and move one step after,including nums with same value.

Thanks for this author about this brilliant idea. Here is my java solution


 */
public class Solution1 {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for(int i=1;i<n;i++){
            int min = Math.min(Math.min(factor2,factor3),factor5);
            ugly[i] = min;
            if(factor2 == min)
                factor2 = 2*ugly[++index2];
            if(factor3 == min)
                factor3 = 3*ugly[++index3];
            if(factor5 == min)
                factor5 = 5*ugly[++index5];
        }
        return ugly[n-1];
    }
}

/*
Java Easy Understand O(n) Solution
The basic idea of this problem is to compute all the ugly numbers in sequence and count to the given number of k ugly numbers. The way I approached this problem is first I have a arraylist to store the ugly numbers in sequence. Then I declared three counter variables: a,b,and c which represent the corresponding index in the arraylist for the multiplier of 2,3,and 5. Since each previous ugly number times one of the multiplier will produce a new ugly number, I start from the starting index 0 and multiply the ugly number at that index with each multiplier and get the smallest product which is the next ugly number from the three. The corresponding multipliers' index will be incremented by one and we do this recursively until we have K ugly numbers. Here is the code implementation in Java:
 */
public class Solution2 {
    public int nthUglyNumber(int n) {
        if(n<=0) return 0;
        int a=0,b=0,c=0;
        List<Integer> table = new ArrayList<Integer>();
        table.add(1);
        while(table.size()<n)
        {
            int next_val = Math.min(table.get(a)*2,Math.min(table.get(b)*3,table.get(c)*5));
            table.add(next_val);
            if(table.get(a)*2==next_val) a++;
            if(table.get(b)*3==next_val) b++;
            if(table.get(c)*5==next_val) c++;
        }
        return table.get(table.size()-1);
    }
}
//-------------------------------------------------------------------------/
// 9Ch
    // version 1: O(n) scan
    class Jiuzhang1 {
        /**
         * @param n an integer
         * @return the nth prime number as description.
         */
        public int nthUglyNumber(int n) {
            List<Integer> uglys = new ArrayList<Integer>();
            uglys.add(1);

            int p2 = 0, p3 = 0, p5 = 0;
            // p2, p3 & p5 share the same queue: uglys

            for (int i = 1; i < n; i++) {
                int lastNumber = uglys.get(i - 1);
                while (uglys.get(p2) * 2 <= lastNumber) p2++;
                while (uglys.get(p3) * 3 <= lastNumber) p3++;
                while (uglys.get(p5) * 5 <= lastNumber) p5++;

                uglys.add(Math.min(
                        Math.min(uglys.get(p2) * 2, uglys.get(p3) * 3),
                        uglys.get(p5) * 5
                ));
            }

            return uglys.get(n - 1);
        }
    };

    // version 2 O(nlogn) HashMap + Heap
    class Jiuzhang2 {
        /**
         * @param n an integer
         * @return the nth prime number as description.
         */
        public int nthUglyNumber(int n) {
            // Write your code here
            Queue<Long> Q = new PriorityQueue<Long>();
            HashSet<Long> inQ = new HashSet<Long>();
            Long[] primes = new Long[3];
            primes[0] = Long.valueOf(2);
            primes[1] = Long.valueOf(3);
            primes[2] = Long.valueOf(5);
            for (int i = 0; i < 3; i++) {
                Q.add(primes[i]);
                inQ.add(primes[i]);
            }
            Long number = Long.valueOf(1);
            for (int i = 1; i < n; i++) {
                number = Q.poll();
                for (int j = 0; j < 3; j++) {
                    if (!inQ.contains(primes[j] * number)) {
                        Q.add(number * primes[j]);
                        inQ.add(number * primes[j]);
                    }
                }
            }
            return number.intValue();
        }
    };
//-------------------------------------------------------------------------/


}
/*
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number, and n does not exceed 1690.
 */

/*
设计一个算法，找出只含素因子2，3，5 的第 n 小的数。

符合条件的数如：1, 2, 3, 4, 5, 6, 8, 9, 10, 12...

 注意事项

我们可以认为1也是一个丑数

您在真实的面试中是否遇到过这个题？ Yes
样例
如果n = 9， 返回 10

挑战
要求时间复杂度为O(nlogn)或者O(n)


 */