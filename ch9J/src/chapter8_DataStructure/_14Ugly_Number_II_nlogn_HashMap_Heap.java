package chapter8_DataStructure;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class _14Ugly_Number_II_nlogn_HashMap_Heap {
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
}

/*Ugly number is a number that only have factors 2, 3 and 5.

Design an algorithm to find the nth ugly number. 
The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...

 Notice

Note that 1 is typically treated as an ugly number.

Have you met this question in a real interview? Yes
Example
If n=9, return 10.

Challenge 
O(n log n) or O(n) time.

Tags 
LintCode Copyright Priority Queue
Related Problems 
Medium Super Ugly Number 25 %
Medium Perfect Squares 27 %
Easy Happy Number 29 %
Medium Merge k Sorted Lists 27 %*/