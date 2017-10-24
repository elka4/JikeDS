package _05_DFS._Backtracking;
import java.util.*;
import org.junit.Test;
public class _052_BackTracking_N_Queens_II_H {

    class Solution{
        private final Set<Integer> occupiedCols = new HashSet<Integer>();
        private final Set<Integer> occupiedDiag1s = new HashSet<Integer>();
        private final Set<Integer> occupiedDiag2s = new HashSet<Integer>();
        public int totalNQueens(int n) {
            return totalNQueensHelper(0, 0, n);
        }

        private int totalNQueensHelper(int row, int count, int n) {
            for (int col = 0; col < n; col++) {
                if (occupiedCols.contains(col))
                    continue;
                int diag1 = row - col;
                if (occupiedDiag1s.contains(diag1))
                    continue;
                int diag2 = row + col;
                if (occupiedDiag2s.contains(diag2))
                    continue;
                // we can now place a queen here
                if (row == n-1)
                    count++;
                else {
                    occupiedCols.add(col);
                    occupiedDiag1s.add(diag1);
                    occupiedDiag2s.add(diag2);
                    count = totalNQueensHelper(row+1, count, n);
                    // recover
                    occupiedCols.remove(col);
                    occupiedDiag1s.remove(diag1);
                    occupiedDiag2s.remove(diag2);
                }
            }

            return count;
        }
    }

    public class Solution2 {
        int result = 0;
        public int totalNQueens(int n) {
            boolean[] column = new boolean[n];
            boolean[] dia45 = new boolean[2 * n - 1];
            boolean[] dia135 = new boolean[2 * n - 1];
            helper(0, n, column, dia45, dia135);
            return result;
        }
        private void helper(int row, int n, boolean[] column, boolean[] dia45, boolean[] dia135) {
            if (row == n) {
                result++;
                return;
            }
            for (int col = 0; col < n; col++) {
                if (!column[col] && !dia45[col + row] && !dia135[n - 1- row + col]) {
                    column[col] = dia45[col + row] = dia135[n - 1- row + col] = true;
                    helper(row + 1, n, column, dia45, dia135);
                    column[col] = dia45[col + row] = dia135[n - 1- row + col] = false;
                }
            }
        }
    }

    class Solution3{
        int res = 0;
        public int totalNQueens(int n) {
            //int[] horizonal = new int[n];
            boolean[] vertical = new boolean[n];
            boolean[] leftfall = new boolean[2 * n - 1];
            boolean[] rightfall = new boolean[2 * n - 1];
            helper(n, vertical, leftfall, rightfall, 0);
            return res;
        }

        void helper(int n, boolean[] v, boolean[] l, boolean[] r, int row){

            if(row == n){
                res++;
                return;
            }

            for(int col = 0; col < n; col++){
                if(v[col] || l[row - col + n - 1] || r[row + col]) continue;
                v[col] = true;
                l[row - col + n - 1] = true;
                r[row + col] = true;

                helper(n, v, l, r, row + 1);

                v[col] = false;
                l[row - col + n - 1] = false;
                r[row + col] = false;
            }
        }
    }
}
