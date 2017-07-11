package J_2_Binary_Search.Related_8;
import java.util.*;
/**
437
Copy Books
 * Created by tianhuizhu on 6/28/17.
 */
public class _437_Copy_Books {
    // version 1: Binary Search
// this version cost O(n log m) where n is the number of books and m is the sum of the pages.
    public class Solution1 {
        /**
         * @param pages: an array of integers
         * @param k: an integer
         * @return: an integer
         */
        public int copyBooks(int[] pages, int k) {
            if (pages.length == 0) {
                return 0;
            }

            int total = 0;
            int max = pages[0];
            for (int i = 0; i < pages.length; i++) {
                total += pages[i];
                if (max < pages[i]) {
                    max = pages[i];
                }
            }

            int start = max;
            int end = total;

            while (start + 1 < end) {
                int mid = (end - start) / 2 + start;
                if (countCopiers(pages, mid) > k) {
                    start = mid;
                } else {
                    end = mid;
                }
            }

            if (countCopiers(pages, start) <= k) {
                return start;
            }

            return end;
        }

        private int countCopiers(int[] pages, int limit) {
            if (pages.length == 0) {
                return 0;
            }

            int copiers = 1;
            int sum = pages[0]; // limit is always >= pages[0]
            for (int i = 1; i < pages.length; i++) {
                if (sum + pages[i] > limit) {
                    copiers++;
                    sum = 0;
                }
                sum += pages[i];
            }

            return copiers;
        }
    }

    // version 2: Dynamic Programming
    public class Solution2 {
        /**
         * @param pages: an array of integers
         * @param k: an integer
         * @return: an integer
         */
        public int copyBooks(int[] pages, int k) {
            // write your code here
            if(pages == null){
                return 0;
            }
            int n = pages.length;
            if (n == 0){
                return 0;
            }

            if (k > n) {
                k = n;
            }
            int[] sum = new int[n];
            sum[0] = pages[0];
            for (int i = 1; i < n; ++i) {
                sum[i] = sum[i-1] + pages[i];
            }
            int[][] f = new int[n][k];
            for (int i=0; i<n; ++i) f[i][0] = sum[i];
            for (int j=1; j<k; ++j) {
                int p = 0;
                f[0][j] = pages[0];
                for (int i = 1; i < j; ++i) f[i][j] = Math.max(f[i-1][j], pages[i]);
                for (int i = j; i < n; ++i) {
                    while (p < i && f[p][j-1] < sum[i] - sum[p]) ++p;
                    f[i][j] = Math.max(f[p][j - 1], sum[i] - sum[p]);
                    if (p > 0) {
                        --p;
                    }
                    f[i][j] = Math.min(f[i][j], Math.max(f[p][j - 1], sum[i] - sum[p]));
                }
            }
            return f[n - 1][k - 1];
        }
    }

}
