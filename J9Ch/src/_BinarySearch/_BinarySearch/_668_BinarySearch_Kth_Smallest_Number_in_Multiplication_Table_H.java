package _BinarySearch._BinarySearch;
import java.util.*;
import org.junit.Test;
public class _668_BinarySearch_Kth_Smallest_Number_in_Multiplication_Table_H {

//Approach #1: Brute Force [Memory Limit Exceeded]


//Approach #2: Next Heap [Time Limit Exceeded]



//Approach #3: Binary Search [Accepted]
class Solution3{
    public boolean enough(int x, int m, int n, int k){
        int count = 0;
        for(int i = 1; i <= m; i++) {
            count += Math.min(x / i, n);
        }
        return count >= k;
    }

    public int findKthNumber(int m, int n, int k){
        int lo = 1, hi = m * n;
        while(lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (!enough(mi, m, n, k)) lo  = mi + 1;
            else hi = mi;
        }
        return  lo;
    }
}



//    Java solution, binary search
    class Solution2 {
        public int findKthNumber(int m, int n, int k) {
            int low = 1 , high = m * n + 1;

            while (low < high) {
                int mid = low + (high - low) / 2;
                int c = count(mid, m, n);
                if (c >= k) high = mid;
                else low = mid + 1;
            }

            return high;
        }

        private int count(int v, int m, int n) {
            int count = 0;
            for (int i = 1; i <= m; i++) {
                int temp = Math.min(v / i , n);
                count += temp;
            }
            return count;
        }
    }

//    solution like Kth Smallest Number in Sorted Matrix
    class Solution4 {
        public int findKthNumber(int m, int n, int k) {
            int left = 1 * 1;
            int right = m * n;
            while (left < right) {
                int mid = left + (right - left) / 2;
                int count = count(mid, m, n);
                if (count < k) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return right;
        }

        private int count(int value, int m, int n) {
            int i = m, j = 1;
            int count = 0;
            while (i >= 1 && j <= n) {
                if (i * j <= value) {
                    count += i;
                    j++;
                } else {
                    i--;
                }
            }
            return count;
        }
    }
}
/*
Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?

Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.

Example 1:
Input: m = 3, n = 3, k = 5
Output:
Explanation:
The Multiplication Table:
1	2	3
2	4	6
3	6	9

The 5-th smallest number is 3 (1, 2, 2, 3, 3).
Example 2:
Input: m = 2, n = 3, k = 6
Output:
Explanation:
The Multiplication Table:
1	2	3
2	4	6

The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
Note:
The m and n will be in the range [1, 30000].
The k will be in the range [1, m * n]

 */