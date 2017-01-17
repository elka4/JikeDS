package ch9J.chapter8_DataStructure;

import java.util.*;

public class _14Ugly_Number_II_On_scan {
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