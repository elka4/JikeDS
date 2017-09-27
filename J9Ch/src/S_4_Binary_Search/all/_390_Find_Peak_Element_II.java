package S_4_Binary_Search.all;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 390 Find Peak Element II


 * Created by tianhuizhu on 6/28/17.
 */
public class _390_Find_Peak_Element_II {

    // O(m + n) 算法
    class Solution1 {
        /**
         * @param A: An integer matrix
         * @return: The index of the peak
         */
        public List<Integer> find(int x1, int x2, int y1, int y2,
                                  int[][] A, boolean flag) {

            if (flag) {
                int mid = x1 + (x2 - x1) / 2;
                int index = y1;
                for (int i = y1; i <= y2; ++i)
                    if (A[mid][i] > A[mid][index])
                        index = i;

                if (A[mid - 1][index] > A[mid][index])
                    return find(x1, mid - 1, y1, y2, A, !flag);
                else if (A[mid + 1][index] > A[mid][index])
                    return find(mid + 1, x2, y1, y2, A, !flag);
                else
                    return new ArrayList<Integer>(Arrays.asList(mid, index));
            } else {
                int mid = y1 + (y2 - y1) / 2;
                int index = x1;
                for (int i = x1; i <= x2; ++i)
                    if (A[i][mid] > A[index][mid])
                        index = i;

                if (A[index][mid - 1] > A[index][mid])
                    return find(x1, x2, y1, mid - 1, A, !flag);
                else if (A[index][mid + 1] > A[index][mid])
                    return find(x1, x2, mid + 1, y2, A, !flag);
                else
                    return new ArrayList<Integer>(Arrays.asList(index, mid));
            }
        }
        public List<Integer> findPeakII(int[][] A) {
            // write your code here
            int n = A.length;
            int m = A[0].length;
            return find(1, n - 2, 1, m - 2, A, true);
        }
    }


    class Solution2 {
        /**
         * @param A: An integer matrix
         * @return: The index of the peak
         */
        public List<Integer> findPeakII(int[][] A) {
            // this is the nlog(n) method
            int low = 1, high = A.length-2;
            List<Integer> ans = new ArrayList<Integer>();
            while(low <= high) {
                int mid = (low + high) / 2;
                int col = find(mid, A);
                if(A[mid][col] < A[mid - 1][col]) {
                    high = mid - 1;
                } else if(A[mid][col] < A[mid + 1][col]) {
                    low = mid + 1;
                } else {
                    ans.add(mid);
                    ans.add(col);
                    break;
                }
            }
            return ans;
        }
        int find(int row, int [][]A) {
            int col = 0;
            for(int i = 0; i < A[row].length; i++) {
                if(A[row][i] > A[row][col]) {
                    col = i;
                }
            }
            return col;
        }
    }
}
