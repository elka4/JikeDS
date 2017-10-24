package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _357_BackTracking_Count_Numbers_with_Unique_Digits_M {
//DP
public int countNumbersWithUniqueDigits(int n) {
    if (n == 0)     return 1;

    int res = 10;
    int uniqueDigits = 9;
    int availableNumber = 9;
    while (n-- > 1 && availableNumber > 0) {
        uniqueDigits = uniqueDigits * availableNumber;
        res += uniqueDigits;
        availableNumber--;
    }
    return res;
}


    public class Solution2 {
        public int countNumbersWithUniqueDigits(int n) {
            if (n > 10) {
                return countNumbersWithUniqueDigits(10);
            }
            int count = 1; // x == 0
            long max = (long) Math.pow(10, n);

            boolean[] used = new boolean[10];

            for (int i = 1; i < 10; i++) {
                used[i] = true;
                count += search(i, max, used);
                used[i] = false;
            }

            return count;
        }

        private int search(long prev, long max, boolean[] used) {
            int count = 0;
            if (prev < max) {
                count += 1;
            } else {
                return count;
            }

            for (int i = 0; i < 10; i++) {
                if (!used[i]) {
                    used[i] = true;
                    long cur = 10 * prev + i;
                    count += search(cur, max, used);
                    used[i] = false;
                }
            }

            return count;
        }
    }

    public class Solution3 {
        public int countNumbersWithUniqueDigits(int n) {
            if (n == 0) {
                return 2;
            }
            if (n == 1) {
                return 10; // should be 11
            }
            n = Math.min(n, 10);
            int sum = 10;
            int tmp = 9;
            for (int i = 1; i < n; i++) {
                tmp *= 10 - i;
                sum += tmp;
            }
            return sum;
        }
    }
}
